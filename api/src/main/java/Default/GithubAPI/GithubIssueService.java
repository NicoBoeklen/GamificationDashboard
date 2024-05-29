package Default.GithubAPI;

import Default.Issue.Issue;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.sql.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Functionality to request issues via GitHub API
 * Uses API Key to avoid rate-limit
 * With API-Key 5000 Requests per Hour are possible
 */
@Service
public class GithubIssueService {

    private final WebClient webClient;

    /**
     * Defines Header and webClient with API-Key
     */
    public GithubIssueService(WebClient.Builder webClientBuilder) {
        // GitHub API key NicoBoeklen
        String githubApiKey = "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";

        this.webClient = webClientBuilder
            .baseUrl("https://api.github.com")
            .defaultHeader(HttpHeaders.AUTHORIZATION, "Bearer " + githubApiKey)
            .build();
    }

    /**
     * This Method is called to get all Issues from a GitHub Repo
     * And then get the Details by requesting every specific issue again with its URL
     *
     * @param owner Name of the Owner of the GitHub Repository
     * @param repo  Name of the GitHub Repository
     * @return Returns a Flux (Datastream) of Issues
     */
    public Flux<Issue> getIssues(String owner, String repo) {
        String url = String.format("/repos/%s/%s/issues", owner, repo);
        return getIssuesRecursively(url)
            .flatMap(issue -> fetchIssueDetails(issue, owner, repo));
    }


    /**
     * Gets the issues sha and author
     * Gets all issues of the actual page and adds them to the other flux of issues
     * Recursively continues with next page
     *
     * @param url
     * @return Returns a Flux of Issues
     */
    private Flux<Issue> getIssuesRecursively(String url) {
        return webClient.get()
            .uri(url)
            .retrieve()
            .bodyToFlux(Issue.class)
            .collectList()
            .flatMapMany(issues -> getNextPageUrl(url)
                .flatMapMany(nextUrl -> Flux.fromIterable(issues).concatWith(getIssuesRecursively(nextUrl)))
                .switchIfEmpty(Flux.fromIterable(issues)));
    }

    /**
     * Method to Avoid Pagination of GitHub
     *
     * @param url
     * @return
     */
    private Mono<String> getNextPageUrl(String url) {
        return webClient.get()
            .uri(url)
            .exchangeToMono(response -> {
                HttpHeaders headers = response.headers().asHttpHeaders();
                List<String> linkHeaders = headers.get(HttpHeaders.LINK);
                if (linkHeaders == null || linkHeaders.isEmpty()) {
                    return Mono.empty();
                }

                Pattern pattern = Pattern.compile("<(.*?)>;\\s*rel=\"next\"");
                for (String header : linkHeaders) {
                    Matcher matcher = pattern.matcher(header);
                    if (matcher.find()) {
                        return Mono.just(matcher.group(1));
                    }
                }

                return Mono.empty();
            });
    }

    /**
     * Gets the details of the issue: message, date, additions, deletions
     * And sets the boolean isMerge depending on the Message
     * Requests every single issue with own URL
     *
     * @param issue issue to be requested
     * @param owner  Name of the owner of the GitHub Repository
     * @param repo   Name of the GitHub Repository
     * @return Single Issue with als attributes
     */
    private Mono<Issue> fetchIssueDetails(Issue issue, String owner, String repo) {
        
        String url = String.format("/repos/%s/%s/issues/%s", owner, repo, issue.getId());
        return webClient.get()
            .uri(url)
            .retrieve()
            .bodyToMono(IssueDetails.class)
            .map(details -> {
                issue.setDateOpened(details.getIssueInfo().getCreatedAt());
                issue.setDeletions(details.getStats().getDeletions());
                issue.setDate(details.getIssueInfo().getAuthor().getDate());
                issue.setMessage(details.getIssueInfo().getMessage());
                return issue;
            })
            .map(issues -> setIssueMerge(issue));
    }
    

    /**
     * Anonym Class for Requesting Issue Details
     * Represents the structure of the response of the GitHub API
     */
    private static class IssueDetails {

        

        Date createdAt;
        Date updatedAt;
        public Date getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(Date createdAt) {
            this.createdAt = createdAt;
        }

        public Date getUpdatedAt() {
            return updatedAt;
        }

        public void setUpdatedAt(Date updatedAt) {
            this.updatedAt = updatedAt;
        }
    }
}




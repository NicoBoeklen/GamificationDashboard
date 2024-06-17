package Default.GithubAPI;

import Default.Apikey;
import Default.Issue.Issue;
import Default.User.UserService;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Functionality to request issues via GitHub API
 * Uses API Key to avoid rate-limit
 * With API-Key 5000 Requests per Hour are possible
 */
@Service
public class GithubAPIIssueService {

    private final WebClient webClient;

    @Autowired
    private UserService userService;

    /**
     * Defines Header and webClient with API-Key
     */
    public GithubAPIIssueService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder
            .baseUrl("https://api.github.com")
            .defaultHeader(HttpHeaders.AUTHORIZATION, "Bearer " + Apikey.Key.apiKey)
            .build();
    }

    /**
     * This Method is called to get all Issues from a GitHub Repo
     * And then get the Details (closedBy) by requesting every specific issue again with its URL
     *
     * @param owner Name of the Owner of the GitHub Repository
     * @param repo  Name of the GitHub Repository
     * @return Returns a Flux (Data-stream) of Issues
     */
    public Flux<Issue> getIssues(String owner, String repo, Long repoId) {
        String url = String.format("/repos/%s/%s/issues", owner, repo);
        String url2 = String.format("/repos/%s/%s/issues?state=closed", owner, repo);
        return Flux.merge(getIssuesRecursively(url, repoId), getIssuesRecursively(url2, repoId))
            .distinct(Issue::getId) // Remove duplicates by Issue ID
            .flatMap(issue -> fetchIssueDetails(issue, owner, repo, repoId));
    }


    /**
     * Gets the issues number (id), state, closed date, opened date, openedBy
     * Gets all issues of the actual page and adds them to the other flux of issues
     * Recursively continues with next page
     *
     * @param url URL
     * @return Returns a Flux of Issues
     */
    private Flux<Issue> getIssuesRecursively(String url, Long repoId) {
        return webClient.get()
            .uri(url)
            .retrieve()
            .bodyToFlux(Issue.class)
            .map(issue -> {
                // Setze den RepoId-Schlüssel
                issue.getOpenedBy().setRepoId(repoId);
                return issue;
            })
            .collectList()
            .flatMapMany(issues -> getNextPageUrl(url)
                .flatMapMany(nextUrl -> Flux.fromIterable(issues).concatWith(getIssuesRecursively(nextUrl, repoId)))
                .switchIfEmpty(Flux.fromIterable(issues)));
    }

    /**
     * Method to Avoid Pagination of GitHub
     *
     * @param url The current page URL
     * @return A Mono containing the URL of the next page, or empty if no next page exists
     */
    private Mono<String> getNextPageUrl(String url) {
        URI uri = URI.create(url);
        String baseUrl = uri.getPath();
        String query = uri.getQuery();
        int currentPage = 1;

        // Extract the current page number from the query, if present
        if (query != null && query.contains("page=")) {
            Pattern pattern = Pattern.compile("page=(\\d+)");
            Matcher matcher = pattern.matcher(query);
            if (matcher.find()) {
                currentPage = Integer.parseInt(matcher.group(1));
            }
        }

        int nextPage = currentPage + 1;
        String nextUrl;
        if (query != null && !query.isEmpty()) {
            nextUrl = baseUrl + "?" + query.replaceAll("page=\\d+", "") + "&page=" + nextPage;
        } else {
            nextUrl = baseUrl + "?page=" + nextPage;
        }

        System.out.println(nextUrl);

        return webClient.get()
            .uri(nextUrl)
            .exchangeToMono(response -> {
                if (response.statusCode().is2xxSuccessful()) {
                    return response.bodyToMono(List.class).flatMap(body -> {
                        if (body == null || body.isEmpty()) {
                            return Mono.empty();
                        }
                        return Mono.just(nextUrl);
                    });
                }
                return Mono.empty();
            });
    }


    /**
     * Gets the details of the issue: closedBy
     * Requests every single issue with own URL
     *
     * @param issue issue to be requested
     * @param owner Name of the owner of the GitHub Repository
     * @param repo  Name of the GitHub Repository
     * @return Single Issue with als attributes
     */
    private Mono<Issue> fetchIssueDetails(Issue issue, String owner, String repo, Long repoId) {
        String url = String.format("/repos/%s/%s/issues/%s", owner, repo, issue.getNumber());
        return webClient.get()
            .uri(url)
            .retrieve()
            .bodyToMono(IssueDetails.class)
            .map(details -> {
                //Get closedBy User if getDateClosed is not null
                if (issue.getDateClosed() != null) {
                    try {
                        //It can happen that an issue is closed by a user that is not a contributor
                        if (issue.getOpenedBy() != null) {
                            issue.setClosedBy(userService.findById(details.getUser().getId(), issue.getOpenedBy().getRepoId()).orElseThrow(ChangeSetPersister.NotFoundException::new));
                            issue.getClosedBy().setRepoId(repoId);
                        }
                    } catch (ChangeSetPersister.NotFoundException e) {
                        issue.setClosedBy(null);
                    }
                }
                try {
                    //It can happen that an issue is opened by a user that is not a contributor
                    if (issue.getOpenedBy() != null) {
                        issue.setOpenedBy(userService.findById(issue.getOpenedBy().getUserId(), issue.getOpenedBy().getRepoId()).orElseThrow(ChangeSetPersister.NotFoundException::new));
                    }
                } catch (ChangeSetPersister.NotFoundException e) {
                    issue.setOpenedBy(null);
                }
                return issue;
            });
    }


    /**
     * Anonym Class for Requesting Issue Details
     * Represents the structure of the response of the GitHub API
     */
    private static class IssueDetails {
        @JsonProperty("closed_by")
        private CloseDetails closedDetails;

        public CloseDetails getUser() {
            return this.closedDetails;
        }

        private static class CloseDetails {
            @JsonProperty("id")
            private Long id;

            public Long getId() {
                return id;
            }

            public void setId(Long id) {
                this.id = id;
            }
        }
    }
}




package Default.GithubAPI;

import Default.Apikey;
import Default.Issue.IssueService;
import Default.PullRequest.PullRequest;
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
 * 
 * ToDo: Check if mergedBy is good for closedBy (=Who reviewed). Whats with auto merge?
 */
@Service
public class GithubAPIPullRequestService {

    private final WebClient webClient;

    @Autowired
    private IssueService issueService;

    @Autowired
    private UserService userService;

    /**
     * Defines Header and webClient with API-Key
     */
    public GithubAPIPullRequestService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder
            .baseUrl("https://api.github.com")
            .defaultHeader(HttpHeaders.AUTHORIZATION, "Bearer " + Apikey.Key.apiKey)
            .build();
    }

    /**
     * This Method is called to get all PullRequests from a GitHub Repo
     * Requests all pulls
     * And then get the Details () by requesting every specific issue again with its URL
     *
     * @param owner Name of the Owner of the GitHub Repository
     * @param repo  Name of the GitHub Repository
     * @return Returns a Flux (Datastream) of PullRequests
     */
    public Flux<PullRequest> getPullRequests(String owner, String repo) {
        String url = String.format("/repos/%s/%s/pulls?state=closed", owner, repo);
        return getPullsRecursively(url)
            .flatMap(pull -> fetchPullDetails(pull, owner, repo));
    }


    /**
     * Gets the pullRequest
     * Gets all pullRequest of the actual page and adds them to the other flux of pullRequest
     * Recursively continues with next page
     *
     * @param url
     * @return Returns a Flux of pullRequest
     */
    private Flux<PullRequest> getPullsRecursively(String url) {
        return webClient.get()
            .uri(url)
            .retrieve()
            .bodyToFlux(PullRequest.class)
            .collectList()
            .flatMapMany(pullRequest -> getNextPageUrl(url)
                .flatMapMany(nextUrl -> Flux.fromIterable(pullRequest).concatWith(getPullsRecursively(nextUrl)))
                .switchIfEmpty(Flux.fromIterable(pullRequest)));
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
     * Gets the details of the pullRequest: closedBy
     * Requests every single pullRequest with own URL
     *
     * @param pullRequest pullRequest to be requested
     * @param owner       Name of the owner of the GitHub Repository
     * @param repo        Name of the GitHub Repository
     * @return Single pullRequest with als attributes
     */
    private Mono<PullRequest> fetchPullDetails(PullRequest pullRequest, String owner, String repo) {

        String url = String.format("/repos/%s/%s/pulls/%s", owner, repo, pullRequest.getId());
        //Only if Pull Request search for details
        return webClient.get()
            .uri(url)
            .retrieve()
            .bodyToMono(PullRequestDetails.class)
            .map(details -> {
                try {
                    pullRequest.setClosedBy(issueService.findClosedByWithId(pullRequest.getId()));
                } catch (ChangeSetPersister.NotFoundException e) {
                    pullRequest.setClosedBy(null);
                }
                pullRequest.setAdditions(details.additions);
                pullRequest.setDeletions(details.deletions);
                pullRequest.setCommentNumber(details.commentNumber);
                pullRequest.setCommitNumber(details.commitNumber);
                try {
                    //It can happen that an issue is opened by a user that is not a contributor
                    pullRequest.setOpenedBy(userService.findById(pullRequest.getOpenedBy().getId()).orElseThrow(ChangeSetPersister.NotFoundException::new));
                } catch (ChangeSetPersister.NotFoundException e) {
                    pullRequest.setOpenedBy(null);
                }
                return pullRequest;
            });
    }
        

    /**
     * Anonym Class for Requesting Issue Details
     * Represents the structure of the response of the GitHub API
     */
    private static class PullRequestDetails {
        
        @JsonProperty("additions")
        private Integer additions;

        @JsonProperty("deletions")
        private Integer deletions;

        @JsonProperty("commits")
        private Integer commitNumber;

        @JsonProperty("review_comments")
        private Integer commentNumber;

        @JsonProperty("merged_by")
        private MergeDetails mergeDetails;

        public MergeDetails getUser() {
            return this.mergeDetails;
        }

        private static class MergeDetails {
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




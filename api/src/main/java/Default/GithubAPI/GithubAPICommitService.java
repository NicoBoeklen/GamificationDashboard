package Default.GithubAPI;

import Default.Apikey;
import Default.Commit.Commit;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Functionality to request commits via GitHub API
 * Uses API Key to avoid rate-limit
 * With API-Key 5000 Requests per Hour are possible
 */
@Service
public class GithubAPICommitService {

    private final WebClient webClient;

    /**
     * Defines Header and webClient with API-Key
     */
    public GithubAPICommitService(WebClient.Builder webClientBuilder, String apiKey) {
        this.webClient = webClientBuilder
            .baseUrl("https://api.github.com")
            .defaultHeader(HttpHeaders.AUTHORIZATION, "Bearer " + apiKey)
            .build();
    }

    /**
     * This Method is called to get all Commits from a GitHub Repo
     * And then get the Details by requesting every specific commit again with its URL
     *
     * @param owner Name of the Owner of the GitHub Repository
     * @param repo  Name of the GitHub Repository
     * @return Returns a Flux (Datastream) of Commits
     */
    public Flux<Commit> getCommits(String owner, String repo, Long repoId) {
        String url = String.format("/repos/%s/%s/commits", owner, repo);
        return getCommitsRecursively(url)
            .flatMap(commit -> fetchCommitDetails(commit, owner, repo, repoId));
    }


    /**
     * Gets the commits sha and author
     * Gets all commits of the actual page and adds them to the other flux of commits
     * Recursively continues with next page
     *
     * @param url
     * @return Returns a Flux of Commits
     */
    private Flux<Commit> getCommitsRecursively(String url) {
        return webClient.get()
            .uri(url)
            .retrieve()
            .bodyToFlux(Commit.class)
            .collectList()
            .flatMapMany(commits -> getNextPageUrl(url)
                .flatMapMany(nextUrl -> Flux.fromIterable(commits).concatWith(getCommitsRecursively(nextUrl)))
                .switchIfEmpty(Flux.fromIterable(commits)));
    }

    /**
     * Method to Avoid Pagination of GitHub
     *
     * @param url The current page URL
     * @return A Mono containing the URL of the next page, or empty if no next page exists
     */
    private Mono<String> getNextPageUrl(String url) {
        URI uri = URI.create(url);
        String baseUrl = uri.getPath().split("\\?")[0];
        String query = uri.getQuery();
        int currentPage = 1;

        if (query != null && query.contains("page=")) {
            Pattern pattern = Pattern.compile("page=(\\d+)");
            Matcher matcher = pattern.matcher(query);
            if (matcher.find()) {
                currentPage = Integer.parseInt(matcher.group(1));
            }
        }

        int nextPage = currentPage + 1;
        String nextUrl = String.format("%s?page=%d", baseUrl, nextPage);
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
     * Gets the details of the commit: message, date, additions, deletions
     * And sets the boolean isMerge depending on the Message
     * Requests every single commit with own URL
     *
     * @param commit commit to be requested
     * @param owner  Name of the owner of the GitHub Repository
     * @param repo   Name of the GitHub Repository
     * @return Single Commit with als attributes
     */
    private Mono<Commit> fetchCommitDetails(Commit commit, String owner, String repo, Long repoId) {
        String url = String.format("/repos/%s/%s/commits/%s", owner, repo, commit.getId());
        return webClient.get()
            .uri(url)
            .retrieve()
            .bodyToMono(CommitDetails.class)
            .map(details -> {
                commit.setRepoId(repoId);
                commit.setAdditions(details.getStats().getAdditions());
                commit.setDeletions(details.getStats().getDeletions());
                commit.setDate(details.getCommitInfo().getAuthor().getDate());
                //commit.setMessage(details.getCommitInfo().getMessage());
                commit.setMerge(details.getParents().size()>1);
                return commit;
            });
            //.map(commits -> setCommitMerge(commit));
    }

    /**
     * Sets the boolean whether the commit is a merge commit
     * Depends on the message if it contains "Merge"
     *
     * @param commit
     * @return Commit with set merge boolean
     */
    private Commit setCommitMerge(Commit commit) {
        if (commit.getMessage() != null) {
            commit.setMerge(commit.getMessage().contains("Merge"));
        }
        commit.setMessage("");
        return commit;
    }

    /**
     * Anonym Class for Requesting Commit Details
     * Represents the structure of the response of the GitHub API
     */
    private static class CommitDetails {
        @JsonProperty("commit")
        private CommitInfo commitInfo;

        @JsonProperty("stats")
        private Stats stats;
        
        @JsonProperty("parents")
        private List<Parents> parents;
        
        public CommitInfo getCommitInfo() {
            return commitInfo;
        }

        public void setCommitInfo(CommitInfo commitInfo) {
            this.commitInfo = commitInfo;
        }

        public Stats getStats() {
            return stats;
        }

        public void setStats(Stats stats) {
            this.stats = stats;
        }

        public List<Parents> getParents() {
            return parents;
        }

        public void setParents(List<Parents> parents) {
            this.parents = parents;
        }

        private static class CommitInfo {
            @JsonProperty("author")
            private Author author;

            @JsonProperty("message")
            private String message;

            public Author getAuthor() {
                return author;
            }

            public void setAuthor(Author author) {
                this.author = author;
            }

            public String getMessage() {
                return message;
            }

            public void setMessage(String message) {
                this.message = message;
            }

            private static class Author {
                @JsonProperty("date")
                private LocalDateTime date;

                public LocalDateTime getDate() {
                    return date;
                }

                public void setDate(LocalDateTime date) {
                    this.date = date;
                }
            }
        }

        private static class Stats {
            private int total;
            private int additions;
            private int deletions;

            public int getTotal() {
                return total;
            }

            public void setTotal(int total) {
                this.total = total;
            }

            public int getAdditions() {
                return additions;
            }

            public void setAdditions(int additions) {
                this.additions = additions;
            }

            public int getDeletions() {
                return deletions;
            }

            public void setDeletions(int deletions) {
                this.deletions = deletions;
            }
        }
        
        private static class Parents {
            private String sha;
            private String url;
            private String html_url;

            public String getSha() {
                return sha;
            }

            public void setSha(String sha) {
                this.sha = sha;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public String getHtml_url() {
                return html_url;
            }

            public void setHtml_url(String html_url) {
                this.html_url = html_url;
            }
        }
    }
}




package Default.GithubAPI;

import Default.Apikey;
import Default.Commit.Commit;
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
    public GithubAPICommitService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder
            .baseUrl("https://api.github.com")
            .defaultHeader(HttpHeaders.AUTHORIZATION, "Bearer " + Apikey.Key.apiKey)
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
    public Flux<Commit> getCommits(String owner, String repo) {
        String url = String.format("/repos/%s/%s/commits", owner, repo);
        return getCommitsRecursively(url)
            .flatMap(commit -> fetchCommitDetails(commit, owner, repo));
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
     * Gets the details of the commit: message, date, additions, deletions
     * And sets the boolean isMerge depending on the Message
     * Requests every single commit with own URL
     *
     * @param commit commit to be requested
     * @param owner  Name of the owner of the GitHub Repository
     * @param repo   Name of the GitHub Repository
     * @return Single Commit with als attributes
     */
    private Mono<Commit> fetchCommitDetails(Commit commit, String owner, String repo) {
        String url = String.format("/repos/%s/%s/commits/%s", owner, repo, commit.getId());
        return webClient.get()
            .uri(url)
            .retrieve()
            .bodyToMono(CommitDetails.class)
            .map(details -> {
                commit.setAdditions(details.getStats().getAdditions());
                commit.setDeletions(details.getStats().getDeletions());
                commit.setDate(details.getCommitInfo().getAuthor().getDate());
                commit.setMessage(details.getCommitInfo().getMessage());
                return commit;
            })
            .map(commits -> setCommitMerge(commit));
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
                private Date date;

                public Date getDate() {
                    return date;
                }

                public void setDate(Date date) {
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
    }
}




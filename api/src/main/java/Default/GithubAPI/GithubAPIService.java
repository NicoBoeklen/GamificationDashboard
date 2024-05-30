package Default.GithubAPI;

import Default.Apikey;
import Default.Commit.Commit;
import Default.GithubRepo.GithubRepo;
import Default.Release.Release;
import Default.User.User;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Functionality to request contributors, releases and Repository via GitHub API
 * Uses API Key to avoid rate-limit
 * With API-Key 5000 Requests per Hour are possible
 */
@Service
public class GithubAPIService {

    private final WebClient webClient;

    /**
     * Defines Header and webClient with API-Key
     */
    public GithubAPIService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder
            .baseUrl("https://api.github.com")
            .defaultHeader(HttpHeaders.AUTHORIZATION, "Bearer " + Apikey.Key.apiKey)
            .build();
    }

    /**
     * This Method is called to get the Repository from a GitHub Repo
     *
     * @param owner Name of the Owner of the GitHub Repository
     * @param repo  Name of the GitHub Repository
     * @return Returns a Mono (Data Object) of the Repository
     */
    public Mono<GithubRepo> getRepository(String owner, String repo) {
        return this.webClient.get()
            .uri("/repos/{owner}/{repo}", owner, repo)
            .retrieve()
            .bodyToMono(GithubRepo.class);
    }

    /**
     * This Method is called to get all Releases from a GitHub Repo
     *
     * @param owner Name of the Owner of the GitHub Repository
     * @param repo  Name of the GitHub Repository
     * @return Returns a Flux (Datastream) of Releases
     */
    public Flux<Release> getReleases(String owner, String repo) {
        return this.webClient.get()
            .uri("/repos/{owner}/{repo}/releases", owner, repo)
            .retrieve()
            .bodyToFlux(Release.class);
    }

    /**
     * This Method is called to get all Contributors (Users) from a GitHub Repo
     *
     * @param owner Name of the Owner of the GitHub Repository
     * @param repo  Name of the GitHub Repository
     * @return Returns a Flux (Datastream) of Users
     */
    public Flux<User> getContributors(String owner, String repo) {
        String url = String.format("/repos/%s/%s/contributors", owner, repo);
        return getContributorsRecursively(url);
    }

    /**
     * Gets the Contributors
     * Gets all Contributors of the actual page and adds them to the other flux of Contributors
     * Recursively continues with next page
     *
     * @param url
     * @return Returns a Flux of Contributors
     */
    private Flux<User> getContributorsRecursively(String url) {
        return webClient.get()
            .uri(url)
            .retrieve()
            .bodyToFlux(User.class)
            .collectList()
            .flatMapMany(contributors -> getNextPageUrl(url)
                .flatMapMany(nextUrl -> Flux.fromIterable(contributors).concatWith(getContributorsRecursively(nextUrl)))
                .switchIfEmpty(Flux.fromIterable(contributors)));
    }

    /**
     * Method to Avoid Pagination of GitHub
     *
     * @param url The current page URL
     * @return A Mono containing the URL of the next page, or empty if no next page exists
     */
    private Mono<String> getNextPageUrl(String url) {
        // Parse the current URL to get the base and the current page number
        URI uri = URI.create(url);
        String baseUrl = uri.getPath().split("\\?")[0];
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
        String nextUrl = String.format("%s?page=%d", baseUrl, nextPage);

        return webClient.get()
            .uri(nextUrl)
            .exchangeToMono(response -> {
                if (response.statusCode().is2xxSuccessful()) {
                    return response.bodyToMono(String.class).flatMap(body -> {
                        // Check if the body contains data, if not return empty
                        if (body.isEmpty()) {
                            return Mono.empty();
                        }
                        return Mono.just(nextUrl);
                    });
                }
                return Mono.empty();
            });
    }
}


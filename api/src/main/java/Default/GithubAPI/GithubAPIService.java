package Default.GithubAPI;

import Default.GithubRepo.GithubRepo;
import Default.Login.LoginRepository;
import Default.Release.Release;
import Default.User.User;
import Default.User.UserService;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.List;
import java.util.NoSuchElementException;
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

    @Autowired
    UserService userService;
    @Autowired
    private LoginRepository loginRepository;
    WebClient.Builder webClientBuilder;
    /**
     * Defines Header and webClient with API-Key
     */
    public GithubAPIService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder
            .baseUrl("https://api.github.com").build();
    }
    Mono<Long> getUserId(String user, Long repoId, Long sessionId) {
        return Mono.fromCallable(() -> getUserIdByNameAndRepo(user, repoId,sessionId));
    }
    /**
     * This Method is called to get the Repository from a GitHub Repo
     *
     * @param owner Name of the Owner of the GitHub Repository
     * @param repo  Name of the GitHub Repository
     * @return Returns a Mono (Data Object) of the Repository
     */
    public Mono<GithubRepo> getRepository(String owner, String repo, Long sessionId) {
        System.out.println("getRepository"+loginRepository.getApiKeyForLoggedUser(sessionId));
        return this.webClient.get()
            .uri("/repos/{owner}/{repo}", owner, repo)
            .header("Authorization", "Bearer " + loginRepository.getApiKeyForLoggedUser(sessionId))
            .retrieve()
            .bodyToMono(GithubRepo.class);
    }

    public Mono<Long> getRepositoryId(String owner, String repo, Long sessionId) {
        return this.webClient.get()
            .uri("/repos/{owner}/{repo}", owner, repo)
            .header("Authorization", "Bearer " + loginRepository.getApiKeyForLoggedUser(sessionId))
            .retrieve()
            .bodyToMono(JsonNode.class)
            .map(jsonNode -> jsonNode.get("id").asLong());
    }

    /**
     * This Method is called to get all Releases from a GitHub Repo
     *
     * @param owner Name of the Owner of the GitHub Repository
     * @param repo  Name of the GitHub Repository
     * @return Returns a Flux (Datastream) of Releases
     */
    public Flux<Release> getReleases(String owner, String repo, Long repoId,Long sessionId) {
        return this.webClient.get()
            .uri("/repos/{owner}/{repo}/releases", owner, repo)
            .header("Authorization", "Bearer " + loginRepository.getApiKeyForLoggedUser(sessionId))
            .retrieve()
            .bodyToFlux(Release.class);
    }

    /**
     * This Method is called to get all Contributors (Users) from a GitHub Repo
     *
     * @param owner  Name of the Owner of the GitHub Repository
     * @param repo   Name of the GitHub Repository
     * @param repoId
     * @return Returns a Flux (Datastream) of Users
     */
    public Flux<User> getContributors(String owner, String repo, Long repoId,Long sessionId) {
        String url = String.format("/repos/%s/%s/contributors", owner, repo);
        return getContributorsRecursively(url, repoId,sessionId);
    }

    /**
     * Gets the Contributors
     * Gets all Contributors of the actual page and adds them to the other flux of Contributors
     * Recursively continues with next page
     *
     * @param url
     * @return Returns a Flux of Contributors
     */
    private Flux<User> getContributorsRecursively(String url, Long repoId, Long sessionId) {
        return webClient.get()
            .uri(url)
            .header("Authorization", "Bearer " + loginRepository.getApiKeyForLoggedUser(sessionId))
            .retrieve()
            .bodyToFlux(User.class)
            .map(user -> {
                // Setze den RepoId-Schlüssel
                user.setRepoId(repoId);
                return user;
            })
            .collectList()
            .flatMapMany(contributors -> getNextPageUrl(url,sessionId)
                .flatMapMany(nextUrl -> Flux.fromIterable(contributors).concatWith(getContributorsRecursively(nextUrl, repoId,sessionId)))
                .switchIfEmpty(Flux.fromIterable(contributors)));
    }

    /**
     * Method to Avoid Pagination of GitHub
     *
     * @param url The current page URL
     * @return A Mono containing the URL of the next page, or empty if no next page exists
     */
    private Mono<String> getNextPageUrl(String url,Long sessionId) {
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
            .header("Authorization", "Bearer " + loginRepository.getApiKeyForLoggedUser(sessionId))
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

    public Long getUserIdByNameAndRepo(String user, Long repoId, Long sessionId) {
        return userService.findAll().stream().filter(u -> u.getRepoId().equals(repoId)).filter(u -> u.getName().equals(user)).findFirst().orElseThrow(() -> new NoSuchElementException()).getUserId();
    }
    
}


package Default.GithubAPI;

import Default.Apikey;
import Default.GithubRepo.GithubRepo;
import Default.Release.Release;
import Default.User.User;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

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
     * This Method is called to get all Contributors (Users) from a GitHub Repo
     *
     * @param owner Name of the Owner of the GitHub Repository
     * @param repo  Name of the GitHub Repository
     * @return Returns a Flux (Datastream) of Users
     */
    public Flux<User> getContributors(String owner, String repo) {
        return this.webClient.get()
            .uri("/repos/{owner}/{repo}/contributors", owner, repo)
            .retrieve()
            .bodyToFlux(User.class);
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
}


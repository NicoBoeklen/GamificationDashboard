package Default.GithubAPI;

import Default.GithubRepo.GithubRepo;
import Default.User.User;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Functionality to request contributors and Repository via GitHub API
 * Uses API Key to avoid rate-limit
 * With API-Key 5000 Requests per Hour are possible
 */
@Service
public class GithubService {

    private final WebClient webClient;

    /**
     * Defines Header and webClient with API-Key
     */
    public GithubService(WebClient.Builder webClientBuilder) {
        // GitHub API key
        String githubApiKey = "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";

        this.webClient = webClientBuilder
            .baseUrl("https://api.github.com")
            .defaultHeader(HttpHeaders.AUTHORIZATION, "Bearer " + githubApiKey)
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
}


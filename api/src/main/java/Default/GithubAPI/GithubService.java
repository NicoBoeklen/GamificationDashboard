package Default.GithubAPI;

import Default.Commit.Commit;
import Default.GithubRepo.GithubRepo;
import Default.User.User;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class GithubService {
    private final WebClient webClient;

    public GithubService(WebClient.Builder webClientBuilder) {
        // GitHub API key
        String githubApiKey = "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";

        this.webClient = webClientBuilder
            .baseUrl("https://api.github.com")
            .defaultHeader(HttpHeaders.AUTHORIZATION, "Bearer " + githubApiKey)
            .build();
    }

    public Flux<User> getContributors(String owner, String repo) {
        return this.webClient.get()
            .uri("/repos/{owner}/{repo}/contributors", owner, repo)
            .retrieve()
            .bodyToFlux(User.class);
    }

    public Mono<GithubRepo> getRepository(String owner, String repo) {
        return this.webClient.get()
            .uri("/repos/{owner}/{repo}", owner, repo)
            .retrieve()
            .bodyToMono(GithubRepo.class);
    }
}


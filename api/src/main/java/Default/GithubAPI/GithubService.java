package Default.GithubAPI;

import Default.User.User;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class GithubService {
    private final WebClient webClient;

    public GithubService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("https://api.github.com").build();
    }

    public Flux<User> getContributors(String owner, String repo) {
        return this.webClient.get()
            .uri("/repos/{owner}/{repo}/contributors", owner, repo)
            .retrieve()
            .bodyToFlux(User.class);
    }
}


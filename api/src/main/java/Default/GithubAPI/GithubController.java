package Default.GithubAPI;

import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

/**
 * Should provide the URL /updateData at localhost to Request all relevant Data from a GitHub Repository at once
 * ToDo: Change that updateData is called with data from login
 */
@RestController
public class GithubController {

    private final WebClient webClient;

    public GithubController(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("http://localhost:8080").build();
    }

    /**
     * Calls all relevant methods in the controller to get all Data from the Repository
     * Such as Issues, Commits, User Data, ...
     * Its Important that User Data is requested first, then Repository Data
     *
     * @return 200 if successful
     */
    @GetMapping("/updateData/{owner}/{repo}")
    @Transactional
    public Mono<ResponseEntity<String>> getData(@PathVariable String owner, @PathVariable String repo) {
        return getDataFromContributors(owner, repo)
            .then(getDataFromRepository(owner, repo))
            .then(getDataFromCommits(owner, repo))
            .then(Mono.just(ResponseEntity.ok("Data saved successfully")))
            .onErrorResume(e -> Mono.just(ResponseEntity.status(500).body("Error occurred: " + e.getMessage())));
    }

    /**
     * Calls API Request on User Controller
     *
     * @return String if Successfully or not
     */
    private Mono<String> getDataFromContributors(String owner, String repo) {
        return webClient.get()
            .uri("/contributors/{owner}/{repo}", owner, repo)
            .retrieve()
            .bodyToMono(String.class)
            .doOnNext(response -> System.out.println("Contributors Response: " + response))
            .doOnError(error -> System.err.println("Error in getDataFromContributors: " + error.getMessage()));
    }

    /**
     * Calls API Request on Repository Controller
     *
     * @return String if Successfully or not
     */
    private Mono<String> getDataFromRepository(String owner, String repo) {
        return webClient.get()
            .uri("/repository/{owner}/{repo}", owner, repo)
            .retrieve()
            .bodyToMono(String.class)
            .doOnNext(response -> System.out.println("Repository Response: " + response))
            .doOnError(error -> System.err.println("Error in getDataFromRepository: " + error.getMessage()));
    }

    /**
     * Calls API Request on Commit Controller
     *
     * @return String if Successfully or not
     */
    private Mono<String> getDataFromCommits(String owner, String repo) {
        return webClient.get()
            .uri("/commits/{owner}/{repo}", owner, repo)
            .retrieve()
            .bodyToMono(String.class)
            .doOnNext(response -> System.out.println("Commits Response: " + response))
            .doOnError(error -> System.err.println("Error in getDataFromCommits: " + error.getMessage()));
    }
}

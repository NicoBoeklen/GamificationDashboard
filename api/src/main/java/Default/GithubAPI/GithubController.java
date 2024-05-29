package Default.GithubAPI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

/**
 * Should provide the URL /updateData at localhost to Request all relevant Data from a GitHub Repository at once
 * ToDo: Change that updateData is called with owner and repo
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
    public ResponseEntity<String> getData(@PathVariable String owner, @PathVariable String repo) {
        try {
            Mono<String> contributorsResponse = getDataFromContributors(owner, repo);
            Mono<String> repositoryResponse = contributorsResponse.then(getDataFromRepository(owner, repo));
            Mono<String> commitsResponse = repositoryResponse.then(getDataFromCommits(owner, repo));

            commitsResponse.block(); // Wait for the last operation to complete

            return ResponseEntity.ok("Data saved successfully");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error occurred: " + e.getMessage());
        }
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
            .bodyToMono(String.class);
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
            .bodyToMono(String.class);
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
            .bodyToMono(String.class);
    }
}


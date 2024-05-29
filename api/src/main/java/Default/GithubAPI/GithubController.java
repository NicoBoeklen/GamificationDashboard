package Default.GithubAPI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * Should provide the URL /getData at localhost to Request all relevant Data from a GitHub Repository at once
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
    @GetMapping("/getData")
    public ResponseEntity<String> getData() {
        String response1 = getDataFromContributors();
        String response2 = getDataFromCommits();
        String response3 = getDataFromRepository();

        return ResponseEntity.ok("Data saved successfully");
    }

    /**
     * Calls API Request on User Controller
     *
     * @return String if Successfully or not
     */
    private String getDataFromContributors() {
        return webClient.get()
            .uri("/contributors/NicoBoeklen/ProjektSoftwarepraktikum")
            .retrieve()
            .bodyToMono(String.class)
            .block();
    }

    /**
     * Calls API Request on Repository Controller
     *
     * @return String if Successfully or not
     */
    private String getDataFromRepository() {
        return webClient.get()
            .uri("/repository/NicoBoeklen/ProjektSoftwarepraktikum")
            .retrieve()
            .bodyToMono(String.class)
            .block();
    }

    /**
     * Calls API Request on Commit Controller
     *
     * @return String if Successfully or not
     */
    private String getDataFromCommits() {
        return webClient.get()
            .uri("/commits/NicoBoeklen/ProjektSoftwarepraktikum")
            .retrieve()
            .bodyToMono(String.class)
            .block();
    }
}


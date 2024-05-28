package Default.GithubAPI;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RestController
public class GithubController {

    private final WebClient webClient;

    public GithubController(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("http://localhost:8080").build();
    }

    @GetMapping("/getData")
    public ResponseEntity<String> getData() {
        String response1 = getDataFromContributors();
        String response2 = getDataFromCommits();
        String response3 = getDataFromRepository();

        return ResponseEntity.ok("Data saved successfully");
    }

    private String getDataFromContributors() {
        return webClient.get()
            .uri("/contributors/NicoBoeklen/ProjektSoftwarepraktikum")
            .retrieve()
            .bodyToMono(String.class)
            .block();
    }

    private String getDataFromRepository() {
        return webClient.get()
            .uri("/repository/NicoBoeklen/ProjektSoftwarepraktikum")
            .retrieve()
            .bodyToMono(String.class)
            .block();
    }

    private String getDataFromCommits() {
        return webClient.get()
            .uri("/commits/NicoBoeklen/ProjektSoftwarepraktikum")
            .retrieve()
            .bodyToMono(String.class)
            .block();
    }
}


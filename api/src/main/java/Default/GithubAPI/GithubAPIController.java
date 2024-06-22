package Default.GithubAPI;

import Default.Login.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

/**
 * Should provide the URL /updateData at localhost to Request all relevant Data from a GitHub Repository at once
 * ToDo: Change that updateData is called with data from login
 */
@RestController
public class GithubAPIController {

    private final WebClient webClient;

    @Autowired
    GithubAPIService githubAPIService;
    @Autowired
    private LoginRepository loginRepository;

    public GithubAPIController(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("http://localhost:8080").build();
    }
    /**
     * Calls all relevant methods in the controller to get all Data from the Repository
     * Such as Issues, Commits, User Data, Repo Data and PullRequests
     * Its Important that User Data is requested first, then Repository Data
     *
     * @return 200 if successful
     */
    @Transactional
    public Mono<ResponseEntity<String>> getData(String owner, String repo, String user,Long sessionId) {
        System.out.println("getData"+loginRepository.getApiKeyForLoggedUser(sessionId));
        Long repoId = githubAPIService.getRepositoryId(owner, repo,sessionId).block();
        return getDataFromContributors(owner, repo, repoId,sessionId)
            .then(getDataFromRepository(owner, repo, repoId,sessionId))
            .then(getDataFromCommits(owner, repo, repoId,sessionId))
            .then(getDataFromIssues(owner, repo, repoId,sessionId))
            .then(getDataFromPullRequest(owner, repo, repoId,sessionId))
            .then(getDataFromReleases(owner, repo, repoId,sessionId))
            .then(Mono.just(ResponseEntity.ok("Data saved successfully")))
            .onErrorResume(e -> Mono.just(ResponseEntity.status(500).body("Error occurred: " + e.getMessage())));
    }

    ///////////////////////////////////////////////
    // Requesting methods
    ///////////////////////////////////////////////

    /**
     * Calls API Request on User Controller
     *
     * @return String if Successfully or not
     */
    Mono<String> getDataFromContributors(String owner, String repo, Long repoId, Long sessionId) {
        return webClient.get()
            .uri("/contributors/{owner}/{repo}/{repoId}/{sessionId}", owner, repo, repoId,sessionId)
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
    Mono<String> getDataFromRepository(String owner, String repo, Long repoId, Long sessionId) {
        return webClient.get()
            .uri("/repository/{owner}/{repo}/{repoId}/{sessionId}", owner, repo, repoId)
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
    Mono<String> getDataFromCommits(String owner, String repo, Long repoId, Long sessionId) {
        return webClient.get()
            .uri("/commits/{owner}/{repo}/{repoId}/{sessionId}", owner, repo, repoId,sessionId)
            .retrieve()
            .bodyToMono(String.class)
            .doOnNext(response -> System.out.println("Commits Response: " + response))
            .doOnError(error -> System.err.println("Error in getDataFromCommits: " + error.getMessage()));
    }

    /**
     * Calls API Request on Issue Controller
     *
     * @return String if Successfully or not
     */
    Mono<String> getDataFromIssues(String owner, String repo, Long repoId, Long sessionId) {
        return webClient.get()
            .uri("/issues/{owner}/{repo}/{repoId}/{sessionId}", owner, repo, repoId,sessionId)
            .retrieve()
            .bodyToMono(String.class)
            .doOnNext(response -> System.out.println("Issues Response: " + response))
            .doOnError(error -> System.err.println("Error in getDataFromIssues: " + error.getMessage()));
    }

    /**
     * Calls API Request on PullRequest Controller
     * Issues have to be in the database first (PullRequests are Issues with extra Data)
     *
     * @return String if Successfully or not
     */
    Mono<String> getDataFromPullRequest(String owner, String repo, Long repoId,Long sessionId) {
        return webClient.get()
            .uri("/pullRequests/{owner}/{repo}/{repoId}/{sessionId}", owner, repo, repoId,sessionId)
            .retrieve()
            .bodyToMono(String.class)
            .doOnNext(response -> System.out.println("PullRequest Response: " + response))
            .doOnError(error -> System.err.println("Error in getDataFromPullRequests: " + error.getMessage()));
    }

    /**
     * Calls API Request on Release Controller
     *
     * @return String if Successfully or not
     */
    Mono<String> getDataFromReleases(String owner, String repo, Long repoId, Long sessionId) {
        return webClient.get()
            .uri("/release/{owner}/{repo}/{repoId}/{sessionId}", owner, repo, repoId,sessionId)
            .retrieve()
            .bodyToMono(String.class)
            .doOnNext(response -> System.out.println("Release Response: " + response))
            .doOnError(error -> System.err.println("Error in getDataFromReleases: " + error.getMessage()));
    }
}

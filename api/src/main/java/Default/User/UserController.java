package Default.User;

import Default.GithubAPI.GithubAPIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.NoSuchElementException;

/**
 * Controller to provide get URL for User (localhost)
 * Uses the GithubAPIService
 */
@RestController
public class UserController {

    @Autowired
    private GithubAPIService githubAPIService;

    @Autowired
    private UserService userService;
    @Qualifier("webClientBuilder")

    /**
     * Saves the Contributors (Users) in the repository. Calls the Methods in GithubAPIService
     *
     * @param owner Owner of the GitHub repository
     * @param repo  GitHub Repository name
     * @return "Contributors saved successfully" with 200 or 500 Error if exception is thrown
     */
    @GetMapping("/contributors/{owner}/{repo}/{repoId}/{sessionId}")
    public Mono<ResponseEntity<String>> getContributors(@PathVariable String owner, @PathVariable String repo, @PathVariable Long repoId,@PathVariable Long sessionId) {
        return githubAPIService.getContributors(owner, repo, repoId,sessionId)
            .flatMap(userService::saveUser)  // Save each user
            .then(Mono.just(ResponseEntity.ok("Contributors saved successfully")))
            .onErrorResume(e -> Mono.just(ResponseEntity.status(500).body("An error occurred: " + e.getMessage())));
    }
    
    @GetMapping("/avatar/user/{userId}/{repoId}")
    public User getUser(@PathVariable Long userId, @PathVariable Long repoId) {
        try {
            return userService.findById(userId, repoId).orElseThrow(NoSuchElementException::new);
        } catch (NoSuchElementException e) {
            return null;
        }
    }
}

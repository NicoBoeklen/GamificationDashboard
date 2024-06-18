package Default.User;

import Default.GithubAPI.GithubAPIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

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

    /**
     * Saves the Contributors (Users) in the repository. Calls the Methods in GithubAPIService
     *
     * @param owner Owner of the GitHub repository
     * @param repo  GitHub Repository name
     * @return "Contributors saved successfully" with 200 or 500 Error if exception is thrown
     */
    @GetMapping("/contributors/{owner}/{repo}/{repoId}")
    public Mono<ResponseEntity<String>> getContributors(@PathVariable String owner, @PathVariable String repo, @PathVariable Long repoId) {
        return githubAPIService.getContributors(owner, repo, repoId)
            .flatMap(userService::saveUser)  // Save each user
            .then(Mono.just(ResponseEntity.ok("Contributors saved successfully")))
            .onErrorResume(e -> Mono.just(ResponseEntity.status(500).body("An error occurred: " + e.getMessage())));
    }
    
    @GetMapping("/avatar/user/{userId}/{repoId}")
    public String getAvatar(@PathVariable Long userId, @PathVariable Long repoId) {
        try {
            return userService.getUserAvatar(userId, repoId);
        } catch (Error e) {
            return "";
        }
    }
}

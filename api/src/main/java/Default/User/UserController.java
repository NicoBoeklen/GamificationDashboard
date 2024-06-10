package Default.User;

import Default.GithubAPI.GithubAPIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import reactor.core.publisher.Mono;

/**
 * Controller to provide get URL for User (localhost)
 * Uses the GithubAPIService
 */
@Controller
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
    @GetMapping("/contributors/{owner}/{repo}")
    public Mono<ResponseEntity<String>> getContributors(@PathVariable String owner, @PathVariable String repo) {
        return githubAPIService.getContributors(owner, repo)
            .flatMap(userService::saveUser)  // Save each user
            .then(Mono.just(ResponseEntity.ok("Contributors saved successfully")))
            .onErrorResume(e -> Mono.just(ResponseEntity.status(500).body("An error occurred: " + e.getMessage())));
    }
    @GetMapping("/avatar/user/{username}")
    public String getAvatar(@PathVariable String username) {
        return userService.getUserAvatar(username);
    }
}

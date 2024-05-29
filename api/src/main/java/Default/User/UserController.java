package Default.User;

import Default.GithubAPI.GithubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import reactor.core.publisher.Flux;

/**
 * Controller to provide get URL for User (localhost)
 * Uses the GithubService
 */
@Controller
public class UserController {

    @Autowired
    private GithubService githubService;

    @Autowired
    private UserService userService;

    /**
     * Saves the Contributors (Users) in the repository. Calls the Methods in GithubService
     *
     * @param owner Owner of the GitHub repository
     * @param repo  GitHub Repository name
     * @return "Contributors saved successfully" with 200 or 500 Error if exception is thrown
     */
    @GetMapping("/contributors/{owner}/{repo}")
    public ResponseEntity<?> getContributors(@PathVariable String owner, @PathVariable String repo) {
        try {
            Flux<User> contributorsFlux = githubService.getContributors(owner, repo);

            //Save die Users in JpaRepository
            contributorsFlux.subscribe(userService::saveUser);

            return ResponseEntity.ok("Contributors saved successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred");
        }
    }
}

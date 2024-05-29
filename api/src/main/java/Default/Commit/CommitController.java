package Default.Commit;

import Default.GithubAPI.GithubAPICommitService;
import Default.User.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import reactor.core.publisher.Flux;

/**
 * Controller to provide get URL for Commits (localhost)
 * Uses the GithubAPICommitService
 */
@Controller
public class CommitController {

    @Autowired
    private GithubAPICommitService githubAPICommitService;

    @Autowired
    private CommitService commitService;

    @Autowired
    private UserService userService;

    /**
     * Important: First Users and Then Repository must be in the database or method will fail
     * Saves the commits in the repository. Calls the Methods in GithubAPICommitService
     *
     * @param owner Owner of the GitHub repository
     * @param repo  GitHub Repository name
     * @return "Commits saved successfully" with 200 or 500 Error if exception is thrown
     */
    @GetMapping("/commits/{owner}/{repo}")
    public ResponseEntity<?> getCommits(@PathVariable String owner, @PathVariable String repo) {
        try {
            //Call Request-Method in githubCommitService
            Flux<Commit> commitsFlux = githubAPICommitService.getCommits(owner, repo);

            //Save Commits in JpaRepository
            commitsFlux.subscribe(commitService::saveCommit);

            return ResponseEntity.ok("Commits saved successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred");
        }
    }

    /**
     * 
     * @param userId
     * @return
     */
    @GetMapping("/commitCount/{userId}")
    public String getCommitCount(@PathVariable Long userId) {
        return commitService.getCommitCount(userService.findById(userId).orElseThrow(NullPointerException::new)).toString();
    }
}

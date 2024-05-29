package Default.PullRequest;

import Default.GithubAPI.GithubAPIPullRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import reactor.core.publisher.Flux;

/**
 * Controller to provide get URL for PullRequest (localhost)
 * Uses the GithubAPIPullRequestService
 */
@Controller
public class PullRequestController {

    @Autowired
    private GithubAPIPullRequestService githubAPIPullRequestService;

    @Autowired
    private PullRequestService pullRequestService;

    /**
     * Important: First Users and Then Issues must be in the database or method will fail
     * Saves the pullRequests in the repository. Calls the Methods in GithubAPIPullRequestService
     * PullRequests are also an Issue
     * @param owner Owner of the GitHub repository
     * @param repo  GitHub Repository name
     * @return "Pull Requests saved successfully" with 200 or 500 Error if exception is thrown
     */
    @GetMapping("/pullRequests/{owner}/{repo}")
    public ResponseEntity<?> getPullRequest(@PathVariable String owner, @PathVariable String repo) {
        try {
            //Call Request-Method in githubPullRequestService
            Flux<PullRequest> pullRequestFlux = githubAPIPullRequestService.getPullRequests(owner, repo);

            //Save PullRequest in JpaRepository and deletes the issue
            pullRequestFlux.subscribe(pullRequestService::savePullRequest);

            return ResponseEntity.ok("PullRequests saved successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred");
        }
    }
}

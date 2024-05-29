package Default.Issue;

import Default.GithubAPI.GithubAPIIssueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import reactor.core.publisher.Flux;

/**
 * Controller to provide get URL for Issues (localhost)
 * Uses the GithubAPIIssueService
 */
@Controller
public class IssueController {

    @Autowired
    private GithubAPIIssueService githubAPIIssueService;

    @Autowired
    private IssueService issueService;

    /**
     * Important: First Users and Then Repository must be in the database or method will fail
     * Saves the issues in the repository. Calls the Methods in GithubAPIIssueService
     *
     * @param owner Owner of the GitHub repository
     * @param repo  GitHub Repository name
     * @return "Issues saved successfully" with 200 or 500 Error if exception is thrown
     */
    @GetMapping("/issues/{owner}/{repo}")
    public ResponseEntity<?> getIssues(@PathVariable String owner, @PathVariable String repo) {
        try {
            //Call Request-Method in githubIssueService
            Flux<Issue> issuesFlux = githubAPIIssueService.getIssues(owner, repo);

            //Save Issues in JpaRepository
            issuesFlux.subscribe(issueService::saveIssue);

            return ResponseEntity.ok("Issues saved successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred");
        }
    }
}

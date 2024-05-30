package Default.Issue;

import Default.GithubAPI.GithubAPIIssueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

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
     * Important: First Issues then PullRequests
     * Saves the issues in the repository. Calls the Methods in GithubAPIIssueService
     *
     * @param owner Owner of the GitHub repository
     * @param repo  GitHub Repository name
     * @return "Issues saved successfully" with 200 or 500 Error if exception is thrown
     */
    @GetMapping("/issues/{owner}/{repo}")
    public Mono<ResponseEntity<String>> getIssues(@PathVariable String owner, @PathVariable String repo) {
        return githubAPIIssueService.getIssues(owner, repo)
            .flatMap(issueService::saveIssue)  // Save each issue
            .then(Mono.just(ResponseEntity.ok("Issues saved successfully")))
            .onErrorResume(e -> Mono.just(ResponseEntity.status(500).body("An error occurred: " + e.getMessage())));
    }
}

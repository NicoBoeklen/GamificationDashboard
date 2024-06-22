package Default.PullRequest;

import Default.GithubAPI.GithubAPIPullRequestService;
import Default.PullRequest.Stats.PullRequestMetric;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * Controller to provide get URL for PullRequest (localhost)
 * Uses the GithubAPIPullRequestService
 */
@RestController
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
    @GetMapping("/pullRequests/{owner}/{repo}/{repoId}/{sessionId}")
    public Mono<ResponseEntity<String>> getPullRequest(@PathVariable String owner, @PathVariable String repo, @PathVariable Long repoId,@PathVariable Long sessionId) {
        return githubAPIPullRequestService.getPullRequests(owner, repo, repoId,sessionId)
            .flatMap(pullRequestService::savePullRequest)  // Save each Pull Request
            .then(Mono.just(ResponseEntity.ok("PullRequests saved successfully")))
            .onErrorResume(e -> Mono.just(ResponseEntity.status(500).body("An error occurred: " + e.getMessage())));
    }

    @GetMapping("/pullMetrics/{userId}/{repoId}")
    public PullRequestMetric getPullRequestMetrics(@PathVariable Long userId, @PathVariable Long repoId) {
        return new PullRequestMetric(pullRequestService.getNumberReviews(userId, repoId),
            pullRequestService.getNumberReviewsTotal(repoId),
            pullRequestService.getAverageCommentsOfLastFivePullRequestsByUser(userId, repoId),
            pullRequestService.getOpenPullRequests(repoId),
            pullRequestService.getClosedPullRequestsLastMonth(repoId), 
            pullRequestService.getAverageAdditionsOfLastFivePullRequests(repoId),
            pullRequestService.getAverageDeletionsOfLastFivePullRequests(repoId),
            pullRequestService.getAverageCommitsOfLastFivePullRequests(repoId), 
            pullRequestService.getAverageProcessTimeOfLastFivePullRequests(repoId));
    }
}

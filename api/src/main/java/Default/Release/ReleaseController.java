package Default.Release;

import Default.GithubAPI.GithubAPIService;
import Default.PullRequest.Stats.PullRequestMetric;
import Default.Release.Stats.ReleaseMetrics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

/**
 * Controller to provide get URL for Release (localhost)
 * Uses the GithubAPIService
 */
@RestController
public class ReleaseController {
    @Autowired
    private GithubAPIService githubAPIService;

    @Autowired
    private ReleaseService releaseService;

    /**
     * Saves the Releases in the JPA-Repository. Calls the Methods in GithubAPIService
     *
     * @param owner Owner of the GitHub repository
     * @param repo  GitHub Repository name
     * @return "Releases saved successfully" with 200 or 500 Error if exception is thrown
     */
    @GetMapping("/release/{owner}/{repo}")
    public ResponseEntity<?> getRelease(@PathVariable String owner, @PathVariable String repo) {
        try {
            Flux<Release> releaseFlux = githubAPIService.getReleases(owner, repo);

            //Save the Releases in JpaRepository
            releaseFlux.subscribe(releaseService::saveRelease);

            return ResponseEntity.ok("Release saved successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred");
        }
    }

    @GetMapping("/releaseMetrics")
    public ReleaseMetrics getReleaseMetrics(@PathVariable Long userId) {
        return new ReleaseMetrics(releaseService.getNumberOfReleases(),
            releaseService.getAverageTimeBetweenReleases(), releaseService.getAllRelease());
    }
}

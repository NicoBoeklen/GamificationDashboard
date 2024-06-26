package Default.Release;

import Default.GithubAPI.GithubAPIService;
import Default.GithubRepo.GithubRepoService;
import Default.Release.Stats.ReleaseMetrics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

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
    
    @Autowired
    GithubRepoService githubRepoService;

    /**
     * Saves the Releases in the JPA-Repository. Calls the Methods in GithubAPIService
     *
     * @param owner Owner of the GitHub repository
     * @param repo  GitHub Repository name
     * @return "Releases saved successfully" with 200 or 500 Error if exception is thrown
     */
    @GetMapping("/release/{owner}/{repo}/{repoId}/{sessionId}")
    public ResponseEntity<?> getRelease(@PathVariable String owner, @PathVariable String repo, @PathVariable Long repoId,@PathVariable Long sessionId) {
        try {
            Flux<Release> releaseFlux = githubAPIService.getReleases(owner, repo, repoId,sessionId)
                .flatMap(release -> githubRepoService.findById(repoId)
                    .flatMap(repository -> {
                        if (repository != null) {
                            release.setRepo(repository);
                        }
                        return Mono.just(release);
                    })
                    .defaultIfEmpty(release) // Falls kein Repository gefunden wird, release zurückgeben
                );

            //Save the Releases in JpaRepository
            releaseFlux.subscribe(releaseService::saveRelease);

            return ResponseEntity.ok("Release saved successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred");
        }
    }

    @GetMapping("/releaseMetrics/{repoId}")
    public ReleaseMetrics getReleaseMetrics(@PathVariable Long repoId) {
        return new ReleaseMetrics(releaseService.getNumberOfReleases(repoId),
            releaseService.getAverageTimeBetweenReleases(repoId), releaseService.getAllRelease().stream().filter(r -> r.getRepo().getId().equals(repoId)).toList());
    }
}

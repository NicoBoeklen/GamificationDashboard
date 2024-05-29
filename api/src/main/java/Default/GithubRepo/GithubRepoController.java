package Default.GithubRepo;

import Default.GithubAPI.GithubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import reactor.core.publisher.Mono;

/**
 * Controller to provide get URL for Repository (localhost)
 * Uses the GithubService
 */
@Controller
public class GithubRepoController {

    @Autowired
    private GithubService githubService;

    @Autowired
    private GithubRepoService repoService;

    /**
     * Important: First Users must be in the database or method will fail
     * Saves the Repository in the JPA-Repository. Calls the Methods in GithubService
     *
     * @param owner Owner of the GitHub repository
     * @param repo  GitHub Repository name
     * @return "Repository saved successfully" with 200 or 500 Error if exception is thrown
     */
    @GetMapping("/repository/{owner}/{repo}")
    public ResponseEntity<?> getRepository(@PathVariable String owner, @PathVariable String repo) {
        try {
            Mono<GithubRepo> repoMono = githubService.getRepository(owner, repo);

            //Save the Contributors in JpaRepository
            repoMono.subscribe(repoService::saveRepo);

            return ResponseEntity.ok("Repository saved successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred");
        }
    }
}

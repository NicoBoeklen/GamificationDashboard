package Default.GithubRepo;

import Default.GithubAPI.GithubAPIService;
import Default.User.User;
import Default.User.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;

/**
 * Controller to provide get URL for Repository (localhost)
 * Uses the GithubAPIService
 */
@RestController
public class GithubRepoController {

    @Autowired
    private GithubAPIService githubAPIService;

    @Autowired
    private GithubRepoService repoService;

    @Autowired
    private UserService userService;
    @Autowired
    private GithubRepoRepository githubRepoRepository;

    /**
     * Important: First Users must be in the database or method will fail
     * Saves the Repository in the JPA-Repository. Calls the Methods in GithubAPIService
     *
     * @param owner Owner of the GitHub repository
     * @param repo  GitHub Repository name
     * @return "Repository saved successfully" with 200 or 500 Error if exception is thrown
     */
    @GetMapping("/repository/{owner}/{repo}/{repoId}")
    public ResponseEntity<?> getRepository(@PathVariable String owner, @PathVariable String repo, @PathVariable Long repoId) {
        try {
            //If owner is not a contributor (no User exists in Database)
            Mono<GithubRepo> repoMono = githubAPIService.getRepository(owner, repo)
                .flatMap(repos -> {
                    repos.getOwner().setRepoId(repoId);
                    Optional<User> userOptional = userService.findById(repos.getOwner().getUserId(), repos.getOwner().getRepoId());
                    if (userOptional.isEmpty()) {
                        repos.setOwner(null);
                    }
                    return Mono.just(repos);
                });

            // Save the Repository in JpaRepository
            repoMono.subscribe(repoService::saveRepo);

            return ResponseEntity.ok("Repository saved successfully");
        } catch (
            Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred");
        }
    }
    @GetMapping("/api/repositoryData/{repoId}")
    public GithubRepo getRepo(@PathVariable Long repoId) {
        return githubRepoRepository.findByRepoIdUserId(repoId);
    }
    
    @GetMapping("/api/repositoryData/all")
    public List<GithubRepo> getRepos() {
        return githubRepoRepository.findAll();
    }
}

package Default.GithubRepo;

import Default.GithubAPI.GithubService;
import Default.User.User;
import Default.User.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import reactor.core.publisher.Mono;

@Controller
public class GithubRepoController {
    @Autowired
    private GithubService githubService;

    @Autowired
    private GithubRepoService repoService;

    @GetMapping("/repository/{owner}/{repo}")
    public ResponseEntity<?> getRepository(@PathVariable String owner, @PathVariable String repo) {
        try {
            // Rufe die Contributors des angegebenen Repositorys ab
            Mono<GithubRepo> repoMono = githubService.getRepository(owner, repo);

            // Speichere die Contributors in deinem JpaRepository
            repoMono.subscribe(repoService::saveRepo);
            
            return ResponseEntity.ok("Repository saved successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred");
        }
    }
}

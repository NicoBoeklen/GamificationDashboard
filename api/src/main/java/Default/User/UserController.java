package Default.User;

import Default.GithubAPI.GithubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import reactor.core.publisher.Flux;

@Controller
public class UserController {
    @Autowired
    private GithubService githubService;

    @Autowired
    private UserRepository userRepository;
    
    @GetMapping("/contributors/{owner}/{repo}")
    public ResponseEntity<?> getContributors(@PathVariable String owner, @PathVariable String repo) {
        try {
            // Rufe die Contributors des angegebenen Repositorys ab
            Flux<User> contributorsFlux = githubService.getContributors(owner, repo);

            // Speichere die Contributors in deinem JpaRepository
            contributorsFlux.subscribe(userRepository::save);

            return ResponseEntity.ok("Contributors saved successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred");
        }
    }
}

package Default.Commit;

import Default.GithubAPI.GithubCommitService;
import Default.GithubAPI.GithubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import reactor.core.publisher.Flux;

@Controller
public class CommitController {
    @Autowired
    private GithubCommitService githubService;

    @Autowired
    private CommitService commitService;

    /**
     * Important: First Users and Then Repository must be in the database or method will fail
     * 
     * @param owner
     * @param repo
     * @return
     */
    @GetMapping("/commits/{owner}/{repo}")
    public ResponseEntity<?> getCommits(@PathVariable String owner, @PathVariable String repo) {
        try {
            // Rufe die Contributors des angegebenen Repositorys ab
            Flux<Commit> commitsFlux = githubService.getCommits(owner, repo);

            // Speichere die Contributors in deinem JpaRepository
            commitsFlux.subscribe(commitService::saveCommit);

            return ResponseEntity.ok("Commits saved successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred");
        }
    }
}

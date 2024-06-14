package Default.Commit;

import Default.Commit.Stats.CodeGrowth;
import Default.Commit.Stats.CommitMetric;
import Default.GithubAPI.GithubAPICommitService;
import Default.User.User;
import Default.User.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;

/**
 * Controller to provide get URL for Commits (localhost)
 * Uses the GithubAPICommitService
 */
@RestController
public class CommitController {

    @Autowired
    private GithubAPICommitService githubAPICommitService;

    @Autowired
    private CommitService commitService;

    @Autowired
    private UserService userService;

    /**
     * Important: First Users and Then Repository must be in the database or method will fail
     * Saves the commits in the repository. Calls the Methods in GithubAPICommitService
     *
     * @param owner Owner of the GitHub repository
     * @param repo  GitHub Repository name
     * @return "Commits saved successfully" with 200 or 500 Error if exception is thrown
     */
    @GetMapping("/commits/{owner}/{repo}")
    public Mono<ResponseEntity<String>> getCommits(@PathVariable String owner, @PathVariable String repo) {
        return githubAPICommitService.getCommits(owner, repo)
            .flatMap(commit -> {
                //If author is not a contributor (no User exists in Database)
                if (commit.getAuthor() != null) {
                    Optional<User> userOptional = userService.findById(commit.getAuthor().getId());
                    if (userOptional.isEmpty()) {
                        commit.setAuthor(null);
                    }
                }
                return commitService.saveCommit(commit);
            })
            .then(Mono.just(ResponseEntity.ok("Commits saved successfully")))
            .onErrorResume(e -> Mono.just(ResponseEntity.status(500).body("An error occurred: " + e.getMessage())));
    }

    /**
     * @param userId
     * @return
     */
    @GetMapping("/commitCount/{userId}")
    public Integer getCommitCount(@PathVariable Long userId) {
        return commitService.getCommitCount(userId);
    }

    /**
     * @param userId
     * @return
     */
    @GetMapping("/deletionCount/{userId}")
    public Integer getDeletionCount(@PathVariable Long userId) {
        return commitService.getDeletionCount(userId);
    }

    /**
     * @param userId
     * @return
     */
    @GetMapping("/additionCount/{userId}")
    public Integer getAdditionCount(@PathVariable Long userId) {
        return commitService.getAdditionCount(userId);
    }

    /**
     * @return
     */
    @GetMapping("/codeGrowth")
    public List<CodeGrowth> getCodeGrowth() {
        return commitService.getCodeGrowth();
    }

    @GetMapping("/averageAdditions/{userId}")
    public Double getAverageAdditions(@PathVariable Long userId) {
        return commitService.getAverageAdditionsOfLastFiveCommitsByUser(userId);
    }

    @GetMapping("/averageDeletions/{userId}")
    public Double getAverageDeletions(@PathVariable Long userId) {
        return commitService.getAverageDeletionsOfLastFiveCommitsByUser(userId);
    }

    @GetMapping("/commitMetrics/{userId}")
    public CommitMetric getCommitMetrics(@PathVariable Long userId) {
        return new CommitMetric(commitService.getCodeGrowth(),
            commitService.getCommitCount(userId), commitService.getDeletionCount(userId),
            commitService.getAdditionCount(userId), commitService.getAverageAdditionsOfLastFiveCommitsByUser(userId),
            commitService.getAverageDeletionsOfLastFiveCommitsByUser(userId), commitService.getCommitsUser(userId),
            commitService.getAverageUserProductivity(userId), userService.findAll().size(), commitService.getTotalLoC(),
            commitService.getTotalCommits());
    }
}

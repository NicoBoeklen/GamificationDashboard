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
    @GetMapping("/commits/{owner}/{repo}/{repoId}")
    public Mono<ResponseEntity<String>> getCommits(@PathVariable String owner, @PathVariable String repo, @PathVariable Long repoId) {
        return githubAPICommitService.getCommits(owner, repo)
            .flatMap(commit -> {
                //If author is not a contributor (no User exists in Database)
                if (commit.getAuthor() != null) {
                    commit.getAuthor().setRepoId(repoId);
                    Optional<User> userOptional = userService.findById(commit.getAuthor().getUserId(), commit.getAuthor().getRepoId());
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
    @GetMapping("/commitCount/{userId}/{repoId}")
    public Integer getCommitCount(@PathVariable Long userId, @PathVariable Long repoId) {
        return commitService.getCommitCount(userId, repoId);
    }

    /**
     * @param userId
     * @return
     */
    @GetMapping("/deletionCount/{userId}/{repoId}")
    public Integer getDeletionCount(@PathVariable Long userId, @PathVariable Long repoId) {
        return commitService.getDeletionCount(userId, repoId);
    }

    /**
     * @param userId
     * @return
     */
    @GetMapping("/additionCount/{userId}/{repoId}")
    public Integer getAdditionCount(@PathVariable Long userId, @PathVariable Long repoId) {
        return commitService.getAdditionCount(userId, repoId);
    }

    /**
     * @return
     */
    @GetMapping("/codeGrowth/{repoId}")
    public List<CodeGrowth> getCodeGrowth(@PathVariable Long repoId) {
        return commitService.getCodeGrowth(repoId);
    }

    @GetMapping("/averageAdditions/{userId}/{repoId}")
    public Double getAverageAdditions(@PathVariable Long userId, @PathVariable Long repoId) {
        return commitService.getAverageAdditionsOfLastFiveCommitsByUser(userId, repoId);
    }

    @GetMapping("/averageDeletions/{userId}/{repoId}")
    public Double getAverageDeletions(@PathVariable Long userId, @PathVariable Long repoId) {
        return commitService.getAverageDeletionsOfLastFiveCommitsByUser(userId, repoId);
    }

    @GetMapping("/commitMetrics/{userId}/{repoId}")
    public CommitMetric getCommitMetrics(@PathVariable Long userId, @PathVariable Long repoId) {
        return new CommitMetric(commitService.getCodeGrowth(repoId),
            commitService.getCommitCount(userId, repoId), commitService.getDeletionCount(userId, repoId),
            commitService.getAdditionCount(userId, repoId), commitService.getAverageAdditionsOfLastFiveCommitsByUser(userId, repoId),
            commitService.getAverageDeletionsOfLastFiveCommitsByUser(userId, repoId), commitService.getCommitsUser(userId, repoId),
            commitService.getAverageUserProductivity(userId, repoId), userService.findAll().size(), commitService.getTotalLoC(repoId),
            commitService.getTotalCommits(repoId));
    }
}

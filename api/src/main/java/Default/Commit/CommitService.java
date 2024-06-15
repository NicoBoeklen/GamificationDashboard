package Default.Commit;

import Default.Commit.Stats.CodeGrowth;
import Default.Commit.Stats.CommitsUser;
import Default.User.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CommitService {

    @Autowired
    private CommitRepository commitRepository;
    
    @Autowired
    private UserService userService;

    /**
     * Saves a commit in the repository
     *
     * @param commit Commit to be saved
     * @return The saved commit
     */
    public Mono<Commit> saveCommit(Commit commit) {
        return Mono.fromCallable(() -> commitRepository.save(commit));
    }

    /**
     * Gives back all Commits from the user excluding merge commits
     *
     * @param userId
     * @return count of Commits
     */
    public Integer getCommitCount(Long userId, Long repoId) {
        return commitRepository.getAllCommitsBy(userId, repoId);
    }

    /**
     * Gives back all Deletions from the user excluding merge commits
     *
     * @param userId
     * @return count of deletions
     */
    public Integer getDeletionCount(Long userId, Long repoId) {
        return commitRepository.getAllDeletionsBy(userId, repoId);
    }

    /**
     * Gives back all Additions from the user excluding merge commits
     *
     * @param userId
     * @return count of additions
     */
    public Integer getAdditionCount(Long userId, Long repoId) {
        return commitRepository.getAllAdditionsBy(userId,repoId);
    }

    /**
     * Gives back a List of weeks with total Changes in the code (additions -  deletions)
     *
     * @return List of Code Growth Objects
     */
    public List<CodeGrowth> getCodeGrowth(Long repoId) {
        return commitRepository.getCodeGrowth(repoId);
    }

    /**
     * Gives back all Lines of code (additions -  deletions excluding mergeCommits)
     *
     * @return long TotalLoC
     */
    public Long getTotalLoC(Long repoId) {
        return commitRepository.getTotalLoC(repoId);
    }

    public Long getTotalLoCAdded(Long repoId) {
        return commitRepository.getTotalLoCAdded(repoId);
    }

    public Long getTotalLoCDeleted(Long repoId) {
        return commitRepository.getTotalLoCDeleted(repoId);
    }
    
    /**
     * Gives back all commits (excluding mergeCommits)
     *
     * @return total Commits
     */
    public Integer getTotalCommits(Long repoId) {
        return commitRepository.getTotalCommitCount(repoId);
    }

    public Double getAverageAdditionsOfLastFiveCommitsByUser(Long userId, Long repoId) {
        Pageable pageable = PageRequest.of(0, 5);
        List<Commit> commits = commitRepository.findLastFiveCommitsByUser(userId, pageable, repoId);
        return commits.stream()
            .mapToInt(Commit::getAdditions)
            .average()
            .orElse(0.0);
    }

    public Double getAverageDeletionsOfLastFiveCommitsByUser(Long userId, Long repoId) {
        Pageable pageable = PageRequest.of(0, 5);
        List<Commit> commits = commitRepository.findLastFiveCommitsByUser(userId, pageable, repoId);
        return commits.stream()
            .mapToInt(Commit::getDeletions)
            .average()
            .orElse(0.0);
    }

    /**
     * Gives back a List of weeks with total commits of user
     *
     * @return List of CommitsUser Objects
     */
    public List<CommitsUser> getCommitsUser(Long userId, Long repoId) {
        return commitRepository.getCommitsUser(userId,repoId);
    }

    public Double getAverageUserProductivity(Long userId, Long repoId) {
        List<Object[]> userProductivityList = commitRepository.getUserProductivity(userId, repoId);

        // Verwenden Sie die gesamte Liste, wenn sie weniger als fünf Elemente enthält
        List<Object[]> lastFiveDays = userProductivityList.size() < 5 ?
            userProductivityList :
            userProductivityList.subList(0, 5);

        // Berechne die Summe der Produktivitätswerte der letzten 5 Arbeitstage.
        long sumProductivity = lastFiveDays.stream()
            .mapToLong(array -> (long) array[1]) // Der Index 1 enthält die Produktivität.
            .sum();

        // Berechne den Durchschnitt der Produktivitätswerte der letzten 5 Arbeitstage.
        return lastFiveDays.isEmpty() ? 0.0 : (double) sumProductivity / lastFiveDays.size();
    }
    
    public Long getLoCTillDate(LocalDateTime date, Long repoId) {
        return commitRepository.getLoCTillDate(date, repoId);
    }
    
    public Integer getMaxCommitsSingleUser(Long repoId) {
        return commitRepository.getMaxCommitsSingleUser(repoId);
    }

    public Double getMaxProductivitySingleUser(Long repoId) {
        return userService.findAll().stream().filter(u -> u.getRepoId().equals(repoId)).mapToDouble(u -> getAverageUserProductivity(u.getUserId(), repoId)).max().orElse(0.001);
    }
}

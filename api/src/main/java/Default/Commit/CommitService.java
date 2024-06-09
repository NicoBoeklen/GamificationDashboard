package Default.Commit;

import Default.Commit.Stats.CodeGrowth;
import Default.Commit.Stats.CommitsUser;
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
    public Integer getCommitCount(Long userId) {
        return commitRepository.getAllCommitsBy(userId);
    }

    /**
     * Gives back all Deletions from the user excluding merge commits
     *
     * @param userId
     * @return count of deletions
     */
    public Integer getDeletionCount(Long userId) {
        return commitRepository.getAllDeletionsBy(userId);
    }

    /**
     * Gives back all Additions from the user excluding merge commits
     *
     * @param userId
     * @return count of additions
     */
    public Integer getAdditionCount(Long userId) {
        return commitRepository.getAllAdditionsBy(userId);
    }

    /**
     * Gives back a List of weeks with total Changes in the code (additions -  deletions)
     *
     * @return List of Code Growth Objects
     */
    public List<CodeGrowth> getCodeGrowth() {
        return commitRepository.getCodeGrowth();
    }

    /**
     * Gives back all Lines of code (additions -  deletions excluding mergeCommits)
     *
     * @return long TotalLoC
     */
    public Long getTotalLoC() {
        return commitRepository.getTotalLoC();
    }
    
    /**
     * Gives back all commits (excluding mergeCommits)
     *
     * @return total Commits
     */
    public Integer getTotalCommits() {
        return commitRepository.getTotalCommitCount();
    }

    public Double getAverageAdditionsOfLastFiveCommitsByUser(Long userId) {
        Pageable pageable = PageRequest.of(0, 5);
        List<Commit> commits = commitRepository.findLastFiveCommitsByUser(userId, pageable);
        return commits.stream()
            .mapToInt(Commit::getAdditions)
            .average()
            .orElse(0.0);
    }

    public Double getAverageDeletionsOfLastFiveCommitsByUser(Long userId) {
        Pageable pageable = PageRequest.of(0, 5);
        List<Commit> commits = commitRepository.findLastFiveCommitsByUser(userId, pageable);
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
    public List<CommitsUser> getCommitsUser(Long userId) {
        return commitRepository.getCommitsUser(userId);
    }

    public Double getAverageUserProductivity(Long userId) {
        List<Object[]> userProductivityList = commitRepository.getUserProductivity(userId);

        List<Object[]> lastFiveDays = userProductivityList.subList(0, 5);

        // Berechne die Summe der Produktivitätswerte der letzten 5 Arbeitstage.
        long sumProductivity = lastFiveDays.stream()
            .mapToLong(array -> (long) array[1]) // Der Index 1 enthält die Produktivität.
            .sum();

        // Berechne den Durchschnitt der Produktivitätswerte der letzten 5 Arbeitstage.
        return lastFiveDays.isEmpty() ? 0.0 : (double) sumProductivity / lastFiveDays.size();
    }
    public List<CodeGrowth> getLoCTillDate(LocalDateTime date) {
        return commitRepository.getLoCTillDate(date);
    }
}

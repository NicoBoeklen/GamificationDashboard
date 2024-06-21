package Default.Commit;

import Default.Commit.Stats.CodeGrowth;
import Default.Commit.Stats.CommitsUser;
import Default.User.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Mono;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
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

    public Integer getAdditionCountByDay(Long userId, Long repoId, LocalDate day) {
        return commitRepository.getAllAdditionsByUserByDay(userId, repoId, day);
    }

    /**
     * Gives back a List of weeks with total Changes in the code (additions -  deletions)
     *
     * @return List of Code Growth Objects
     */
    /*public List<CodeGrowth> getCodeGrowth(Long repoId) {
        return commitRepository.getCodeGrowth(repoId);
    }*/

    public List<CodeGrowth> getCodeGrowth(Long repoId) {
        List<CodeGrowth> codeGrowthList = commitRepository.getCodeGrowth(repoId);
        List<CodeGrowth> cumulativeCodeGrowthList = new ArrayList<>();

        if (codeGrowthList.isEmpty()) {
            return cumulativeCodeGrowthList;
        }

        LocalDate currentWeek = codeGrowthList.get(0).getWeek();
        Long cumulativeTotalChanges = 0L;
        int index = 0;

        while (index < codeGrowthList.size()) {
            CodeGrowth codeGrowth = codeGrowthList.get(index);

            // Add missing weeks
            while (currentWeek.isBefore(codeGrowth.getWeek())) {
                cumulativeCodeGrowthList.add(new CodeGrowth(currentWeek, cumulativeTotalChanges));
                currentWeek = currentWeek.plusWeeks(1);
            }

            // Add current week's changes
            cumulativeTotalChanges += codeGrowth.getTotalChanges();
            cumulativeCodeGrowthList.add(new CodeGrowth(currentWeek, cumulativeTotalChanges));
            currentWeek = currentWeek.plusWeeks(1);
            index++;
        }

        return cumulativeCodeGrowthList;
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

    public Long getTotalLoCAddedByDay(Long repoId, LocalDate day) {
        return commitRepository.getTotalLoCAddedByDay(repoId, day);
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

    public int getTotalCommitsByDay(Long repoId, LocalDate day) {
        return commitRepository.getTotalCommitCountByDay(repoId, day);
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
    /*public List<CommitsUser> getCommitsUser(Long userId, Long repoId) {
        return commitRepository.getCommitsUser(userId,repoId);
    }*/

    public List<CommitsUser> getCommitsUser(Long userId, Long repoId) {
        List<CommitsUser> commitsUserList = commitRepository.getCommitsUser(userId, repoId);
        List<CommitsUser> completeCommitsUserList = new ArrayList<>();

        if (commitsUserList.isEmpty()) {
            return completeCommitsUserList;
        }

        LocalDate currentWeek = commitsUserList.get(0).getWeek();
        int index = 0;

        while (index < commitsUserList.size()) {
            CommitsUser commitsUser = commitsUserList.get(index);

            // Add missing weeks
            while (currentWeek.isBefore(commitsUser.getWeek())) {
                completeCommitsUserList.add(new CommitsUser(currentWeek, 0L));
                currentWeek = currentWeek.plusWeeks(1);
            }

            // Add current week's commits
            completeCommitsUserList.add(new CommitsUser(commitsUser.getWeek(), commitsUser.getTotalCommits()));
            currentWeek = currentWeek.plusWeeks(1);
            index++;
        }

        // Add any remaining weeks up to the current week
        while (currentWeek.isBefore(commitsUserList.get(commitsUserList.size()-1).getWeek())) {
            completeCommitsUserList.add(new CommitsUser(currentWeek, 0L));
            currentWeek = currentWeek.plusWeeks(1);
        }

        return completeCommitsUserList;
    }

    public Long getCommitsUserByDay(Long userId, Long repoId, LocalDate day) {
        return commitRepository.getCommitsUserByDay(userId, repoId, day);
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

package Default.Commit;

import Default.Commit.Stats.CodeGrowth;
import Default.User.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Pageable;
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
    public Commit saveCommit(Commit commit) {
        return commitRepository.save(commit);
    }
    
    /**
     * Gives back all Commits from the user excluding merge commits
     * @param user
     * @return count of Commits
     */
    public Integer getCommitCount(User user) {
        return commitRepository.getAllCommitsBy(user.getId());
    }

    /**
     * Gives back all Deletions from the user excluding merge commits
     * @param user
     * @return count of deletions
     */
    public Integer getDeletionCount(User user) {
        return commitRepository.getAllDeletionsBy(user.getId());
    }

    /**
     * Gives back all Additions from the user excluding merge commits
     * @param user
     * @return count of additions
     */
    public Integer getAdditionCount(User user) {
        return commitRepository.getAllAdditionsBy(user.getId());
    }

    /**
     * Gives back a List of weeks with total Changes in the code (additions -  deletions) 
     * @return List of Code Growth Objects
     */
    public List<CodeGrowth> getCodeGrowth() {
        return commitRepository.getCodeGrowth();
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
}

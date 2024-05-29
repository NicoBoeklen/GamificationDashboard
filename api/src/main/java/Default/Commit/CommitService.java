package Default.Commit;

import Default.User.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}

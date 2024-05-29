package Default.Commit;

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
}

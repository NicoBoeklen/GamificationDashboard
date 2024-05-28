package Default.Commit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommitService {
    @Autowired
    private CommitRepository commitRepository;
    
    public Commit saveCommit(Commit commit) {
        return commitRepository.save(commit);
    }
}

package Default.Issue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import Default.User.User;

import java.util.Optional;

@Service
public class IssueService {

    @Autowired
    private IssueRepository issueRepository;

    /**
     * Saves an issue in the repository
     *
     * @param issue Issue to be saved
     * @return The saved issue
     */
    public Issue saveIssue(Issue issue) {
        return issueRepository.save(issue);
    }

    /**
     * Delete an issue from the repository
     *
     * @param issueId Issue to be saved
     * @return The deleted issue
     */
    public void deleteIssueById(Integer issueId) {
        issueRepository.deleteById(issueId);
    }

    /**
     * 
     * @param id id of the issue
     * @return Closed by User or throws exception
     */
    public User findClosedByWithId(Integer id) throws ChangeSetPersister.NotFoundException {
        return issueRepository.findById(id).orElseThrow(ChangeSetPersister.NotFoundException::new).getClosedBy();
    }
}

package Default.Issue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import Default.User.User;
import reactor.core.publisher.Mono;

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
    public Mono<Issue> saveIssue(Issue issue) {
        return Mono.fromCallable(() -> issueRepository.save(issue));
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
     * @return Closed by User or throws exception, can be null
     */
    public User findClosedByWithId(Integer id) throws ChangeSetPersister.NotFoundException {
        return issueRepository.findById(id).orElseThrow(ChangeSetPersister.NotFoundException::new).getClosedBy();
    }
    public Integer getOpenIssuesTeam() {
        return issueRepository.getOpenIssuesTeam();
    }
    public Integer getFixedIssuesTeam() {
        return issueRepository.getFixedIssuesTeam();
    }
    public Integer getAllIssuesTeam() {
        return issueRepository.getAllIssuesTeam();
    }
    public Integer getTotalClosedIssuesUser(Long userId) {
        return issueRepository.getTotalClosedIssuesUser(userId);
    }
    
}

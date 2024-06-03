package Default.Issue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import Default.User.User;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

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
    public Double getAverageAgeOfOpenIssuesTeam() {
        return issueRepository.findAll().stream().filter(issue -> issue.getState().equals("open")).mapToLong(issue -> ChronoUnit.DAYS.between(issue.getDateOpened(), LocalDateTime.now())).average().orElse(0);
    }
    public Double getAverageAgeOfTotalIssuesTeam() {
        return issueRepository.findAll().stream().filter(issue -> issue.getState().equals("closed")).mapToLong(issue -> ChronoUnit.DAYS.between(issue.getDateOpened(), issue.getDateClosed())).average().orElse(0);
    }
    public Object getWeeklyClosedPullRequests() {
        return issueRepository.findWeeklyClosedPullRequests();
    }
    
}

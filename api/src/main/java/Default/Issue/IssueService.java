package Default.Issue;

import Default.Commit.Stats.CodeGrowth;
import Default.Issue.Stats.IssuesWeekly;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import Default.User.User;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class IssueService {

    @Autowired
    private IssueRepository issueRepository;
    @Autowired
    private Default.Commit.CommitRepository commitRepository;

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
    public Double getAverageAgeOfOpenIssues() {
        return issueRepository.findAll().stream().filter(issue -> issue.getState().equals("open")).mapToLong(issue -> ChronoUnit.DAYS.between(issue.getDateOpened(), LocalDateTime.now())).average().orElse(0);
    }
    //get the average time to fix an issue for the last 5 closed issues
    public Double getTeamAverageTimeFixIssue() {
        Pageable pageable = PageRequest.of(0, 5);
        return issueRepository.findLastFiveClosedIssues(pageable).stream().mapToLong(issue -> ChronoUnit.DAYS.between(issue.getDateOpened(), issue.getDateClosed())).average().orElse(0.0);
    }
    public List<IssuesWeekly> getWeeklyClosedIssues() {
        return issueRepository.findWeeklyClosedIssues();
    }
    public List<IssuesWeekly> getWeeklyOpenIssues() {
        return issueRepository.findWeeklyOpenIssues();
    }
    public List<IssuesWeekly> getWeeklyTotalIssues() {
        return issueRepository.findWeeklyTotalIssues();
    }
    public List<IssuesWeekly> getIssuesPer1000LoCPerWeek() {
        List<IssuesWeekly> weeklyIssues = issueRepository.findExactDateTotalIssues();
        List<IssuesWeekly> issuesPer1000LoCPerWeek = new ArrayList<>();
        weeklyIssues.forEach(issuesWeekly -> System.out.println("LOL"+issuesWeekly.getWeek()));
        weeklyIssues.forEach(issuesWeekly -> System.out.println(issuesWeekly.getWeek()+" is "+(commitRepository.getLoCTillDate(issuesWeekly.getWeek()))));
        weeklyIssues.forEach(issuesWeekly -> issuesPer1000LoCPerWeek.add(new IssuesWeekly(issuesWeekly.getWeek(),(double)issuesWeekly.getIssues()/(double)commitRepository.getLoCTillDate(issuesWeekly.getWeek())/1000)));
        return issuesPer1000LoCPerWeek;
    }
}

package Default.Issue;

import Default.Issue.Stats.IssuesWeekly;
import Default.Issue.Stats.IssuesWeeklyDouble;
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
import java.util.List;

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
    public void deleteIssueById(Long issueId) {
        issueRepository.deleteById(issueId);
    }
    
    public Integer getOpenIssuesTeam(Long repoId) {
        return issueRepository.getOpenIssuesTeam(repoId);
    }

    public Integer getFixedIssuesTeam(Long repoId) {
        return issueRepository.getFixedIssuesTeam(repoId);
    }

    public Integer getAllIssuesTeam(Long repoId) {
        return issueRepository.getAllIssuesTeam(repoId);
    }

    public Integer getTotalClosedIssuesUser(Long userId, Long repoId) {
        return issueRepository.getTotalClosedIssuesUser(userId, repoId);
    }

    public Double getAverageAgeOfOpenIssues(Long repoId) {
        return issueRepository.findAll().stream().filter(issue -> issue.getState().equals("open")).filter(issue -> issue.getOpenedBy().getRepoId().equals(repoId)).mapToLong(issue -> ChronoUnit.DAYS.between(issue.getDateOpened(), LocalDateTime.now())).average().orElse(0);
    }

    public Integer getCountOpenIssuesLessSevenDays(Long repoId) {
        LocalDateTime dateThreshold = LocalDateTime.now().minusDays(7);
        List<Issue> recentIssues = issueRepository.findOpenIssuesWithinLastDays(dateThreshold, repoId);
        return recentIssues.size();
    }

    public Integer getCountOpenIssuesMoreOneMonth(Long repoId) {
        LocalDateTime dateThreshold = LocalDateTime.now().minusDays(30);
        List<Issue> recentIssues = issueRepository.findOpenIssuesWithoutLastDays(dateThreshold, repoId);
        return recentIssues.size();
    }

    //get the average time to fix an issue for the last 5 closed issues
    public Double getTeamAverageTimeFixIssue(Long repoId) {
        Pageable pageable = PageRequest.of(0, 5);
        return issueRepository.findLastFiveClosedIssues(pageable, repoId).stream().mapToLong(issue -> ChronoUnit.DAYS.between(issue.getDateOpened(), issue.getDateClosed())).average().orElse(0.0);
    }

    public List<IssuesWeekly> getWeeklyClosedIssues(Long repoId) {
        return issueRepository.findWeeklyClosedIssues(repoId);
    }

    public List<IssuesWeekly> getWeeklyOpenIssues(Long repoId) {
        return issueRepository.findWeeklyOpenIssues(repoId);
    }

    public List<IssuesWeekly> getWeeklyTotalIssues(Long repoId) {
        return issueRepository.findWeeklyTotalIssues(repoId);
    }

    public List<IssuesWeeklyDouble> getIssuesPer1000LoCPerWeek(Long repoId) {
        List<IssuesWeekly> weeklyIssues = issueRepository.findExactDateTotalIssues(repoId);
        List<IssuesWeeklyDouble> issuesPer1000LoC = new ArrayList<>();
        weeklyIssues.forEach(issuesWeeklyDouble -> System.out.println(issuesWeeklyDouble.getWeek() + " is " + (((double) commitRepository.getLoCTillDate(issuesWeeklyDouble.getWeek(), repoId) / 1000))));
        for (IssuesWeekly issuesWeekly : weeklyIssues) {
            double issues = issuesWeekly.getIssues();
            double locTillDate = commitRepository.getLoCTillDate(issuesWeekly.getWeek(), repoId);
            double issuesPerThousandLoc = issues / (locTillDate / 1000);
            double roundedIssuesPerThousandLoc = Math.round(issuesPerThousandLoc * 100.0) / 100.0;
            IssuesWeeklyDouble newIssuesWeekly = new IssuesWeeklyDouble(issuesWeekly.getWeek(), roundedIssuesPerThousandLoc);
            issuesPer1000LoC.add(newIssuesWeekly);
        }
        return issuesPer1000LoC;
    }
}

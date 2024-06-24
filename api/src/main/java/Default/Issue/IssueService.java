package Default.Issue;

import Default.Commit.CommitService;
import Default.Commit.Stats.CodeGrowth;
import Default.Issue.Stats.IssuesWeekly;
import Default.Issue.Stats.IssuesWeeklyDouble;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;

@Service
public class IssueService {

    @Autowired
    private IssueRepository issueRepository;
    
    @Autowired
    private CommitService commitService;

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
    
    public List<Issue> findIssuesByNumber(Integer number) {
        return issueRepository.findIssuesByNumber(number);
    }
    
    public Integer getOpenIssuesTeam(Long repoId) {
        return issueRepository.getOpenIssuesTeam(repoId);
    }

    public Integer getFixedIssuesTeam(Long repoId) {
        return issueRepository.getFixedIssuesTeam(repoId);
    }
    
    public Integer getFixedIssuesTeamByDay(Long repoId, LocalDate day) {
        return issueRepository.getFixedIssuesTeamByDay(repoId, day);
    }

    public Integer getAllIssuesTeam(Long repoId) {
        return issueRepository.getAllIssuesTeam(repoId);
    }

    public Integer getTotalClosedIssuesUser(Long userId, Long repoId) {
        return issueRepository.getTotalClosedIssuesUser(userId, repoId);
    }

    public int getTotalClosedIssuesUserByDay(Long userId, Long repoId, LocalDate day) {
        return issueRepository.getTotalClosedIssuesUserByDay(userId, repoId, day);
    }

    public Double getAverageAgeOfOpenIssues(Long repoId) {
        return issueRepository.findAll().stream().filter(issue -> issue.getState().equals("open")).filter(issue -> issue.getOpenedBy() != null).filter(issue -> issue.getRepoId().equals(repoId)).mapToLong(issue -> ChronoUnit.DAYS.between(issue.getDateOpened(), LocalDateTime.now())).average().orElse(0);
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
        List<IssuesWeekly> issuesList = issueRepository.getClosedIssuesWeekly(repoId);
        return getIssuesWeeklies(issuesList);
    }

    public List<IssuesWeekly> getWeeklyTotalIssues(Long repoId) {
        List<IssuesWeekly> issuesList = issueRepository.getTotalIssuesWeekly(repoId);
        return getIssuesWeeklies(issuesList);
    }

    public List<IssuesWeekly> getWeeklyOpenIssues(Long repoId) {
        List<IssuesWeekly> issuesList = issueRepository.getOpenIssuesWeekly(repoId);
        return getIssuesWeeklies(issuesList);
    }

    private List<IssuesWeekly> getIssuesWeeklies(List<IssuesWeekly> issuesList) {
        List<IssuesWeekly> cumulativeIssuesList = new ArrayList<>();

        if (issuesList.isEmpty()) {
            return cumulativeIssuesList;
        }

        LocalDate currentWeek = issuesList.get(0).getWeek();
        Long cumulativeTotalChanges = 0L;
        int index = 0;

        while (index < issuesList.size()) {
            IssuesWeekly issues = issuesList.get(index);

            // Add missing weeks
            while (currentWeek.isBefore(issues.getWeek())) {
                cumulativeIssuesList.add(new IssuesWeekly(currentWeek, cumulativeTotalChanges));
                currentWeek = currentWeek.plusWeeks(1);
            }

            // Add current week's changes
            cumulativeTotalChanges += issues.getIssues();
            cumulativeIssuesList.add(new IssuesWeekly(currentWeek, cumulativeTotalChanges));
            currentWeek = currentWeek.plusWeeks(1);
            index++;
        }

        return cumulativeIssuesList;
    }

    public List<IssuesWeeklyDouble> getIssuesPer1000LoCPerWeek(Long repoId) {
        List<IssuesWeekly> issues = getWeeklyOpenIssues(repoId);
        List<CodeGrowth> code = commitService.getCodeGrowth(repoId);
        List<IssuesWeeklyDouble> issueDensity = new ArrayList<>();

        Map<LocalDate, Long> issuesMap = new HashMap<>();
        for (IssuesWeekly issue : issues) {
            issuesMap.put(issue.getWeek(), issue.getIssues());
        }

        for (CodeGrowth codeGrowth : code) {
            LocalDate week = codeGrowth.getWeek();
            if (issuesMap.containsKey(week)) {
                Long issuesCount = issuesMap.get(week);
                double codeChanges = codeGrowth.getTotalChanges() / 1000.0;
                double density;
                if (codeChanges == 0) {
                    density = 0;
                } else {
                    density = issuesCount / codeChanges;
                }
                issueDensity.add(new IssuesWeeklyDouble(week, density));
            }
        }

        return issueDensity;
        
    }

    public Integer getMaxFixedIssuesSingleUser(Long repoId) {
        return issueRepository.getMaxFixedIssuesSingleUser(repoId);
    }

}

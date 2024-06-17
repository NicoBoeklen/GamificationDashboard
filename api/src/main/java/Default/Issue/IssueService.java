package Default.Issue;

import Default.Issue.Stats.IssuesWeekly;
import Default.Issue.Stats.IssuesWeeklyDouble;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDateTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.*;

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
        return issueRepository.findAll().stream().filter(issue -> issue.getState().equals("open")).filter(issue -> issue.getOpenedBy() != null).filter(issue -> issue.getOpenedBy().getRepoId().equals(repoId)).mapToLong(issue -> ChronoUnit.DAYS.between(issue.getDateOpened(), LocalDateTime.now())).average().orElse(0);
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
        Set<LocalDateTime> weekList = getWeeksBetweenDates(issueRepository.findWeekFirstIssue(repoId));
        List<IssuesWeekly> weeklyTotalIssues = new ArrayList<>();
        for (LocalDateTime week : weekList) {
            long openIssuesCount = 0;
            for (Issue issue : issueRepository.getAllClosedIssues(repoId)) {
                LocalDateTime truncatedClosedDate= truncateDate(issue.getDateClosed());
                if(truncatedClosedDate.isBefore(week)||truncatedClosedDate.isEqual(week)){
                    openIssuesCount++;
                }
            }
            weeklyTotalIssues.add(new IssuesWeekly(week, openIssuesCount));
        }
        return weeklyTotalIssues;
    }
    public List<IssuesWeekly> getWeeklyTotalIssues(Long repoId) {
        Set<LocalDateTime> weekList = getWeeksBetweenDates(issueRepository.findWeekFirstIssue(repoId));
        List<IssuesWeekly> weeklyTotalIssues = new ArrayList<>();
        for (LocalDateTime week : weekList) {
            long openIssuesCount = 0;
            for (Issue issue : issueRepository.getAllIssues(repoId)) {
                LocalDateTime truncatedOpenDate= truncateDate(issue.getDateOpened());
                LocalDateTime truncatedClosedDate = null;
                if (issue.getDateClosed() != null) {
                    truncatedClosedDate = truncateDate(issue.getDateOpened());
                }
                if (truncatedOpenDate.isBefore((week))||truncatedOpenDate.isEqual(week)) {
                    openIssuesCount++;
                }
            }
            weeklyTotalIssues.add(new IssuesWeekly(week, openIssuesCount));
        }
        return weeklyTotalIssues;
    }
    private Set<LocalDateTime> getWeeksBetweenDates(LocalDateTime startDate) {
        LocalDateTime endDate = LocalDateTime.now();
        Set<LocalDateTime> weeks = new TreeSet<>();
        if (startDate==null){
            return weeks;
        }
        while (!startDate.isAfter(endDate)) {
            weeks.add(startDate.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY)).truncatedTo(ChronoUnit.DAYS));
            startDate = startDate.plusWeeks(1);
        }
        return weeks;
    }
    private LocalDateTime truncateDate(LocalDateTime date) {
        return date.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY)).truncatedTo(java.time.temporal.ChronoUnit.DAYS);
    }
    public List<IssuesWeekly> getWeeklyOpenIssues(Long repoId){
        List<Issue> issues= issueRepository.getAllIssues(repoId);
        List<IssuesWeekly> weeklyOpenIssues = new ArrayList<>();
        Set<LocalDateTime> weekList = getWeeksBetweenDates(issueRepository.findWeekFirstIssue(repoId));
        for (LocalDateTime week : weekList) {
            long openIssuesCount = 0;
            for (Issue issue : issues) {
                LocalDateTime truncatedOpenDate = truncateDate(issue.getDateOpened());
                LocalDateTime truncatedClosedDate = null;
                if (issue.getDateClosed() != null) {
                    truncatedClosedDate = truncateDate(issue.getDateClosed());
                }
                if ((truncatedOpenDate.isBefore(week)|| truncatedOpenDate.isEqual(week)) && (truncatedClosedDate == null || truncatedClosedDate.isAfter(week))) {
                    openIssuesCount++;
                }
            }
            weeklyOpenIssues.add(new IssuesWeekly(week, openIssuesCount));
        }
        return weeklyOpenIssues;
    }
    public List<IssuesWeeklyDouble> getIssuesPer1000LoCPerWeek(Long repoId) {
        List<IssuesWeekly> weeklyIssues = issueRepository.findExactDateTotalIssues(repoId);
        List<IssuesWeeklyDouble> issuesPer1000LoC = new ArrayList<>();
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

    public Integer getMaxFixedIssuesSingleUser(Long repoId) {
        return issueRepository.getMaxFixedIssuesSingleUser(repoId);
    }

}

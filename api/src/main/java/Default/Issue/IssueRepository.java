package Default.Issue;

import Default.Issue.Stats.IssuesWeeklyDouble;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import Default.Issue.Stats.IssuesWeekly;

import java.time.LocalDateTime;
import java.util.List;

/**
Repository for Issues identified by ID (long)
 */
public interface IssueRepository extends JpaRepository<Issue, Integer>{
    @Query("SELECT COUNT(i) FROM Issue i WHERE NOT TYPE(i)=PullRequest")
    Integer getAllIssuesTeam();
    @Query("SELECT COUNT(i) FROM Issue i WHERE i.state= 'open' AND NOT TYPE(i)=PullRequest")
    Integer getOpenIssuesTeam();
    @Query("SELECT COUNT(i) FROM Issue i WHERE i.state= 'closed' AND NOT TYPE(i)=PullRequest")
    Integer getFixedIssuesTeam();
    @Query("SELECT COUNT(i) FROM Issue i WHERE i.closedBy.id = :userId AND NOT TYPE(i)=PullRequest")
    Integer getTotalClosedIssuesUser(@Param("userId") Long userId);
    
    
    @Query("SELECT new Default.Issue.Stats.IssuesWeekly(DATE_TRUNC('WEEK', i.dateOpened), COUNT(i)) " +
        "FROM Issue i " +
        "WHERE (i.state = 'closed' AND NOT TYPE(i)=PullRequest)"+
        "GROUP BY DATE_TRUNC('WEEK', i.dateOpened) ")
    List<IssuesWeekly> findWeeklyClosedIssues();
    @Query("SELECT new Default.Issue.Stats.IssuesWeekly(DATE_TRUNC('WEEK', i.dateOpened), COUNT(i)) " +
        "FROM Issue i " +
        "WHERE (i.state = 'open' AND NOT TYPE(i)=PullRequest)"+
        "GROUP BY DATE_TRUNC('WEEK', i.dateOpened) ")
    List<IssuesWeekly> findWeeklyOpenIssues();
    //@TODO besprechen ob bei Total opened oder closed Date sein soll
    @Query("SELECT new Default.Issue.Stats.IssuesWeekly(DATE_TRUNC('WEEK', i.dateOpened), COUNT(i))" +
        "FROM Issue i " +
        "WHERE NOT (TYPE(i)=PullRequest)"+
        "GROUP BY DATE_TRUNC('WEEK', i.dateOpened) ")
    List<IssuesWeekly> findWeeklyTotalIssues();
    @Query("SELECT new Default.Issue.Stats.IssuesWeekly(i.dateOpened, COUNT(i))" +
        "FROM Issue i " +
        "WHERE NOT (TYPE(i)=PullRequest)"+
        "GROUP BY i.dateOpened ")
    List<IssuesWeekly> findExactDateTotalIssues();

    @Query("SELECT i FROM Issue i WHERE i.state= 'closed' AND NOT TYPE(i)=PullRequest ORDER BY i.dateClosed DESC")
    List<Issue> findLastFiveClosedIssues(Pageable pageable);

    @Query("SELECT i FROM Issue i WHERE i.state = 'open' AND i.dateOpened >= :dateThreshold AND NOT TYPE(i)=PullRequest")
    List<Issue> findOpenIssuesWithinLastDays(LocalDateTime dateThreshold);
    
    @Query("SELECT i FROM Issue i WHERE i.state = 'open' AND i.dateOpened <= :dateThreshold AND NOT TYPE(i)=PullRequest")
    List<Issue> findOpenIssuesWithoutLastDays(LocalDateTime dateThreshold);
}

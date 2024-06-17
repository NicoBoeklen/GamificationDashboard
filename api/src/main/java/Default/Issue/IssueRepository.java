package Default.Issue;

import Default.Issue.Stats.IssuesWeeklyDouble;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import Default.Issue.Stats.IssuesWeekly;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
Repository for Issues identified by ID (long)
 */
public interface IssueRepository extends JpaRepository<Issue, Long>{
    
    List<Issue> findIssuesByNumber(Integer number);
    @Query("SELECT MIN(DATE_TRUNC('WEEK', i.dateOpened)) FROM Issue i WHERE i.openedBy.repoId = :repoId AND NOT TYPE(i)=PullRequest")
    LocalDateTime findWeekFirstIssue(@Param("repoId") Long repoId);
        
    @Query("SELECT i FROM Issue i WHERE NOT TYPE(i)=PullRequest AND i.openedBy.repoId = :repoId")
    List<Issue> getAllIssues(@Param("repoId") Long repoId);
    
    @Query("SELECT i FROM Issue i WHERE NOT TYPE(i)=PullRequest AND i.openedBy.repoId = :repoId AND i.state= 'closed'")
    List<Issue> getAllClosedIssues(@Param("repoId") Long repoId);
    
    @Query("SELECT COUNT(i) FROM Issue i WHERE NOT TYPE(i)=PullRequest AND i.openedBy.repoId = :repoId")
    Integer getAllIssuesTeam(@Param("repoId") Long repoId);
    
    @Query("SELECT COUNT(i) FROM Issue i WHERE i.state= 'open' AND NOT TYPE(i)=PullRequest AND i.openedBy.repoId = :repoId")
    Integer getOpenIssuesTeam(@Param("repoId") Long repoId);
    
    @Query("SELECT COUNT(i) FROM Issue i WHERE i.state= 'closed' AND NOT TYPE(i)=PullRequest AND i.openedBy.repoId = :repoId")
    Integer getFixedIssuesTeam(@Param("repoId") Long repoId);

    @Query("SELECT COUNT(i) FROM Issue i WHERE i.state= 'closed' AND NOT TYPE(i)=PullRequest AND i.openedBy.repoId = :repoId AND CAST(i.dateClosed AS DATE) = :day")
    Integer getFixedIssuesTeamByDay(@Param("repoId")Long repoId, @Param("day") LocalDate day);
    
    @Query("SELECT COUNT(i) FROM Issue i WHERE i.closedBy.userId = :userId AND NOT TYPE(i)=PullRequest AND i.openedBy.repoId = :repoId")
    Integer getTotalClosedIssuesUser(@Param("userId") Long userId, @Param("repoId") Long repoId);

    @Query("SELECT COUNT(i) FROM Issue i WHERE i.closedBy.userId = :userId AND NOT TYPE(i)=PullRequest AND i.openedBy.repoId = :repoId AND CAST(i.dateClosed AS DATE) = :day")
    Integer getTotalClosedIssuesUserByDay(@Param("userId") Long userId, @Param("repoId") Long repoId, @Param("day") LocalDate day);

    @Query("SELECT new Default.Issue.Stats.IssuesWeekly(DATE_TRUNC('WEEK', i.dateOpened), SUM(COUNT(i)) OVER (ORDER BY date_trunc('week', i.dateOpened))) " +
        "FROM Issue i " +
        "WHERE (i.openedBy.repoId = :repoId AND NOT TYPE(i)=PullRequest)"+
        "GROUP BY DATE_TRUNC('WEEK', i.dateOpened) ")
    List<IssuesWeekly> findWeeklyClosedIssues(@Param("repoId") Long repoId);
    
    //@TODO besprechen ob bei Total opened oder closed Date sein soll
    @Query("SELECT new Default.Issue.Stats.IssuesWeekly(DATE_TRUNC('WEEK', i.dateOpened), SUM(COUNT(i)) OVER (ORDER BY date_trunc('week', i.dateOpened))) " +
        "FROM Issue i " +
        "WHERE (i.openedBy.repoId = :repoId AND NOT TYPE(i)=PullRequest)"+
        "GROUP BY DATE_TRUNC('WEEK', i.dateOpened) ")
    List<IssuesWeekly> findWeeklyTotalIssues(@Param("repoId") Long repoId);
    
    @Query("SELECT new Default.Issue.Stats.IssuesWeekly(i.dateOpened, COUNT(i))" +
        "FROM Issue i " +
        "WHERE (i.openedBy.repoId = :repoId AND NOT TYPE(i) = PullRequest)"+
        "GROUP BY i.dateOpened ")
    List<IssuesWeekly> findExactDateTotalIssues(@Param("repoId") Long repoId);

    @Query("SELECT i FROM Issue i WHERE i.state= 'closed' AND NOT TYPE(i)=PullRequest AND i.openedBy.repoId = :repoId ORDER BY i.dateClosed DESC")
    List<Issue> findLastFiveClosedIssues(Pageable pageable, @Param("repoId") Long repoId);

    @Query("SELECT i FROM Issue i WHERE i.state = 'open' AND i.dateOpened >= :dateThreshold AND i.openedBy.repoId = :repoId AND NOT TYPE(i)=PullRequest")
    List<Issue> findOpenIssuesWithinLastDays(LocalDateTime dateThreshold, @Param("repoId") Long repoId);
    
    @Query("SELECT i FROM Issue i WHERE i.state = 'open' AND i.dateOpened <= :dateThreshold AND NOT TYPE(i)=PullRequest AND i.openedBy.repoId = :repoId")
    List<Issue> findOpenIssuesWithoutLastDays(LocalDateTime dateThreshold, @Param("repoId") Long repoId);

    @Query("SELECT COALESCE(MAX(i.count),0) FROM (SELECT COUNT(i) AS count FROM Issue i WHERE i.state= 'closed' AND NOT TYPE(i)=PullRequest AND i.closedBy.repoId = :repoId GROUP BY i.closedBy.userId) i")
    Integer getMaxFixedIssuesSingleUser(@Param("repoId") Long repoId);
    
}

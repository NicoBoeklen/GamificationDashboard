package Default.Issue;

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

    @Query("SELECT new Default.Issue.Stats.IssuesWeekly(CAST(DATE_TRUNC('week', i.dateClosed) AS LocalDate), COUNT (i)) " +
        "FROM Issue i " +
        "WHERE i.state = 'closed' AND i.repositoryId = :repoId " +
        "GROUP BY CAST(DATE_TRUNC('week', i.dateClosed) AS LocalDate)" +
        "ORDER BY CAST(DATE_TRUNC('week', i.dateClosed) AS LocalDate)")
    List<IssuesWeekly> getClosedIssuesWeekly(@Param("repoId") Long repoId);

    @Query("SELECT new Default.Issue.Stats.IssuesWeekly(CAST(DATE_TRUNC('week', i.dateOpened) AS LocalDate), COUNT (i)) " +
        "FROM Issue i " +
        "WHERE i.state = 'open' AND i.repositoryId = :repoId " +
        "GROUP BY CAST(DATE_TRUNC('week', i.dateOpened) AS LocalDate)" +
        "ORDER BY CAST(DATE_TRUNC('week', i.dateOpened) AS LocalDate)")
    List<IssuesWeekly> getOpenIssuesWeekly(@Param("repoId") Long repoId);

    @Query("SELECT new Default.Issue.Stats.IssuesWeekly(CAST(DATE_TRUNC('week', i.dateOpened) AS LocalDate), COUNT (i)) " +
        "FROM Issue i " +
        "WHERE i.repositoryId = :repoId " +
        "GROUP BY CAST(DATE_TRUNC('week', i.dateOpened) AS LocalDate)" +
        "ORDER BY CAST(DATE_TRUNC('week', i.dateOpened) AS LocalDate)")
    List<IssuesWeekly> getTotalIssuesWeekly(@Param("repoId") Long repoId);

    @Query("SELECT COUNT(i) FROM Issue i WHERE NOT TYPE(i)=PullRequest AND i.repositoryId = :repoId")
    Integer getAllIssuesTeam(@Param("repoId") Long repoId);
    
    @Query("SELECT COUNT(i) FROM Issue i WHERE i.state= 'open' AND NOT TYPE(i)=PullRequest AND i.repositoryId = :repoId")
    Integer getOpenIssuesTeam(@Param("repoId") Long repoId);
    
    @Query("SELECT COUNT(i) FROM Issue i WHERE i.state= 'closed' AND NOT TYPE(i)=PullRequest AND i.repositoryId = :repoId")
    Integer getFixedIssuesTeam(@Param("repoId") Long repoId);

    @Query("SELECT COUNT(i) FROM Issue i WHERE i.state= 'closed' AND NOT TYPE(i)=PullRequest AND i.repositoryId = :repoId AND CAST(i.dateClosed AS DATE) = :day")
    Integer getFixedIssuesTeamByDay(@Param("repoId")Long repoId, @Param("day") LocalDate day);
    
    @Query("SELECT COUNT(i) FROM Issue i WHERE i.closedBy.userId = :userId AND NOT TYPE(i)=PullRequest AND i.openedBy.repoId = :repoId")
    Integer getTotalClosedIssuesUser(@Param("userId") Long userId, @Param("repoId") Long repoId);

    @Query("SELECT COUNT(i) FROM Issue i WHERE i.closedBy.userId = :userId AND NOT TYPE(i)=PullRequest AND i.openedBy.repoId = :repoId AND CAST(i.dateClosed AS DATE) = :day")
    Integer getTotalClosedIssuesUserByDay(@Param("userId") Long userId, @Param("repoId") Long repoId, @Param("day") LocalDate day);
    
    @Query("SELECT i FROM Issue i WHERE i.state= 'closed' AND NOT TYPE(i)=PullRequest AND i.repositoryId = :repoId ORDER BY i.dateClosed DESC")
    List<Issue> findLastFiveClosedIssues(Pageable pageable, @Param("repoId") Long repoId);

    @Query("SELECT i FROM Issue i WHERE i.state = 'open' AND i.dateOpened >= :dateThreshold AND i.repositoryId = :repoId AND NOT TYPE(i)=PullRequest")
    List<Issue> findOpenIssuesWithinLastDays(LocalDateTime dateThreshold, @Param("repoId") Long repoId);
    
    @Query("SELECT i FROM Issue i WHERE i.state = 'open' AND i.dateOpened <= :dateThreshold AND NOT TYPE(i)=PullRequest AND i.repositoryId = :repoId")
    List<Issue> findOpenIssuesWithoutLastDays(LocalDateTime dateThreshold, @Param("repoId") Long repoId);

    @Query("SELECT COALESCE(MAX(i.count),0) FROM (SELECT COUNT(i) AS count FROM Issue i WHERE i.state= 'closed' AND NOT TYPE(i)=PullRequest AND i.repositoryId = :repoId GROUP BY i.closedBy.userId) i")
    Integer getMaxFixedIssuesSingleUser(@Param("repoId") Long repoId);
}

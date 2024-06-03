package Default.Issue;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
Repository for Issues identified by ID (long)
 */
public interface IssueRepository extends JpaRepository<Issue, Integer>{
    @Query("SELECT COUNT(i) FROM Issue i")
    Integer getAllIssuesTeam();
    @Query("SELECT COUNT(i) FROM Issue i WHERE i.state= 'open'")
    Integer getOpenIssuesTeam();
    @Query("SELECT COUNT(i) FROM Issue i WHERE i.state= 'closed'")
    Integer getFixedIssuesTeam();
    @Query("SELECT COUNT(i) FROM Issue i WHERE i.closedBy.id = :userId")
    Integer getTotalClosedIssuesUser(@Param("userId") Long userId);
    @Query("SELECT DATE_TRUNC('WEEK', i.dateOpened) AS week, COUNT(i) AS issueCount " +
        "FROM Issue i " +
        "WHERE (i.state = 'closed' AND NOT TYPE(i)=PullRequest)"+
        "GROUP BY DATE_TRUNC('WEEK', i.dateOpened) ")
    List<Object[]> findWeeklyClosedIssues();

    @Query("SELECT i FROM Issue i WHERE i.state= 'closed' ORDER BY i.dateClosed DESC")
    List<Issue> findLastFiveClosedIssues(Pageable pageable);
}

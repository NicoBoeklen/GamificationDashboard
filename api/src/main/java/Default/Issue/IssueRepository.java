package Default.Issue;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

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
}
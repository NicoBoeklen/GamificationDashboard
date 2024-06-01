package Default.Issue;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
Repository for Issues identified by ID (long)
 */
public interface IssueRepository extends JpaRepository<Issue, Integer>{
    @Query("SELECT COUNT(i) FROM Issue i")
    Integer getAllIssues();
    @Query("SELECT COUNT(i) FROM Issue i WHERE i.state= 'closed'")
    Integer getOpenIssues();
    @Query("SELECT COUNT(i) FROM Issue i WHERE i.state= 'closed'")
    Integer getFixedIssues();
}

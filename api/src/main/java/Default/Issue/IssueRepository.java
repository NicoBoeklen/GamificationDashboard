package Default.Issue;

import org.springframework.data.jpa.repository.JpaRepository;

/**
Repository for Issues identified by ID (long)
 */
public interface IssueRepository extends JpaRepository<Issue, Integer>{
}

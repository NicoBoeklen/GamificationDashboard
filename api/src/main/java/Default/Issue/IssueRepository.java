package Default.Issue;

import Default.Commit.Commit;
import org.springframework.data.jpa.repository.JpaRepository;

/**
Repository for Commits identified by ID/Sha (long)
 */
public interface IssueRepository extends JpaRepository<Issue, Long>{
}

package Default.PullRequest;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 Repository for PullRequests identified by ID (long)
 */
public interface PullRequestRepository extends JpaRepository<PullRequest, Long> {
}

package Default.PullRequest;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 Repository for PullRequests identified by ID (long)
 */
public interface PullRequestRepository extends JpaRepository<PullRequest, Long> {
    @Query("SELECT COUNT(p) FROM PullRequest p WHERE p.closedBy = :userId")
    Integer getNumberReviews(@Param("userId") Long userId);

    @Query("SELECT p FROM PullRequest p WHERE p.closedBy = :userId AND p.state= 'closed' ORDER BY p.dateClosed DESC")
    List<PullRequest> findLastFivePullRequestsByUser(@Param("userId") Long userId, Pageable pageable);
}

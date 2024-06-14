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
    @Query("SELECT COUNT(p) FROM PullRequest p WHERE p.closedBy.userId = :userId AND p.openedBy.repoId = :repoId")
    Integer getNumberReviews(@Param("userId") Long userId, @Param("repoId") Long repoId);

    @Query("SELECT p FROM PullRequest p WHERE p.closedBy.userId = :userId AND p.state= 'closed' AND p.openedBy.repoId = :repoId ORDER BY p.dateClosed DESC")
    List<PullRequest> findLastFivePullRequestsByUser(@Param("userId") Long userId, Pageable pageable, @Param("repoId") Long repoId);

    @Query("SELECT p FROM PullRequest p WHERE p.state= 'closed' AND p.openedBy.repoId = :repoId ORDER BY p.dateClosed DESC")
    List<PullRequest> findLastFivePullRequests(Pageable pageable, @Param("repoId") Long repoId);

    @Query("SELECT p FROM PullRequest p WHERE p.state= 'open' AND p.openedBy.repoId = :repoId ORDER BY p.dateClosed DESC")
    List<PullRequest> findLastFiveOpenPullRequests(Pageable pageable, @Param("repoId") Long repoId);

    @Query("SELECT COUNT(p) FROM PullRequest p WHERE p.state= 'open' AND p.openedBy.repoId = :repoId")
    Integer getOpenPullRequests(@Param("repoId") Long repoId);

    @Query("SELECT COUNT(p) FROM PullRequest p WHERE p.state= 'closed' AND p.dateClosed >= CURRENT_DATE - 30 AND p.openedBy.repoId = :repoId")
    Integer getClosedPullRequestsLastMonth(@Param("repoId") Long repoId);

    @Query("SELECT MAX(p.count) FROM (SELECT COUNT(p) AS count FROM PullRequest p WHERE p.state= 'closed' AND p.closedBy.repoId = :repoId GROUP BY p.closedBy.userId) p")
    Double getMaxReviewsSingleUser(Long repoId);
}

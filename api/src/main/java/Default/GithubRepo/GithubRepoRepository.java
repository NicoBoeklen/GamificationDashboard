package Default.GithubRepo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Repository for GitHub-Repository identified by ID (long)
 */
public interface GithubRepoRepository extends JpaRepository<GithubRepo, Long> {
    @Query("SELECT r FROM GithubRepo r WHERE r.owner.repoId=:repoId AND r.owner.userId=:userId")
    GithubRepo findByRepoIdUserId(@Param("userId") Long userId, @Param("repoId") Long repoId);
}


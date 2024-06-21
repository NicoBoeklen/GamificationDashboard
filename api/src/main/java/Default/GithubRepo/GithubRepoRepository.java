package Default.GithubRepo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Repository for GitHub-Repository identified by ID (long)
 */
public interface GithubRepoRepository extends JpaRepository<GithubRepo, Long> {

    @Query("SELECT r FROM GithubRepo r WHERE r.owner.repoId=:repoId")
    GithubRepo findByRepoIdUserId(@Param("repoId") Long repoId);
}


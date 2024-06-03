package Default.GithubRepo;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository for GitHub-Repository identified by ID (long)
 */
public interface GithubRepoRepository extends JpaRepository<GithubRepo, Long> {
}


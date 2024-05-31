package Default.Release;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository for Users identified by ID (long)
 */
public interface ReleaseRepository extends JpaRepository<Release, Long> {
}

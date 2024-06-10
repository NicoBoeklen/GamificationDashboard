package Default.Release;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Repository for Users identified by ID (long)
 */
public interface ReleaseRepository extends JpaRepository<Release, Long> {
    
    @Query("SELECT Count(r) FROM Release r")
    Integer getNumberOfRelease();

    @Query("SELECT r FROM Release r ORDER BY r.publishedAt DESC")
    List<Release> findLastFiveReleases(Pageable pageable);
}

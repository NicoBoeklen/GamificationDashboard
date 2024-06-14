package Default.Release;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Repository for Users identified by ID (long)
 */
public interface ReleaseRepository extends JpaRepository<Release, Long> {
    
    @Query("SELECT Count(r) FROM Release r WHERE r.repo.id= :repoId")
    Integer getNumberOfRelease(@Param("repoId") Long repoId);

    @Query("SELECT r FROM Release r WHERE r.repo.id= :repoId ORDER BY r.publishedAt DESC")
    List<Release> findLastFiveReleases(Pageable pageable, @Param("repoId") Long repoId);
}

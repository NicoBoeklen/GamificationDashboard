package Default.Commit;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
Repository for Commits identified by ID/Sha (long)
 */
public interface CommitRepository extends JpaRepository<Commit, Long>{

    @Query("SELECT COUNT(c) FROM Commit c WHERE c.author.id = :userId AND c.isMerge = false")
    Integer getAllCommitsBy(@Param("userId") Long userId);
}

package Default.Commit;

import Default.Commit.Stats.CodeGrowth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
Repository for Commits identified by ID/Sha (long)
 */
public interface CommitRepository extends JpaRepository<Commit, Long>{

    @Query("SELECT COUNT(c) FROM Commit c WHERE c.author.id = :userId AND c.isMerge = false")
    Integer getAllCommitsBy(@Param("userId") Long userId);

    @Query("SELECT SUM(c.deletions) FROM Commit c WHERE c.author.id = :userId AND c.isMerge = false")
    Integer getAllDeletionsBy(@Param("userId") Long userId);

    @Query("SELECT SUM(c.additions) FROM Commit c WHERE c.author.id = :userId AND c.isMerge = false")
    Integer getAllAdditionsBy(@Param("userId") Long userId);
    
    @Query("SELECT new Default.Commit.Stats.CodeGrowth(DATE_TRUNC('week', c.date), (SUM(c.additions) - SUM(c.deletions))) " +
        "FROM Commit c " +
        "GROUP BY DATE_TRUNC('week', c.date) " +
        "ORDER BY DATE_TRUNC('week', c.date)")
    List<CodeGrowth> getCodeGrowth();
}

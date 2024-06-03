package Default.Commit;

import Default.Commit.Stats.CodeGrowth;
import Default.Commit.Stats.CommitsUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import org.springframework.data.domain.Pageable;
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
        "WHERE c.isMerge = false " +
        "GROUP BY DATE_TRUNC('week', c.date) " +
        "ORDER BY DATE_TRUNC('week', c.date) ")
    List<CodeGrowth> getCodeGrowth();
    
    @Query("SELECT SUM(c.additions) - SUM(c.deletions) FROM Commit c WHERE c.isMerge = false")
    Long getTotalLoC();

    @Query("SELECT c FROM Commit c WHERE c.author.id = :userId AND c.isMerge = false ORDER BY c.date DESC")
    List<Commit> findLastFiveCommitsByUser(@Param("userId") Long userId, Pageable pageable);

    @Query("SELECT new Default.Commit.Stats.CommitsUser(DATE_TRUNC('week', c.date), COUNT(c)) " +
        "FROM Commit c " +
        "WHERE c.isMerge = false AND c.author.id = :userId " +
        "GROUP BY DATE_TRUNC('week', c.date) " +
        "ORDER BY DATE_TRUNC('week', c.date) ")
    List<CommitsUser> getCommitsUser(@Param("userId") Long userId);

    @Query("SELECT DATE_TRUNC('day', c.date) AS day, (SUM(c.additions) + SUM(c.deletions)) AS productivity " +
        "FROM Commit c " +
        "WHERE c.author.id = :userId " +
        "GROUP BY DATE_TRUNC('day', c.date) " +
        "ORDER BY DATE_TRUNC('day', c.date) desc")
    List<Object[]> getUserProductivity(@Param("userId") Long userId);
}

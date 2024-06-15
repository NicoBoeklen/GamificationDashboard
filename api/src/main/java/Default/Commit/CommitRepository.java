package Default.Commit;

import Default.Commit.Stats.CodeGrowth;
import Default.Commit.Stats.CommitsUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.time.LocalDateTime;

import org.springframework.data.domain.Pageable;
import java.util.List;


/**
Repository for Commits identified by ID/Sha (long)
 */
public interface CommitRepository extends JpaRepository<Commit, Long>{

    @Query("SELECT COUNT(c) FROM Commit c WHERE c.author.userId = :userId AND c.isMerge = false AND c.author.repoId = :repoId")
    Integer getAllCommitsBy(@Param("userId") Long userId, @Param("repoId") Long repoId);

    @Query("SELECT SUM(c.deletions) FROM Commit c WHERE c.author.userId = :userId AND c.isMerge = false AND c.author.repoId = :repoId")
    Integer getAllDeletionsBy(@Param("userId") Long userId, @Param("repoId") Long repoId);

    @Query("SELECT SUM(c.additions) FROM Commit c WHERE c.author.userId = :userId AND c.isMerge = false AND c.author.repoId = :repoId")
    Integer getAllAdditionsBy(@Param("userId") Long userId, @Param("repoId") Long repoId);
    
    @Query("SELECT new Default.Commit.Stats.CodeGrowth(DATE_TRUNC('week', c.date), (SUM(c.additions) - SUM(c.deletions))) " +
        "FROM Commit c " +
        "WHERE c.isMerge = false AND c.author.repoId = :repoId " +
        "GROUP BY DATE_TRUNC('week', c.date) " +
        "ORDER BY DATE_TRUNC('week', c.date) ")
    List<CodeGrowth> getCodeGrowth(@Param("repoId") Long repoId);
    
    @Query("SELECT SUM(c.additions - c.deletions) " +
        "FROM Commit c " +
        "WHERE c.isMerge = false AND c.date<= :date AND c.author.repoId = :repoId ")
    Long getLoCTillDate(@Param("date") LocalDateTime date, @Param("repoId") Long repoId);
    
    @Query("SELECT SUM(c.additions) - SUM(c.deletions) FROM Commit c WHERE c.isMerge = false AND c.author.repoId = :repoId")
    Long getTotalLoC(@Param("repoId") Long repoId);

    @Query("SELECT SUM(c.additions)FROM Commit c WHERE c.isMerge = false AND c.author.repoId = :repoId")
    Long getTotalLoCAdded(@Param("repoId") Long repoId);

    @Query("SELECT SUM(c.deletions) FROM Commit c WHERE c.isMerge = false AND c.author.repoId = :repoId")
    Long getTotalLoCDeleted(@Param("repoId") Long repoId);

    @Query("SELECT count(c) FROM Commit c WHERE c.isMerge = false AND c.author.repoId = :repoId")
    Integer getTotalCommitCount(@Param("repoId") Long repoId);

    @Query("SELECT c FROM Commit c WHERE c.author.userId = :userId AND c.isMerge = false AND c.author.repoId = :repoId ORDER BY c.date DESC")
    List<Commit> findLastFiveCommitsByUser(@Param("userId") Long userId, Pageable pageable, @Param("repoId") Long repoId);

    @Query("SELECT new Default.Commit.Stats.CommitsUser(DATE_TRUNC('week', c.date), COUNT(c)) " +
        "FROM Commit c " +
        "WHERE c.isMerge = false AND c.author.userId = :userId AND c.author.repoId = :repoId " +
        "GROUP BY DATE_TRUNC('week', c.date) " +
        "ORDER BY DATE_TRUNC('week', c.date) ")
    List<CommitsUser> getCommitsUser(@Param("userId") Long userId, @Param("repoId") Long repoId);

    @Query("SELECT DATE_TRUNC('day', c.date) AS day, (SUM(c.additions) + SUM(c.deletions)) AS productivity " +
        "FROM Commit c " +
        "WHERE c.author.userId = :userId AND c.author.repoId = :repoId " +
        "GROUP BY DATE_TRUNC('day', c.date) " +
        "ORDER BY DATE_TRUNC('day', c.date) desc")
    List<Object[]> getUserProductivity(@Param("userId") Long userId, @Param("repoId") Long repoId);

    @Query("SELECT MAX(c.count) FROM (SELECT COUNT(c) AS count FROM Commit c WHERE c.isMerge = false AND c.author.repoId = :repoId GROUP BY c.author.userId) c")
    Integer getMaxCommitsSingleUser(@Param("repoId") Long repoId);
}

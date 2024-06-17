package Default.Commit;

import Default.Commit.Stats.CodeGrowth;
import Default.Commit.Stats.CommitsUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.data.domain.Pageable;
import java.util.List;


/**
Repository for Commits identified by ID/Sha (long)
 */
public interface CommitRepository extends JpaRepository<Commit, Long>{

    @Query("SELECT COUNT(c) FROM Commit c WHERE c.author.userId = :userId AND c.isMerge = false AND c.author.repoId = :repoId")
    Integer getAllCommitsBy(@Param("userId") Long userId, @Param("repoId") Long repoId);

    @Query("SELECT COALESCE(SUM(c.deletions),0) FROM Commit c WHERE c.author.userId = :userId AND c.isMerge = false AND c.author.repoId = :repoId")
    Integer getAllDeletionsBy(@Param("userId") Long userId, @Param("repoId") Long repoId);

    @Query("SELECT COALESCE(SUM(c.additions),0) FROM Commit c WHERE c.author.userId = :userId AND c.isMerge = false AND c.author.repoId = :repoId")
    Integer getAllAdditionsBy(@Param("userId") Long userId, @Param("repoId") Long repoId);
    
    @Query("SELECT COALESCE(SUM(c.additions), 0) FROM Commit c WHERE c.author.userId = :userId AND c.isMerge = false AND c.author.repoId = :repoId AND CAST(c.date AS DATE) = :day")
    Integer getAllAdditionsByUserByDay(@Param("userId") Long userId, @Param("repoId") Long repoId, @Param("day") LocalDate day);
    
    @Query("SELECT new Default.Commit.Stats.CodeGrowth(CAST(DATE_TRUNC('week', c.date) AS LocalDate), (SUM(c.additions) - SUM(c.deletions))) " +
        "FROM Commit c " +
        "WHERE c.isMerge = false AND c.author.repoId = :repoId " +
        "GROUP BY CAST(DATE_TRUNC('week', c.date) AS LocalDate)" +
        "ORDER BY CAST(DATE_TRUNC('week', c.date) AS LocalDate)")
    List<CodeGrowth> getCodeGrowth(@Param("repoId") Long repoId);
    
    @Query("SELECT COALESCE(SUM(c.additions - c.deletions),0) " +
        "FROM Commit c " +
        "WHERE c.isMerge = false AND c.date<= :date AND c.author.repoId = :repoId ")
    Long getLoCTillDate(@Param("date") LocalDateTime date, @Param("repoId") Long repoId);
    
    @Query("SELECT COALESCE(SUM(c.additions) - SUM(c.deletions),0) FROM Commit c WHERE c.isMerge = false AND c.author.repoId = :repoId")
    Long getTotalLoC(@Param("repoId") Long repoId);

    @Query("SELECT COALESCE(SUM(c.additions), 0) FROM Commit c WHERE c.isMerge = false AND c.author.repoId = :repoId")
    Long getTotalLoCAdded(@Param("repoId") Long repoId);
    
    @Query("SELECT COALESCE(SUM(c.additions), 0) FROM Commit c WHERE c.isMerge = false AND c.author.repoId = :repoId AND CAST(c.date AS DATE) = :day")
    Long getTotalLoCAddedByDay(@Param("repoId") Long repoId, @Param("day") LocalDate day);

    @Query("SELECT COALESCE(SUM(c.deletions), 0) FROM Commit c WHERE c.isMerge = false AND c.author.repoId = :repoId")
    Long getTotalLoCDeleted(@Param("repoId") Long repoId);

    @Query("SELECT count(c) FROM Commit c WHERE c.isMerge = false AND c.author.repoId = :repoId")
    Integer getTotalCommitCount(@Param("repoId") Long repoId);

    @Query("SELECT count(c) FROM Commit c WHERE c.isMerge = false AND c.author.repoId = :repoId AND CAST(c.date AS DATE) = :day")
    Integer getTotalCommitCountByDay(@Param("repoId") Long repoId, @Param("day") LocalDate day);

    @Query("SELECT c FROM Commit c WHERE c.author.userId = :userId AND c.isMerge = false AND c.author.repoId = :repoId ORDER BY c.date DESC")
    List<Commit> findLastFiveCommitsByUser(@Param("userId") Long userId, Pageable pageable, @Param("repoId") Long repoId);

    @Query("SELECT new Default.Commit.Stats.CommitsUser(CAST(DATE_TRUNC('week', c.date) AS LocalDate), COUNT(c)) " +
        "FROM Commit c " +
        "WHERE c.isMerge = false AND c.author.userId = :userId AND c.author.repoId = :repoId " +
        "GROUP BY CAST(DATE_TRUNC('week', c.date) AS LocalDate)" +
        "ORDER BY CAST(DATE_TRUNC('week', c.date) As LocalDate)")
    List<CommitsUser> getCommitsUser(@Param("userId") Long userId, @Param("repoId") Long repoId);

    @Query("SELECT COUNT(c) " +
        "FROM Commit c " +
        "WHERE c.isMerge = false AND c.author.userId = :userId AND c.author.repoId = :repoId AND CAST(c.date AS DATE) = :day")
    Long getCommitsUserByDay(@Param("userId")Long userId, @Param("repoId")Long repoId, @Param("day") LocalDate day);
    
    @Query("SELECT DATE_TRUNC('day', c.date) AS day, (SUM(c.additions) + SUM(c.deletions)) AS productivity " +
        "FROM Commit c " +
        "WHERE c.author.userId = :userId AND c.author.repoId = :repoId " +
        "GROUP BY DATE_TRUNC('day', c.date) " +
        "ORDER BY DATE_TRUNC('day', c.date) desc")
    List<Object[]> getUserProductivity(@Param("userId") Long userId, @Param("repoId") Long repoId);

    @Query("SELECT COALESCE(MAX(c.count),0) FROM (SELECT COUNT(c) AS count FROM Commit c WHERE c.isMerge = false AND c.author.repoId = :repoId GROUP BY c.author.userId) c")
    Integer getMaxCommitsSingleUser(@Param("repoId") Long repoId);
}

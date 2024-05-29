package Default.Commit;

import org.springframework.data.jpa.repository.JpaRepository;

/**
Repository for Commits identified by ID/Sha (long)
 */
public interface CommitRepository extends JpaRepository<Commit, Long>{
}

package Default.User;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository for Users identified by ID (long)
 */
public interface UserRepository extends JpaRepository<User, Long> {
}

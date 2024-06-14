package Default.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Repository for Users identified by ID (long)
 */
public interface UserRepository extends JpaRepository<User, UserRepoId> {
    @Query("SELECT u.avatarUrl FROM User u WHERE u.name = :username")
    String getUserAvatar(@Param("username") String username);
}

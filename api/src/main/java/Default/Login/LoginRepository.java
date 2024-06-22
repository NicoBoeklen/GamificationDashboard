package Default.Login;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LoginRepository extends JpaRepository<Login, Long> {
    @Query("SELECT l from Login l where l.repoId = :repoId AND l.userId")
    Login getLoggedUser(@Param("repoId") Long repoId, @Param("repoId") Long userId);
    
    void deleteAll();
}

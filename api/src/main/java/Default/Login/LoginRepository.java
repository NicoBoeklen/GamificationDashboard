package Default.Login;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface LoginRepository extends JpaRepository<Login, Long> {
    @Query("SELECT l from Login l where l.id = :sessionId")
    Login getLoggedUser(@Param("sessionId") Long sessionId);
    @Query("SELECT l.apiKey from Login l where l.id= :sessionId")
    String getApiKeyForLoggedUser(@Param("sessionId") Long sessionId);
    void deleteAll();
}

package Default.Gamification.Quest;

import Default.User.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserQuestRepository extends JpaRepository<UserQuest, Long> {
    
    @Query("SELECT CASE WHEN COUNT(ua) > 0 THEN TRUE ELSE FALSE END FROM UserQuest ua WHERE ua.user = :user AND ua.quest = :quest")
    boolean existsByUserAndQuest(@Param("user") User user, @Param("quest") Quest quest);
}

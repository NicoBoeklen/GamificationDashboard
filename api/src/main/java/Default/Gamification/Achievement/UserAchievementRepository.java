package Default.Gamification.Achievement;

import Default.User.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserAchievementRepository extends JpaRepository<UserAchievement, Long> {
    @Query("SELECT CASE WHEN COUNT(ua) > 0 THEN TRUE ELSE FALSE END FROM UserAchievement ua WHERE ua.user = :user AND ua.achievement = :achievement")
    boolean existsByUserAndAchievement(@Param("user") User user, @Param("achievement") Achievement achievement);
}

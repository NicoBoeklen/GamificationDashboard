package Default.Gamification.Achievement;

import Default.User.User;
import Default.User.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
public class AchievementController {

    @Autowired
    private UserService userService;

    @Autowired
    private AchievementService achievementService;

    @GetMapping("/milestones/{userId}/{repoId}")
    public List<UserMilestone> getUserMilestones(@PathVariable Long userId, @PathVariable Long repoId) {
        try {
            setAchievements();
            User user = userService.findById(userId, repoId).orElseThrow(NoSuchElementException::new);
            List<UserMilestone> milestoneAchievement = achievementService.checkAndAwardAchievements(user);
            return milestoneAchievement;
        } catch (NoSuchElementException e) {
            return new ArrayList<>();
        }
    }

    @GetMapping("/achievements/{userId}/{repoId}")
    public List<UserAchievement> getUserAchievements(@PathVariable Long userId, @PathVariable Long repoId) {
        try {
            setAchievements();
            User user = userService.findById(userId, repoId).orElseThrow();
            achievementService.checkAndAwardAchievements(user);
            return achievementService.getAchievements(user);
        } catch (Error e) {
            return new ArrayList<>();
        }
    }
    
    private ResponseEntity<?> setAchievements() {
        achievementService.setAchievements();
        return ResponseEntity.ok("Achievements created");
    }


}

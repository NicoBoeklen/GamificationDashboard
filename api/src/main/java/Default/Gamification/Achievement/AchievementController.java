package Default.Gamification.Achievement;

import Default.User.User;
import Default.User.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AchievementController {

    @Autowired
    private UserService userService;

    @Autowired
    private AchievementService achievementService;

    @GetMapping("/achievements/{userId}/{repoId}")
    public ResponseEntity<?> getUserAchievements(@PathVariable Long userId, @PathVariable Long repoId) {
        try {
            User user = userService.findById(userId, repoId).orElseThrow();
            achievementService.checkAndAwardAchievements(user);
            return ResponseEntity.ok("Achievements saved" + achievementService.getAchievements(user));
        } catch (Error e) {
            return ResponseEntity.status(404).body("Error occurred: " + e.getMessage());
        }
    }

    @GetMapping("/setAchievements")
    public ResponseEntity<?> setAchievements() {
        achievementService.setAchievements();
        return ResponseEntity.ok("Achievements created");
    }


}

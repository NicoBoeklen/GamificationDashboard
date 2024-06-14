package Default.Gamification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GamificationController {
    
     @Autowired
    private GamificationService gamificationService;
     
     @GetMapping("/getLeaderboard/{userId}/{repoId}")
    public Leaderboard getLeaderboard(@PathVariable Long userId, @PathVariable Long repoId) {
         return null;
     }

    @GetMapping("/getSkills/{userId}/{repoId}")
    public Skill getSkills(@PathVariable Long userId, @PathVariable Long repoId) {
        return gamificationService.getSkills(userId, repoId);
    }
}

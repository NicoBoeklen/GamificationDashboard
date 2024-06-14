package Default.Gamification.Achievement;

import Default.Commit.CommitService;
import Default.User.User;
import Default.User.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AchievementService {

    @Autowired
    private AchievementRepository achievementRepository;

    @Autowired
    private UserAchievementRepository userAchievementRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private CommitService commitService;
    
    public void checkAndAwardAchievements(User user) {
        // Hier alle Achievements durchgehen und prüfen, ob der User die Bedingungen erfüllt
        List<Achievement> achievements = achievementRepository.findAll();

        for (Achievement achievement : achievements) {
            switch (achievement.getType()) {
                case "commits":
                    int userCommits = commitService.getCommitsUser(user.getUserId(), user.getRepoId()).size();
                    if (userCommits >= achievement.getCondition()) {
                        awardAchievement(user, achievement);
                    }
                    break;
                // Weitere Fälle für andere Typen wie "debugging", "team_collaboration" etc.
            }
        }
    }

    private void awardAchievement(User user, Achievement achievement) {
        boolean alreadyAwarded = userAchievementRepository.existsByUserAndAchievement(user, achievement);
        if (!alreadyAwarded) {
            userAchievementRepository.save(new UserAchievement(user, achievement));
            user.setLevel(user.getLevel() + achievement.getXp());
            userService.saveUser2(user);
        }
    }
}


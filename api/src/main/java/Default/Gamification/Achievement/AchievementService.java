package Default.Gamification.Achievement;

import Default.Commit.CommitService;
import Default.Issue.IssueService;
import Default.PullRequest.PullRequestService;
import Default.User.User;
import Default.User.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    private IssueService issueService;

    @Autowired
    private CommitService commitService;
    
    @Autowired
    private PullRequestService pullRequestService;
    
    public void checkAndAwardAchievements(User user) {
        // Hier alle Achievements durchgehen und prüfen, ob der User die Bedingungen erfüllt
        List<Achievement> achievements = achievementRepository.findAll();

        for (Achievement achievement : achievements) {
            switch (achievement.getType()) {
                case "commits":
                    Long userCommits = commitService.getCommitsUser(user.getUserId(), user.getRepoId()).stream().mapToLong(u -> u.getTotalCommits()).sum();
                    if (userCommits >= achievement.getCondition()) {
                        awardAchievement(user, achievement);
                    }
                    break;
                case "issues":
                    int userIssues = issueService.getTotalClosedIssuesUser(user.getUserId(), user.getRepoId());
                    if (userIssues >= achievement.getCondition()) {
                        awardAchievement(user, achievement);
                    }
                    break;
                case "linesOfCodeAdded":
                    int userLoC = commitService.getAdditionCount(user.getUserId(), user.getRepoId());
                    if (userLoC >= achievement.getCondition()) {
                        awardAchievement(user, achievement);
                    }
                    break;
                case "linesOfCodeDeleted":
                    int userLoCDeleted = commitService.getDeletionCount(user.getUserId(), user.getRepoId());
                    if (userLoCDeleted >= achievement.getCondition()) {
                        awardAchievement(user, achievement);
                    }
                    break;
                case "pullRequests":
                    int userReviews = pullRequestService.getNumberReviews(user.getUserId(), user.getRepoId());
                    if (userReviews >= achievement.getCondition()) {
                        awardAchievement(user, achievement);
                    }
                    break;
                case "pullRequestsTeam":
                    int teamReviews = pullRequestService.getTeamReviews(user.getRepoId());
                    if (teamReviews >= achievement.getCondition()) {
                        awardAchievement(user, achievement);
                    }
                    break;
                case "commitsTeam":
                    int teamCommits = commitService.getTotalCommits(user.getRepoId());
                    if (teamCommits >= achievement.getCondition()) {
                        awardAchievement(user, achievement);
                    }
                    break;
                case "issuesTeam":
                    int teamIssues = issueService.getFixedIssuesTeam(user.getRepoId());
                    if (teamIssues >= achievement.getCondition()) {
                        awardAchievement(user, achievement);
                    }
                    break;
                case "linesOfCodeAddedTeam":
                    Long teamLoC = commitService.getTotalLoCAdded(user.getRepoId());
                    if (teamLoC >= achievement.getCondition()) {
                        awardAchievement(user, achievement);
                    }
                    break;
                case "linesOfCodeDeletedTeam":
                    Long teamLoCDeleted = commitService.getTotalLoCDeleted(user.getRepoId());
                    if (teamLoCDeleted >= achievement.getCondition()) {
                        awardAchievement(user, achievement);
                    }
                    break;
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
    
    public List<UserAchievement> getAchievements(User user) {
        return userAchievementRepository.findAll().stream().filter(achievement -> achievement.getUser().equals(user)).toList();
    }

    public void setAchievements() {
        if (achievementRepository.findAll().size() == 0) {
            List<Achievement> achievements = new ArrayList<>();
            //Commits
            achievements.add(new Achievement("First Commit", "Get 1 Commit.", 10, "commits", 1));
            achievements.add(new Achievement("100 Commits", "Get 100 Commit.", 50, "commits", 100));
            achievements.add(new Achievement("500 Commits", "Get 500 Commit.", 300, "commits", 500));
            achievements.add(new Achievement("1000 Commits", "Get 1000 Commit.", 500, "commits", 1000));
            //Issues
            achievements.add(new Achievement("Debugging", "Fix 1 Issue.", 10, "issues", 1));
            achievements.add(new Achievement("Debugging Amateur", "Fix 5 Issue.", 50, "issues", 5));
            achievements.add(new Achievement("Debugging Apprentice", "Fix 10 Issue.", 100, "issues", 10));
            achievements.add(new Achievement("Debugging Pro", "Fix 15 Issue.", 100, "issues", 15));
            achievements.add(new Achievement("Debugging Master", "Fix 20 Issue.", 200, "issues", 20));
            //Reviews
            achievements.add(new Achievement("Code Reviewer", "Review 20 Pull Requests.", 500, "pullRequests", 20));
            //LoC Deleted
            achievements.add(new Achievement("Refactoring Hero", "Delete 500 Lines of Code.", 100, "linesOfCodeDeleted", 500));
            //LoC Added
            achievements.add(new Achievement("Code Marathoner Bronze", "Write 100 Lines of Code.", 100, "linesOfCodeAdded", 100));
            achievements.add(new Achievement("Code Marathoner Silver", "Write 500 Lines of Code.", 300, "linesOfCodeAdded", 500));
            achievements.add(new Achievement("Code Marathoner Gold", "Write 1000 Lines of Code.", 1000, "linesOfCodeAdded", 1000));

            //Team-Achievements
            //Commits
            achievements.add(new Achievement("1000 Team Commits", "Get 1000 Commit in your team.", 500, "commitsTeam", 1000));
            achievements.add(new Achievement("5000 Team Commits", "Get 5000 Commit in your team.", 1000, "commitsTeam", 5000));
            //Issues
            achievements.add(new Achievement("Debugging Team Amateur", "Fix 50 Issues in team.", 200, "issuesTeam", 50));
            achievements.add(new Achievement("Debugging Team Apprentice", "Fix 100 Issues in team.", 400, "issuesTeam", 100));
            achievements.add(new Achievement("Debugging Team Master", "Fix 200 Issues in team.", 800, "issuesTeam", 200));
            //Reviews
            achievements.add(new Achievement("Peer Review Experts", "Review 100 Pull Requests in team.", 1000, "pullRequestsTeam", 100));
            //LoC Deleted
            achievements.add(new Achievement("Refactoring Team Hero", "Delete 2000 Lines of Code in team.", 300, "linesOfCodeDeletedTeam", 2000));
            //LoC Added
            achievements.add(new Achievement("Team Code Marathoners Bronze", "Write 100 Lines of Code in team.", 300, "linesOfCodeAddedTeam", 1000));
            achievements.add(new Achievement("Team Code Marathoners Silver", "Write 500 Lines of Code in team.", 600, "linesOfCodeAddedTeam", 500));
            achievements.add(new Achievement("Team Code Marathoners Gold", "Write 1000 Lines of Code in team.", 1500, "linesOfCodeAddedTeam", 10000));

            achievementRepository.saveAll(achievements);
        }
    }
}


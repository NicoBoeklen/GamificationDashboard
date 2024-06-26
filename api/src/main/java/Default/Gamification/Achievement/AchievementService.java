package Default.Gamification.Achievement;

import Default.Commit.CommitService;
import Default.Gamification.Quest.UserQuestRepository;
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
    private UserQuestRepository userQuestRepository;

    @Autowired
    private UserService userService;
    
    @Autowired
    private IssueService issueService;

    @Autowired
    private CommitService commitService;
    
    @Autowired
    private PullRequestService pullRequestService;
    
    public List<UserMilestone> checkAndAwardAchievements(User user) {
        // Hier alle Achievements durchgehen und prüfen, ob der User die Bedingungen erfüllt
        List<Achievement> achievements = achievementRepository.findAll();
        List<UserMilestone> milestones = new ArrayList<>();
        
        for (Achievement achievement : achievements) {
            switch (achievement.getType()) {
                case "commits":
                    Long userCommits = commitService.getCommitsUser(user.getUserId(), user.getRepoId()).stream().mapToLong(u -> u.getTotalCommits()).sum();
                    if (userCommits >= achievement.getCondition()) {
                        awardAchievement(user, achievement);
                    }
                    milestones.add(new UserMilestone(user, achievement, userCommits));
                    break;
                case "issues":
                    int userIssues = issueService.getTotalClosedIssuesUser(user.getUserId(), user.getRepoId());
                    if (userIssues >= achievement.getCondition()) {
                        awardAchievement(user, achievement);
                    }
                    milestones.add(new UserMilestone(user, achievement, (long) userIssues));
                    break;
                case "linesOfCodeAdded":
                    int userLoC = commitService.getAdditionCount(user.getUserId(), user.getRepoId());
                    if (userLoC >= achievement.getCondition()) {
                        awardAchievement(user, achievement);
                    }
                    milestones.add(new UserMilestone(user, achievement, (long) userLoC));
                    break;
                case "linesOfCodeDeleted":
                    int userLoCDeleted = commitService.getDeletionCount(user.getUserId(), user.getRepoId());
                    if (userLoCDeleted >= achievement.getCondition()) {
                        awardAchievement(user, achievement);
                    }
                    milestones.add(new UserMilestone(user, achievement, (long) userLoCDeleted));
                    break;
                case "pullRequests":
                    int userReviews = pullRequestService.getNumberReviews(user.getUserId(), user.getRepoId());
                    if (userReviews >= achievement.getCondition()) {
                        awardAchievement(user, achievement);
                    }
                    milestones.add(new UserMilestone(user, achievement, (long) userReviews));
                    break;
                case "pullRequestsTeam":
                    int teamReviews = pullRequestService.getTeamReviews(user.getRepoId());
                    if (teamReviews >= achievement.getCondition()) {
                        awardAchievement(user, achievement);
                    }
                    milestones.add(new UserMilestone(user, achievement, (long) teamReviews));
                    break;
                case "commitsTeam":
                    int teamCommits = commitService.getTotalCommits(user.getRepoId());
                    if (teamCommits >= achievement.getCondition()) {
                        awardAchievement(user, achievement);
                    }
                    milestones.add(new UserMilestone(user, achievement, (long) teamCommits));
                    break;
                case "issuesTeam":
                    int teamIssues = issueService.getFixedIssuesTeam(user.getRepoId());
                    if (teamIssues >= achievement.getCondition()) {
                        awardAchievement(user, achievement);
                    }
                    milestones.add(new UserMilestone(user, achievement, (long) teamIssues));
                    break;
                case "linesOfCodeAddedTeam":
                    Long teamLoC = commitService.getTotalLoCAdded(user.getRepoId());
                    if (teamLoC >= achievement.getCondition()) {
                        awardAchievement(user, achievement);
                    }
                    milestones.add(new UserMilestone(user, achievement, teamLoC));
                    break;
                case "linesOfCodeDeletedTeam":
                    Long teamLoCDeleted = commitService.getTotalLoCDeleted(user.getRepoId());
                    if (teamLoCDeleted >= achievement.getCondition()) {
                        awardAchievement(user, achievement);
                    }
                    milestones.add(new UserMilestone(user, achievement, teamLoCDeleted));
                    break;
            }
        }
        user.setLevel(userQuestRepository.findAll().stream().filter(q -> q.getUser().equals(user)).mapToInt(q -> q.getQuest().getXp()).sum()
            + userAchievementRepository.findAll().stream().filter(q -> q.getUser().equals(user)).mapToInt(q -> q.getAchievement().getXp()).sum());
        userService.saveUser2(user);
        return milestones;
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
            achievements.add(new Achievement("First Commit", "Get 1 Commit.", 10, "commits", 1, "FirstCommit.png"));
            achievements.add(new Achievement("10 Commits", "Get 10 Commits.", 50, "commits", 10, "10Commits.png"));
            achievements.add(new Achievement("50 Commits", "Get 50 Commits.", 300, "commits", 50, "50Commits.png"));
            achievements.add(new Achievement("100 Commits", "Get 100 Commits.", 500, "commits", 100, "100Commits.png"));
            //Issues 
            achievements.add(new Achievement("Debugging", "Fix 1 Issue.", 10, "issues", 1, "1FixDebugg.png"));
            achievements.add(new Achievement("Debugging Amateur", "Fix 5 Issues.", 50, "issues", 5, "5Issue1.png"));
            achievements.add(new Achievement("Debugging Apprentice", "Fix 10 Issues.", 100, "issues", 10, "10Issues.png"));
            achievements.add(new Achievement("Debugging Pro", "Fix 15 Issues.", 100, "issues", 15, "15Issues.png"));
            achievements.add(new Achievement("Debugging Master", "Fix 20 Issues.", 200, "issues", 20, "DebuggMaster.png"));
            //Reviews
            achievements.add(new Achievement("Code Reviewer", "Review 10 Pull Requests.", 500, "pullRequests", 10, "CodeReviewer.png"));
            //LoC Deleted
            achievements.add(new Achievement("Refactoring Hero", "Delete 500 Lines of Code.", 100, "linesOfCodeDeleted", 500, ""));
            //LoC Added
            achievements.add(new Achievement("Code Marathoner Bronze", "Write 1000 Lines of Code.", 100, "linesOfCodeAdded", 1000, ""));
            achievements.add(new Achievement("Code Marathoner Silver", "Write 5000 Lines of Code.", 300, "linesOfCodeAdded", 5000, ""));
            achievements.add(new Achievement("Code Marathoner Gold", "Write 10000 Lines of Code.", 1000, "linesOfCodeAdded", 10000, ""));

            //Team-Achievements
            //Commits
            achievements.add(new Achievement("500 Team Commits", "Get 500 Commits in your team.", 500, "commitsTeam", 500, "500TeamCommits.png" ));
            achievements.add(new Achievement("1000 Team Commits", "Get 1000 Commits in your team.", 1000, "commitsTeam", 1000, "1000TeamCommits.png"));
            //Issues
            achievements.add(new Achievement("Debugging Team Amateur", "Fix 25 Issues in team.", 200, "issuesTeam", 25, ""));
            achievements.add(new Achievement("Debugging Team Apprentice", "Fix 50 Issues in team.", 400, "issuesTeam", 50, ""));
            achievements.add(new Achievement("Debugging Team Master", "Fix 100 Issues in team.", 800, "issuesTeam", 100, ""));
            //Reviews
            achievements.add(new Achievement("Peer Review Experts", "Review 50 Pull Requests in team.", 1000, "pullRequestsTeam", 50,"TeamReview.png" ));
            //LoC Deleted
            achievements.add(new Achievement("Refactoring Team Hero", "Delete 2000 Lines of Code in team.", 300, "linesOfCodeDeletedTeam", 2000, ""));
            //LoC Added
            achievements.add(new Achievement("Team Code Marathoners Bronze", "Write 5000 Lines of Code in team.", 300, "linesOfCodeAddedTeam", 5000, ""));
            achievements.add(new Achievement("Team Code Marathoners Silver", "Write 20000 Lines of Code in team.", 600, "linesOfCodeAddedTeam", 20000, ""));
            achievements.add(new Achievement("Team Code Marathoners Gold", "Write 30000 Lines of Code in team.", 1500, "linesOfCodeAddedTeam", 30000, ""));

            achievementRepository.saveAll(achievements);
        }
    }
}


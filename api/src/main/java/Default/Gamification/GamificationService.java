package Default.Gamification;

import Default.Commit.CommitService;
import Default.Issue.IssueService;
import Default.PullRequest.PullRequestService;
import Default.User.User;
import Default.User.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.LinkedHashMap;

@Service
public class GamificationService {
    
    @Autowired
    private IssueService issueService;
    
    @Autowired
    private CommitService commitService;
    
    @Autowired
    private PullRequestService pullRequestService;
    
    @Autowired
    UserService userService;
    
    public Skill getSkills(Long userId, Long repoId) {
        Skill skillsUser = new Skill();

        //Commit-Value
        if (commitService.getMaxCommitsSingleUser(repoId) != null && commitService.getMaxCommitsSingleUser(repoId) != 0) {
            Double commitValue = ((double)commitService.getCommitCount(userId, repoId) / (double)commitService.getMaxCommitsSingleUser(repoId)) * 10.0;
            skillsUser.setCommitValueUser(commitValue);
        } else {
            skillsUser.setCommitValueUser(0.0);
        }

        //Productivity-Value
        if (commitService.getMaxProductivitySingleUser(repoId) != null && commitService.getMaxProductivitySingleUser(repoId) != 0) {
            Double prodValue = (commitService.getAverageUserProductivity(userId, repoId) / commitService.getMaxProductivitySingleUser(repoId)) * 10.0;
            skillsUser.setProductivityValueUser(prodValue);
        } else {
            skillsUser.setProductivityValueUser(0.0);
        }
        
        //Issue-Value
        if (issueService.getMaxFixedIssuesSingleUser(repoId) != null && issueService.getMaxFixedIssuesSingleUser(repoId) != 0) {
            Double issueValue = ((double)issueService.getTotalClosedIssuesUser(userId, repoId) / (double)issueService.getMaxFixedIssuesSingleUser(repoId)) * 10.0;
            skillsUser.setFixedIssuesValueUser(issueValue);
        } else {
            skillsUser.setFixedIssuesValueUser(0.0);
        }
        //Review-Value
        if (pullRequestService.getMaxReviewsSingleUser(repoId) != null && pullRequestService.getMaxReviewsSingleUser(repoId) != 0) {
            Double reviewValue = ((double)pullRequestService.getNumberReviews(userId, repoId) / (double)pullRequestService.getMaxReviewsSingleUser(repoId)) * 10.0;
            skillsUser.setReviewValueUser(reviewValue);
        } else {
            skillsUser.setReviewValueUser(0.0);
        }
            
        return skillsUser;
    }

    public Leaderboard getLeaderboard(Long repoId) {
        Leaderboard leaderboard = new Leaderboard();
        Map<String, Double> leaderboardMap = new HashMap<>();
        List<User> userList = userService.findAll().stream().filter(u -> u.getRepoId().equals(repoId)).toList();
        for (User user: userList) {
            Skill userSkill = getSkills(user.getUserId(), repoId);
            Double userValue = userSkill.getCommitValueUser() + userSkill.getProductivityValueUser() + userSkill.getFixedIssuesValueUser()+ userSkill.getReviewValueUser();
            leaderboardMap.put(user.getName(), userValue);
        }
        Map<String, Double> sortedLeaderboardMap = leaderboardMap.entrySet()
            .stream()
            .sorted(Map.Entry.<String, Double>comparingByValue(Comparator.reverseOrder()))
            .collect(Collectors.toMap(
                Map.Entry::getKey,
                Map.Entry::getValue,
                (e1, e2) -> e1,
                LinkedHashMap::new
            ));

        leaderboard.setLeaderboardMap(sortedLeaderboardMap);
        return leaderboard;
    }
}

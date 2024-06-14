package Default.Gamification;

import Default.Commit.CommitService;
import Default.Issue.IssueService;
import Default.PullRequest.PullRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GamificationService {
    
    @Autowired
    private IssueService issueService;
    
    @Autowired
    private CommitService commitService;
    
    @Autowired
    private PullRequestService pullRequestService;
    
    public Skill getSkills(Long userId, Long repoId) {
        Skill skillsUser = new Skill();

        //Commit-Value
        if (commitService.getMaxCommitsSingleUser(repoId) != 0) {
            System.out.println("Commits user: " + commitService.getCommitCount(userId, repoId) + "Commits MAX: " + commitService.getMaxCommitsSingleUser(repoId));
            Double commitValue = ((double)commitService.getCommitCount(userId, repoId) / (double)commitService.getMaxCommitsSingleUser(repoId)) * 10.0;
            skillsUser.setCommitValueUser(commitValue);
        } else {
            skillsUser.setCommitValueUser(0.0);
            System.out.println("Keine Commits");
        }

        //Productivity-Value
        if (commitService.getMaxProductivitySingleUser(repoId) != 0) {
            System.out.println("Prod user: " + commitService.getAverageUserProductivity(userId, repoId) + "Prod MAX: " + commitService.getMaxProductivitySingleUser(repoId));
            Double prodValue = (commitService.getAverageUserProductivity(userId, repoId) / commitService.getMaxProductivitySingleUser(repoId)) * 10.0;
            skillsUser.setProductivityValueUser(prodValue);
        } else {
            skillsUser.setProductivityValueUser(0.0);
            System.out.println("Keine Commits für Prod");
        }
        
        //Issue-Value
        if (issueService.getMaxFixedIssuesSingleUser(repoId) != 0) {
            System.out.println("Issue user: " + issueService.getTotalClosedIssuesUser(userId, repoId) + "Issue MAX: " + issueService.getMaxFixedIssuesSingleUser(repoId));
            Double issueValue = ((double)issueService.getTotalClosedIssuesUser(userId, repoId) / (double)issueService.getMaxFixedIssuesSingleUser(repoId)) * 10.0;
            skillsUser.setFixedIssuesValueUser(issueValue);
        } else {
            skillsUser.setFixedIssuesValueUser(0.0);
            System.out.println("Keine Issues gefixt");
        }
        //Review-Value
        if (pullRequestService.getMaxReviewsSingleUser(repoId) != 0) {
            System.out.println("Review user: " + pullRequestService.getNumberReviews(userId, repoId) + "Review MAX: " + pullRequestService.getMaxReviewsSingleUser(repoId));
            Double reviewValue = ((double)pullRequestService.getNumberReviews(userId, repoId) / (double)pullRequestService.getMaxReviewsSingleUser(repoId)) * 10.0;
            skillsUser.setReviewValueUser(reviewValue);
        } else {
            skillsUser.setReviewValueUser(0.0);
            System.out.println("Keine Reviews");
        }
            
        return skillsUser;
    }
    
}

package Default.PullRequest;

import Default.Issue.IssueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PullRequestService {
    
    @Autowired
    private PullRequestRepository pullRequestRepository;
    
    @Autowired
    private IssueService issueService;

    /**
     * Saves an pullRequest in the repository
     *
     * @param pullRequest pullRequest to be saved
     * @return The saved pullRequest
     */
    public PullRequest savePullRequest(PullRequest pullRequest) {
        System.out.println("Speichere PullRequest: " + pullRequest.getId());
        issueService.deleteIssueById(pullRequest.getId());
        return pullRequestRepository.save(pullRequest);
    }
}

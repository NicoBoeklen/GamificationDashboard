package Default.PullRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PullRequestService {
    
    @Autowired
    private PullRequestRepository pullRequestRepository;

    /**
     * Saves an pullRequest in the repository
     *
     * @param pullRequest pullRequest to be saved
     * @return The saved pullRequest
     */
    public PullRequest savePullRequest(PullRequest pullRequest) {
        return pullRequestRepository.save(pullRequest);
    }
}

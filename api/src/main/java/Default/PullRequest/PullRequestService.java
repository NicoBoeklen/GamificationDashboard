package Default.PullRequest;

import Default.Issue.IssueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;

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
    public Mono<PullRequest> savePullRequest(PullRequest pullRequest) {
        issueService.deleteIssueById(pullRequest.getId());
        return Mono.fromCallable(() -> pullRequestRepository.save(pullRequest));
    }

    public Double getAverageCommentsOfLastFivePullRequestsByUser(Long userId) {
        Pageable pageable = PageRequest.of(0, 5);
        List<PullRequest> pullRequests = pullRequestRepository.findLastFivePullRequestsByUser(userId, pageable);
        return pullRequests.stream()
            .mapToInt(PullRequest::getCommentNumber)
            .average()
            .orElse(0.0);
    }
    
}

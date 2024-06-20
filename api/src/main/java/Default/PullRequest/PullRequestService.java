package Default.PullRequest;

import Default.Issue.Issue;
import Default.Issue.IssueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

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
        Optional<Issue> issue = issueService.findIssuesByNumber(pullRequest.getNumber()).stream().filter(i -> i.getRepoId().equals(pullRequest.getRepoId())).findFirst();
        if (issue.isPresent()) {
            issueService.deleteIssueById(issue.get().getId());
        }
        return Mono.fromCallable(() -> pullRequestRepository.save(pullRequest));
    }

    public Integer getNumberReviews(Long userId, Long repoId) {
        return pullRequestRepository.getNumberReviews(userId, repoId);
    }

    public Integer getNumberReviewsByDay(Long userId, Long repoId, LocalDate day) {
        return pullRequestRepository.getNumberReviewsByDay(userId, repoId, day);
    }

    public Double getAverageCommentsOfLastFivePullRequestsByUser(Long userId, Long repoId) {
        Pageable pageable = PageRequest.of(0, 5);
        List<PullRequest> pullRequests = pullRequestRepository.findLastFivePullRequestsByUser(userId, pageable, repoId);
        return pullRequests.stream()
            .mapToInt(PullRequest::getCommentNumber)
            .average()
            .orElse(0.0);
    }

    public Double getAverageAdditionsOfLastFivePullRequests(Long repoId) {
        Pageable pageable = PageRequest.of(0, 5);
        List<PullRequest> pullRequests = pullRequestRepository.findLastFivePullRequests(pageable, repoId);
        return pullRequests.stream()
            .mapToInt(PullRequest::getAdditions)
            .average()
            .orElse(0.0);
    }

    public Double getAverageDeletionsOfLastFivePullRequests(Long repoId) {
        Pageable pageable = PageRequest.of(0, 5);
        List<PullRequest> pullRequests = pullRequestRepository.findLastFivePullRequests(pageable, repoId);
        return pullRequests.stream()
            .mapToInt(PullRequest::getDeletions)
            .average()
            .orElse(0.0);
    }

    public Double getAverageCommitsOfLastFivePullRequests(Long repoId) {
        Pageable pageable = PageRequest.of(0, 5);
        List<PullRequest> pullRequests = pullRequestRepository.findLastFivePullRequests(pageable, repoId);
        return pullRequests.stream()
            .mapToInt(PullRequest::getCommitNumber)
            .average()
            .orElse(0.0);
    }

    public Double getAverageProcessTimeOfLastFivePullRequests(Long repoId) {
        Pageable pageable = PageRequest.of(0, 5);
        List<PullRequest> pullRequests = pullRequestRepository.findLastFivePullRequests(pageable, repoId);
        double averageHours = pullRequests.stream()
            .mapToDouble(pr -> ((double) Duration.between(pr.getDateOpened(), pr.getDateClosed()).toMinutes()) / 60)
            .average()
            .orElse(0.0);

        return Math.round(averageHours * 10.0) / 10.0;
    }

    public Integer getOpenPullRequests(Long repoId) {
        return pullRequestRepository.getOpenPullRequests(repoId);
    }

    public Integer getClosedPullRequestsLastMonth(Long repoId) {
        return pullRequestRepository.getClosedPullRequestsLastMonth(repoId);
    }

    public Double getMaxReviewsSingleUser(Long repoId) {
        return pullRequestRepository.getMaxReviewsSingleUser(repoId);
    }

    public int getTeamReviews(Long repoId) {
        return (int) pullRequestRepository.findAll().stream().filter(pr -> pr.getState().equals(("closed"))).filter(pr -> pr.getRepoId().equals(repoId)).count();
    }

    public Integer getTeamReviewsByDay(Long repoId, LocalDate day) {
        return pullRequestRepository.getTeamReviewsByDay(repoId, day);
    }
}

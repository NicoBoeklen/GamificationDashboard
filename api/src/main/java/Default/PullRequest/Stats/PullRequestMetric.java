package Default.PullRequest.Stats;

public class PullRequestMetric {
    
    private Integer numberReviewsUser;
    
    private Double averageCommentsPerReviewUser;
    
    private Integer openPullRequestsLastMonth;
    
    private Integer closedPullRequestsLastMonth;
    
    private Double averageAdditionsPerPRTeam;

    private Double averageDeletionsPerPRTeam;

    private Double averageCommitsPerPRTeam;
    
    private Double averageProcessTime;

    public PullRequestMetric(Integer numberReviewsUser, Double averageCommentsPerReviewUser, Integer openPullRequestsLastMonth, Integer closedPullRequestsLastMonth, Double averageAdditionsPerPRTeam, Double averageDeletionsPerPRTeam, Double averageCommitsPerPRTeam, Double averageProcessTime) {
        this.numberReviewsUser = numberReviewsUser;
        this.averageCommentsPerReviewUser = averageCommentsPerReviewUser;
        this.openPullRequestsLastMonth = openPullRequestsLastMonth;
        this.closedPullRequestsLastMonth = closedPullRequestsLastMonth;
        this.averageAdditionsPerPRTeam = averageAdditionsPerPRTeam;
        this.averageDeletionsPerPRTeam = averageDeletionsPerPRTeam;
        this.averageCommitsPerPRTeam = averageCommitsPerPRTeam;
        this.averageProcessTime = averageProcessTime;
    }

    public Integer getNumberReviewsUser() {
        return numberReviewsUser;
    }

    public void setNumberReviewsUser(Integer numberReviewsUser) {
        this.numberReviewsUser = numberReviewsUser;
    }

    public Double getAverageCommentsPerReviewUser() {
        return averageCommentsPerReviewUser;
    }

    public void setAverageCommentsPerReviewUser(Double averageCommentsPerReviewUser) {
        this.averageCommentsPerReviewUser = averageCommentsPerReviewUser;
    }

    public Integer getOpenPullRequestsLastMonth() {
        return openPullRequestsLastMonth;
    }

    public void setOpenPullRequestsLastMonth(Integer openPullRequestsLastMonth) {
        this.openPullRequestsLastMonth = openPullRequestsLastMonth;
    }

    public Integer getClosedPullRequestsLastMonth() {
        return closedPullRequestsLastMonth;
    }

    public void setClosedPullRequestsLastMonth(Integer closedPullRequestsLastMonth) {
        this.closedPullRequestsLastMonth = closedPullRequestsLastMonth;
    }

    public Double getAverageAdditionsPerPRTeam() {
        return averageAdditionsPerPRTeam;
    }

    public void setAverageAdditionsPerPRTeam(Double averageAdditionsPerPRTeam) {
        this.averageAdditionsPerPRTeam = averageAdditionsPerPRTeam;
    }

    public Double getAverageDeletionsPerPRTeam() {
        return averageDeletionsPerPRTeam;
    }

    public void setAverageDeletionsPerPRTeam(Double averageDeletionsPerPRTeam) {
        this.averageDeletionsPerPRTeam = averageDeletionsPerPRTeam;
    }

    public Double getAverageCommitsPerPRTeam() {
        return averageCommitsPerPRTeam;
    }

    public void setAverageCommitsPerPRTeam(Double averageCommitsPerPRTeam) {
        this.averageCommitsPerPRTeam = averageCommitsPerPRTeam;
    }

    public Double getAverageProcessTime() {
        return averageProcessTime;
    }

    public void setAverageProcessTime(Double averageProcessTime) {
        this.averageProcessTime = averageProcessTime;
    }
}

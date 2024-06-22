package Default.PullRequest.Stats;

public class PullRequestMetric {
    
    private Integer numberReviewsUser;

    private Integer numberReviewsTotal;
    
    private Double averageCommentsPerReviewUser;
    
    private Integer openPullRequests;
    
    private Integer closedPullRequestsLastMonth;
    
    private Double averageAdditionsPerPRTeam;

    private Double averageDeletionsPerPRTeam;

    private Double averageCommitsPerPRTeam;
    
    private Double averageProcessTimeInHours;

    public PullRequestMetric(Integer numberReviewsUser, Integer numberReviewsTotal, Double averageCommentsPerReviewUser, Integer openPullRequests, Integer closedPullRequestsLastMonth, Double averageAdditionsPerPRTeam, Double averageDeletionsPerPRTeam, Double averageCommitsPerPRTeam, Double averageProcessTime) {
        this.numberReviewsUser = numberReviewsUser;
        this.numberReviewsTotal = numberReviewsTotal;
        this.averageCommentsPerReviewUser = averageCommentsPerReviewUser;
        this.openPullRequests = openPullRequests;
        this.closedPullRequestsLastMonth = closedPullRequestsLastMonth;
        this.averageAdditionsPerPRTeam = averageAdditionsPerPRTeam;
        this.averageDeletionsPerPRTeam = averageDeletionsPerPRTeam;
        this.averageCommitsPerPRTeam = averageCommitsPerPRTeam;
        this.averageProcessTimeInHours = averageProcessTime;
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

    public Integer getOpenPullRequests() {
        return openPullRequests;
    }

    public void setOpenPullRequests (Integer openPullRequests) {
        this.openPullRequests = openPullRequests;
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

    public Double getAverageProcessTimeInHours() {
        return averageProcessTimeInHours;
    }

    public void setAverageProcessTime(Double averageProcessTime) {
        this.averageProcessTimeInHours = averageProcessTime;
    }

    public Integer getNumberReviewsTotal() {
        return numberReviewsTotal;
    }

    public void setNumberReviewsTotal(Integer numberReviewsTotal) {
        this.numberReviewsTotal = numberReviewsTotal;
    }

    public void setAverageProcessTimeInHours(Double averageProcessTimeInHours) {
        this.averageProcessTimeInHours = averageProcessTimeInHours;
    }
}

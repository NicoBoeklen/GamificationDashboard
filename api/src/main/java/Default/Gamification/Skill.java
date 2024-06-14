package Default.Gamification;

public class Skill {

    private Double reviewValueUser;
    private Double fixedIssuesValueUser;
    private Double productivityValueUser;
    private Double commitValueUser;

    public Skill(Double reviewValueUser, Double fixedIssuesValueUser, Double productivityValueUser, Double commitValueUser) {
        this.reviewValueUser = reviewValueUser;
        this.fixedIssuesValueUser = fixedIssuesValueUser;
        this.productivityValueUser = productivityValueUser;
        this.commitValueUser = commitValueUser;
    }
    
    public Skill() {
        
    }

    public Double getReviewValueUser() {
        return reviewValueUser;
    }

    public void setReviewValueUser(Double reviewValueUser) {
        this.reviewValueUser = reviewValueUser;
    }

    public Double getFixedIssuesValueUser() {
        return fixedIssuesValueUser;
    }

    public void setFixedIssuesValueUser(Double fixedIssuesValueUser) {
        this.fixedIssuesValueUser = fixedIssuesValueUser;
    }

    public Double getProductivityValueUser() {
        return productivityValueUser;
    }

    public void setProductivityValueUser(Double productivityValueUser) {
        this.productivityValueUser = productivityValueUser;
    }

    public Double getCommitValueUser() {
        return commitValueUser;
    }

    public void setCommitValueUser(Double commitValueUser) {
        this.commitValueUser = commitValueUser;
    }
}

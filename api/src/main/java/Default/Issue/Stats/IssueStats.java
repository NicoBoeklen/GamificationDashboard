package Default.Issue.Stats;


public class IssueStats {
    
    public Integer amountTotalIssuesTeam;
    public Integer amountFixedIssuesTeam;
    public Integer amountOpenIssuesTeam;
    public Integer amountTotalIssuesUser;
    public Double averageTimeFixIssueOpenTeam;
    public Double averageTimeFixIssueTotalTeam;
    public Object weeklyClosedPullRequests;
    
    public IssueStats(Integer amountTotalIssuesTeam, Integer amountFixedIssuesTeam,Integer amountOpenIssuesTeam,Integer amountTotalIssuesUser,
                      Double averageTimeFixIssueOpenTeam, Double averageTimeFixIssueTotalTeam,Object weeklyClosedPullRequests) {
        this.amountTotalIssuesTeam = amountTotalIssuesTeam;
        this.amountFixedIssuesTeam = amountFixedIssuesTeam;
        this.amountOpenIssuesTeam = amountOpenIssuesTeam;
        this.amountTotalIssuesUser = amountTotalIssuesUser;
        this.averageTimeFixIssueOpenTeam = averageTimeFixIssueOpenTeam;
        this.averageTimeFixIssueTotalTeam = averageTimeFixIssueTotalTeam;
        this.weeklyClosedPullRequests = weeklyClosedPullRequests;
        }
    public IssueStats() {
    } 
    
    public Integer getAmountTotalIssuesTeam() {
        return amountTotalIssuesTeam;
    }

    public void setAmountTotalIssuesTeam(Integer amountTotalIssuesTeam) {
        this.amountTotalIssuesTeam = amountTotalIssuesTeam;
    }

    public Integer getAmountFixedIssuesTeam() {
        return amountFixedIssuesTeam;
    }

    public void setAmountFixedIssuesTeam(Integer amountFixedIssuesTeam) {
        this.amountFixedIssuesTeam = amountFixedIssuesTeam;
    }

    public Integer getAmountOpenIssuesTeam() {
        return amountOpenIssuesTeam;
    }

    public void setAmountOpenIssuesTeam(Integer amountOpenIssuesTeam) {
        this.amountOpenIssuesTeam = amountOpenIssuesTeam;
    }
}

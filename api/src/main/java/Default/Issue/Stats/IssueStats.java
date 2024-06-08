package Default.Issue.Stats;


import java.util.List;

public class IssueStats {
    
    public Integer amountTotalIssuesTeam;
    public Integer amountFixedIssuesTeam;
    public Integer amountOpenIssuesTeam;
    public Integer amountTotalIssuesUser;
    public Double averageTimeFixIssueOpenTeam;
    public Double averageTimeFixIssueTotalTeam;
    public List<IssuesWeekly> weeklyClosedIssues;
    public List<IssuesWeekly> weeklyOpenIssues;
    public List<IssuesWeekly> weeklyTotalIssues;
    
    public IssueStats(Integer amountTotalIssuesTeam, Integer amountFixedIssuesTeam, Integer amountOpenIssuesTeam, Integer amountTotalIssuesUser,
                      Double averageTimeFixIssueOpenTeam, Double averageTimeFixIssueTotalTeam, List<IssuesWeekly> weeklyClosedIssues, List<IssuesWeekly> weeklyOpenIssues, List<IssuesWeekly> weeklyTotalIssues) {
        this.amountTotalIssuesTeam = amountTotalIssuesTeam;
        this.amountFixedIssuesTeam = amountFixedIssuesTeam;
        this.amountOpenIssuesTeam = amountOpenIssuesTeam;
        this.amountTotalIssuesUser = amountTotalIssuesUser;
        this.averageTimeFixIssueOpenTeam = averageTimeFixIssueOpenTeam;
        this.averageTimeFixIssueTotalTeam = averageTimeFixIssueTotalTeam;
        this.weeklyClosedIssues = weeklyClosedIssues;
        this.weeklyOpenIssues = weeklyOpenIssues;
        this.weeklyTotalIssues = weeklyTotalIssues;
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

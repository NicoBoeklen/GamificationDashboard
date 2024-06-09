package Default.Issue.Stats;


import java.util.List;

public class IssueStats {
    
    public Integer amountTotalIssuesTeam;
    public Integer amountFixedIssuesTeam;
    public Integer amountOpenIssuesTeam;
    public Integer amountTotalClosedIssuesUser;
    public Double averageTimeFixIssueOpenTeam;
    public Double averageTimeFixIssueTotalTeam;
    public List<IssuesWeekly> weeklyClosedIssues;
    public List<IssuesWeekly> weeklyOpenIssues;
    public List<IssuesWeekly> weeklyTotalIssues;
    public List<IssuesWeekly> issuesPer1000LoC;
    
    public IssueStats(Integer amountTotalIssuesTeam, Integer amountFixedIssuesTeam, Integer amountOpenIssuesTeam, Integer amountTotalClosedIssuesUser,
                      Double averageTimeFixIssueOpenTeam, Double averageTimeFixIssueTotalTeam, List<IssuesWeekly> weeklyClosedIssues, List<IssuesWeekly> weeklyOpenIssues,
                      List<IssuesWeekly> weeklyTotalIssues, List<IssuesWeekly> issuesPer1000LoC) {
        this.amountTotalIssuesTeam = amountTotalIssuesTeam;
        this.amountFixedIssuesTeam = amountFixedIssuesTeam;
        this.amountOpenIssuesTeam = amountOpenIssuesTeam;
        this.amountTotalClosedIssuesUser = amountTotalClosedIssuesUser;
        this.averageTimeFixIssueOpenTeam = averageTimeFixIssueOpenTeam;
        this.averageTimeFixIssueTotalTeam = averageTimeFixIssueTotalTeam;
        this.weeklyClosedIssues = weeklyClosedIssues;
        this.weeklyOpenIssues = weeklyOpenIssues;
        this.weeklyTotalIssues = weeklyTotalIssues;
        this.issuesPer1000LoC = issuesPer1000LoC;
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
    public List<IssuesWeekly> getIssuesPer1000LoC() {
        return issuesPer1000LoC;
    }

    public void setIssuesPer1000LoC(List<IssuesWeekly> issuesPer1000LoC) {
        this.issuesPer1000LoC = issuesPer1000LoC;
    }
}

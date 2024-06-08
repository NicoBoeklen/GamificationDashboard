package Default.Issue.Stats;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

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
    Map<LocalDateTime,Long> IssuesPer1000LoC;
    
    public IssueStats(Integer amountTotalIssuesTeam, Integer amountFixedIssuesTeam, Integer amountOpenIssuesTeam, Integer amountTotalIssuesUser,
                      Double averageTimeFixIssueOpenTeam, Double averageTimeFixIssueTotalTeam, List<IssuesWeekly> weeklyClosedIssues, List<IssuesWeekly> weeklyOpenIssues,
                      List<IssuesWeekly> weeklyTotalIssues, Map<LocalDateTime,Long> IssuesPer1000LoC) {
        this.amountTotalIssuesTeam = amountTotalIssuesTeam;
        this.amountFixedIssuesTeam = amountFixedIssuesTeam;
        this.amountOpenIssuesTeam = amountOpenIssuesTeam;
        this.amountTotalIssuesUser = amountTotalIssuesUser;
        this.averageTimeFixIssueOpenTeam = averageTimeFixIssueOpenTeam;
        this.averageTimeFixIssueTotalTeam = averageTimeFixIssueTotalTeam;
        this.weeklyClosedIssues = weeklyClosedIssues;
        this.weeklyOpenIssues = weeklyOpenIssues;
        this.weeklyTotalIssues = weeklyTotalIssues;
        this.IssuesPer1000LoC = IssuesPer1000LoC;
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

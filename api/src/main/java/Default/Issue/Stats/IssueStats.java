package Default.Issue.Stats;


import java.util.List;

public class IssueStats {
    
    public Integer amountTotalIssuesTeam;
    public Integer amountFixedIssuesTeam;
    public Integer amountOpenIssuesTeam;
    public Integer amountTotalClosedIssuesUser;
    public Double averageAgeOfOpenIssues;
    public Integer amountOpenIssuesLessSevenDays;
    public Integer amountOpenIssuesBetweenWeekAndMonth;
    public Integer amountOpenIssuesMoreThanAMonth;
    public Double averageTimeToFixIssueInDaysTeam;
    public List<IssuesWeekly> weeklyClosedIssues;
    public List<IssuesWeekly> weeklyOpenIssues;
    public List<IssuesWeekly> weeklyTotalIssues;
    public List<IssuesWeeklyDouble> issuesPer1000LoC;
    
    public IssueStats(Integer amountTotalIssuesTeam, Integer amountFixedIssuesTeam, Integer amountOpenIssuesTeam, Integer amountTotalClosedIssuesUser,
                       Double averageAgeOfOpenIssues,Integer amountOpenIssuesLessSevenDays, Integer amountOpenIssuesBetweenWeekAndMonth, Integer amountOpenIssuesMoreThanAMonth, Double averageTimeFixIssueTotalTeam, 
                      List<IssuesWeekly> weeklyClosedIssues, List<IssuesWeekly> weeklyOpenIssues, List<IssuesWeekly> weeklyTotalIssues, List<IssuesWeeklyDouble> issuesPer1000LoC) {
        this.amountTotalIssuesTeam = amountTotalIssuesTeam;
        this.amountFixedIssuesTeam = amountFixedIssuesTeam;
        this.amountOpenIssuesTeam = amountOpenIssuesTeam;
        this.amountTotalClosedIssuesUser = amountTotalClosedIssuesUser;
        this.averageAgeOfOpenIssues = averageAgeOfOpenIssues;
        this.amountOpenIssuesLessSevenDays = amountOpenIssuesLessSevenDays;
        this.amountOpenIssuesBetweenWeekAndMonth = amountOpenIssuesBetweenWeekAndMonth;
        this.amountOpenIssuesMoreThanAMonth = amountOpenIssuesMoreThanAMonth;
        this.averageTimeToFixIssueInDaysTeam = averageTimeFixIssueTotalTeam;
        this.weeklyClosedIssues = weeklyClosedIssues;
        this.weeklyOpenIssues = weeklyOpenIssues;
        this.weeklyTotalIssues = weeklyTotalIssues;
        this.issuesPer1000LoC = issuesPer1000LoC;
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
    public List<IssuesWeeklyDouble> getIssuesPer1000LoC() {
        return issuesPer1000LoC;
    }

    public void setIssuesPer1000LoC(List<IssuesWeeklyDouble> issuesPer1000LoC) {
        this.issuesPer1000LoC = issuesPer1000LoC;
    }
    
}

package Default.Issue.Stats;

import Default.Issue.IssueRepository;
import Default.Issue.IssueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


public class IssueStats {
    public Double averageAgeOfOpenIssuesTeam;
    public Integer amountTotalIssuesTeam;
    public Integer amountFixedIssuesTeam;
    public Integer amountOpenIssuesTeam;
    public Integer amountTotalIssuesUser;
    
    public IssueStats(Integer amountTotalIssuesTeam, Integer amountFixedIssuesTeam,Integer amountOpenIssuesTeam,Integer amountTotalIssuesUser,Double averageAgeOfOpenIssuesTeam) {
        this.amountTotalIssuesTeam = amountTotalIssuesTeam;
        this.amountFixedIssuesTeam = amountFixedIssuesTeam;
        this.amountOpenIssuesTeam = amountOpenIssuesTeam;
        this.amountTotalIssuesUser = amountTotalIssuesUser;
        this.averageAgeOfOpenIssuesTeam = averageAgeOfOpenIssuesTeam;
        
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

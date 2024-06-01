package Default.Issue.Stats;

import Default.Issue.IssueRepository;
import Default.Issue.IssueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class IssueStats {
    @Autowired
    IssueRepository issueRepository;
    public Integer amountTotalIssuesTeam;
    public Integer amountFixedIssuesTeam;
    public Integer amountOpenIssuesTeam;
    public Integer amountTotalIssuesUser;
    
    public IssueStats(Long userId) {
        this.amountTotalIssuesTeam = issueRepository.getAllIssuesTeam();
        this.amountFixedIssuesTeam = issueRepository.getFixedIssuesTeam();
        this.amountOpenIssuesTeam = issueRepository.getOpenIssuesTeam();
        this.amountTotalIssuesUser = issueRepository.getTotalClosedIssuesUser(userId);
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

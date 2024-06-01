package Default.Issue.Stats;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.util.LinkedList;
import java.util.List;

public class IssueStats {

    

    public Integer amountTotalIssues;
    public Integer amountFixedIssues;
    public Integer amountOpenIssues;
    

    public IssueStats(Integer amountTotalIssues, Integer amountFixedIssues, Integer amountOpenIssues) {
        this.amountTotalIssues = amountTotalIssues;
        this.amountFixedIssues = amountFixedIssues;
        this.amountOpenIssues = amountOpenIssues;
    }
    public Integer getAmountTotalIssues() {
        return amountTotalIssues;
    }

    public void setAmountTotalIssues(Integer amountTotalIssues) {
        this.amountTotalIssues = amountTotalIssues;
    }

    public Integer getAmountFixedIssues() {
        return amountFixedIssues;
    }

    public void setAmountFixedIssues(Integer amountFixedIssues) {
        this.amountFixedIssues = amountFixedIssues;
    }

    public Integer getAmountOpenIssues() {
        return amountOpenIssues;
    }

    public void setAmountOpenIssues(Integer amountOpenIssues) {
        this.amountOpenIssues = amountOpenIssues;
    }
}

package Default.Issue.Stats;

import java.util.LinkedList;
import java.util.List;

public class IssueStats {
    
    private Integer amountTotalIssues;
    private Integer amountFixedIssues;
    private Integer amountOpenIssues;

    public IssueStats(Integer amountTotalIssues, Integer amountFixedIssues, Integer amountOpenIssues) {
        this.amountTotalIssues = amountTotalIssues;
        this.amountFixedIssues = amountFixedIssues;
        this.amountOpenIssues = amountOpenIssues;

    }
}

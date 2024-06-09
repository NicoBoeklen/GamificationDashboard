package Default.Issue.Stats;

import java.time.LocalDateTime;

public class IssuesWeekly {
    private LocalDateTime week;
    private Long issues;
    private Double issuesDouble;

    public IssuesWeekly(LocalDateTime week, Long issues) {
        this.week = week;
        this.issues = issues;
    }
    public IssuesWeekly(LocalDateTime week, Double issuesDouble) {
        this.week = week;
        this.issuesDouble = issuesDouble;
    }

    // Getter und Setter
    public LocalDateTime getWeek() {
        return week;
    }

    public void setWeek(LocalDateTime week) {
        this.week = week;
    }

    public Long getIssues() {
        return issues;
    }

    public void setIssues(Long issues) {
        this.issues = issues;
    }
    public Double getIssuesDouble() {
        return issuesDouble;
    }

    public void setIssuesDouble(Double issuesDouble) {
        this.issuesDouble = issuesDouble;
    }
}

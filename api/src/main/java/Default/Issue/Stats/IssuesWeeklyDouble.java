package Default.Issue.Stats;

import java.time.LocalDateTime;

public class IssuesWeeklyDouble {
    private LocalDateTime week;
    private Long issues;
    private Double issuesDouble;
    public IssuesWeeklyDouble(LocalDateTime week, Double issuesDouble) {
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


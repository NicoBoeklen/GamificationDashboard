package Default.Issue.Stats;

import java.time.LocalDateTime;

public class IssuesWeekly {
    private LocalDateTime week;
    private Long issues;

    public IssuesWeekly(LocalDateTime week, Long issues) {
        this.week = week;
        this.issues = issues;
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
}

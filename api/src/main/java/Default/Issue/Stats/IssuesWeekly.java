package Default.Issue.Stats;

import java.time.LocalDate;

public class IssuesWeekly {
    private LocalDate week;
    private Long issues;

    public IssuesWeekly(LocalDate week, Long issues) {
        this.week = week;
        this.issues = issues;
    }
    

    // Getter und Setter
    public LocalDate getWeek() {
        return week;
    }

    public void setWeek(LocalDate week) {
        this.week = week;
    }

    public Long getIssues() {
        return issues;
    }

    public void setIssues(Long issues) {
        this.issues = issues;
    }
}

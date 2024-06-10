package Default.Issue.Stats;

import java.time.LocalDateTime;
/*
zweite Klasse für die Issues pro 1000 LoC
Versucht IssuesWeekly zu überladen aber Java scheint bei JPA Abfragen nicht zu wissen,
welchen Konstuktor er verwenden soll, deshalb wurde eine neue Klasse erstellt
 */
public class IssuesWeeklyDouble {
    private LocalDateTime week;
    private Double issueDensity;
    public IssuesWeeklyDouble(LocalDateTime week, Double issueDensity) {
        this.week = week;
        this.issueDensity = issueDensity;
    }
    // Getter und Setter
    public LocalDateTime getWeek() {
        return week;
    }

    public void setWeek(LocalDateTime week) {
        this.week = week;
    }

    public Double getIssueDensity() {
        return issueDensity;
    }

    public void setIssueDensity(Double issueDensity) {
        this.issueDensity = issueDensity;
    }
}


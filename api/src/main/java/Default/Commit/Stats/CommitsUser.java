package Default.Commit.Stats;

import java.time.LocalDateTime;

public class CommitsUser {
    private LocalDateTime week;
    private Long totalCommits;

    public CommitsUser(LocalDateTime week, Long totalCommits) {
        this.week = week;
        this.totalCommits = totalCommits;
    }

    public LocalDateTime getWeek() {
        return week;
    }

    public void setWeek(LocalDateTime week) {
        this.week = week;
    }

    public Long getTotalCommits() {
        return totalCommits;
    }

    public void setTotalCommits(Long totalCommits) {
        this.totalCommits = totalCommits;
    }
}

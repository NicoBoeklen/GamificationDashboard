package Default.Commit.Stats;

import java.time.LocalDateTime;

public class CommitsUser {
    private LocalDateTime week;
    private Integer totalCommits;

    public CommitsUser(LocalDateTime week, Integer totalCommits) {
        this.week = week;
        this.totalCommits = totalCommits;
    }

    public LocalDateTime getWeek() {
        return week;
    }

    public void setWeek(LocalDateTime week) {
        this.week = week;
    }

    public Integer getTotalCommits() {
        return totalCommits;
    }

    public void setTotalCommits(Integer totalCommits) {
        this.totalCommits = totalCommits;
    }
}

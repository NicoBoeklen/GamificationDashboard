package Default.Commit.Stats;

import java.time.LocalDate;

public class CommitsUser {
    private LocalDate week;
    private Long totalCommits;

    public CommitsUser(LocalDate week, Long totalCommits) {
        this.week = week;
        this.totalCommits = totalCommits;
    }

    public LocalDate getWeek() {
        return week;
    }

    public void setWeek(LocalDate week) {
        this.week = week;
    }

    public Long getTotalCommits() {
        return totalCommits;
    }

    public void setTotalCommits(Long totalCommits) {
        this.totalCommits = totalCommits;
    }
}

package Default.Commit.Stats;

import java.time.LocalDateTime;

public class CodeGrowth {
    private LocalDateTime week;
    private Long totalChanges;

    public CodeGrowth(LocalDateTime week, Long totalChanges) {
        this.week = week;
        this.totalChanges = totalChanges;
    }

    // Getter und Setter
    public LocalDateTime getWeek() {
        return week;
    }

    public void setWeek(LocalDateTime week) {
        this.week = week;
    }

    public Long getTotalChanges() {
        return totalChanges;
    }

    public void setTotalChanges(Long totalChanges) {
        this.totalChanges = totalChanges;
    }
}

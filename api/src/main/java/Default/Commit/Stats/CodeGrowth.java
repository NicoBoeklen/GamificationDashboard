package Default.Commit.Stats;

import java.time.LocalDate;

public class CodeGrowth {
    private LocalDate week;
    private Long totalChanges;

    public CodeGrowth(LocalDate week, Long totalChanges) {
        this.week = week;
        this.totalChanges = totalChanges;
    }

    // Getter und Setter
    public LocalDate getWeek() {
        return week;
    }

    public void setWeek(LocalDate week) {
        this.week = week;
    }

    public Long getTotalChanges() {
        return totalChanges;
    }

    public void setTotalChanges(Long totalChanges) {
        this.totalChanges = totalChanges;
    }
}

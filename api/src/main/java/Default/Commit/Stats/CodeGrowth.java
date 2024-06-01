package Default.Commit.Stats;

import java.sql.Date;

public class CodeGrowth {
    private Date week;
    private Long totalChanges;

    public CodeGrowth(Date week, Long totalChanges) {
        this.week = week;
        this.totalChanges = totalChanges;
    }

    // Getter und Setter
    public Date getWeek() {
        return week;
    }

    public void setWeek(Date week) {
        this.week = week;
    }

    public Long getTotalChanges() {
        return totalChanges;
    }

    public void setTotalChanges(Long totalChanges) {
        this.totalChanges = totalChanges;
    }
}

package Default.Gamification.Quest;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "\"quest\"")
public class Quest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private int xp;
    private String type;
    private int condition;
    private LocalDateTime day;

    public Quest() {
    }

    public Quest(Long id, String name, String description, int xp, String type, int condition, LocalDateTime day) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.xp = xp;
        this.type = type;
        this.condition = condition;
        this.day = day;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getXp() {
        return xp;
    }

    public void setXp(int xp) {
        this.xp = xp;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getCondition() {
        return condition;
    }

    public void setCondition(int condition) {
        this.condition = condition;
    }

    public LocalDateTime getDay() {
        return day;
    }

    public void setDay(LocalDateTime day) {
        this.day = day;
    }
}
package Default.Gamification.Quest;

import jakarta.persistence.*;

import java.time.LocalDate;

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
    private LocalDate tag;

    public Quest() {
    }

    public Quest(String name, String description, int xp, String type, int condition, LocalDate tag) {
        this.name = name;
        this.description = description;
        this.xp = xp;
        this.type = type;
        this.condition = condition;
        this.tag = tag;
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

    public LocalDate getDay() {
        return tag;
    }

    public void setDay(LocalDate day) {
        this.tag = day;
    }
}
package Default.Gamification.Achievement;

import jakarta.persistence.*;

@Entity
@Table(name = "\"achievement\"")
public class Achievement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private int xp;
    private String type;
    private int condition;
    
    private String image;


    public Achievement(String name, String description, int xp, String type, int condition, String image) {
        this.name = name;
        this.description = description;
        this.xp = xp;
        this.type = type;
        this.condition = condition;
        this.image = image;
    }
    
    public Achievement() {
        
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

    public String getImage() {
        return image;
    }
    
    public void setImage(String image) {
        this.image = image;
    }

}

package Default.Gamification.Achievement;

import Default.GithubRepo.GithubRepo;
import jakarta.persistence.*;

@Entity
public class Achievement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private int xp;
    private String type;
    private int condition;

    @ManyToOne
    @JoinColumn(name = "repo_id")
    private GithubRepo githubRepo;

    public Achievement(Long id, String name, String description, int xp, String type, int condition, GithubRepo githubRepo) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.xp = xp;
        this.type = type;
        this.condition = condition;
        this.githubRepo = githubRepo;
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

    public GithubRepo getGithubRepo() {
        return githubRepo;
    }

    public void setGithubRepo(GithubRepo githubRepo) {
        this.githubRepo = githubRepo;
    }
}

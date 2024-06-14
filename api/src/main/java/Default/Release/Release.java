package Default.Release;

import Default.GithubRepo.GithubRepo;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "\"release\"")
public class Release {

    @Id
    private Long id;

    @JsonProperty("tag_name")
    private String name;
    
    @JsonProperty("published_at")
    private LocalDateTime publishedAt;
    
    @ManyToOne
    @JoinColumn(name="repoId")
    private GithubRepo repo;

    ///////////////////////////////////////////////
    // Getter & Setter
    ///////////////////////////////////////////////

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

    public LocalDateTime getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(LocalDateTime publishedAt) {
        this.publishedAt = publishedAt;
    }

    public GithubRepo getRepo() {
        return repo;
    }

    public void setRepo(GithubRepo repo) {
        this.repo = repo;
    }
}

package Default.GithubRepo;

import Default.User.User;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "\"repository\"")
public class GithubRepo {
    @Id
    private Long id;

    private String name;

    @Lob
    private String description;

    private String language;

    @JsonProperty("updated_at")
    private LocalDateTime updatedAt;

    @JsonProperty("created_at")
    private LocalDateTime createdAt;
    
    private Integer openIssues;
    
    private Integer numberOfReleases;

    private Integer numberOfOpenPullRequests;
    
    private Integer numberOfContributors;
    
    @OneToOne
    @JoinColumns({
        @JoinColumn(name = "owner_id", referencedColumnName = "user_id"),
        @JoinColumn(name = "repo_id", referencedColumnName = "repo_id")
    })    
    @JsonProperty("owner")
    private User owner;


    ///////////////////////////////////////////////
    // Getter & Setter
    /////////////////////////////////////////////// 
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getOpenIssues() {
        return openIssues;
    }

    public void setOpenIssues(Integer openIssues) {
        this.openIssues = openIssues;
    }

    public Integer getNumberOfReleases() {
        return numberOfReleases;
    }

    public void setNumberOfReleases(Integer numberOfReleases) {
        this.numberOfReleases = numberOfReleases;
    }

    public Integer getNumberOfOpenPullRequests() {
        return numberOfOpenPullRequests;
    }

    public void setNumberOfOpenPullRequests(Integer numberOfOpenPullRequests) {
        this.numberOfOpenPullRequests = numberOfOpenPullRequests;
    }

    public Integer getNumberOfContributors() {
        return numberOfContributors;
    }

    public void setNumberOfContributors(Integer numberOfContributors) {
        this.numberOfContributors = numberOfContributors;
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

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }
}

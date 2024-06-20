package Default.Commit;

import Default.User.User;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "\"commit\"")
public class Commit {
    @Id
    @JsonProperty("sha")
    private String id;

    @Lob
    private String message;

    private LocalDateTime date;

    private boolean isMerge;

    private Integer additions;

    private Integer deletions;

    @ManyToOne
    @JoinColumns({
        @JoinColumn(name = "author_id", referencedColumnName = "user_id"),
        @JoinColumn(name = "repo_id", referencedColumnName = "repo_id")
    })
    @JsonProperty("author")
    private User author;
    
    private Long repositoryId;
    
    ///////////////////////////////////////////////
    // Getter & Setter
    ///////////////////////////////////////////////    

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public boolean isMerge() {
        return isMerge;
    }

    public void setMerge(boolean merge) {
        isMerge = merge;
    }

    public Integer getAdditions() {
        return additions;
    }

    public void setAdditions(Integer additions) {
        this.additions = additions;
    }

    public Integer getDeletions() {
        return deletions;
    }

    public void setDeletions(Integer deletions) {
        this.deletions = deletions;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Long getRepoId() {
        return repositoryId;
    }

    public void setRepoId(Long repoId) {
        this.repositoryId = repoId;
    }
}

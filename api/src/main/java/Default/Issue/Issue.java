package Default.Issue;

import Default.User.User;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Table(name = "\"commit\"")
public class Issue {
    @Id
    @JsonProperty("sha")
    private String id;

    @Lob
    private String message;

    private Date date;

    private boolean isMerge;

    private Integer additions;

    private Integer deletions;

    @ManyToOne
    @JoinColumn(name = "author_id")
    @JsonProperty("author")
    private User author;

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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
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
}

package Default.Commit;

import Default.User.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Table(name = "\"commit\"")
public class Commit {
    @Id
    @JsonProperty("sha")
    private String id;

    @JsonProperty("commit")
    @JsonDeserialize(using = CommitMessageDeserializer.class)
    private String message;

    //@JsonProperty("commit")
    //@JsonDeserialize(using = CommitDateDeserializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
    private Date date;

    private boolean isMerge;
    

    private Integer additions;

    private Integer deletions;

    @ManyToOne
    @JoinColumn(name = "author_id")
    @JsonProperty("author")
    private User author;

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

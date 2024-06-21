package Default.PullRequest;

import Default.Issue.Issue;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

@Entity
@Table(name = "\"PullRequest\"")
public class PullRequest extends Issue {

    private Integer additions;
    
    private Integer deletions;

    @JsonProperty("commits")
    private Integer commitNumber;

    @JsonProperty("review_comments")
    private Integer commentNumber;
        
    ///////////////////////////////////////////////
    // Getter & Setter
    ///////////////////////////////////////////////
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

    public Integer getCommitNumber() {
        return commitNumber;
    }

    public void setCommitNumber(Integer commitNumber) {
        this.commitNumber = commitNumber;
    }

    public Integer getCommentNumber() {
        return commentNumber;
    }

    public void setCommentNumber(Integer commentNumber) {
        this.commentNumber = commentNumber;
    }

}

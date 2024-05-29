package Default.PullRequest;

import Default.Issue.Issue;
import Default.User.User;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "\"PullRequest\"")
public class PullRequest extends Issue {
    
    private Integer additions;
    
    private Integer deletions;

    @JsonProperty("commits")
    private Integer commitNumber;

    @JsonProperty("review_comments")
    private Integer commentNumber;

    @ManyToOne
    @JoinColumn(name = "reviewedBy_id")
    @JsonProperty("merged_by")
    private User reviewer;

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

    public User getReviewer() {
        return reviewer;
    }

    public void setReviewer(User reviewer) {
        this.reviewer = reviewer;
    }
}

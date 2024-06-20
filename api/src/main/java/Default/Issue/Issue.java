package Default.Issue;

import Default.User.User;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "\"issue\"")
public class Issue {
    @Id
    @JsonProperty("id")
    private Long id;

    @JsonProperty("number")
    private Integer number;

    @JsonProperty("created_at")
    private LocalDateTime dateOpened;

    @JsonProperty("closed_at")
    private LocalDateTime dateClosed;

    private String state;

    @ManyToOne
    @JoinColumns({
        @JoinColumn(name = "closedBy_id", referencedColumnName = "user_id"),
        @JoinColumn(name = "closedBy_repo_id", referencedColumnName = "repo_id")
    })
    private User closedBy;

    @ManyToOne
    @JoinColumns({
        @JoinColumn(name = "openedBy_id", referencedColumnName = "user_id"),
        @JoinColumn(name = "openedBy_repo_id", referencedColumnName = "repo_id")
    })
    @JsonProperty("user")
    private User openedBy;

    private Long repoId;

    ///////////////////////////////////////////////
    // Getter & Setter
    ///////////////////////////////////////////////
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public LocalDateTime getDateOpened() {
        return dateOpened;
    }

    public void setDateOpened(LocalDateTime dateOpened) {
        this.dateOpened = dateOpened;
    }

    public LocalDateTime getDateClosed() {
        return dateClosed;
    }

    public void setDateClosed(LocalDateTime dateClosed) {
        this.dateClosed = dateClosed;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public User getClosedBy() {
        return closedBy;
    }

    public void setClosedBy(User closedBy) {
        this.closedBy = closedBy;
    }

    public User getOpenedBy() {
        return openedBy;
    }

    public void setOpenedBy(User openedBy) {
        this.openedBy = openedBy;
    }

    public Long getRepoId() {
        return repoId;
    }

    public void setRepoId(Long repoId) {
        this.repoId = repoId;
    }
}

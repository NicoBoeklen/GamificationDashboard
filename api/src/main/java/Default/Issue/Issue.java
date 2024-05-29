package Default.Issue;

import Default.GithubRepo.GithubRepo;
import Default.User.User;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Table(name = "\"issue\"")
public class Issue {
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getDateOpened() {
        return dateOpened;
    }

    public void setDateOpened(Date dateOpened) {
        this.dateOpened = dateOpened;
    }

    public Date getClosed() {
        return Closed;
    }

    public void setClosed(Date closed) {
        Closed = closed;
    }

    public String getState() {
        return State;
    }

    public void setState(String state) {
        State = state;
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

    public GithubRepo getRepository() {
        return repository;
    }

    public void setRepository(GithubRepo repository) {
        this.repository = repository;
    }

    @Id
    @JsonProperty("sha")
    private String id;
    private Date dateOpened;

    private Date Closed;
    private String State;
    @OneToOne
    @JoinColumn(name = "closedBy_id")
    @JsonProperty("author")
    private User closedBy;
    @OneToOne
    @JoinColumn(name = "openedBy_id")
    @JsonProperty("author")
    private User openedBy;
    @OneToOne
    @JoinColumn(name = "author_id")
    @JsonProperty("author")
    private GithubRepo repository;
}

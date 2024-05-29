package Default.Issue;

import Default.GithubRepo.GithubRepo;
import Default.User.User;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Table(name = "\"issue\"")
public class Issue {
    @Id
    @JsonProperty("number")
    private Integer id;
    
    @JsonProperty("created_at")
    private Date dateOpened;
    
    @JsonProperty("closed_at")
    private Date dateClosed;
    
    private String state;
/*
    @OneToOne
    @JoinColumn(name = "closedBy_id")
    @JsonProperty("closed_by")
    private User closedBy;
    
    @OneToOne
    @JoinColumn(name = "openedBy_id")
    @JsonProperty("id")
    private User openedBy;
     */
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDateOpened() {
        return dateOpened;
    }

    public void setDateOpened(Date dateOpened) {
        this.dateOpened = dateOpened;
    }

    public Date getDateClosed() {
        return dateClosed;
    }

    public void setDateClosed(Date dateClosed) {
        this.dateClosed = dateClosed;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
    /*
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
    
    */
}

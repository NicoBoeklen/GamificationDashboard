package Default.Issue;

import Default.User.User;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "\"issue\"")
public class Issue {
    @Id
    @JsonProperty("number")
    private Integer id;

    @JsonProperty("created_at")
    private LocalDateTime dateOpened;

    @JsonProperty("closed_at")
    private LocalDateTime dateClosed;

    private String state;

    @ManyToOne
    @JoinColumn(name = "closedBy_id")
    private User closedBy;

    @ManyToOne
    @JoinColumn(name = "openedBy_id")
    @JsonProperty("user")
    private User openedBy;


    ///////////////////////////////////////////////
    // Getter & Setter
    ///////////////////////////////////////////////
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
    
}

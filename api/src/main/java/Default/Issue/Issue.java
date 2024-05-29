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

package Default.Issue;

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

}

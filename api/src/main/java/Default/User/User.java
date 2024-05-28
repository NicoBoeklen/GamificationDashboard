package Default.User;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "\"user\"")
public class User {
    @Id
    private Long id;

    @Column(name = "name")
    private String login;
    
    private Integer level;

    @JsonProperty("avatar_url") // Dieses Attribut gibt an, dass das JSON-Feld avatar_url diesem Java-Feld entspricht
    private String avatarUrl;

    public User() {
        this.level = 0;
    }   
    
    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getAvatarURL() {
        return avatarUrl;
    }

    public void setAvatarURL(String avatarURL) {
        this.avatarUrl = avatarURL;
    }
}

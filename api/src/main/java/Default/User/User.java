package Default.User;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@IdClass(UserRepoId.class)
@Table(name = "\"user\"")
public class User {

    @Id
    @Column(name = "user_id")
    @JsonProperty("id")
    private Long userId;

    @Id
    @Column(name = "repo_id")
    private Long repoId;
        
    @JsonProperty("login")
    private String name;

    private Integer level;

    @JsonProperty("avatar_url")
    private String avatarUrl;

    public User() {
        this.level = 0;
    }

    
    ///////////////////////////////////////////////
    // Getter & Setter
    ///////////////////////////////////////////////

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getRepoId() {
        return repoId;
    }

    public void setRepoId(Long repoId) {
        this.repoId = repoId;
    }

   

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

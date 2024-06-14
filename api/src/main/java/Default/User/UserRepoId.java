package Default.User;

import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class UserRepoId implements Serializable {

    private Long userId;
    private Long repoId;

    public UserRepoId() {
    }

    public UserRepoId(Long userId, Long repoId) {
        this.userId = userId;
        this.repoId = repoId;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserRepoId that = (UserRepoId) o;
        return Objects.equals(userId, that.userId) && Objects.equals(repoId, that.repoId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, repoId);
    }
}

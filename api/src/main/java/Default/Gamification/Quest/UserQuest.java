package Default.Gamification.Quest;

import Default.User.User;
import jakarta.persistence.*;

@Entity
@Table(name = "\"userQuest\"")
public class UserQuest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumns({
        @JoinColumn(name = "user_id", referencedColumnName = "user_id"),
        @JoinColumn(name = "repo_id", referencedColumnName = "repo_id")
    })
    private User user;

    @ManyToOne
    @JoinColumn(name = "quest_id")
    private Quest quest;

    public UserQuest() {
    }

    public UserQuest(User user, Quest quest) {
        this.user = user;
        this.quest = quest;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Quest getQuest() {
        return quest;
    }

    public void setQuest(Quest quest) {
        this.quest = quest;
    }
}

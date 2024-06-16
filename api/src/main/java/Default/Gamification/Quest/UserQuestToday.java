package Default.Gamification.Quest;

import Default.User.User;

public class UserQuestToday {

    private Quest quest;
    private User user;
    private Long progress;

    public UserQuestToday(Quest quest, User user, Long progress) {
        this.quest = quest;
        this.user = user;
        this.progress = progress;
    }

    public Quest getQuest() {
        return quest;
    }

    public void setQuest(Quest quest) {
        this.quest = quest;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getProgress() {
        return progress;
    }

    public void setProgress(Long progress) {
        this.progress = progress;
    }
}


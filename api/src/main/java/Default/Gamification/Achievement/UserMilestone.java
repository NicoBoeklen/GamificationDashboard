package Default.Gamification.Achievement;

import Default.User.User;

public class UserMilestone {
    private User user;
    private Achievement achievement;
    private Long progress;

    public UserMilestone(User user, Achievement achievement, Long progress) {
        this.user = user;
        this.achievement = achievement;
        this.progress = progress;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Achievement getAchievement() {
        return achievement;
    }

    public void setAchievement(Achievement achievement) {
        this.achievement = achievement;
    }

    public Long getProgress() {
        return progress;
    }

    public void setProgress(Long progress) {
        this.progress = progress;
    }
}

package Default.Gamification;

import Default.User.User;

import java.util.List;
import java.util.Map;

public class Leaderboard {
    
    private Map<String, Double> leaderboardMap;
    
    private List<User> userList;

    public Leaderboard(Map<String, Double> leaderboardMap) {
        this.leaderboardMap = leaderboardMap;
    }
    
    public Leaderboard() {
        
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    public Map<String, Double> getLeaderboardMap() {
        return leaderboardMap;
    }

    public void setLeaderboardMap(Map<String, Double> leaderboardMap) {
        this.leaderboardMap = leaderboardMap;
    }
}

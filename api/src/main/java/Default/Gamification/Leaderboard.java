package Default.Gamification;

import java.util.Map;

public class Leaderboard {
    
    private Map<String, Double > leaderboardMap;

    public Leaderboard(Map<String, Double> leaderboardMap) {
        this.leaderboardMap = leaderboardMap;
    }
    
    public Leaderboard() {
        
    }

    public Map<String, Double> getLeaderboardMap() {
        return leaderboardMap;
    }

    public void setLeaderboardMap(Map<String, Double> leaderboardMap) {
        this.leaderboardMap = leaderboardMap;
    }
}

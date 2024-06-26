package Default.Gamification.Quest;

import Default.User.User;
import Default.User.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
public class QuestController {
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private QuestService questService;

    @GetMapping("/quest/{userId}/{repoId}")
    public List<UserQuestToday> getUserQuests(@PathVariable Long userId, @PathVariable Long repoId) {
        try {
            setQuests();
            User user = userService.findById(userId, repoId).orElseThrow(NoSuchElementException::new);
            return questService.checkAndAwardQuests(user);
            //return questService.getQuests(user);
        } catch (NoSuchElementException e) {
            return new ArrayList<>();
        }
    }

    private ResponseEntity<?> setQuests() {
        questService.setQuests();
        return ResponseEntity.ok("Quests created");
    }
}

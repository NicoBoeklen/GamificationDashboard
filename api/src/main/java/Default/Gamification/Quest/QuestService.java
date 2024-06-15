package Default.Gamification.Quest;

import Default.User.User;
import Default.User.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestService {

    @Autowired
    private QuestRepository questRepository;

    @Autowired
    private UserQuestRepository userQuestRepository;

    @Autowired
    private UserService userService;

    public void checkAndAwardQuests(User user) {
        // Hier alle Achievements durchgehen und prüfen, ob der User die Bedingungen erfüllt
        List<Quest> quests = questRepository.findAll();

        for (Quest quest : quests) {
            switch (quest.getType()) {
                case "commits":
                    break;
            }
        }
    }

    private void awardQuests(User user, Quest quest) {
        boolean alreadyAwarded = userQuestRepository.existsByUserAndQuest(user, quest);
        if (!alreadyAwarded) {
            userQuestRepository.save(new UserQuest(user, quest));
            user.setLevel(user.getLevel() + quest.getXp());
            userService.saveUser2(user);
        }
    }

    public List<UserQuest> getQuests(User user) {
        return userQuestRepository.findAll().stream().filter(quest -> quest.getUser().equals(user)).toList();
    }

    public void setQuests() {
        if (questRepository.findAll().size() == 0) {
            List<Quest> quests = new ArrayList<>();

            questRepository.saveAll(quests);
        }
    }
}
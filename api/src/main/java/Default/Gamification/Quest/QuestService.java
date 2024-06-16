package Default.Gamification.Quest;

import Default.Commit.CommitService;
import Default.Issue.IssueService;
import Default.PullRequest.PullRequestService;
import Default.User.User;
import Default.User.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class QuestService {

    @Autowired
    private QuestRepository questRepository;

    @Autowired
    private UserQuestRepository userQuestRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private IssueService issueService;

    @Autowired
    private CommitService commitService;

    @Autowired
    private PullRequestService pullRequestService;

    public List<UserQuestToday> checkAndAwardQuests(User user) {
        // Hier alle Achievements durchgehen und prüfen, ob der User die Bedingungen erfüllt
        List<Quest> quests = questRepository.findAll();
        List<UserQuestToday> todaysQuests = new ArrayList<>();

        for (Quest quest : quests) {
            switch (quest.getType()) {
                case "commits":
                    Long userCommits = commitService.getCommitsUserByDay(user.getUserId(), user.getRepoId(), quest.getDay());
                    if (userCommits >= quest.getCondition()) {
                        awardQuests(user, quest);
                    } 
                    if (quest.getDay().equals(LocalDate.now())) {
                        todaysQuests.add(new UserQuestToday(quest, user, userCommits));
                    }
                    break;
                case "issues":
                    int userIssues = issueService.getTotalClosedIssuesUserByDay(user.getUserId(), user.getRepoId(), quest.getDay());
                    if (userIssues >= quest.getCondition()) {
                        awardQuests(user, quest);
                    } 
                    if (quest.getDay().equals(LocalDate.now())) {
                        todaysQuests.add(new UserQuestToday(quest, user, (long) userIssues));
                    }
                    break;
                case "linesOfCodeAdded":
                    int userLoC = commitService.getAdditionCountByDay(user.getUserId(), user.getRepoId(), quest.getDay());
                    if (userLoC >= quest.getCondition()) {
                        awardQuests(user, quest);
                    } 
                    if (quest.getDay().equals(LocalDate.now())) {
                        todaysQuests.add(new UserQuestToday(quest, user, (long) userLoC));
                    }
                    break;
                case "pullRequests":
                    int userReviews = pullRequestService.getNumberReviewsByDay(user.getUserId(), user.getRepoId(), quest.getDay());
                    if (userReviews >= quest.getCondition()) {
                        awardQuests(user, quest);
                    }  
                    if (quest.getDay().equals(LocalDate.now())) {
                        todaysQuests.add(new UserQuestToday(quest, user, (long) userReviews));
                    }
                    break;
                case "pullRequestsTeam":
                    int teamReviews = pullRequestService.getTeamReviewsByDay(user.getRepoId(), quest.getDay());
                    if (teamReviews >= quest.getCondition()) {
                        awardQuests(user, quest);
                    }  
                    if (quest.getDay().equals(LocalDate.now())) {
                        todaysQuests.add(new UserQuestToday(quest, user, (long) teamReviews));
                    }
                    break;
                case "commitsTeam":
                    int teamCommits = commitService.getTotalCommitsByDay(user.getRepoId(), quest.getDay());
                    if (teamCommits >= quest.getCondition()) {
                        awardQuests(user, quest);
                    }  
                    if (quest.getDay().equals(LocalDate.now())) {
                        todaysQuests.add(new UserQuestToday(quest, user, (long) teamCommits));
                    }
                    break;
                case "issuesTeam":
                    int teamIssues = issueService.getFixedIssuesTeamByDay(user.getRepoId(), quest.getDay());
                    if (teamIssues >= quest.getCondition()) {
                        awardQuests(user, quest);
                    }  
                    if (quest.getDay().equals(LocalDate.now())) {
                        todaysQuests.add(new UserQuestToday(quest, user, (long) teamIssues));
                    }
                    break;
                case "linesOfCodeAddedTeam":
                    Long teamLoC = commitService.getTotalLoCAddedByDay(user.getRepoId(), quest.getDay());
                    if (teamLoC >= quest.getCondition()) {
                        awardQuests(user, quest);
                    }  
                    if (quest.getDay().equals(LocalDate.now())) {
                        todaysQuests.add(new UserQuestToday(quest, user, teamLoC));
                    }
                    break;
            }
        }
        return todaysQuests;
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
        if (questRepository.findAll().stream().map(q -> q.getDay().equals(LocalDate.now())).count()==0) {
            List<Quest> teamQuests = new ArrayList<>();
            List<Quest> quests = new ArrayList<>();
            
            //Team Quests
            teamQuests.add(new Quest("5 commits", "Make 5 commits.", 30, "commitsTeam", 5, LocalDate.now()));
            teamQuests.add(new Quest("6 commits", "Make 6 commits.", 30, "commitsTeam", 6, LocalDate.now()));
            teamQuests.add(new Quest("7 commits", "Make 7 commits.", 30, "commitsTeam", 7, LocalDate.now()));
            teamQuests.add(new Quest("8 commits", "Make 8 commits.", 30, "commitsTeam", 8, LocalDate.now()));
            teamQuests.add(new Quest("9 commits", "Make 9 commits.", 30, "commitsTeam", 9, LocalDate.now()));
            teamQuests.add(new Quest("10 commits", "Make 10 commits.", 30, "commitsTeam", 10, LocalDate.now()));
            teamQuests.add(new Quest("2 issues", "Fix 2 issues.", 30, "issuesTeam", 2, LocalDate.now()));
            teamQuests.add(new Quest("3 issues", "Fix 3 issues.", 30, "issuesTeam", 3, LocalDate.now()));
            teamQuests.add(new Quest("Pull Request", "Review 1 Pull Request.", 30, "pullRequestsTeam", 1, LocalDate.now()));
            teamQuests.add(new Quest("200 LoC", "Write 200 Lines of Code.", 30, "linesOfCodeAddedTeam", 200, LocalDate.now()));
            teamQuests.add(new Quest("300 LoC", "Write 300 Lines of Code.", 30, "linesOfCodeAddedTeam", 300, LocalDate.now()));
            teamQuests.add(new Quest("400 LoC", "Write 400 Lines of Code.", 30, "linesOfCodeAddedTeam", 400, LocalDate.now()));

            //Individuelle Quests
            quests.add(new Quest("1 commits", "Make 1 commits.", 10, "commits", 1, LocalDate.now()));
            quests.add(new Quest("2 commits", "Make 2 commits.", 10, "commits", 2, LocalDate.now()));
            quests.add(new Quest("3 commits", "Make 3 commits.", 10, "commits", 3, LocalDate.now()));
            quests.add(new Quest("Issue", "Fix 1 issue.", 10, "issues", 1, LocalDate.now()));
            quests.add(new Quest("Pull Request", "Review 1 Pull Request.", 10, "pullRequests", 1, LocalDate.now()));
            quests.add(new Quest("50 LoC", "Write 50 Lines of Code.", 10, "linesOfCodeAdded", 50, LocalDate.now()));
            quests.add(new Quest("75 LoC", "Write 75 Lines of Code.", 10, "linesOfCodeAdded", 75, LocalDate.now()));
            quests.add(new Quest("100 LoC", "Write 100 Lines of Code.", 10, "linesOfCodeAdded", 100, LocalDate.now()));

            Random random = new Random();
            questRepository.save(quests.get(random.nextInt(8)));
            questRepository.save(teamQuests.get(random.nextInt(12)));
        }
    }
}
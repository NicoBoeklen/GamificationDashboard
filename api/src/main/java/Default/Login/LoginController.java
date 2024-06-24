package Default.Login;

import Default.Apikey;
import Default.GithubAPI.GithubAPIController;
import Default.GithubAPI.GithubAPIService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class LoginController {
    @Autowired
    LoginService loginService;
    @Autowired
    GithubAPIService githubAPIService;
    @Autowired
    private GithubAPIController githubAPIController;

    @PostMapping("/api/login")
    @ResponseStatus(HttpStatus.CREATED)
    public Login login(@Valid @RequestBody Login loginRequest) {
        
        Login login = new Login();
        login.setOwnerName(loginRequest.getOwnerName());
        login.setRepoName(loginRequest.getRepoName());
        login.setUserName(loginRequest.getUserName());
        Long repoId = githubAPIService.getRepositoryId(login.getOwnerName(), login.getRepoName()).block();
        System.out.println("RepoIdsdaada" + repoId);
        login.setRepoId(repoId);
        githubAPIController.getData(login.getOwnerName(), login.getRepoName(), login.getUserName()).block();
        Long userId = githubAPIService.getUserIdByNameAndRepo(login.getUserName(), repoId);
        System.out.println("UserId: " + userId);
        login.setUserId(userId);
        login.setRepoId(repoId);
        login.setApiKey(Apikey.Key.apiKey);
        return loginService.saveLogin(login);
    }
    @GetMapping("/api/loggedUser/{sessionId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Login getLoggedUser(@PathVariable Long sessionId) {
        return loginService.getLoggedUser(sessionId);
    }
}

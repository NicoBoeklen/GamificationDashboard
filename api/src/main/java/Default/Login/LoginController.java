package Default.Login;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
    @Autowired
    LoginService loginService;
    
    @PostMapping("/login")
    @ResponseStatus(HttpStatus.CREATED)
    public Login login(@Valid @RequestBody Login loginRequest) {
        loginService.deleteAll();
        Login login = new Login();
        login.setOwnerName(loginRequest.getOwnerName());
        login.setRepoName(loginRequest.getRepoName());
        login.setUserName(loginRequest.getUserName());
        return loginService.saveLogin(login);
    }
    @PostMapping("/api/loggedUser")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Login getLoggedUsers(@Valid @RequestBody Long sessionId) {
        return loginService.getLoggedUsers(sessionId);
    }
}

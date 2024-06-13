package Default.Login;

import Default.Apikey;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class LoginController {
    @Autowired
    LoginService loginService;
    
    @PostMapping("/api/login")
    @ResponseStatus(HttpStatus.CREATED)
    public Login login(@Valid @RequestBody Login loginRequest) {
        
        Login login = new Login();
        login.setOwnerName(loginRequest.getOwnerName());
        login.setRepoName(loginRequest.getRepoName());
        login.setUserName(loginRequest.getUserName());
        //@TODO: Noch zu ändern mit API Key
        login.setApiKey(Apikey.Key.apiKey);
        return loginService.saveLogin(login);
    }
    @GetMapping("/api/loggedUser/{sessionId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Login getLoggedUser(@PathVariable Long sessionId) {
        return loginService.getLoggedUser(sessionId);
    }
}

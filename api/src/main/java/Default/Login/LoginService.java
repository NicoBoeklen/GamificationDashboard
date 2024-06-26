package Default.Login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    @Autowired
    LoginRepository loginRepository;

    public Login saveLogin(Login login) {
        return loginRepository.save(login);
    }
    public Login getLoggedUser(Long sessionId) {
        return loginRepository.getLoggedUser(sessionId);
    }
    public void deleteAll() {
        loginRepository.deleteAll();
    }
}

package Default.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    /**
     * Saves a User in the repository
     *
     * @param user User to be saved
     * @return The User commit
     */
    public User saveUser(User user) {
        return userRepository.save(user);
    }
}

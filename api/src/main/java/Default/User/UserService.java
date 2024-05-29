package Default.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    /**
     * Save a user to the repository
     *
     * @param user User to be saved
     * @return Mono<User> indicating completion
     */
    public Mono<User> saveUser(User user) {
        return Mono.fromCallable(() -> userRepository.save(user));
    }
}

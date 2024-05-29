package Default.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Optional;
import java.util.List;

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

    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }
    public List<User> getAllUser() {
        return userRepository.findAll();
    }
}

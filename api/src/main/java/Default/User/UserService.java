package Default.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;

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
    
    public Optional<User> findById(Long userId, Long repoId) {
        return userRepository.findById(new UserRepoId(userId, repoId));
    }
    
    public List<User> findAll() {
        return userRepository.findAll();
    }
    public String getUserAvatar(String username) {
        return userRepository.getUserAvatar(username);
    }
}

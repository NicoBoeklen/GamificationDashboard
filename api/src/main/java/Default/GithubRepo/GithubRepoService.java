package Default.GithubRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Service
public class GithubRepoService {

    @Autowired
    private GithubRepoRepository repoRepository;

    /**
     * Saves a Repo in the JPA-repository
     *
     * @param repo Repository to be saved
     * @return The saved repo
     */
    public GithubRepo saveRepo(GithubRepo repo) {
        return repoRepository.save(repo);
    }

    public Mono<GithubRepo> findById(Long repoId) {
        return Mono.justOrEmpty(repoRepository.findById(repoId));
    }
}

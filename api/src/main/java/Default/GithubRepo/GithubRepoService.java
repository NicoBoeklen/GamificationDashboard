package Default.GithubRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GithubRepoService {
    @Autowired
    private GithubRepoRepository repoRepository;

    public GithubRepo saveRepo(GithubRepo repo) {
        return repoRepository.save(repo);
    }
}

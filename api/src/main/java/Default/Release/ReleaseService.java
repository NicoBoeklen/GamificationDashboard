package Default.Release;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReleaseService {
    
    @Autowired
    private ReleaseRepository releaseRepository;

    /**
     * Save a Release to the repository
     *
     * @param release Release to be saved
     * @return Release indicating completion
     */
    public Release saveRelease(Release release) {
        return releaseRepository.save(release);
    }

}

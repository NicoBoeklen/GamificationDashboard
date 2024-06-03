package Default.Release;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.List;

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

    public Integer getNumberOfReleases() {
        return releaseRepository.getNumberOfRelease();
    }

    public List<Release> getAllRelease() {
        return releaseRepository.findAll();
    }

    public Double getAverageTimeBetweenReleases() {
        Pageable pageable = PageRequest.of(0, 5);
        List<Release> releases = releaseRepository.findLastFiveReleases(pageable);
        if (releases.size() < 2) {
            // Not enough releases to calculate the average time between them
            return 0.0;
        }
        // Calculate the time differences between consecutive releases
        double totalHours = 0.0;
        for (int i = 0; i < releases.size() - 1; i++) {
            Duration duration = Duration.between(releases.get(i + 1).getPublishedAt(), releases.get(i).getPublishedAt());
            totalHours += duration.toHours();
        }
        double averageHours = totalHours / (releases.size() - 1);
        return Math.round(averageHours * 10.0) / 10.0;
    }
}

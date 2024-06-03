package Default.Release.Stats;

import Default.Release.Release;

import java.util.List;

public class ReleaseMetrics {
    
    private Integer numberOfReleases;
    
    private Double averageTimeBetweenReleases;
    
    private List<Release> releaseList;

    public ReleaseMetrics(Integer numberOfReleases, Double averageTimeBetweenReleases, List<Release> releaseList) {
        this.numberOfReleases = numberOfReleases;
        this.averageTimeBetweenReleases = averageTimeBetweenReleases;
        this.releaseList = releaseList;
    }

    public Integer getNumberOfReleases() {
        return numberOfReleases;
    }

    public void setNumberOfReleases(Integer numberOfReleases) {
        this.numberOfReleases = numberOfReleases;
    }

    public Double getAverageTimeBetweenReleases() {
        return averageTimeBetweenReleases;
    }

    public void setAverageTimeBetweenReleases(Double averageTimeBetweenReleases) {
        this.averageTimeBetweenReleases = averageTimeBetweenReleases;
    }

    public List<Release> getReleaseList() {
        return releaseList;
    }

    public void setReleaseList(List<Release> releaseList) {
        this.releaseList = releaseList;
    }
}

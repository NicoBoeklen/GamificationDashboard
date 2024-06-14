package Default.Release.Stats;

import Default.Release.Release;

import java.util.List;

public class ReleaseMetrics {
    
    private Integer numberOfReleases;
    
    private Double averageTimeBetweenReleasesInDays;
    
    private List<Release> releaseList;

    public ReleaseMetrics(Integer numberOfReleases, Double averageTimeBetweenReleases, List<Release> releaseList) {
        this.numberOfReleases = numberOfReleases;
        this.averageTimeBetweenReleasesInDays = averageTimeBetweenReleases;
        this.releaseList = releaseList;
    }

    public Integer getNumberOfReleases() {
        return numberOfReleases;
    }

    public void setNumberOfReleases(Integer numberOfReleases) {
        this.numberOfReleases = numberOfReleases;
    }

    public Double getAverageTimeBetweenReleases() {
        return averageTimeBetweenReleasesInDays;
    }

    public void setAverageTimeBetweenReleases(Double averageTimeBetweenReleases) {
        this.averageTimeBetweenReleasesInDays = averageTimeBetweenReleases;
    }

    public List<Release> getReleaseList() {
        return releaseList;
    }

    public void setReleaseList(List<Release> releaseList) {
        this.releaseList = releaseList;
    }
}

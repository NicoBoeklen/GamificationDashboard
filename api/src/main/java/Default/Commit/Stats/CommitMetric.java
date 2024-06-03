package Default.Commit.Stats;

import java.util.List;

public class CommitMetric {
    
    private List<CodeGrowth> codeGrowthList;
    
    private Integer commitCountUser;
    
    private Integer deletionCountUser;
    
    private Integer additionCountUser;

    private Double averageAdditionsUser;

    private Double averageDeletionsUser;
    
    private List<CommitsUser> commitsUser;
    
    private Double productivityUser;
    
    private Long totalLoC;
    
    private Integer numberOfContributors;
    
    private Integer commitCountTotal;

    public CommitMetric(List<CodeGrowth> codeGrowthList, Integer commitCountUser, Integer deletionCountUser, Integer additionCountUser, Double averageAdditionsUser, Double averageDeletionsUser, List<CommitsUser> commitsUser, Double productivityUser, Integer numberOfContributors, Long totalLoC, Integer commitCountTotal) {
        this.codeGrowthList = codeGrowthList;
        this.commitCountUser = commitCountUser;
        this.deletionCountUser = deletionCountUser;
        this.additionCountUser = additionCountUser;
        this.averageAdditionsUser = averageAdditionsUser;
        this.averageDeletionsUser = averageDeletionsUser;
        this.commitsUser = commitsUser;
        this.productivityUser = productivityUser;
        this.numberOfContributors = numberOfContributors;
        this.totalLoC = totalLoC;
        this.commitCountTotal = commitCountTotal;
    }

    public Integer getCommitCountTotal() {
        return commitCountTotal;
    }

    public void setCommitCountTotal(Integer commitCountTotal) {
        this.commitCountTotal = commitCountTotal;
    }

    public Long getTotalLoC() {
        return totalLoC;
    }

    public void setTotalLoC(Long totalLoC) {
        this.totalLoC = totalLoC;
    }

    public Integer getNumberOfContributors() {
        return numberOfContributors;
    }

    public void setNumberOfContributors(Integer numberOfContributors) {
        this.numberOfContributors = numberOfContributors;
    }

    public Double getProductivityUser() {
        return productivityUser;
    }

    public void setProductivityUser(Double productivityUser) {
        this.productivityUser = productivityUser;
    }

    public List<CommitsUser> getCommitsUser() {
        return commitsUser;
    }

    public void setCommitsUser(List<CommitsUser> commitsUser) {
        this.commitsUser = commitsUser;
    }

    public List<CodeGrowth> getCodeGrowthList() {
        return codeGrowthList;
    }

    public void setCodeGrowthList(List<CodeGrowth> codeGrowthList) {
        this.codeGrowthList = codeGrowthList;
    }

    public Integer getCommitCountUser() {
        return commitCountUser;
    }

    public void setCommitCountUser(Integer commitCountUser) {
        this.commitCountUser = commitCountUser;
    }

    public Integer getDeletionCountUser() {
        return deletionCountUser;
    }

    public void setDeletionCountUser(Integer deletionCountUser) {
        this.deletionCountUser = deletionCountUser;
    }

    public Integer getAdditionCountUser() {
        return additionCountUser;
    }

    public void setAdditionCountUser(Integer additionCountUser) {
        this.additionCountUser = additionCountUser;
    }

    public Double getAverageAdditionsUser() {
        return averageAdditionsUser;
    }

    public void setAverageAdditionsUser(Double averageAdditionsUser) {
        this.averageAdditionsUser = averageAdditionsUser;
    }

    public Double getAverageDeletionsUser() {
        return averageDeletionsUser;
    }

    public void setAverageDeletionsUser(Double averageDeletionsUser) {
        this.averageDeletionsUser = averageDeletionsUser;
    }
}

package org.hwbot.api.esports;

import java.util.List;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;
import org.hwbot.api.generic.dto.SubmissionDTO;

/**
 * Roadmap for pro oc, challenger, etc on esports site.
 * 
 * @author frederik
 * 
 */
@JsonSerialize(include = Inclusion.NON_NULL)
public class CompetitionStageDTO {

    private int contestBenchmarkId;
    private String label;
    private long startDate;
    private long endDate;
    private int applicationId;
    private int contestId;
    private String pointsDistribution;
    private CompetitionRoundDTO round;
    private List<SubmissionDTO> submissions;

    // leader

    // derived
    private int participants;
    private String timeLeft;
    private String ends;

    private List<String> limitations;
    private List<SubmissionDTO> latestSubmissions;
    private List<StageRankDTO> stageRanking;

    public CompetitionStageDTO() {
        super();
    }

    public CompetitionStageDTO(int contestBenchmarkId, String label, long startDate, long endDate, int applicationId, int contestId, String pointsDistribution,
            CompetitionRoundDTO round, List<SubmissionDTO> submissions, int participants, String timeLeft, String ends, List<String> limitations,
            List<SubmissionDTO> latestSubmissions, List<StageRankDTO> stageRanking) {
        super();
        this.contestBenchmarkId = contestBenchmarkId;
        this.label = label;
        this.startDate = startDate;
        this.endDate = endDate;
        this.applicationId = applicationId;
        this.contestId = contestId;
        this.pointsDistribution = pointsDistribution;
        this.round = round;
        this.submissions = submissions;
        this.participants = participants;
        this.timeLeft = timeLeft;
        this.ends = ends;
        this.limitations = limitations;
        this.latestSubmissions = latestSubmissions;
        this.stageRanking = stageRanking;
    }

    public List<SubmissionDTO> getSubmissions() {
        return submissions;
    }

    public void setSubmissions(List<SubmissionDTO> submissions) {
        this.submissions = submissions;
    }

    public int getContestBenchmarkId() {
        return contestBenchmarkId;
    }

    public void setContestBenchmarkId(int contestBenchmarkId) {
        this.contestBenchmarkId = contestBenchmarkId;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public long getStartDate() {
        return startDate;
    }

    public void setStartDate(long startdate) {
        this.startDate = startdate;
    }

    public long getEndDate() {
        return endDate;
    }

    public void setEndDate(long enddate) {
        this.endDate = enddate;
    }

    public int getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(int applicationId) {
        this.applicationId = applicationId;
    }

    public int getContestId() {
        return contestId;
    }

    public void setContestId(int contestId) {
        this.contestId = contestId;
    }

    public String getPointsDistribution() {
        return pointsDistribution;
    }

    public void setPointsDistribution(String pointsDistribution) {
        this.pointsDistribution = pointsDistribution;
    }

    public CompetitionRoundDTO getRound() {
        return round;
    }

    public void setRound(CompetitionRoundDTO round) {
        this.round = round;
    }

    public int getParticipants() {
        return participants;
    }

    public void setParticipants(int participants) {
        this.participants = participants;
    }

    public String getTimeLeft() {
        return timeLeft;
    }

    public void setTimeLeft(String timeLeft) {
        this.timeLeft = timeLeft;
    }

    public String getEnds() {
        return ends;
    }

    public void setEnds(String ends) {
        this.ends = ends;
    }

    public List<String> getLimitations() {
        return limitations;
    }

    public void setLimitations(List<String> limitations) {
        this.limitations = limitations;
    }

    public List<SubmissionDTO> getLatestSubmissions() {
        return latestSubmissions;
    }

    public void setLatestSubmissions(List<SubmissionDTO> latestSubmissions) {
        this.latestSubmissions = latestSubmissions;
    }

    public List<StageRankDTO> getStageRanking() {
        return stageRanking;
    }

    public void setStageRanking(List<StageRankDTO> stageRanking) {
        this.stageRanking = stageRanking;
    }

}

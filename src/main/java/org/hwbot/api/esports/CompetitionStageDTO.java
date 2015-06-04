package org.hwbot.api.esports;

import java.text.SimpleDateFormat;
import java.util.List;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;
import org.hwbot.api.generic.dto.ApplicationDTO;
import org.hwbot.api.generic.dto.SubmissionDTO;

/**
 * Roadmap for pro oc, challenger, etc on esports site.
 * 
 * @author frederik
 * 
 */
@JsonSerialize(include = Inclusion.NON_NULL)
public class CompetitionStageDTO extends DiscussableDTO {

    private static final long serialVersionUID = 1L;

    private int id;
    private String label;
    private String safeName;
    private String roundSafeName;
    private long startDate;
    private long endDate;
    private int applicationId;
    private int contestId;
    private String pointsDistribution;
    private CompetitionRoundDTO round;
    private String unit;
    private String type;
    private String excerpt;
    private String excerptLead;
    private String mobileBanner;
    private ApplicationDTO benchmark;

    // leader

    // derived
    private int participants;
    private String timeLeft;
    private String ends;

    private List<String> limitations;
    private List<SubmissionDTO> latestSubmissions;
    private List<RankDTO> stageRanking;

    public CompetitionStageDTO() {
        super();
    }

    public CompetitionStageDTO(int contestBenchmarkId, String label, long startDate, long endDate, int applicationId, int contestId, String pointsDistribution,
            CompetitionRoundDTO round, int participants, String timeLeft, String ends, List<String> limitations, List<SubmissionDTO> latestSubmissions,
            List<RankDTO> stageRanking, String excerpt, String excerptLead) {
        super();
        this.id = contestBenchmarkId;
        this.label = label;
        this.startDate = startDate;
        this.endDate = endDate;
        this.applicationId = applicationId;
        this.contestId = contestId;
        this.pointsDistribution = pointsDistribution;
        this.round = round;
        this.participants = participants;
        this.timeLeft = timeLeft;
        this.ends = ends;
        this.limitations = limitations;
        this.latestSubmissions = latestSubmissions;
        this.stageRanking = stageRanking;
        this.excerpt = excerpt;
        this.excerptLead = excerptLead;
    }

    public String getMobileBanner() {
        return mobileBanner;
    }

    public void setMobileBanner(String mobileBanner) {
        this.mobileBanner = mobileBanner;
    }

    public String getExcerptLead() {
        return excerptLead;
    }

    public void setExcerptLead(String excerptLead) {
        this.excerptLead = excerptLead;
    }

    public String getExcerpt() {
        return excerpt;
    }

    public void setExcerpt(String excerpt) {
        this.excerpt = excerpt;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int contestBenchmarkId) {
        this.id = contestBenchmarkId;
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

    public List<RankDTO> getStageRanking() {
        return stageRanking;
    }

    public void setStageRanking(List<RankDTO> stageRanking) {
        this.stageRanking = stageRanking;
    }

    public String getSafeName() {
        return safeName;
    }

    public void setSafeName(String safeName) {
        this.safeName = safeName;
    }

    public String getRoundSafeName() {
        return roundSafeName;
    }

    public void setRoundSafeName(String roundSafeName) {
        this.roundSafeName = roundSafeName;
    }

    public String getStartDateFormatted() {
        return new SimpleDateFormat("dd.MM.yyyy").format(startDate);
    }

    public String getStartTimeFormatted() {
        return new SimpleDateFormat("HH:mm zz").format(startDate);
    }


    public String getEndDateFormatted() {
        return new SimpleDateFormat("dd.MM.yyyy").format(endDate);
    }

    public String getEndTimeFormatted() {
        return new SimpleDateFormat("HH:mm zz").format(endDate);
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public boolean isActive() {
        return !isPassed() && (this.startDate < System.currentTimeMillis());
    }

    public boolean isPassed() {
        return (this.endDate <= System.currentTimeMillis());
    }

    public ApplicationDTO getBenchmark() {
        return benchmark;
    }

    public void setBenchmark(ApplicationDTO benchmark) {
        this.benchmark = benchmark;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("CompetitionStageDTO{");
        sb.append("id=").append(id);
        sb.append(", label='").append(label).append('\'');
        sb.append(", safeName='").append(safeName).append('\'');
        sb.append(", roundSafeName='").append(roundSafeName).append('\'');
        sb.append(", startDate=").append(startDate);
        sb.append(", endDate=").append(endDate);
        sb.append(", applicationId=").append(applicationId);
        sb.append(", contestId=").append(contestId);
        sb.append(", pointsDistribution='").append(pointsDistribution).append('\'');
        sb.append(", round=").append(round);
        sb.append(", unit='").append(unit).append('\'');
        sb.append(", type='").append(type).append('\'');
        sb.append(", excerpt='").append(excerpt).append('\'');
        sb.append(", excerptLead='").append(excerptLead).append('\'');
        sb.append(", mobileBanner='").append(mobileBanner).append('\'');
        sb.append(", participants=").append(participants);
        sb.append(", timeLeft='").append(timeLeft).append('\'');
        sb.append(", ends='").append(ends).append('\'');
        sb.append(", limitations=").append(limitations);
        sb.append(", latestSubmissions=").append(latestSubmissions);
        sb.append(", stageRanking=").append(stageRanking);
        sb.append('}');
        return sb.toString();
    }
}

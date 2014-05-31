package org.hwbot.api.esports;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;
import org.hwbot.api.generic.dto.CountryDTO;
import org.hwbot.api.generic.dto.SubmissionDTO;
import org.hwbot.api.generic.dto.TeamDTO;
import org.hwbot.api.generic.dto.UserDTO;

/**
 * Competition info for esports.
 * 
 * @author frederik
 * 
 */
@JsonSerialize(include = Inclusion.NON_NULL)
public class CompetitionRoundDTO implements Serializable {

    private static final long serialVersionUID = 2L;

    private int id;
    private Integer parentId;

    private Date startDate;
    private Date endDate;

    private String name;
    private String safeName;

    private String competitorType;
    private boolean openParticipation = false;
    private boolean closed = false;

    private String pictureLarge;
    private String partnerPicture;

    private String description;
    private String excerptLead;
    private String excerpt;
    private String rules;

    private Integer level;
    private boolean live = false;
    private boolean notHosted = false;
    private boolean event = false;

    private List<CompetitionStageDTO> stages;
    private List<UserDTO> participants;
    private List<RankDTO> roundRanking;
    private List<SubmissionDTO> latestSubmissions;
    private UserDTO leadingMember;
    private TeamDTO leadingTeam;
    private CountryDTO leadingCountry;

    public CompetitionRoundDTO(int contestId, Integer parentContestId, Date startdate, Date enddate, String name, String safeName, String competitorType,
            boolean openParticipation, boolean closed, String pictureLarge, String partnerPicture, Integer level) {
        super();
        this.id = contestId;
        this.parentId = parentContestId;
        this.startDate = startdate;
        this.endDate = enddate;
        this.name = name;
        this.safeName = safeName;
        this.competitorType = competitorType;
        this.openParticipation = openParticipation;
        this.closed = closed;
        this.pictureLarge = pictureLarge;
        this.partnerPicture = partnerPicture;
        this.level = level;
    }

    public Integer getParentId() {
        return parentId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public String getName() {
        return name;
    }

    public String getSafeName() {
        return safeName;
    }

    public String getCompetitorType() {
        return competitorType;
    }

    public boolean isOpenParticipation() {
        return openParticipation;
    }

    public boolean isClosed() {
        return closed;
    }

    public String getPictureLarge() {
        return pictureLarge;
    }

    public String getPartnerPicture() {
        return partnerPicture;
    }

    public String getDescription() {
        return description;
    }

    public String getRules() {
        return rules;
    }

    public Integer getLevel() {
        return level;
    }

    public boolean isLive() {
        return live;
    }

    public boolean isNotHosted() {
        return notHosted;
    }

    public boolean isEvent() {
        return event;
    }

    public List<CompetitionStageDTO> getStages() {
        return stages;
    }

    public List<UserDTO> getParticipants() {
        return participants;
    }

    public void setId(int contestId) {
        this.id = contestId;
    }

    public void setParentId(Integer parentContestId) {
        this.parentId = parentContestId;
    }

    public void setStartDate(Date startdate) {
        this.startDate = startdate;
    }

    public void setEndDate(Date enddate) {
        this.endDate = enddate;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSafeName(String safeName) {
        this.safeName = safeName;
    }

    public void setCompetitorType(String competitorType) {
        this.competitorType = competitorType;
    }

    public void setOpenParticipation(boolean openParticipation) {
        this.openParticipation = openParticipation;
    }

    public void setClosed(boolean closed) {
        this.closed = closed;
    }

    public void setPictureLarge(String pictureLarge) {
        this.pictureLarge = pictureLarge;
    }

    public void setPartnerPicture(String partnerPicture) {
        this.partnerPicture = partnerPicture;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setRules(String rules) {
        this.rules = rules;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public void setLive(boolean live) {
        this.live = live;
    }

    public void setNotHosted(boolean notHosted) {
        this.notHosted = notHosted;
    }

    public void setEvent(boolean event) {
        this.event = event;
    }

    public void setStages(List<CompetitionStageDTO> stages) {
        this.stages = stages;
    }

    public void setParticipants(List<UserDTO> participants) {
        this.participants = participants;
    }

    public List<RankDTO> getRoundRanking() {
        return roundRanking;
    }

    public void setRoundRanking(List<RankDTO> roundRanking) {
        this.roundRanking = roundRanking;
    }

    public String getStartDayOfMonth() {
        return new SimpleDateFormat("d").format(startDate);
    }

    public String getEndDayOfMonth() {
        return new SimpleDateFormat("d").format(endDate);
    }

    public String getStartMonth() {
        return new SimpleDateFormat("MMM").format(startDate);
    }

    public String getEndMonth() {
        return new SimpleDateFormat("MMM").format(endDate);
    }

    public boolean isActive() {
        return !isPassed() && (this.getStartDate() != null && this.getStartDate().getTime() < System.currentTimeMillis());
    }

    public boolean isPassed() {
        return (this.getEndDate() == null || this.getEndDate().getTime() <= System.currentTimeMillis());
    }

    public int getId() {
        return id;
    }

    public List<SubmissionDTO> getLatestSubmissions() {
        return latestSubmissions;
    }

    public void setLatestSubmissions(List<SubmissionDTO> latestSubmissions) {
        this.latestSubmissions = latestSubmissions;
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

    public UserDTO getLeadingMember() {
        return leadingMember;
    }

    public void setLeadingMember(UserDTO leadingMember) {
        this.leadingMember = leadingMember;
    }

    public TeamDTO getLeadingTeam() {
        return leadingTeam;
    }

    public void setLeadingTeam(TeamDTO leadingTeam) {
        this.leadingTeam = leadingTeam;
    }

    public CountryDTO getLeadingCountry() {
        return leadingCountry;
    }

    public void setLeadingCountry(CountryDTO leadingCountry) {
        this.leadingCountry = leadingCountry;
    }

}

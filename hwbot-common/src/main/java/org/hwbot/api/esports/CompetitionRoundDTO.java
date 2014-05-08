package org.hwbot.api.esports;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;
import org.hwbot.api.generic.dto.UserDTO;

/**
 * Competition info for esports.
 * 
 * @author frederik
 * 
 */
@JsonSerialize(include = Inclusion.NON_NULL)
public class CompetitionRoundDTO {

    private int contestId;
    private Integer parentContestId;

    private Date startdate;
    private Date enddate;

    private String name;
    private String url;

    private String competitorType;
    private boolean openParticipation = false;
    private boolean closed = false;

    private String pictureLarge;
    private String partnerPicture;

    private String description;
    private String rules;

    private Integer level;
    private boolean live = false;
    private boolean notHosted = false;
    private boolean event = false;

    private List<CompetitionStageDTO> stages;
    private List<UserDTO> participants;
    private List<RoundRankDTO> roundRanking;

    public CompetitionRoundDTO(int contestId, Integer parentContestId, Date startdate, Date enddate, String name, String url, String competitorType,
            boolean openParticipation, boolean closed, String pictureLarge, String partnerPicture, Integer level) {
        super();
        this.contestId = contestId;
        this.parentContestId = parentContestId;
        this.startdate = startdate;
        this.enddate = enddate;
        this.name = name;
        this.url = url;
        this.competitorType = competitorType;
        this.openParticipation = openParticipation;
        this.closed = closed;
        this.pictureLarge = pictureLarge;
        this.partnerPicture = partnerPicture;
        this.level = level;
    }

    public int getContestId() {
        return contestId;
    }

    public Integer getParentContestId() {
        return parentContestId;
    }

    public Date getStartdate() {
        return startdate;
    }

    public Date getEnddate() {
        return enddate;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
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

    public void setContestId(int contestId) {
        this.contestId = contestId;
    }

    public void setParentContestId(Integer parentContestId) {
        this.parentContestId = parentContestId;
    }

    public void setStartdate(Date startdate) {
        this.startdate = startdate;
    }

    public void setEnddate(Date enddate) {
        this.enddate = enddate;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUrl(String url) {
        this.url = url;
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

    public List<RoundRankDTO> getRoundRanking() {
        return roundRanking;
    }

    public void setRoundRanking(List<RoundRankDTO> roundRanking) {
        this.roundRanking = roundRanking;
    }

    public String getStartDayOfMonth() {
        return new SimpleDateFormat("d").format(startdate);
    }

    public String getEndDayOfMonth() {
        return new SimpleDateFormat("d").format(enddate);
    }

    public String getStartMonth() {
        return new SimpleDateFormat("MMM").format(startdate);
    }

    public String getEndMonth() {
        return new SimpleDateFormat("MMM").format(enddate);
    }

    public boolean isActive() {
        return (this.getStartdate() != null && this.getStartdate().getTime() > System.currentTimeMillis());
    }

    public boolean isPassed() {
        return (this.getEnddate() == null || this.getEnddate().getTime() > System.currentTimeMillis());
    }

}

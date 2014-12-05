package org.hwbot.api.esports;

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
public class CompetitionRoundDTO extends DiscussableDTO {

    private static final long serialVersionUID = 2L;

    private int id;
    private Integer parentId;

    private Date startDate;
    private Date endDate;

    private String name;
    private String subtitle;
    private String safeName;
    private String type;

    private String competitorType;
    private boolean openParticipation = false;
    private boolean closed = false;

    private String pictureLarge;
    private String pictureThumb;
    private String partnerPicture;

    private String description;
    private String excerptLead;
    private String excerpt;
    private String prizes;
    private String rules;
    private String livestreamFeed;
    private String livestreamChat;
    private String unit;

    private Integer level;
    private boolean live = false;
    private boolean notHosted = false;
    private boolean event = false;

    private int participants;
    private List<CompetitionStageDTO> stages;
    private List<RankDTO> roundRanking;
    private List<SubmissionDTO> latestSubmissions; // ok to add, or get submissions with a seperate request?
    private List<PartnerImageDTO> partnerImages;

    private UserDTO leadingMember;
    private TeamDTO leadingTeam;
    private CountryDTO leadingCountry;

    public CompetitionRoundDTO(int contestId, Integer parentContestId, Date startdate, Date enddate, String name, String subtitle, String safeName,
            String competitorType, boolean openParticipation, boolean closed, String pictureLarge, String partnerPicture, Integer level) {
        super();
        this.id = contestId;
        this.parentId = parentContestId;
        this.startDate = startdate;
        this.endDate = enddate;
        this.name = name;
        this.subtitle = subtitle;
        this.safeName = safeName;
        this.competitorType = competitorType;
        this.openParticipation = openParticipation;
        this.closed = closed;
        this.pictureLarge = pictureLarge;
        this.partnerPicture = partnerPicture;
        this.level = level;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getLivestreamFeed() {
        return livestreamFeed;
    }

    public void setLivestreamFeed(String livestreamFeed) {
        this.livestreamFeed = livestreamFeed;
    }

    public String getLivestreamChat() {
        return livestreamChat;
    }

    public void setLivestreamChat(String livestreamChat) {
        this.livestreamChat = livestreamChat;
    }

    public List<PartnerImageDTO> getPartnerImages() {
        return partnerImages;
    }

    public void setPartnerImages(List<PartnerImageDTO> partnerImages) {
        this.partnerImages = partnerImages;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public int getParticipants() {
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

    public void setParticipants(int participants) {
        this.participants = participants;
    }

    public List<RankDTO> getRoundRanking() {
        return roundRanking;
    }

    public void setRoundRanking(List<RankDTO> roundRanking) {
        this.roundRanking = roundRanking;
    }

    public List<SubmissionDTO> getLatestSubmissions() {
        return latestSubmissions;
    }

    public void setLatestSubmissions(List<SubmissionDTO> latestSubmissions) {
        this.latestSubmissions = latestSubmissions;
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

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getPrizes() {
        return prizes;
    }

    public void setPrizes(String prizes) {
        this.prizes = prizes;
    }

    public String getPictureThumb() {
        return pictureThumb;
    }

    public void setPictureThumb(String pictureThumb) {
        this.pictureThumb = pictureThumb;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("CompetitionRoundDTO{");
        sb.append("id=").append(id);
        sb.append(", parentId=").append(parentId);
        sb.append(", startDate=").append(startDate);
        sb.append(", endDate=").append(endDate);
        sb.append(", name='").append(name).append('\'');
        sb.append(", subtitle='").append(subtitle).append('\'');
        sb.append(", safeName='").append(safeName).append('\'');
        sb.append(", type='").append(type).append('\'');
        sb.append(", competitorType='").append(competitorType).append('\'');
        sb.append(", openParticipation=").append(openParticipation);
        sb.append(", closed=").append(closed);
        sb.append(", pictureLarge='").append(pictureLarge).append('\'');
        sb.append(", partnerPicture='").append(partnerPicture).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append(", excerptLead='").append(excerptLead).append('\'');
        sb.append(", excerpt='").append(excerpt).append('\'');
        sb.append(", prizes='").append(prizes).append('\'');
        sb.append(", rules='").append(rules).append('\'');
        sb.append(", livestreamFeed='").append(livestreamFeed).append('\'');
        sb.append(", livestreamChat='").append(livestreamChat).append('\'');
        sb.append(", unit='").append(unit).append('\'');
        sb.append(", level=").append(level);
        sb.append(", live=").append(live);
        sb.append(", notHosted=").append(notHosted);
        sb.append(", event=").append(event);
        sb.append(", participants=").append(participants);
        sb.append(", stages=").append(stages);
        sb.append(", roundRanking=").append(roundRanking);
        sb.append(", latestSubmissions=").append(latestSubmissions);
        sb.append(", partnerImages=").append(partnerImages);
        sb.append(", leadingMember=").append(leadingMember);
        sb.append(", leadingTeam=").append(leadingTeam);
        sb.append(", leadingCountry=").append(leadingCountry);
        sb.append('}');
        return sb.toString();
    }
}

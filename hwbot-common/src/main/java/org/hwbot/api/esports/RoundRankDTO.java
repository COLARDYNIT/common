package org.hwbot.api.esports;

import java.util.List;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;
import org.hwbot.util.StringUtil;

/**
 * Roadmap for pro oc, challenger, etc on esports site.
 * 
 * @author frederik
 * 
 */
@JsonSerialize(include = Inclusion.NON_NULL)
public class RoundRankDTO {

    private int id;
    private int position;
    private Float points;
    private int userId;
    private int teamId;
    private int countryId;
    private String nickName;

    // placeholder for when rank is improved
    private Boolean up;
    private long lastUpdated;

    private List<StageRankDTO> stageRanks;

    public RoundRankDTO(int id, int position, Float points, String nickName, long lastUpdated) {
        super();
        this.id = id;
        this.position = position;
        this.points = points;
        this.nickName = nickName;
        this.lastUpdated = lastUpdated;
    }

    public String getRankFull() {
        return StringUtil.getNumberSuffix(getPosition());
    }

    public int getId() {
        return id;
    }

    public int getPosition() {
        return position;
    }

    public Float getPoints() {
        return points;
    }

    public int getUserId() {
        return userId;
    }

    public int getTeamId() {
        return teamId;
    }

    public int getCountryId() {
        return countryId;
    }

    public String getNickName() {
        return nickName;
    }

    public Boolean getUp() {
        return up;
    }

    public long getLastUpdated() {
        return lastUpdated;
    }

    public List<StageRankDTO> getStageRanks() {
        return stageRanks;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public void setPoints(Float points) {
        this.points = points;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public void setUp(Boolean up) {
        this.up = up;
    }

    public void setLastUpdated(long lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public void setStageRanks(List<StageRankDTO> stageRanks) {
        this.stageRanks = stageRanks;
    }

}

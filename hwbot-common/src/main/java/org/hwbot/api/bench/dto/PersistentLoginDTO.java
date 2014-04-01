package org.hwbot.api.bench.dto;

import java.io.Serializable;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

@JsonSerialize(include = Inclusion.NON_NULL)
public class PersistentLoginDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String userName;
    private String avatar;
    private String teamName;
    private String countryName;
    private String token;
    private String league;
    private Long dateUntil;
    private Integer userId;
    private Integer teamId;
    private Integer countryId;

    // when persistent login is not ok
    private transient String errorMessage;

    public PersistentLoginDTO() {
        super();
    }

    public PersistentLoginDTO(String errorMessage) {
        this();
        this.errorMessage = errorMessage;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Long getDateUntil() {
        return dateUntil;
    }

    public void setDateUntil(Long dateUntil) {
        this.dateUntil = dateUntil;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getTeamId() {
        return teamId;
    }

    public void setTeamId(Integer teamId) {
        this.teamId = teamId;
    }

    public Integer getCountryId() {
        return countryId;
    }

    public void setCountryId(Integer countryId) {
        this.countryId = countryId;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getLeague() {
        return league;
    }

    public void setLeague(String league) {
        this.league = league;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    @Override
    public String toString() {
        return "PersistentLoginDTO [" + (userName != null ? "userName=" + userName + ", " : "") + (avatar != null ? "avatar=" + avatar + ", " : "")
                + (teamName != null ? "teamName=" + teamName + ", " : "") + (countryName != null ? "countryName=" + countryName + ", " : "")
                + (token != null ? "token=" + token + ", " : "") + (league != null ? "league=" + league + ", " : "")
                + (dateUntil != null ? "dateUntil=" + dateUntil + ", " : "") + (userId != null ? "userId=" + userId + ", " : "")
                + (teamId != null ? "teamId=" + teamId + ", " : "") + (countryId != null ? "countryId=" + countryId : "") + "]";
    }

}

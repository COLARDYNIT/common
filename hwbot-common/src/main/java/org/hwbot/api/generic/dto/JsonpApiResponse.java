package org.hwbot.api.generic.dto;

import java.util.List;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

@JsonSerialize(include = Inclusion.NON_NULL)
public class JsonpApiResponse {

    private String format;
    private String target;
    private String message;
    private List<Object> list;
    private SubmissionDTO result;
    private NameIdDTO user;
    private NameIdDTO team;
    private NameIdDTO application;
    private NameIdDTO country;
    private NameIdDTO region;
    private AchievementDTO lastAchievement;
    private AchievementDTO lastAchievementInProgress;

    public JsonpApiResponse(String target) {
        super();
        this.target = target;
    }

    public JsonpApiResponse(String target, String format, String message, List list) {
        this(target);
        this.format = format;
        this.message = message;
        this.list = list;
    }

    public AchievementDTO getLastAchievement() {
        return lastAchievement;
    }

    public void setLastAchievement(AchievementDTO lastAchievement) {
        this.lastAchievement = lastAchievement;
    }

    public AchievementDTO getLastAchievementInProgress() {
        return lastAchievementInProgress;
    }

    public void setLastAchievementInProgress(AchievementDTO lastAchievementInProgress) {
        this.lastAchievementInProgress = lastAchievementInProgress;
    }

    public String getTarget() {
        return target;
    }

    public String getMessage() {
        return message;
    }

    public List<Object> getList() {
        return list;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public void setList(List list) {
        this.list = list;
    }

    public String getFormat() {
        return format;
    }

    public NameIdDTO getUser() {
        return user;
    }

    public void setUser(NameIdDTO user) {
        this.user = user;
    }

    public NameIdDTO getTeam() {
        return team;
    }

    public void setTeam(NameIdDTO team) {
        this.team = team;
    }

    public NameIdDTO getApplication() {
        return application;
    }

    public void setApplication(NameIdDTO application) {
        this.application = application;
    }

    public NameIdDTO getCountry() {
        return country;
    }

    public void setCountry(NameIdDTO country) {
        this.country = country;
    }

    public NameIdDTO getRegion() {
        return region;
    }

    public void setRegion(NameIdDTO region) {
        this.region = region;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public SubmissionDTO getResult() {
        return result;
    }

    public void setResult(SubmissionDTO result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "JsonpApiResponse [" + (format != null ? "format=" + format + ", " : "") + (target != null ? "target=" + target + ", " : "")
                + (message != null ? "message=" + message + ", " : "") + (list != null ? "list=" + list + ", " : "")
                + (result != null ? "result=" + result + ", " : "") + (user != null ? "user=" + user + ", " : "") + (team != null ? "team=" + team + ", " : "")
                + (application != null ? "application=" + application + ", " : "") + (country != null ? "country=" + country + ", " : "")
                + (region != null ? "region=" + region : "") + "]";
    }

}
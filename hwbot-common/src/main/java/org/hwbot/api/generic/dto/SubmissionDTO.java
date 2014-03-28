package org.hwbot.api.generic.dto;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

@JsonSerialize(include = Inclusion.NON_NULL)
public class SubmissionDTO {

    private String user;
    private String team;
    private String hardware;
    private String score;
    private String points;
    private String country;
    private String app;
    private String image;
    private String description;
    private String kernel;
    private String osBuild;
    private Integer cpuFreq;
    private final int id;

    @Override
    public String toString() {
        return "SubmissionDTO [" + (user != null ? "user=" + user + ", " : "") + (team != null ? "team=" + team + ", " : "")
                + (hardware != null ? "hardware=" + hardware + ", " : "") + (score != null ? "score=" + score + ", " : "")
                + (points != null ? "points=" + points + ", " : "") + (country != null ? "country=" + country + ", " : "")
                + (app != null ? "app=" + app + ", " : "") + "id=" + id + "]";
    }

    public SubmissionDTO(int id, String user, String team, String hardware, String score, String points, String country, String app, String image,
            String description, String kernel, String osBuild, Integer cpuFreq) {
        super();
        this.id = id;
        this.user = user;
        this.team = team;
        this.hardware = hardware;
        this.score = score;
        this.points = points;
        this.country = country;
        this.app = app;
        this.image = image;
        this.description = description;
        this.kernel = kernel;
        this.osBuild = osBuild;
        this.cpuFreq = cpuFreq;
    }

    public String getKernel() {
        return kernel;
    }

    public void setKernel(String kernel) {
        this.kernel = kernel;
    }

    public String getOsBuild() {
        return osBuild;
    }

    public void setOsBuild(String osBuild) {
        this.osBuild = osBuild;
    }

    public Integer getCpuFreq() {
        return cpuFreq;
    }

    public void setCpuFreq(Integer cpuFreq) {
        this.cpuFreq = cpuFreq;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public String getApp() {
        return app;
    }

    public void setApp(String app) {
        this.app = app;
    }

    public String getUser() {
        return user;
    }

    public String getScore() {
        return score;
    }

    public String getCountry() {
        return country;
    }

    public String getTeam() {
        return team;
    }

    public String getHardware() {
        return hardware;
    }

    public String getPoints() {
        return points;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public void setHardware(String hardware) {
        this.hardware = hardware;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public void setPoints(String points) {
        this.points = points;
    }

    public void setCountry(String country) {
        this.country = country;
    }

}
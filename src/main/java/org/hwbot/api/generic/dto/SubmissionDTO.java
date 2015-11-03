package org.hwbot.api.generic.dto;

import java.io.Serializable;
import java.util.List;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;
import org.hwbot.api.esports.DiscussableDTO;

@JsonSerialize(include = Inclusion.NON_NULL)
public class SubmissionDTO extends DiscussableDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final String UNIT_TIME = "time";

    private String user;
    private String team;
    private String hardware;
    private String cooling;
    // either one is filled in, not both
    private String score;
    private Integer hour, min, sec, msec;
    private String unit;
    private String points;
    private String country;
    private String app;
    private String image;
    private String description;
    private String kernel;
    private String osBuild;
    private String device;
    private Integer cpuFreq;
    private final int id;
    private int likes;
    private int replies;
    private Integer stageId;
    private List<String> images;

    private String processor;
    private String videocard;
    private String motherboard;
    private String memory;
    private String disk;
    private String powerSupply;

    @Override
    public String toString() {
        return "SubmissionDTO [" + (user != null ? "user=" + user + ", " : "") + (team != null ? "team=" + team + ", " : "")
                + (hardware != null ? "hardware=" + hardware + ", " : "")
                + (cooling != null ? "cooling=" + cooling + ", " : "") + (score != null ? "score=" + score + ", " : "")
                + (points != null ? "points=" + points + ", " : "") + (country != null ? "country=" + country + ", " : "")
                + (app != null ? "app=" + app + ", " : "") + (image != null ? "image=" + image + ", " : "")
                + (description != null ? "description=" + description + ", " : "") + (kernel != null ? "kernel=" + kernel + ", " : "")
                + (osBuild != null ? "osBuild=" + osBuild + ", " : "") + (cpuFreq != null ? "cpuFreq=" + cpuFreq + ", " : "") + "id=" + id + ", likes=" + likes
                + ", replies=" + replies + "]";
    }

    public SubmissionDTO(int id, String user, String team, String hardware, String cooling, String points, String country, String app, String image, String description,
            String kernel, String osBuild, Integer cpuFreq, int likes, int replies) {
        super();
        this.id = id;
        this.user = user;
        this.team = team;
        this.hardware = hardware;
        this.cooling = cooling;
        this.points = points;
        this.country = country;
        this.app = app;
        this.image = image;
        this.description = description;
        this.kernel = kernel;
        this.osBuild = osBuild;
        this.cpuFreq = cpuFreq;
        this.likes = likes;
        this.replies = replies;
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

    public boolean isRemoteImage() {
        return getImage() != null && getImage().startsWith("http");
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

    public String getCooling() {
        return cooling;
    }

    public void setCooling(String cooling) {
        this.cooling = cooling;
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

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getReplies() {
        return replies;
    }

    public void setReplies(int replies) {
        this.replies = replies;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Integer getHour() {
        return hour;
    }

    public void setHour(Integer hour) {
        this.hour = hour;
    }

    public Integer getMin() {
        return min;
    }

    public void setMin(Integer minute) {
        this.min = minute;
    }

    public Integer getSec() {
        return sec;
    }

    public void setSec(Integer second) {
        this.sec = second;
    }

    public Integer getMsec() {
        return msec;
    }

    public void setMsec(Integer msec) {
        this.msec = msec;
    }

    public Integer getStageId() {
        return stageId;
    }

    public void setStageId(Integer stageId) {
        this.stageId = stageId;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public String getProcessor() {
        return processor;
    }

    public void setProcessor(String processor) {
        this.processor = processor;
    }

    public String getVideocard() {
        return videocard;
    }

    public void setVideocard(String videocard) {
        this.videocard = videocard;
    }

    public String getMotherboard() {
        return motherboard;
    }

    public void setMotherboard(String motherboard) {
        this.motherboard = motherboard;
    }

    public String getMemory() {
        return memory;
    }

    public void setMemory(String memory) {
        this.memory = memory;
    }

    public String getDisk() {
        return disk;
    }

    public void setDisk(String disk) {
        this.disk = disk;
    }

    public String getPowerSupply() {
        return powerSupply;
    }

    public void setPowerSupply(String powerSupply) {
        this.powerSupply = powerSupply;
    }


}
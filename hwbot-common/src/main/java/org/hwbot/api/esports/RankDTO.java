package org.hwbot.api.esports;

import java.io.Serializable;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;
import org.hwbot.api.generic.dto.SubmissionDTO;

@JsonSerialize(include = Inclusion.NON_NULL)
public class RankDTO implements Comparable<RankDTO>, Serializable {

    private static final long serialVersionUID = 1L;
    public String name;
    public int position;
    public int points;
    public String countryCode;
    public String url;
    public String avatar;

    // details
    protected List<SubmissionDTO> submissionDTOs;
    protected String scoreFormatted;
    protected final List<String> images = new ArrayList<String>();
    protected final List<RankDTO> stages = new ArrayList<RankDTO>();

    public RankDTO() {
        super();
    }

    public RankDTO(String name, int position, int points, String countryCode, String url, String avatar) {
        super();
        this.name = name;
        this.position = position;
        this.points = points;
        this.countryCode = countryCode;
        this.url = url;
        this.avatar = avatar;
    }

    @Override
    public String toString() {
        return "CompetitionRankDTO [" + (name != null ? "name=" + name + ", " : "") + "position=" + position + ", "
                + (points != 0 ? "points=" + points + ", " : "") + (countryCode != null ? "countryCode=" + countryCode + ", " : "")
                + (url != null ? "url=" + url + ", " : "") + (avatar != null ? "avatar=" + avatar : "") + "]";
    }

    public boolean isRemoteAvatar() {
        return getAvatar() != null && getAvatar().startsWith("http");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getPointsFormatted() {
        NumberFormat numberInstance = NumberFormat.getNumberInstance(Locale.ENGLISH);
        numberInstance.setMaximumFractionDigits(2);
        numberInstance.setMinimumFractionDigits(0);
        String format = numberInstance.format(getPoints() / 100f);
        return format;
    }

    @Override
    public int compareTo(RankDTO o) {
        if (o.getPoints() == this.getPoints()) {
            return 0;
        } else if (o.getPoints() > this.getPoints()) {
            return 1;
        } else {
            return -1;
        }
    }

    public List<RankDTO> getStages() {
        return stages;
    }

    public List<SubmissionDTO> getSubmissionDTOs() {
        return submissionDTOs;
    }

    public void setSubmissionDTOs(List<SubmissionDTO> submissionDTOs) {
        this.submissionDTOs = submissionDTOs;
    }

    public String getScoreFormatted() {
        return scoreFormatted;
    }

    public void setScoreFormatted(String scoreFormatted) {
        this.scoreFormatted = scoreFormatted;
    }

    public List<String> getImages() {
        return images;
    }

}

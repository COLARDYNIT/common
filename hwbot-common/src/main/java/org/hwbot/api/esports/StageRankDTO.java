package org.hwbot.api.esports;

import java.io.Serializable;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;
import org.hwbot.api.generic.dto.SubmissionDTO;

@JsonSerialize(include = Inclusion.NON_NULL)
public class StageRankDTO implements Serializable {

    protected static final long serialVersionUID = 1L;

    protected int id;

    protected Integer[] resultIds;

    protected List<SubmissionDTO> submissionDTOs;

    /** User that made the submission if it's a team/country competition */
    protected int userId;

    protected CompetitorType type;

    protected int position;

    protected String name;

    protected String scoreFormatted;

    protected float score;

    protected int weight;

    protected float points;

    protected String safeName;

    protected String countryCode;

    protected Date date;

    protected final List<Integer> images = new ArrayList<Integer>();

    protected Date endDate;

    protected Integer amount;

    public StageRankDTO() {
    }

    public StageRankDTO(int position) {
        this.position = position;
    }

    public StageRankDTO(CompetitorType competitorType, int id, Integer[] resultIds, String name, String safeName, int position, float score,
            String scoreFormatted, int weight, float points, Date date, Date endDate, String countryCode, Integer amount) {
        this.type = competitorType;
        this.id = id;
        this.resultIds = resultIds;
        this.name = name;
        this.safeName = safeName;
        this.position = position;
        this.score = score;
        this.scoreFormatted = scoreFormatted;
        this.weight = weight;
        this.points = points;
        this.date = date;
        this.endDate = endDate;
        this.countryCode = countryCode;
        this.amount = amount;
    }

    public void addImage(Integer id) {
        if (id != null && id != 0) {
            images.add(id);
        }
    }

    public boolean isWithImages() {
        return images.size() > 0;
    }

    public float getPoints() {
        return points;
    }

    public String getPointsFormatted() {
        return NumberFormat.getNumberInstance(Locale.ENGLISH).format(points);
    }

    public void setPoints(float points) {
        this.points = points;
    }

    public String getLink() {
        if (safeName == null) {
            return null;
        }
        if (CompetitorType.user.equals(type)) {
            return "/user/" + safeName + "/";
        } else if (CompetitorType.country.equals(type)) {
            return null;
        } else {
            return "/team/" + safeName + "/";
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getFlag() {
        return getCountryFlag();
    }

    public String getCountryFlag() {
        if (countryCode != null) {
            return "<img src='/images/spacer.gif' class='flag flag-" + countryCode + "' alt='" + countryCode + "' title='" + countryCode + "'/>";
        } else {
            return "<img src='/img/country/unknown.png' class='flag' />";
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    public CompetitorType getType() {
        return type;
    }

    public void setType(CompetitorType type) {
        this.type = type;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getResultId() {
        return resultIds[0];
    }

    public Integer[] getResultIds() {
        return resultIds;
    }

    public void setResultIds(Integer[] resultIds) {
        this.resultIds = resultIds;
    }

    public String getScoreFormatted() {
        return scoreFormatted;
    }

    public void setScoreFormatted(String scoreFormatted) {
        this.scoreFormatted = scoreFormatted;
    }

    @Override
    public String toString() {
        final int maxLen = 10;
        return "ContestBenchmarkRank [id=" + id + ", "
                + (resultIds != null ? "resultIds=" + Arrays.asList(resultIds).subList(0, Math.min(resultIds.length, maxLen)) + ", " : "")
                + (type != null ? "type=" + type + ", " : "") + "position=" + position + ", " + (name != null ? "name=" + name + ", " : "")
                + (scoreFormatted != null ? "scoreFormatted=" + scoreFormatted + ", " : "") + "score=" + score + ", weight=" + weight + ", points=" + points
                + ", " + (safeName != null ? "safeName=" + safeName + ", " : "") + (countryCode != null ? "countryCode=" + countryCode + ", " : "")
                + (date != null ? "date=" + date + ", " : "") + (endDate != null ? "endDate=" + endDate + ", " : "")
                + (amount != null ? "amount=" + amount : "") + "]";
    }

    public List<Integer> getImages() {
        return images;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public Integer getAmount() {
        return amount;
    }

    public int getUserId() {
        if (type == CompetitorType.user) {
            return id;
        } else {
            return userId;
        }
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public List<SubmissionDTO> getSubmissionDTOs() {
        return submissionDTOs;
    }

    public void setSubmissionDTOs(List<SubmissionDTO> submissionDTOs) {
        this.submissionDTOs = submissionDTOs;
    }

}

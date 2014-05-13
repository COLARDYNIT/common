package org.hwbot.api.esports;

public class CompetitionRoundRankDTO {

    public String name;
    public int position;
    public String points;
    public String countryCode;
    public String url;
    public String avatar;

    public CompetitionRoundRankDTO() {
        super();
    }

    public CompetitionRoundRankDTO(String name, int position, String points, String countryCode, String url, String avatar) {
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
                + (points != null ? "points=" + points + ", " : "") + (countryCode != null ? "countryCode=" + countryCode + ", " : "")
                + (url != null ? "url=" + url + ", " : "") + (avatar != null ? "avatar=" + avatar : "") + "]";
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

    public String getPoints() {
        return points;
    }

    public void setPoints(String points) {
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

}

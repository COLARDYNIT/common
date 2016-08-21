package org.hwbot.api.generic.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize.Inclusion;

import java.io.Serializable;

/**
 * Contians basic user info.
 * 
 * @author frederik
 * 
 */
@JsonSerialize(include = Inclusion.NON_NULL)
public class UserDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    private String name;
    private String url;
    private int id;
    private String team;
    private String countryFlag;
    private String avatar;
    private String country;
    private String proOcTeam;
    private String series;
    private String division;

    public UserDTO(String name, String url, int id, String team, String countryFlag, String avatar) {
        super();
        this.name = name;
        this.url = url;
        this.id = id;
        this.team = team;
        this.countryFlag = countryFlag;
        this.avatar = avatar;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public int getId() {
        return id;
    }

    public String getTeam() {
        return team;
    }

    public String getCountryFlag() {
        return countryFlag;
    }

    public String getAvatar() {
        return avatar;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getProOcTeam() {
        return proOcTeam;
    }

    public void setProOcTeam(String proOcTeam) {
        this.proOcTeam = proOcTeam;
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public String getDivision() {
        return division;
    }

    public void setDivision(String division) {
        this.division = division;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public void setCountryFlag(String countryFlag) {
        this.countryFlag = countryFlag;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

}

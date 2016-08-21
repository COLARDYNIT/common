package org.hwbot.api.generic.dto;

import java.io.Serializable;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize.Inclusion;

@JsonSerialize(include = Inclusion.NON_NULL)
public class PointsDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    private String name;
    private String points;
    private String country;

    public PointsDTO() {
        super();
    }

    public PointsDTO(String name, String points, String country) {
        super();
        this.name = name;
        this.points = points;
        this.country = country;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPoints() {
        return points;
    }

    public void setPoints(String points) {
        this.points = points;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

}
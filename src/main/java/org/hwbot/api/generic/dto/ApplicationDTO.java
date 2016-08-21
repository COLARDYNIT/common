package org.hwbot.api.generic.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize.Inclusion;

import java.io.Serializable;

@JsonSerialize(include = Inclusion.NON_NULL)
public class ApplicationDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    private String name;
    private String logo;
    private int id;
    private String website;
    private String download;
    private String type;
    private String unit;
    private boolean lowerbetter;

    public ApplicationDTO(String name, String logo, int id, String website, String download, String type, String unit, boolean lowerbetter) {
        this.name = name;
        this.logo = logo;
        this.id = id;
        this.website = website;
        this.download = download;
        this.type = type;
        this.unit = unit;
        this.lowerbetter = lowerbetter;
    }

    public boolean isLowerbetter() {
        return lowerbetter;
    }

    public void setLowerbetter(boolean lowerbetter) {
        this.lowerbetter = lowerbetter;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDownload() {
        return download;
    }

    public void setDownload(String download) {
        this.download = download;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

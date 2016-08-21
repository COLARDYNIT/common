package org.hwbot.api.esports;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize.Inclusion;
import org.hwbot.api.generic.dto.SubmissionDTO;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Roadmap for pro oc, challenger, etc on esports site.
 * 
 * @author frederik
 * 
 */
@JsonSerialize(include = Inclusion.NON_NULL)
public class PartnerImageDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;

    private String url;

    private String image;

    public PartnerImageDTO(String name, String url, String image) {
        this.name = name;
        this.url = url;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}

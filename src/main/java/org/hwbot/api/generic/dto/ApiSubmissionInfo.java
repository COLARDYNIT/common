package org.hwbot.api.generic.dto;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;


@JacksonXmlRootElement(localName = "submission")
@XmlType(propOrder = { "user", "benchmark", "score", "date", "team", "proocteam", "country", "contest", "stage", "league", "ranking", "state", "description",
        "screenshot", "pictures", "hardware" })
public class ApiSubmissionInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    // attributes
    private String id;
    private String url;

    private String score;
    private Date date;
    private String league;
    private String description;

    private XmlRankingElement ranking;

    private XmlNameElementWithIdAttribute user;
    private XmlNameElementWithIdAttribute benchmark;
    private XmlNameElementWithIdAttribute team;
    private XmlNameElementWithIdAttribute proocteam;
    private XmlNameElementWithIdAttribute country;
    private XmlNameElementWithIdAttribute contest;
    private XmlNameElementWithIdAttribute stage;
    private XmlNameElementWithIdAttribute state;

    private String screenshot;
    private List<String> pictures;
    private List<XmlHardwareElement> hardware;

    public ApiSubmissionInfo() {

    }

    @XmlID
    @XmlAttribute
    public String getId() {
        return id;
    }

    @XmlAttribute
    public String getUrl() {
        return url;
    }

    @XmlElement
    public String getScore() {
        return score;
    }

    @XmlElement
    public Date getDate() {
        return date;
    }

    @XmlElement
    public String getDescription() {
        return description;
    }

    @XmlElement
    public String getLeague() {
        return league;
    }

    @XmlElement
    public XmlRankingElement getRanking() {
        return ranking;
    }

    @XmlElement
    public XmlNameElementWithIdAttribute getBenchmark() {
        return benchmark;
    }

    @XmlElement
    public XmlNameElementWithIdAttribute getUser() {
        return user;
    }

    @XmlElement
    public XmlNameElementWithIdAttribute getTeam() {
        return team;
    }

    @XmlElement
    public XmlNameElementWithIdAttribute getProocteam() {
        return proocteam;
    }

    @XmlElement
    public XmlNameElementWithIdAttribute getCountry() {
        return country;
    }

    @XmlElement
    public XmlNameElementWithIdAttribute getContest() {
        return contest;
    }

    @XmlElement
    public XmlNameElementWithIdAttribute getStage() {
        return stage;
    }

    @XmlElement
    public XmlNameElementWithIdAttribute getState() {
        return state;
    }

    @XmlElement
    public String getScreenshot() {
        return screenshot;
    }

    @JacksonXmlProperty(localName ="picture")
    @XmlElementWrapper
    public List<String> getPictures() {
        return pictures;
    }

    @JacksonXmlProperty(localName ="component")
    @XmlElementWrapper
    public List<XmlHardwareElement> getHardware() {
        return hardware;
    }

    private String createImageLink(int imageId) {
        if (imageId != 0) {
            return "/image/" + imageId;
        } else {
            return null;
        }
    }
}

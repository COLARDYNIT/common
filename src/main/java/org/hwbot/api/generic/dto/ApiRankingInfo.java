package org.hwbot.api.generic.dto;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlType;
import java.util.Date;

@JacksonXmlRootElement(localName = "ranking")
@XmlType(propOrder = { "user", "score", "date", "team", "proocteam", "country", "league", "ranking", "description" })
public class ApiRankingInfo {

    // attributes
    private String id;
    private String url;

    private String score;
    private Date date;
    private String league;
    private String description;
    private XmlRankingElement ranking;

    private XmlNameElementWithIdAttribute user;
    private XmlNameElementWithIdAttribute team;
    private XmlNameElementWithIdAttribute proocteam;
    private XmlNameElementWithIdAttribute country;

    public ApiRankingInfo() {

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
}

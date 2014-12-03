package org.hwbot.api.generic.dto;

import java.util.Date;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "competition")
@XmlType(propOrder={"name", "startDate", "endDate", "type", "externalUrl", "twitterhash", "tag", "level", "parentId", "closed", "event", "challenge", "participants"})
public class ApiContestInfo {

	// attributes
	private String id;	
	private String safeName;

	private String name;
	private Date startDate;
	private Date endDate;
	private String type;
	private String externalUrl;
	private String twitterhash;
	private String tag;
	private Integer level;
	private Integer parentId;
	
	private Boolean closed;
	private Boolean event;
	private Boolean challenge;
	
	private Integer participants;
	
	public ApiContestInfo() {
		
	}

	@XmlID
	@XmlAttribute
	public String getId() {
		return id;
	}

	@XmlAttribute
	public String getSafeName() {
		return safeName;
	}
	
	@XmlElement
	public String getName() {
		return name;
	}

	@XmlElement
	public Date getStartDate() {
		return startDate;
	}

	@XmlElement
	public Date getEndDate() {
		return endDate;
	}

	@XmlElement
	public String getType() {
		return type;
	}

	@XmlElement
	public String getExternalUrl() {
		return externalUrl;
	}

	@XmlElement
	public String getTwitterhash() {
		return twitterhash;
	}

	@XmlElement
	public String getTag() {
		return tag;
	}

	@XmlElement
	public Integer getLevel() {
		return level;
	}

	@XmlElement
	public Integer getParentId() {
		return parentId;
	}

	@XmlElement
	public Boolean getClosed() {
		return closed;
	}

	@XmlElement
	public Boolean getEvent() {
		return event;
	}

	@XmlElement
	public Boolean getChallenge() {
		return challenge;
	}

	@XmlElement
	public Integer getParticipants() {
		return participants;
	}
}

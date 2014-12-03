package org.hwbot.api.generic.dto;

import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


@XmlRootElement(name = "member")
@XmlType(propOrder={"name", "team", "proOcTeam", "country", "league", "joinDate", "achievementsUnlocked", "ranking", "awards"})
public class ApiMemberInfo {

	// ranking calculation variables
	private static final long DAY_MILLIS = 1000 * 60 * 60 * 24 * 1;
	private static final long WEEK_MILLIS = DAY_MILLIS * 6;
	private static final long MONTH_MILLIS = WEEK_MILLIS * 4;
	private static final String TODAY = "today";
	private static final String YESTERDAY = "yesterday";
	private static final String LASTWEEK = "lastweek";
	private static final String LASTMONTH = "lastmonth";
	
	// attributes
	private String id;	
	private String url;

	private String name;
	private String league;
	private Date joinDate;
	private Integer achievementsUnlocked;
	
	private XmlNameElementWithIdAttribute team;
        private XmlNameElementWithIdAttribute proOcTeam;
	private XmlNameElementWithIdAttribute country;

	private List<XmlMemberRankingElement> ranking;
	private List<XmlMemberAwardsElement> awards;
	
	
	public ApiMemberInfo() {	
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
	public String getName() {
		return name;
	}

	@XmlElement
	public String getLeague() {
		return league;
	}
	
	@XmlElement
	public Date getJoinDate() {
		return joinDate;
	}
	
	@XmlElement
	public XmlNameElementWithIdAttribute getTeam() {
		return team;
	}

        @XmlElement
        public XmlNameElementWithIdAttribute getProOcTeam() {
                return proOcTeam;
        }

        public void setProOcTeam(XmlNameElementWithIdAttribute proOcTeam) {
                this.proOcTeam = proOcTeam;
        }

	@XmlElement
	public XmlNameElementWithIdAttribute getCountry() {
		return country;
	}
	
	@XmlElement
	public Integer getAchievementsUnlocked() {
		return achievementsUnlocked;
	}
	
	@XmlElement(name="rank")
	@XmlElementWrapper
	public List<XmlMemberRankingElement> getRanking() {
		return ranking;
	}
	
	@XmlElementWrapper
	public List<XmlMemberAwardsElement> getAwards() {
		return awards;
	}
	
}

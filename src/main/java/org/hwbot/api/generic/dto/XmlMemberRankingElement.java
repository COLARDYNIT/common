package org.hwbot.api.generic.dto;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

/**
 * Utility class to represent a member rank and points for the Query API
 */
@XmlType(propOrder={"results", "hwboints", "rank", "time"}) // has to be reversed for attributes for some reason
public class XmlMemberRankingElement {
	
	@XmlAttribute
	public String time;
	
	@XmlAttribute
	public Integer rank;
	
	@XmlAttribute
	public String hwboints;
	
	@XmlAttribute
	public Integer results;

	public XmlMemberRankingElement() {	
	}
	
	public XmlMemberRankingElement(String time, Integer rank, String hwboints, Integer results) {
		this.time = time;
		this.rank = rank;
		this.hwboints = hwboints;
		this.results = results;
	}
}

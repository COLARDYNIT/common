package org.hwbot.api.generic.dto;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

/**
 * Utility class to represent a member awards for the Query API
 */
@XmlType(propOrder={"medal", "bronze", "silver", "gold", "type"}) // has to be reversed for attributes for some reason
public class XmlMemberAwardsElement {
	
	@XmlAttribute
	public String type;
	
	@XmlAttribute
	public Integer gold;
	
	@XmlAttribute
	public Integer silver;
	
	@XmlAttribute
	public Integer bronze;
	
	@XmlAttribute
	public Integer medal;

	public XmlMemberAwardsElement() {	
	}
	
	public XmlMemberAwardsElement(String type, Integer gold, Integer silver, Integer bronze, Integer medal) {
		this.type = type;
		this.gold = gold;
		this.silver = silver;
		this.bronze = bronze;
		this.medal = medal;
	}
}

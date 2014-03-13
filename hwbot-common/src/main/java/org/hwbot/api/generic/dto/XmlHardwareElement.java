package org.hwbot.api.generic.dto;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

/**
 * Utility class to represent a submission hardware component for the Query API.
 * Not all hw components use the same fields, each component has it's own constructor.
 */
public class XmlHardwareElement {
	
	private String componentType;
	private Integer amount;
	private String frequency;
	private String timings;
	private String capacity;
	
	private XmlNameElementWithIdAttribute model;
	private XmlNameElementWithIdAttribute vendor;
	private XmlNameElementWithIdAttribute cooling;
	private XmlNameElementWithIdAttribute type;
	
	public XmlHardwareElement() {	
	}
	
	
	@XmlAttribute(name = "type")
	public String getComponentType() {
		return componentType;
	}
	
	@XmlElement
	public XmlNameElementWithIdAttribute getModel() {
		return model;
	}

	@XmlElement
	public XmlNameElementWithIdAttribute getVendor() {
		return vendor;
	}

	@XmlElement
	public XmlNameElementWithIdAttribute getCooling() {
		return cooling;
	}

	@XmlElement
	public Integer getAmount() {
		return amount;
	}

	@XmlElement
	public String getFrequency() {
		return frequency;
	}

	@XmlElement
	public String getTimings() {
		return timings;
	}

	@XmlElement
	public XmlNameElementWithIdAttribute getType() {
		return type;
	}

	@XmlElement
	public String getCapacity() {
		return capacity;
	}
}

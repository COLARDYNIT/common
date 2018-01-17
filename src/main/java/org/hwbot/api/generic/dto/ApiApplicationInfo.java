package org.hwbot.api.generic.dto;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import javax.xml.bind.annotation.*;
import java.util.List;

@JacksonXmlRootElement(localName = "benchmark")
@XmlType(propOrder={"safeName", "website", "downloadLink", "description", "type", "supportedVersions", "wrpoints", "glpoints", "hwpoints", "multithreaded", "parentId", "country", "region"})
public class ApiApplicationInfo {

	// attributes
	private String id;	
	private String name;

	private String safeName;
	private String website;
	private String downloadLink;
	private String description;
	private String type;
	private List<String> supportedVersions;
	
	private Boolean wrpoints;
	private Boolean glpoints;
	private Boolean hwpoints;
	private Boolean multithreaded;
	private Integer parentId;
	
	private String country;
	private String region;
	
	public ApiApplicationInfo() {
		
	}
	
	@XmlID
	@XmlAttribute
	public String getId() {
		return id;
	}

	@XmlAttribute
	public String getName() {
		return name;
	}
	
	@XmlElement
	public String getSafeName() {
		return safeName;
	}

	@XmlElement
	public String getWebsite() {
		return website;
	}

	@XmlElement
	public String getDownloadLink() {
		return downloadLink;
	}

	@XmlElement
	public String getDescription() {
		return description;
	}

	@XmlElement
	public String getType() {
		return type;
	}

	@XmlElement(name="version")
	@XmlElementWrapper
	public List<String> getSupportedVersions() {
		return supportedVersions;
	}

	@XmlElement
	public Boolean getWrpoints() {
		return wrpoints;
	}

	@XmlElement
	public Boolean getGlpoints() {
		return glpoints;
	}

	@XmlElement
	public Boolean getHwpoints() {
		return hwpoints;
	}

	@XmlElement
	public Boolean getMultithreaded() {
		return multithreaded;
	}

	@XmlElement
	public Integer getParentId() {
		return parentId;
	}

	@XmlElement
	public String getCountry() {
		return country;
	}

	@XmlElement
	public String getRegion() {
		return region;
	}

}

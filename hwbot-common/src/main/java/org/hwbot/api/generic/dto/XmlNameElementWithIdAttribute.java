package org.hwbot.api.generic.dto;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlValue;

/**
 * Utility class for the HWBOT API. An API response generally contains user, team, processor, etc. elements. All of these should have an ID attribute.
 * For easy access, users can use the id to perform API search queries.
 * Generated xml looks like this: &lt;user id="1"&gt;root&lt;/user&gt;
 */
public class XmlNameElementWithIdAttribute {
	
	@XmlAttribute
	private String id;

	@XmlValue
	private String name;

	public XmlNameElementWithIdAttribute() {
	}

        public XmlNameElementWithIdAttribute(Integer id, String name) {
                this.id = id + "";
                this.name = name;
	}

	public XmlNameElementWithIdAttribute(String id, String name) {
		this.id = id;
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}
}

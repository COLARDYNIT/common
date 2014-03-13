package org.hwbot.api.submit.dto;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "response")
public class VersionErrorResponse implements VersionResponse, Serializable {
    private static final long serialVersionUID = 1L;
    private String error;

    public VersionErrorResponse() {
        super();
    }

    public VersionErrorResponse(String error) {
        this();
        this.error = error;
    }

    @XmlElement
    public String getError() {
        return error;
    }
}
package org.hwbot.api.submit.dto;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "response")
public class VersionApiResponse implements Serializable {

    private static final long serialVersionUID = 1L;
    private String url;
    private String version;
    private String error;

    public VersionApiResponse() {
    }

    public VersionApiResponse(String url, String version) {
        super();
        this.url = url;
        this.version = version;
    }

    public VersionApiResponse(String error) {
        super();
        this.error = error;
    }

    @XmlElement
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @XmlElement
    public String getVersion() {
        return version;
    }

    public void setVersion(String status) {
        this.version = status;
    }

    @XmlElement
    public String getError() {
        return error;
    }

    public void setError(String message) {
        this.error = message;
    }

}

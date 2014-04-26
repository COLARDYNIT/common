package org.hwbot.api.submit.dto;

import java.io.Serializable;
import java.util.Set;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

/**
 * Submission and versioning API, for benchmark clients
 * 
 * @author frederik
 * 
 */
@XmlRootElement(name = "response")
@JsonSerialize(include = Inclusion.NON_NULL)
public class VersionApiResponse implements Serializable {

    private static final long serialVersionUID = 1L;
    private String url;
    private String version;
    private Set<String> supportedVersions;
    private String error;

    public VersionApiResponse() {
    }

    public VersionApiResponse(String url, String version, Set<String> supportedVersions) {
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

    @XmlElement
    public Set<String> getSupportedVersions() {
        return supportedVersions;
    }

    public void setSupportedVersions(Set<String> supportedVersions) {
        this.supportedVersions = supportedVersions;
    }

    @Override
    public String toString() {
        return "VersionApiResponse [" + (url != null ? "url=" + url + ", " : "") + (version != null ? "version=" + version + ", " : "")
                + (supportedVersions != null ? "supportedVersions=" + supportedVersions + ", " : "") + (error != null ? "error=" + error : "") + "]";
    }

}

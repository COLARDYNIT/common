package org.hwbot.api.submit.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize.Inclusion;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import javax.xml.bind.annotation.XmlElement;
import java.io.Serializable;

/**
 * Submission and versioning API, for benchmark clients
 * 
 * @author frederik
 *
 */
@JacksonXmlRootElement(localName = "response")
@JsonSerialize(include = Inclusion.NON_NULL)
public class SubmitApiResponse implements Serializable {

    private static final long serialVersionUID = 1L;
    private String url;
    private String status;
    private String message;
    private String technicalMessage;

    public SubmitApiResponse() {
    }

    public SubmitApiResponse(String url, String status, String message) {
        super();
        this.url = url;
        this.status = status;
        this.message = message;
    }

    public SubmitApiResponse(String url, String status, String message, String technicalMessage) {
        super();
        this.url = url;
        this.status = status;
        this.message = message;
        this.technicalMessage = technicalMessage;
    }

    @Override
    public String toString() {
        return "SubmitApiResponse [" + (url != null ? "url=" + url + ", " : "") + (status != null ? "status=" + status + ", " : "")
                + (message != null ? "message=" + message : "") + "]";
    }

    @XmlElement
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @XmlElement
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @XmlElement
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @XmlElement
    public String getTechnicalMessage() {
        return technicalMessage;
    }

    public void setTechnicalMessage(String technicalMessage) {
        this.technicalMessage = technicalMessage;
    }

}

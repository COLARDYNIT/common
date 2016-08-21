package org.hwbot.api.generic.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "response")
public class GenericApiResponse<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    private int numberOfResults;

    private List<T> results;

    private List<String> errors;

    private String callback;

    public GenericApiResponse() {
        results = new ArrayList<T>();
    }

    @XmlElement
    public int getNumberOfResults() {
        return numberOfResults;
    }

    public void setNumberOfResults(int numberOfResults) {
        this.numberOfResults = numberOfResults;
    }

    @XmlElements({ @XmlElement(name = "submission", type = ApiSubmissionInfo.class), @XmlElement(name = "submission", type = ApiRankingInfo.class),
            @XmlElement(name = "member", type = ApiMemberInfo.class), @XmlElement(name = "benchmark", type = ApiApplicationInfo.class),
            @XmlElement(name = "competition", type = ApiContestInfo.class) })
    @XmlElementWrapper
    public List<T> getResults() {
        return results;
    }

    public void setResults(List<T> results) {
        this.results = results;
    }

    public void addResult(T result) {
        results.add(result);
    }

    @XmlElement(name = "error")
    @XmlElementWrapper
    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }

    @XmlTransient
    @JsonIgnore
    public String getCallback() {
        return callback;
    }

    public void setCallback(String callback) {
        this.callback = callback;
    }
}

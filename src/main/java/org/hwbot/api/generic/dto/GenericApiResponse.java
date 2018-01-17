package org.hwbot.api.generic.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@JacksonXmlRootElement(localName = "response")
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

    public List<T> getResults() {
        return results;
    }

    public void setResults(List<T> results) {
        this.results = results;
    }

    public void addResult(T result) {
        results.add(result);
    }

    @JacksonXmlProperty(localName ="error")
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

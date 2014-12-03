package org.hwbot.api.generic.dto;

import java.io.Serializable;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

/**
 * Contians basic user info.
 * 
 * @author frederik
 * 
 */
@JsonSerialize(include = Inclusion.NON_NULL)
public class CountryDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    private String name;
    private String url;
    private String flag;
    private String code;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

}

package org.hwbot.api.generic.dto;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

/**
 * Contians basic user info.
 * 
 * @author frederik
 * 
 */
@JsonSerialize(include = Inclusion.NON_NULL)
public class UserDTO {

    private String name;
    private String url;
    private int id;
    private String team;
    private String countryFlag;
    private String avatar;

    public UserDTO(String name, String url, int id, String team, String countryFlag, String avatar) {
        super();
        this.name = name;
        this.url = url;
        this.id = id;
        this.team = team;
        this.countryFlag = countryFlag;
        this.avatar = avatar;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public int getId() {
        return id;
    }

    public String getTeam() {
        return team;
    }

    public String getCountryFlag() {
        return countryFlag;
    }

    public String getAvatar() {
        return avatar;
    }

}

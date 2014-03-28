package org.hwbot.api.generic.dto;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

@JsonSerialize(include = Inclusion.NON_NULL)
public class NameIdDTO {
    private String name;
    private int id;

    public NameIdDTO() {
        super();
    }

    public NameIdDTO(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "[" + (name != null ? "name=" + name + ", " : "") + "id=" + id + "]";
    }

}
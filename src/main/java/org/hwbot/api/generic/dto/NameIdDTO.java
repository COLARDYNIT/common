package org.hwbot.api.generic.dto;

import java.io.Serializable;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize.Inclusion;

@JsonSerialize(include = Inclusion.NON_NULL)
public class NameIdDTO implements Serializable {

    private static final long serialVersionUID = 1L;
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
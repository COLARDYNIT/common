package org.hwbot.api.esports;

import java.io.Serializable;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

@JsonSerialize(include = Inclusion.NON_NULL)
public class TagDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    public Integer id;
    public String tag;
    public String type;
    public String name;
    public String fullName;

    public TagDTO(Integer id, String name, String fullName, String tag, String type) {
        super();
        this.id = id;
        this.name = name;
        this.fullName = fullName;
        this.tag = tag;
        this.type = type;
    }

    @Override
    public String toString() {
        return "TagDTO [" + (id != null ? "id=" + id + ", " : "") + (tag != null ? "tag=" + tag + ", " : "") + (type != null ? "type=" + type : "") + "]";
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        TagDTO other = (TagDTO) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

}

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

    public TagDTO(Integer id, String tag, String type) {
        super();
        this.id = id;
        this.tag = tag;
        this.type = type;
    }

    @Override
    public String toString() {
        return "TagDTO [" + (id != null ? "id=" + id + ", " : "") + (tag != null ? "tag=" + tag + ", " : "") + (type != null ? "type=" + type : "") + "]";
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

}

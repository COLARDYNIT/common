package org.hwbot.api.esports;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

/**
 * Competition info for esports.
 * 
 * @author frederik
 * 
 */
@JsonSerialize(include = Inclusion.NON_NULL)
public class CompetitionScheduleRowDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;
    private String safeName;
    private String type;
    private List<CompetitionScheduleDTO> list = new ArrayList<CompetitionScheduleDTO>();

    public CompetitionScheduleRowDTO(String name, String type, String safeName) {
        this.type = type;
        this.name = name;
        this.safeName = safeName;
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

    public String getSafeName() {
        return safeName;
    }

    public void setSafeName(String safeName) {
        this.safeName = safeName;
    }

    public List<CompetitionScheduleDTO> getList() {
        return list;
    }

    public void setList(List<CompetitionScheduleDTO> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "CompetitionScheduleRowDTO [" + (name != null ? "name=" + name + ", " : "") + (safeName != null ? "safeName=" + safeName + ", " : "")
                + (type != null ? "type=" + type + ", " : "") + (list != null ? "list=" + list : "") + "]";
    }

}

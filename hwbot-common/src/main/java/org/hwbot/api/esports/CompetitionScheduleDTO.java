package org.hwbot.api.esports;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;
import org.hwbot.util.StringUtil;

/**
 * Competition info for esports.
 * 
 * @author frederik
 * 
 */
@JsonSerialize(include = Inclusion.NON_NULL)
public class CompetitionScheduleDTO implements Serializable {

    private static final long serialVersionUID = 2L;

    private int id;
    private String name;
    private String safeName;
    private String shortName;
    private Date startDate;
    private Date endDate;
    private boolean empty;

    public CompetitionScheduleDTO(Date startDate, Date endDate) {
        this.empty = true;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public CompetitionScheduleDTO(int id, String name, String safeName, String shortName, Date startDate, Date endDate) {
        super();
        this.id = id;
        this.shortName = shortName;
        this.name = name;
        this.empty = false;
        this.safeName = safeName;
        this.startDate = startDate;
        this.endDate = endDate;
        if (this.shortName == null || this.shortName.length() == 0) {
            this.shortName = StringUtil.abbreviate(this.name, 20);
        }
    }

    public String getPeriod() {
        if (getStartMonth() == getEndMonth()) {
            return new SimpleDateFormat("d").format(startDate) + " - " + new SimpleDateFormat("d MMM").format(endDate);
        } else {
            return new SimpleDateFormat("MMM dd").format(startDate) + " - " + new SimpleDateFormat("MMM dd").format(endDate);
        }
    }

    public int getMonths() {
        Integer startMonth = getStartMonth();
        Integer endMonth = getEndMonth();
        return (startMonth - endMonth) + 1;
    }

    @JsonIgnore
    public Integer getEndMonth() {
        Integer endMonth = Integer.valueOf(new SimpleDateFormat("M").format(startDate)) + 1;
        return endMonth;
    }

    @JsonIgnore
    public Integer getStartMonth() {
        Integer startMonth = Integer.valueOf(new SimpleDateFormat("M").format(endDate)) + 1;
        return startMonth;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public boolean isEmpty() {
        return empty;
    }

    public void setEmpty(boolean empty) {
        this.empty = empty;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    @Override
    public String toString() {
        return "CompetitionScheduleDTO [id=" + id + ", " + (name != null ? "name=" + name + ", " : "")
                + (safeName != null ? "safeName=" + safeName + ", " : "") + (shortName != null ? "shortName=" + shortName + ", " : "")
                + (startDate != null ? "startDate=" + startDate + ", " : "") + (endDate != null ? "endDate=" + endDate + ", " : "") + "empty=" + empty + "]";
    }

}

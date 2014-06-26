package org.hwbot.api.esports;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

/**
 * Competition info for esports.
 * 
 * @author frederik
 * 
 */
@JsonSerialize(include = Inclusion.NON_NULL)
public class CompetitionScheduleDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private int id;
    private String name;
    private String safeName;
    private Date startDate;
    private Date endDate;

    public CompetitionScheduleDTO(int id, String name, String safeName, Date startDate, Date endDate) {
        super();
        this.id = id;
        this.name = name;
        this.safeName = safeName;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getStartDateFormatted() {
        return new SimpleDateFormat("MMM dd").format(startDate);
    }

    public String getEndDateFormatted() {
        return new SimpleDateFormat("MMM dd").format(endDate);
    }

    public int getStartMonth() {
        Calendar instance = Calendar.getInstance();
        instance.setTime(startDate);
        return instance.get(Calendar.MONTH) + 1;
    }

    public int getEndMonth() {
        Calendar instance = Calendar.getInstance();
        instance.setTime(endDate);
        return instance.get(Calendar.MONTH) + 1;
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

    @Override
    public String toString() {
        return "CompetitionScheduleDTO [id=" + id + ", " + (name != null ? "name=" + name + ", " : "")
                + (safeName != null ? "safeName=" + safeName + ", " : "") + (startDate != null ? "startDate=" + startDate + ", " : "")
                + (endDate != null ? "endDate=" + endDate : "") + "]";
    }

}

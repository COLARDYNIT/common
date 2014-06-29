package org.hwbot.api.esports;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

/**
 * Roadmap for pro oc, challenger, etc on esports site.
 * 
 * @author frederik
 * 
 */
@JsonSerialize(include = Inclusion.NON_NULL)
public class ScheduleDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    public int currentYear;
    public int currentMonth;

    public List<CompetitionScheduleRowDTO> rows = new ArrayList<CompetitionScheduleRowDTO>();

    public ScheduleDTO(int year, int currentMonth) {
        this.currentYear = year;
        this.currentMonth = currentMonth;
    }

    public int getCurrentYear() {
        return currentYear;
    }

    public void setCurrentYear(int currentYear) {
        this.currentYear = currentYear;
    }

    public int getCurrentMonth() {
        return currentMonth;
    }

    public void setCurrentMonth(int currentMonth) {
        this.currentMonth = currentMonth;
    }

    public List<CompetitionScheduleRowDTO> getRows() {
        return rows;
    }

    public void setRows(List<CompetitionScheduleRowDTO> rows) {
        this.rows = rows;
    }

    public List<CompetitionScheduleRowDTO> getRowsOfType(String type) {
        if (this.rows == null) {
            return Collections.emptyList();
        }
        List<CompetitionScheduleRowDTO> rowsOfType = new ArrayList<CompetitionScheduleRowDTO>();
        for (CompetitionScheduleRowDTO row : this.rows) {
            if (type.equals(row.getType())) {
                rowsOfType.add(row);
            } else {
                System.out.println("type: " + row.getType());
            }
        }
        return rowsOfType;
    }

    @Override
    public String toString() {
        return "ScheduleDTO [currentYear=" + currentYear + ", currentMonth=" + currentMonth + ", " + (rows != null ? "rows=" + rows : "") + "]";
    }

}

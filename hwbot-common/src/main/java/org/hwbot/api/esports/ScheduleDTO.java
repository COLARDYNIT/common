package org.hwbot.api.esports;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
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
            }
        }
        return rowsOfType;
    }

    @Override
    public String toString() {
        return "ScheduleDTO [currentYear=" + currentYear + ", currentMonth=" + currentMonth + ", " + (rows != null ? "rows=" + rows : "") + "]";
    }

    public void fillGaps() {
        // fils the gaps in between the competitions
        for (CompetitionScheduleRowDTO row : this.rows) {
            for (int i = 1; i <= 12; i++) {
                boolean gap = true;
                List<CompetitionScheduleDTO> list = row.getList();
                for (CompetitionScheduleDTO dto : list) {
                    Calendar start = Calendar.getInstance();
                    start.setTime(dto.getStartDate());
                    int startMonth = start.get(Calendar.MONTH) + 1;
                    start.setTime(dto.getEndDate());
                    int endMonth = start.get(Calendar.MONTH) + 1;
                    int endYear = start.get(Calendar.YEAR);

                    if (i == startMonth || (i == endMonth && this.currentYear == endYear) || i > startMonth && (i < endMonth && this.currentYear == endYear)) {
                        gap = false;
                        break;
                    }
                }
                // System.out.println(row.getType() + " month #" + i + " gap? " + gap);
                if (gap) {
                    Calendar cal = Calendar.getInstance();
                    cal.set(this.currentYear, i - 1, 1);
                    Date start = cal.getTime();
                    cal.set(this.currentYear, i - 1, 28);
                    Date end = cal.getTime();
                    list.add(new CompetitionScheduleDTO(start, end));
                }
            }
            Collections.sort(row.getList(), new Comparator<CompetitionScheduleDTO>() {
                @Override
                public int compare(CompetitionScheduleDTO o1, CompetitionScheduleDTO o2) {
                    return o1.getStartDate().compareTo(o2.getStartDate());
                }
            });
        }

    }

}
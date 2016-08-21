package org.hwbot.api.esports;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize.Inclusion;
import org.hwbot.util.StringUtil;

/**
 * Roadmap for pro oc, challenger, etc on esports site.
 * 
 * @author frederik
 * 
 */
@JsonSerialize(include = Inclusion.NON_NULL)
public class ScheduleDTO extends DiscussableDTO {

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
        StringBuilder builder = new StringBuilder("Schedule " + currentYear);
        builder.append("\n");
        for (CompetitionScheduleRowDTO row : this.rows) {
            builder.append(" - " + row.getType() + " - " + row.getName() + "\t");
            List<CompetitionScheduleDTO> list = row.getList();
            for (CompetitionScheduleDTO dto : list) {
                builder.append(" " + (!dto.isEmpty() ? dto.getName() : ""));
            }
            builder.append("\n");
        }

        return builder.toString();
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

    public void add(String fullName, String tag, String type, CompetitionScheduleDTO dto) {
        tag = StringUtil.makeUrlSafe(tag);
        boolean added = false;
        boolean firstRow = true;
        for (CompetitionScheduleRowDTO row : this.rows) {
            if (tag.equals(row.getTag())) {
                firstRow = false;
                boolean overlaps = false;
                for (CompetitionScheduleDTO schedule : row.getList()) {
                    // check if month overlaps
                    if (schedule.getStartMonth() == dto.getStartMonth() || schedule.getStartMonth() == dto.getEndMonth()) {
                        overlaps = true;
                        break;
                    }
                    if (schedule.getEndMonth() == dto.getEndMonth() || schedule.getEndMonth() == dto.getStartMonth()) {
                        overlaps = true;
                        break;
                    }
                }
                if (!overlaps) {
                    row.add(dto);
                    added = true;
                    break;
                }
            }
        }

        if (!added) {
            CompetitionScheduleRowDTO row = new CompetitionScheduleRowDTO((firstRow ? fullName : null), tag, type);
            this.rows.add(row);
            row.add(dto);
        }

    }

}

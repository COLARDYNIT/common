package org.hwbot.api.esports;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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

    public void add(CompetitionScheduleDTO scheduleDTO) {
        Date startDate = scheduleDTO.getStartDate();
        Date endDate = scheduleDTO.getEndDate();
        Calendar startCal = Calendar.getInstance();
        Calendar endCal = Calendar.getInstance();
        startCal.setTime(startDate);
        endCal.setTime(endDate);

        int startMonth = startCal.get(Calendar.MONTH) + 1;
        int endMonth = endCal.get(Calendar.MONTH) + 1;
        int endYear = endCal.get(Calendar.YEAR);
        if (endMonth < startMonth) {
            endMonth = 12;
        }

        for (CompetitionScheduleDTO dto : this.list) {
            // overlaps?
            startCal.setTime(dto.getStartDate());
            endCal.setTime(dto.getEndDate());

            int startMonthDto = startCal.get(Calendar.MONTH) + 1;
            int endMonthDto = endCal.get(Calendar.MONTH) + 1;
            int endYearDto = endCal.get(Calendar.YEAR);
            if (endMonthDto < startMonthDto) {
                endMonthDto = 12;
            }

            if (startMonth == startMonthDto) {
                dto.setName(dto.getName() + ", " + scheduleDTO.getName());
                if (endMonth > endMonthDto) {
                    dto.setEndDate(endDate);
                }
            } else if (startMonth > startMonthDto) {
                if (endMonth <= endMonthDto) {
                    dto.setName(dto.getName() + ", " + scheduleDTO.getName());
                } else if (startMonth <= endMonthDto) {
                    dto.setEndDate(endDate);
                    dto.setName(dto.getName() + ", " + scheduleDTO.getName());
                }
            } else if (startMonth < startMonthDto) {
                if ((endMonth > startMonthDto || endMonth >= endMonthDto)) {
                    dto.setName(dto.getName() + ", " + scheduleDTO.getName());
                    dto.setStartDate(startDate);
                }
            }
        }

    }
}

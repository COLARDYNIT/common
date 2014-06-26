package org.hwbot.api.esports;

import java.io.Serializable;
import java.util.ArrayList;
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

    public List<CompetitionScheduleDTO> sponsored = new ArrayList<CompetitionScheduleDTO>();
    public List<CompetitionScheduleDTO> events = new ArrayList<CompetitionScheduleDTO>();
    public List<CompetitionScheduleDTO> rookie = new ArrayList<CompetitionScheduleDTO>();
    public List<CompetitionScheduleDTO> team = new ArrayList<CompetitionScheduleDTO>();
    public List<CompetitionScheduleDTO> country = new ArrayList<CompetitionScheduleDTO>();

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

    public List<CompetitionScheduleDTO> getSponsored() {
        return sponsored;
    }

    public void setSponsored(List<CompetitionScheduleDTO> sponsored) {
        this.sponsored = sponsored;
    }

    public List<CompetitionScheduleDTO> getEvents() {
        return events;
    }

    public void setEvents(List<CompetitionScheduleDTO> events) {
        this.events = events;
    }

    public List<CompetitionScheduleDTO> getRookie() {
        return rookie;
    }

    public void setRookie(List<CompetitionScheduleDTO> rookie) {
        this.rookie = rookie;
    }

    public List<CompetitionScheduleDTO> getTeam() {
        return team;
    }

    public void setTeam(List<CompetitionScheduleDTO> team) {
        this.team = team;
    }

    public List<CompetitionScheduleDTO> getCountry() {
        return country;
    }

    public void setCountry(List<CompetitionScheduleDTO> country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "Schedule " + currentYear + ", " + (sponsored != null ? "sponsored=" + sponsored.size() + ", " : "")
                + (events != null ? "events=" + events.size() + ", " : "") + (rookie != null ? "rookie=" + rookie.size() + ", " : "")
                + (team != null ? "team=" + team.size() + ", " : "") + (country != null ? "country=" + country.size() : "") + "]";
    }

}

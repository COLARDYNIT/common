package org.hwbot.api.esports;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Roadmap for pro oc, challenger, etc on esports site.
 *
 * @author frederik
 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class CompetitionStageLapDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer nr;
    private Long start;
    private Long end;
    private List<RankDTO> ranking;

    public CompetitionStageLapDTO(Integer nr, Long start, Long end) {
        this.nr = nr;
        this.start = start;
        this.end = end;
    }

    public Integer getNr() {
        return nr;
    }

    public void setNr(Integer nr) {
        this.nr = nr;
    }

    public Long getStart() {
        return start;
    }

    public void setStart(Long start) {
        this.start = start;
    }

    public Long getEnd() {
        return end;
    }

    public void setEnd(Long end) {
        this.end = end;
    }

    public List<RankDTO> getRanking() {
        return ranking;
    }

    public void setRanking(List<RankDTO> ranking) {
        this.ranking = ranking;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ContestBenchmarkLap{");
        sb.append("nr=").append(nr);
        sb.append(", start=").append(new Date(start));
        sb.append(", end=").append(new Date(end));
        sb.append('}');
        return sb.toString();
    }

    @JsonProperty
    public boolean isOpen() {
        long now = System.currentTimeMillis();
        return start <= now && now < end;
    }

}

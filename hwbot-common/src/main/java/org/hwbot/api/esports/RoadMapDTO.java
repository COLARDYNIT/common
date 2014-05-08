package org.hwbot.api.esports;

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
public class RoadMapDTO {

    private String name;
    private List<CompetitionRoundDTO> rounds;

    public RoadMapDTO() {
    }

    public RoadMapDTO(String name, List<CompetitionRoundDTO> rounds) {
        super();
        this.name = name;
        this.rounds = rounds;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<CompetitionRoundDTO> getRounds() {
        return rounds;
    }

    public void setRounds(List<CompetitionRoundDTO> rounds) {
        this.rounds = rounds;
    }

    @Override
    public String toString() {
        return "RoadMapDTO [" + (name != null ? "name=" + name + ", " : "") + (rounds != null ? "rounds=" + rounds : "") + "]";
    }

}

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
    private String type;
    private List<CompetitionRoundDTO> rounds;
    private List<RankDTO> ranking;

    public RoadMapDTO() {
        super();
    }

    public RoadMapDTO(String type, String name, List<CompetitionRoundDTO> rounds) {
        super();
        this.type = type;
        this.name = name;
        this.rounds = rounds;
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

    public List<CompetitionRoundDTO> getRounds() {
        return rounds;
    }

    public void setRounds(List<CompetitionRoundDTO> rounds) {
        this.rounds = rounds;
    }

    public CompetitionRoundDTO getCurrent() {
        CompetitionRoundDTO current = null;
        for (CompetitionRoundDTO round : getRounds()) {
            // active one
            if (round.isActive()) {
                current = round;
                break;
            }
        }
        if (current == null) {
            // none active, last one passed
            for (CompetitionRoundDTO round : getRounds()) {
                if (round.isPassed() && (current == null || round.getStartDate().after(current.getStartDate()))) {
                    current = round;
                    break;
                }
            }
        }
        if (current == null) {
            // not yet started, first one
            if (getRounds().size() > 0) {
                current = getRounds().get(0);
            }
        }
        return current;
    }

    public int getCurrentIndex() {
        CompetitionRoundDTO current = getCurrent();
        int index = 1;
        if (current != null) {
            for (CompetitionRoundDTO round : getRounds()) {
                if (round.equals(current)) {
                    break;
                }
                index++;
            }
        }
        return index;
    }

    public List<RankDTO> getRanking() {
        return ranking;
    }

    public void setRanking(List<RankDTO> ranking) {
        this.ranking = ranking;
    }

    public int getPercentComplete() {
        return 0;
        // TODO reliable way to find correct percentage
        // int total = getRounds().size();
        // float passed = 0;
        // for (CompetitionRoundDTO round : getRounds()) {
        // if (round.isPassed()) {
        // passed++;
        // }
        // }
        // return ((int) ((passed / total) * 100));
    }

    @Override
    public String toString() {
        return "RoadMapDTO [" + (name != null ? "name=" + name + ", " : "") + (rounds != null ? "rounds=" + rounds : "") + "]";
    }

}

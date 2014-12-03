package org.hwbot.api.esports;

import java.util.HashMap;
import java.util.Map;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

/**
 * Competition stage info for esports.
 * 
 * @author frederik
 * 
 */
@JsonSerialize(include = Inclusion.NON_NULL)
public class StageOverviewDTO extends DiscussableDTO {

    private static final long serialVersionUID = 1L;

    public Map<Integer, RankDTO> currentRankMap = new HashMap<Integer, RankDTO>();
    public Map<Integer, RankDTO> betterRankMap = new HashMap<Integer, RankDTO>();

    @Override
    public String toString() {
        return "StageOverviewDTO [" + (currentRankMap != null ? "currentRankMap=" + currentRankMap + ", " : "")
                + (betterRankMap != null ? "betterRankMap=" + betterRankMap : "") + "]";
    }

    public Map<Integer, RankDTO> getCurrentRankMap() {
        return currentRankMap;
    }

    public void setCurrentRankMap(Map<Integer, RankDTO> currentRankMap) {
        this.currentRankMap = currentRankMap;
    }

    public Map<Integer, RankDTO> getBetterRankMap() {
        return betterRankMap;
    }

    public void setBetterRankMap(Map<Integer, RankDTO> betterRankMap) {
        this.betterRankMap = betterRankMap;
    }

}

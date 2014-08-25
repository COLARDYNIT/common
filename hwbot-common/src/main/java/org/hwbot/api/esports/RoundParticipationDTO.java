package org.hwbot.api.esports;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

@JsonSerialize(include = Inclusion.NON_NULL)
public class RoundParticipationDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private final Map<Integer, RankDTO> currentRankMap = new HashMap<Integer, RankDTO>();
    private final Map<Integer, RankDTO> betterRankMap = new HashMap<Integer, RankDTO>();

    public Map<Integer, RankDTO> getCurrentRankMap() {
        return currentRankMap;
    }

    public Map<Integer, RankDTO> getBetterRankMap() {
        return betterRankMap;
    }

}

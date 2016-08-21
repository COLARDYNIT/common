package org.hwbot.api.esports;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by frederik on 16/01/15.
 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class GroupDTO implements Serializable{

    private static final long serialVersionUID = 1L;

    private String label;
    private List<RankDTO> ranks = new ArrayList<RankDTO>();

    public GroupDTO(String label) {
        this.label = label;
    }

    public void add(RankDTO rank){
        ranks.add(rank);
    }

    public List<RankDTO> getRanks() {
        return ranks;
    }

    public void setRanks(List<RankDTO> ranks) {
        this.ranks = ranks;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("GroupDTO{");
        sb.append("label='").append(label).append('\'');
        sb.append(", ranks=").append(ranks);
        sb.append('}');
        return sb.toString();
    }
}

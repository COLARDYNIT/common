package org.hwbot.api.generic.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize.Inclusion;

@JsonSerialize(include = Inclusion.NON_NULL)
public class RankStatusDTO {

    private SubmissionDTO better;
    private SubmissionDTO worse;
    private SubmissionDTO me;

    private int rank;
    private int total;

    public SubmissionDTO getBetter() {
        return better;
    }

    public void setBetter(SubmissionDTO better) {
        this.better = better;
    }

    public SubmissionDTO getWorse() {
        return worse;
    }

    public void setWorse(SubmissionDTO worse) {
        this.worse = worse;
    }

    public SubmissionDTO getMe() {
        return me;
    }

    public void setMe(SubmissionDTO me) {
        this.me = me;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

}
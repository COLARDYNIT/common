package org.hwbot.api.webservice.dto;

import java.util.List;

public class CompetitionLeaderboardDTO {


//	- competition name 
//	- start time (contest.startDate) 
//	- end time (contest.endDate) 


    private String startDate;
    private String endDate;
    private String competetionName;
    private List<CompetitionLeaderboardRankDTO> roundRanking;


    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public List<CompetitionLeaderboardRankDTO> getRoundRanking() {
        return roundRanking;
    }

    public void setRoundRanking(List<CompetitionLeaderboardRankDTO> roundRanking) {
        this.roundRanking = roundRanking;
    }

    public String getCompetetionName() {
        return competetionName;
    }

    public void setCompetetionName(String competetionName) {
        this.competetionName = competetionName;
    }


}

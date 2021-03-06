package org.hwbot.api.bench.dto;

import java.io.Serializable;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize.Inclusion;

/**
 * For (android) benchmark app
 * 
 * @author frederik
 * 
 */
@JsonSerialize(include = Inclusion.NON_NULL)
public class UserStatsDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Float leaguePoints;
    private Float teamPowerPoints;
    private Integer leagueRank;
    private Integer leagueRankTotal;
    private Integer leagueNationalRank;
    private Integer leagueNationalRankTotal;
    private Integer leagueTeamRank;
    private Integer leagueTeamRankTotal;
    private Integer achievements;
    private Integer achievementsTotal;
    private Integer challengesWon;
    private Integer challengesTotal;
    private Integer hardwareMasterRank;

    public Float getLeaguePoints() {
        return leaguePoints;
    }

    public void setLeaguePoints(Float leaguePoints) {
        this.leaguePoints = leaguePoints;
    }

    public Float getTeamPowerPoints() {
        return teamPowerPoints;
    }

    public void setTeamPowerPoints(Float teamPowerPoints) {
        this.teamPowerPoints = teamPowerPoints;
    }

    public Integer getLeagueRank() {
        return leagueRank;
    }

    public void setLeagueRank(Integer leagueRank) {
        this.leagueRank = leagueRank;
    }

    public Integer getLeagueNationalRank() {
        return leagueNationalRank;
    }

    public void setLeagueNationalRank(Integer leagueNationalRank) {
        this.leagueNationalRank = leagueNationalRank;
    }

    public Integer getLeagueTeamRank() {
        return leagueTeamRank;
    }

    public void setLeagueTeamRank(Integer leagueTeamRank) {
        this.leagueTeamRank = leagueTeamRank;
    }

    public Integer getAchievements() {
        return achievements;
    }

    public void setAchievements(Integer achievements) {
        this.achievements = achievements;
    }

    public Integer getAchievementsTotal() {
        return achievementsTotal;
    }

    public void setAchievementsTotal(Integer achievementsTotal) {
        this.achievementsTotal = achievementsTotal;
    }

    public Integer getChallengesWon() {
        return challengesWon;
    }

    public void setChallengesWon(Integer challengesWon) {
        this.challengesWon = challengesWon;
    }

    public Integer getChallengesTotal() {
        return challengesTotal;
    }

    public void setChallengesTotal(Integer challengesTotal) {
        this.challengesTotal = challengesTotal;
    }

    public Integer getHardwareMasterRank() {
        return hardwareMasterRank;
    }

    public void setHardwareMasterRank(Integer hardwareMasterRank) {
        this.hardwareMasterRank = hardwareMasterRank;
    }

    public Integer getLeagueRankTotal() {
        return leagueRankTotal;
    }

    public void setLeagueRankTotal(Integer leagueRankTotal) {
        this.leagueRankTotal = leagueRankTotal;
    }

    public Integer getLeagueNationalRankTotal() {
        return leagueNationalRankTotal;
    }

    public void setLeagueNationalRankTotal(Integer leagueNationalRankTotal) {
        this.leagueNationalRankTotal = leagueNationalRankTotal;
    }

    public Integer getLeagueTeamRankTotal() {
        return leagueTeamRankTotal;
    }

    public void setLeagueTeamRankTotal(Integer leagueTeamRankTotal) {
        this.leagueTeamRankTotal = leagueTeamRankTotal;
    }

    @Override
    public String toString() {
        return "UserStatsDTO [" + (leaguePoints != null ? "leaguePoints=" + leaguePoints + ", " : "")
                + (teamPowerPoints != null ? "teamPowerPoints=" + teamPowerPoints + ", " : "") + (leagueRank != null ? "leagueRank=" + leagueRank + ", " : "")
                + (leagueNationalRank != null ? "leagueNationalRank=" + leagueNationalRank + ", " : "")
                + (leagueTeamRank != null ? "leagueTeamRank=" + leagueTeamRank + ", " : "")
                + (achievements != null ? "achievements=" + achievements + ", " : "")
                + (achievementsTotal != null ? "achievementsTotal=" + achievementsTotal + ", " : "")
                + (challengesWon != null ? "challengesWon=" + challengesWon + ", " : "")
                + (challengesTotal != null ? "challengesTotal=" + challengesTotal + ", " : "")
                + (hardwareMasterRank != null ? "hardwareMasterRank=" + hardwareMasterRank : "") + "]";
    }

}

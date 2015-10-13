package org.hwbot.api.webservice.dto;

public class CommunityTeamRankingDTO {
    
    private Integer powerPointsRank;
    
    private String score;
    
    private String scoreURL;

    private String userName;
    
    private String flagName;
    
    private String flagCode;

    
    private String frequency;
        
    private String Hardware;
    
    private String powerPoints;
    
    private String coolingCode;

	public Integer getPowerPointsRank() {
		return powerPointsRank;
	}

	public void setPowerPointsRank(Integer powerPointsRank) {
		this.powerPointsRank = powerPointsRank;
	}

	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}

	public String getScoreURL() {
		return scoreURL;
	}

	public void setScoreURL(String scoreURL) {
		this.scoreURL = scoreURL;
	}


	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}


	public String getFrequency() {
		return frequency;
	}

	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}

	public String getHardware() {
		return Hardware;
	}

	public void setHardware(String hardware) {
		Hardware = hardware;
	}

	public String getPowerPoints() {
		return powerPoints;
	}

	public void setPowerPoints(String powerPoints) {
		this.powerPoints = powerPoints;
	}

	public String getCoolingCode() {
		return coolingCode;
	}

	public void setCoolingCode(String coolingCode) {
		this.coolingCode = coolingCode;
	}

	public String getFlagName() {
		return flagName;
	}

	public void setFlagName(String flagName) {
		this.flagName = flagName;
	}

	public String getFlagCode() {
		return flagCode;
	}

	public void setFlagCode(String flagCode) {
		this.flagCode = flagCode;
	}


}

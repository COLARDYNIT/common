package org.hwbot.api.webservice.dto;

public class BenchmarkRankingDTO{
    
    private Integer powerPointsRank;
    
    private String score;
    
    private String scoreURL;

    private String userName;
    
    private String flagName;
    
    private String flagCode;

    private String frequency;
        
    private String hardware;
    
    private String hardwareUrl;
    

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
		return hardware;
	}

	public void setHardware(String hardware) {
		this.hardware = hardware;
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

	public String getHardwareUrl() {
		return hardwareUrl;
	}

	public void setHardwareUrl(String hardwareUrl) {
		this.hardwareUrl = hardwareUrl;
	}


}

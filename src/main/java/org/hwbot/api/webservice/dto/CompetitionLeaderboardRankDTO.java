package org.hwbot.api.webservice.dto;

import java.util.List;

import org.hwbot.api.esports.CompetitionRoundDTO;
import org.hwbot.api.esports.RankDTO;
import org.hwbot.api.generic.dto.ApplicationDTO;
import org.hwbot.api.generic.dto.SubmissionDTO;

public class CompetitionLeaderboardRankDTO {
	
//	- position 
//	- user flag 
//	- user name 
//	- total points 

private String userName;
private int position;
private String totalPoints;
private String countryCode;
private List<CompetitionLeaderboardStageDTO> Stages;
 

public int getPosition() {
	return position;
}
public void setPosition(int position) {
	this.position = position;
}
public String getTotalPoints() {
	return totalPoints;
}
public void setTotalPoints(String totalPoints) {
	this.totalPoints = totalPoints;
}

public List<CompetitionLeaderboardStageDTO> getStages() {
	return Stages;
}
public void setStages(List<CompetitionLeaderboardStageDTO> stages) {
	Stages = stages;
}
public String getCountryCode() {
	return countryCode;
}
public void setCountryCode(String countryCode) {
	this.countryCode = countryCode;
}
public String getUserName() {
	return userName;
}
public void setUserName(String userName) {
	this.userName = userName;
}

	
}

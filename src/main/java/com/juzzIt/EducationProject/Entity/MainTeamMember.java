package com.juzzIt.EducationProject.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class MainTeamMember {
@Id
@Column(name="MAIN_TEAM_MAMBER_ID")
private String mainTeamMemberId;
@Column(name="MAIN_TEAM_MAMBER_NAME")
private String mainTeamMemberName;
@Column(name="MAIN_TEAM_MAMBER_POSITION")
private String mainTeamMemberPosition;
@Column(name="MAIN_TEAM_MAMBER_INTRUDUCTION",length = 1000)
private String mainTeamMemberIntruduction;


public String getMainTeamMemberId() {
	return mainTeamMemberId;
}
public void setMainTeamMemberId(String mainTeamMemberId) {
	this.mainTeamMemberId = mainTeamMemberId;
}
public String getMainTeamMemberName() {
	return mainTeamMemberName;
}
public void setMainTeamMemberName(String mainTeamMemberName) {
	this.mainTeamMemberName = mainTeamMemberName;
}
public String getMainTeamMemberPosition() {
	return mainTeamMemberPosition;
}
public void setMainTeamMemberPosition(String mainTeamMemberPosition) {
	this.mainTeamMemberPosition = mainTeamMemberPosition;
}
public String getMainTeamMemberIntruduction() {
	return mainTeamMemberIntruduction;
}
public void setMainTeamMemberIntruduction(String mainTeamMemberIntruduction) {
	this.mainTeamMemberIntruduction = mainTeamMemberIntruduction;
}
public MainTeamMember() {
	super();
	// TODO Auto-generated constructor stub
}
public MainTeamMember(String mainTeamMemberId, String mainTeamMemberName, String mainTeamMemberPosition,
		String mainTeamMemberIntruduction) {
	super();
	this.mainTeamMemberId = mainTeamMemberId;
	this.mainTeamMemberName = mainTeamMemberName;
	this.mainTeamMemberPosition = mainTeamMemberPosition;
	this.mainTeamMemberIntruduction = mainTeamMemberIntruduction;
}
@Override
public String toString() {
	return "MainTeamMember [mainTeamMemberId=" + mainTeamMemberId + ", mainTeamMemberName=" + mainTeamMemberName
			+ ", mainTeamMemberPosition=" + mainTeamMemberPosition + ", mainTeamMemberIntruduction="
			+ mainTeamMemberIntruduction + "]";
}




}

package com.juzzIt.EducationProject.Entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class TeamMember {
	
	@Id
	@Column(name="TEAM_MAMBER_ID")
	private String teamMemberId;
	@Column(name="TEAM_MAMBER_NAME")
	private String teamMemberName;
	@Column(name="TEAM_MAMBER_POSITION")
	private String teamMemberPosition;
	@Column(name="TEAM_MAMBER_INTRUDUCTION",length = 10000)
	private String teamMemberIntruduction;
	
	@OneToMany(mappedBy = "teamMember",cascade = CascadeType.REMOVE)
	private List<TeamMemberImage> teamMemberImage;
	
	
	public String getTeamMemberId() {
		return teamMemberId;
	}
	public void setTeamMemberId(String teamMemberId) {
		this.teamMemberId = teamMemberId;
	}
	public String getTeamMemberName() {
		return teamMemberName;
	}
	public void setTeamMemberName(String teamMemberName) {
		this.teamMemberName = teamMemberName;
	}
	public String getTeamMemberPosition() {
		return teamMemberPosition;
	}
	public void setTeamMemberPosition(String teamMemberPosition) {
		this.teamMemberPosition = teamMemberPosition;
	}
	public String getTeamMemberIntruduction() {
		return teamMemberIntruduction;
	}
	public void setTeamMemberIntruduction(String teamMemberIntruduction) {
		this.teamMemberIntruduction = teamMemberIntruduction;
	}
	public TeamMember() {
		super();
		// TODO Auto-generated constructor stub
	}
	public TeamMember(String teamMemberId, String teamMemberName, String teamMemberPosition,
			String teamMemberIntruduction) {
		super();
		this.teamMemberId = teamMemberId;
		this.teamMemberName = teamMemberName;
		this.teamMemberPosition = teamMemberPosition;
		this.teamMemberIntruduction = teamMemberIntruduction;
	}
	@Override
	public String toString() {
		return "TeamMember [teamMemberId=" + teamMemberId + ", teamMemberName=" + teamMemberName
				+ ", teamMemberPosition=" + teamMemberPosition + ", teamMemberIntruduction=" + teamMemberIntruduction
				+ "]";
	}



}

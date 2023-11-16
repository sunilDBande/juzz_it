package com.juzzIt.EducationProject.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class TeamMemberImage {

	@Id
	@Column(name="IMAGE_ID")
	private String imageId;
	@Column(name="IMAGE_URL")
	private String imageURL;
	@Column(name="ACTIVE_STATUS")
	private String activeStatus;
	
	
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name="TEAM_MAMBER_ID")
	private TeamMember teamMember;


	public String getImageId() {
		return imageId;
	}


	public void setImageId(String imageId) {
		this.imageId = imageId;
	}


	public String getImageURL() {
		return imageURL;
	}


	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}


	public String getActiveStatus() {
		return activeStatus;
	}


	public void setActiveStatus(String activeStatus) {
		this.activeStatus = activeStatus;
	}


	public TeamMember getTeamMember() {
		return teamMember;
	}


	public void setTeamMember(TeamMember teamMember) {
		this.teamMember = teamMember;
	}


	public TeamMemberImage() {
		super();
		// TODO Auto-generated constructor stub
	}


	@Override
	public String toString() {
		return "TeamMemberImage [imageId=" + imageId + ", imageURL=" + imageURL + ", activeStatus=" + activeStatus
				+ ", teamMember=" + teamMember + "]";
	}


	public TeamMemberImage(String imageId, String imageURL, String activeStatus, TeamMember teamMember) {
		super();
		this.imageId = imageId;
		this.imageURL = imageURL;
		this.activeStatus = activeStatus;
		this.teamMember = teamMember;
	}
}

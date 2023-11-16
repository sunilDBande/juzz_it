package com.juzzIt.EducationProject.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class MainTeamMemberImage {

	
	@Id
	@Column(name="IMAGE_ID")
	private String imageId;
	@Column(name="IMAGE_URL")
	private String imageURL;
	@Column(name="ACTIVE_STATUS")
	private String activeStatus;
	
	
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name="MAIN_TEAM_MAMBER_ID")
	private MainTeamMember mainTeamMember;


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


	public MainTeamMember getMainTeamMember() {
		return mainTeamMember;
	}


	public void setMainTeamMember(MainTeamMember mainTeamMember) {
		this.mainTeamMember = mainTeamMember;
	}


	public MainTeamMemberImage(String imageId, String imageURL, String activeStatus,
			com.juzzIt.EducationProject.Entity.MainTeamMember mainTeamMember) {
		super();
		this.imageId = imageId;
		this.imageURL = imageURL;
		this.activeStatus = activeStatus;
		this.mainTeamMember = mainTeamMember;
	}


	public MainTeamMemberImage() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
	
}

package com.juzzIt.EducationProject.Entity;

import java.time.LocalDateTime;

import javax.tools.Tool;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;


@Entity
public class PlacementImage {

	

	@Id
	@Column(name="PLACEMENT_IMAGE_ID")
	private String palcementImageId;
	@Column(name="IMAGE_URL")
	private String imageURL;
	@Column(name="CREATED_DATE")
	private LocalDateTime createdDate;
	

	@Column(name="ACTIVE_STATUS")
	private String activeStatus;
	
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name="BATCH_COURSE_PLACEMENT_ID")
	private BatchCoursePlacements batchCoursePlacements;

	public PlacementImage() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PlacementImage(String palcementImageId, String imageURL, LocalDateTime createdDate, String activeStatus,
			BatchCoursePlacements batchCoursePlacements) {
		super();
		this.palcementImageId = palcementImageId;
		this.imageURL = imageURL;
		this.createdDate = createdDate;
		this.activeStatus = activeStatus;
		this.batchCoursePlacements = batchCoursePlacements;
	}

	public String getPalcementImageId() {
		return palcementImageId;
	}

	public void setPalcementImageId(String palcementImageId) {
		this.palcementImageId = palcementImageId;
	}

	public String getImageURL() {
		return imageURL;
	}

	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}

	public String getActiveStatus() {
		return activeStatus;
	}

	public void setActiveStatus(String activeStatus) {
		this.activeStatus = activeStatus;
	}

	public BatchCoursePlacements getBatchCoursePlacements() {
		return batchCoursePlacements;
	}

	public void setBatchCoursePlacements(BatchCoursePlacements batchCoursePlacements) {
		this.batchCoursePlacements = batchCoursePlacements;
	}

	@Override
	public String toString() {
		return "PlacementImage [palcementImageId=" + palcementImageId + ", imageURL=" + imageURL + ", createdDate="
				+ createdDate + ", activeStatus=" + activeStatus + ", batchCoursePlacements=" + batchCoursePlacements
				+ "]";
	}
}

package com.juzzIt.EducationProject.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class RecodedStudentPlacementImage {

	@Id
	@Column(name="IMAGE_ID")
	private String imageId;
	@Column(name="IMAGE_URL")
	private String imageURL;
	@Column(name="ACTIVE_STATUS")
	private String activeStatus;
	
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name="RECODED_STUDENT_PLACEMENT_ID")
	private RecordedStudentPlacemants recordedStudentPlacemants;

	public RecodedStudentPlacementImage() {
		super();
		// TODO Auto-generated constructor stub
	}

	public RecodedStudentPlacementImage(String imageId, String imageURL, String activeStatus,
			RecordedStudentPlacemants recordedStudentPlacemants) {
		super();
		this.imageId = imageId;
		this.imageURL = imageURL;
		this.activeStatus = activeStatus;
		this.recordedStudentPlacemants = recordedStudentPlacemants;
	}

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

	public RecordedStudentPlacemants getRecordedStudentPlacemants() {
		return recordedStudentPlacemants;
	}

	public void setRecordedStudentPlacemants(RecordedStudentPlacemants recordedStudentPlacemants) {
		this.recordedStudentPlacemants = recordedStudentPlacemants;
	}

	@Override
	public String toString() {
		return "RecodedStudentPlacementImage [imageId=" + imageId + ", imageURL=" + imageURL + ", activeStatus="
				+ activeStatus + ", recordedStudentPlacemants=" + recordedStudentPlacemants + "]";
	}
	
}

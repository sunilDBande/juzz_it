package com.juzzIt.EducationProject.Entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;


@Entity
public class BatchCourseRecordedVideoSubjectImages {	
	
	
	@Id
	@Column(name="MODULE_IMAGE_ID")
	private String subjectImageId;
	@Column(name="IMAGE_URL")
	private String imageURL;
	@Column(name="CREATED_DATE")
	private LocalDateTime createdDate;
	@Column(name="ACTIVE_STATUS")
	private String activeStatus;
	
	
	@ManyToOne
	@JoinColumn(name="BATCH_COURSE_RECORDED_VIDEO_SUBJECT_ID")
	private BatchCourseRecordedVideoSubject batchCourseRecordedVideoSubject;


	public BatchCourseRecordedVideoSubjectImages() {
		super();
		// TODO Auto-generated constructor stub
	}


	public BatchCourseRecordedVideoSubjectImages(String subjectImageId, String imageURL, LocalDateTime createdDate,
			String activeStatus, BatchCourseRecordedVideoSubject batchCourseRecordedVideoSubject) {
		super();
		this.subjectImageId = subjectImageId;
		this.imageURL = imageURL;
		this.createdDate = createdDate;
		this.activeStatus = activeStatus;
		this.batchCourseRecordedVideoSubject = batchCourseRecordedVideoSubject;
	}


	public String getSubjectImageId() {
		return subjectImageId;
	}


	public void setSubjectImageId(String subjectImageId) {
		this.subjectImageId = subjectImageId;
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


	public BatchCourseRecordedVideoSubject getBatchCourseRecordedVideoSubject() {
		return batchCourseRecordedVideoSubject;
	}


	public void setBatchCourseRecordedVideoSubject(BatchCourseRecordedVideoSubject batchCourseRecordedVideoSubject) {
		this.batchCourseRecordedVideoSubject = batchCourseRecordedVideoSubject;
	}


	@Override
	public String toString() {
		return "BatchCourseRecordedVideoSubjectImages [subjectImageId=" + subjectImageId + ", imageURL=" + imageURL
				+ ", createdDate=" + createdDate + ", activeStatus=" + activeStatus
				+ ", batchCourseRecordedVideoSubject=" + batchCourseRecordedVideoSubject + "]";
	}
}

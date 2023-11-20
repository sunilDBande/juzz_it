package com.juzzIt.EducationProject.Entity;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class RecordedSubjectVideo {
	
	@Id
	@Column(name="RECORDED_VIDEO_ID")
	private String recordedVideoId;
	@Column(name="VIDEO_TOPIC")
	private String vedioTopic;
	@Column(name="TOPIC_DESC")
	private String videoDesc;
	@Column(name="VEDIO_URL")
	private String videoURL;
	@Column(name="CREATED_DATE")
	private LocalDateTime createdDate;
	@Column(name="ACTIVE_STATUS")
	private String activeStatus;
	
	
	@ManyToOne
	@JoinColumn(name="BATCH_COURSE_RECORDED_VIDEO_SUBJECT_ID")
	private BatchCourseRecordedVideoSubject batchCourseRecordedVideoSubject;


	public RecordedSubjectVideo() {
		super();
		// TODO Auto-generated constructor stub
	}


	public String getRecordedVideoId() {
		return recordedVideoId;
	}


	public void setRecordedVideoId(String recordedVideoId) {
		this.recordedVideoId = recordedVideoId;
	}


	public String getVedioTopic() {
		return vedioTopic;
	}


	public void setVedioTopic(String vedioTopic) {
		this.vedioTopic = vedioTopic;
	}


	public String getVideoDesc() {
		return videoDesc;
	}


	public void setVideoDesc(String videoDesc) {
		this.videoDesc = videoDesc;
	}


	public String getVideoURL() {
		return videoURL;
	}


	public void setVideoURL(String videoURL) {
		this.videoURL = videoURL;
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


	public RecordedSubjectVideo(String recordedVideoId, String vedioTopic, String videoDesc, String videoURL,
			LocalDateTime createdDate, String activeStatus,
			BatchCourseRecordedVideoSubject batchCourseRecordedVideoSubject) {
		super();
		this.recordedVideoId = recordedVideoId;
		this.vedioTopic = vedioTopic;
		this.videoDesc = videoDesc;
		this.videoURL = videoURL;
		this.createdDate = createdDate;
		this.activeStatus = activeStatus;
		this.batchCourseRecordedVideoSubject = batchCourseRecordedVideoSubject;
	}


	@Override
	public String toString() {
		return "RecordedSubjectVideo [recordedVideoId=" + recordedVideoId + ", vedioTopic=" + vedioTopic
				+ ", videoDesc=" + videoDesc + ", videoURL=" + videoURL + ", createdDate=" + createdDate
				+ ", activeStatus=" + activeStatus + ", batchCourseRecordedVideoSubject="
				+ batchCourseRecordedVideoSubject + "]";
	}




}

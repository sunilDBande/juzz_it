package com.juzzIt.EducationProject.Entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class BatchCourseRecordedVideoSubject {

	@Id
	@Column(name="VIDEO_SUBJECT_ID")
	private String videoSubjectId;
	@Column(name="VIDEO_SUBJECT_NAME")
	private String videoSubjectName;
	@Column(name="ACTIVE_STATUS")
	private String activeStatus;
	
	
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name="BATCH_COURSE_ID")
	private BatchCourse batchCourse;
	
	
	@OneToMany(mappedBy = "batchCourseRecordedVideoSubject", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
	private List<RecordedSubjectVideo> recordedSubjectVideo;
	
	@OneToMany(mappedBy = "batchCourseRecordedVideoSubject", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
	private List<BatchCourseRecordedVideoSubjectImages> batchCourseRecordedVideoSubjectImages;



	public BatchCourseRecordedVideoSubject() {
		     super();
	}


	public BatchCourseRecordedVideoSubject(String videoSubjectId, String videoSubjectName, String activeStatus,
			BatchCourse batchCourse) {
		super();
		this.videoSubjectId = videoSubjectId;
		this.videoSubjectName = videoSubjectName;
		this.activeStatus = activeStatus;
		this.batchCourse = batchCourse;
	}


	public String getVideoSubjectId() {
		return videoSubjectId;
	}


	public void setVideoSubjectId(String videoSubjectId) {
		this.videoSubjectId = videoSubjectId;
	}


	public String getVideoSubjectName() {
		return videoSubjectName;
	}


	public void setVideoSubjectName(String videoSubjectName) {
		this.videoSubjectName = videoSubjectName;
	}


	public String getActiveStatus() {
		return activeStatus;
	}


	public void setActiveStatus(String activeStatus) {
		this.activeStatus = activeStatus;
	}


	public BatchCourse getBatchCourse() {
		return batchCourse;
	}


	public void setBatchCourse(BatchCourse batchCourse) {
		this.batchCourse = batchCourse;
	}


	@Override
	public String toString() {
		return "BatchCourseRecordedVideoSubject [videoSubjectId=" + videoSubjectId + ", videoSubjectName="
				+ videoSubjectName + ", activeStatus=" + activeStatus + ", batchCourse=" + batchCourse + "]";
	}
	
}

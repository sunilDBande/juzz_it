package com.juzzIt.EducationProject.Entity;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;


@Entity
public class CourseTypeVideo {
	

	@Id
	@Column(name="COURSE_TYPE_VIDEO_ID")
	private String courseTypeVideoId;
	@Column(name="VIDEO_URL")
	private String videoURL;
	@Column(name="CREATED_DATE")
	private LocalDateTime createdDate;
	@Column(name="ACTIVE_STATUS")
	private String activeStatus;

	@ManyToOne
	@JsonIgnore
	@JoinColumn(name="COURSE_TYPE_ID")
	private CourseType courseType;
	
	
	public CourseTypeVideo() {
		super();
		// TODO Auto-generated constructor stub
	}


	public CourseTypeVideo(String courseTypeVideoId, String videoURL, LocalDateTime createdDate, String activeStatus,
			CourseType courseType) {
		super();
		this.courseTypeVideoId = courseTypeVideoId;
		this.videoURL = videoURL;
		this.createdDate = createdDate;
		this.activeStatus = activeStatus;
		this.courseType = courseType;
	}


	public String getCourseTypeVideoId() {
		return courseTypeVideoId;
	}


	public void setCourseTypeVideoId(String courseTypeVideoId) {
		this.courseTypeVideoId = courseTypeVideoId;
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


	public CourseType getCourseType() {
		return courseType;
	}


	public void setCourseType(CourseType courseType) {
		this.courseType = courseType;
	}


	@Override
	public String toString() {
		return "CourseTypeVideo [courseTypeVideoId=" + courseTypeVideoId + ", videoURL=" + videoURL + ", createdDate="
				+ createdDate + ", activeStatus=" + activeStatus + ", courseType=" + courseType + "]";
	}
}

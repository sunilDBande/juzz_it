package com.juzzIt.EducationProject.Entity;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class CourseImage {

	
	@Id
	@Column(name="COURSE_IMAGE_ID")
	private String courseImageId;
	@Column(name="IMAGE_URL")
	private String imageURL;
	@Column(name="CREATED_DATE")
	private LocalDateTime createdDate;
	@Column(name="ACTIVE_STATUS")
	private String activeStatus;
	
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name="COURSE_ID")
	private Course course;

	@Override
	public String toString() {
		return "CourseImage [courseImageId=" + courseImageId + ", imageURL=" + imageURL + ", createdDate=" + createdDate
				+ ", activeStatus=" + activeStatus + ", course=" + course + "]";
	}

	public String getCourseImageId() {
		return courseImageId;
	}

	public void setCourseImageId(String courseImageId) {
		this.courseImageId = courseImageId;
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

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public CourseImage(String courseImageId, String imageURL, LocalDateTime createdDate, String activeStatus, Course course) {
		super();
		this.courseImageId = courseImageId;
		this.imageURL = imageURL;
		this.createdDate = createdDate;
		this.activeStatus = activeStatus;
		this.course = course;
	}

	public CourseImage() {
		super();
		// TODO Auto-generated constructor stub
	}
	

	
}

package com.juzzIt.EducationProject.Entity;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;


@Entity
public class CourseTypeBagroundImage {
	
	

	@Id
	@Column(name="COURSE_IMAGE_ID")
	private String courseTypeBagroundImageId;
	@Column(name="IMAGE_URL")
	private String imageURL;
	@Column(name="CREATED_DATE")
	private LocalDateTime createdDate;
	

	@Column(name="ACTIVE_STATUS")
	private String activeStatus;
	
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name="COURSE_TYPE_ID")
	private CourseType courseType;

	
	@Override
	public String toString() {
		return "CourseTypeBagroundImage [courseTypeBagroundImageId=" + courseTypeBagroundImageId + ", imageURL="
				+ imageURL + ", createdDate=" + createdDate + ", activeStatus=" + activeStatus + ", courseType="
				+ courseType + "]";
	}

	public String getCourseTypeBagroundImageId() {
		return courseTypeBagroundImageId;
	}

	public void setCourseTypeBagroundImageId(String courseTypeBagroundImageId) {
		this.courseTypeBagroundImageId = courseTypeBagroundImageId;
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

	public CourseType getCourseType() {
		return courseType;
	}

	public void setCourseType(CourseType courseType) {
		this.courseType = courseType;
	}

	public CourseTypeBagroundImage(String courseTypeBagroundImageId, String imageURL, LocalDateTime createdDate,
			String activeStatus, CourseType courseType) {
		super();
		this.courseTypeBagroundImageId = courseTypeBagroundImageId;
		this.imageURL = imageURL;
		this.createdDate = createdDate;
		this.activeStatus = activeStatus;
		this.courseType = courseType;
	}

	public CourseTypeBagroundImage() {
		super();
		// TODO Auto-generated constructor stub
	}

}

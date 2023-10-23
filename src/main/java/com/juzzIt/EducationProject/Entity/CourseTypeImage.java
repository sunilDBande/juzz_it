package com.juzzIt.EducationProject.Entity;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class CourseTypeImage {
	

	@Id
	@Column(name="COURSE_IMAGE_ID")
	private String courseTypeImageId;
	@Column(name="IMAGE_URL")
	private String imageURL;
	@Column(name="CREATED_DATE")
	private LocalDateTime createdDate;
	

	@Column(name="ACTIVE_STATUS")
	private String activeStatus;
	
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name="COURSE_Type_ID")
	private CourseType courseType;
	
	
	@Override
	public String toString() {
		return "CourseTypeImage [courseTypeImageId=" + courseTypeImageId + ", imageURL=" + imageURL + ", createdDate="
				+ createdDate + ", activeStatus=" + activeStatus + ", courseType=" + courseType + "]";
	}

	public String getCourseTypeImageId() {
		return courseTypeImageId;
	}

	public void setCourseTypeImageId(String courseTypeImageId) {
		this.courseTypeImageId = courseTypeImageId;
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

	public CourseTypeImage(String courseTypeImageId, String imageURL, LocalDateTime createdDate, String activeStatus,
			CourseType courseType) {
		super();
		this.courseTypeImageId = courseTypeImageId;
		this.imageURL = imageURL;
		this.createdDate = createdDate;
		this.activeStatus = activeStatus;
		this.courseType = courseType;
	}

	public CourseTypeImage() {
		super();
		// TODO Auto-generated constructor stub
	}

	

}

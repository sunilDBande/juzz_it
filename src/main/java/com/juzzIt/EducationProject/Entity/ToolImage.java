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
public class ToolImage {
	
	@Id
	@Column(name="TOOL_IMAGE_ID")
	private String toolImageId;
	@Column(name="IMAGE_URL")
	private String imageURL;
	@Column(name="CREATED_DATE")
	private LocalDateTime createdDate;
	

	@Column(name="ACTIVE_STATUS")
	private String activeStatus;
	
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name="COURSE_TYPE_TOOL_ID")
	private CourseTypeTools CourseTypeTools;

	public ToolImage() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ToolImage(String toolImageId, String imageURL, LocalDateTime createdDate, String activeStatus,
			com.juzzIt.EducationProject.Entity.CourseTypeTools courseTypeTools) {
		super();
		this.toolImageId = toolImageId;
		this.imageURL = imageURL;
		this.createdDate = createdDate;
		this.activeStatus = activeStatus;
		CourseTypeTools = courseTypeTools;
	}

	public String getToolImageId() {
		return toolImageId;
	}

	public void setToolImageId(String toolImageId) {
		this.toolImageId = toolImageId;
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

	public CourseTypeTools getCourseTypeTools() {
		return CourseTypeTools;
	}

	public void setCourseTypeTools(CourseTypeTools courseTypeTools) {
		CourseTypeTools = courseTypeTools;
	}

	@Override
	public String toString() {
		return "ToolImage [toolImageId=" + toolImageId + ", imageURL=" + imageURL + ", createdDate=" + createdDate
				+ ", activeStatus=" + activeStatus + ", CourseTypeTools=" + CourseTypeTools + "]";
	}

	

}

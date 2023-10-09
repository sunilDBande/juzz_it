package com.juzzIt.EducationProject.Entity;

import java.io.Serializable;

// import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;


@Entity
public class CourseTypeProjects implements Serializable{

	@Id
	@Column(name="PROJECT_ID")
	private String projectId;
	@Column(name="PROJECT_NAME")
	private String projectName;
	@Column(name="PROJECT_DESCRIPTION")
	private String projectDesc;
	
	
	@ManyToOne
	// @JsonIgnore
	@JoinColumn(name="COURSE_TYPE_ID")
	private CourseType courseType;


	public CourseTypeProjects() {
		super();
		// TODO Auto-generated constructor stub
	}


	public CourseTypeProjects(String projectId, String projectName, String projectDesc, CourseType courseType) {
		super();
		this.projectId = projectId;
		this.projectName = projectName;
		this.projectDesc = projectDesc;
		this.courseType = courseType;
	}


	public String getProjectId() {
		return projectId;
	}


	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}


	public String getProjectName() {
		return projectName;
	}


	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}


	public String getProjectDesc() {
		return projectDesc;
	}


	public void setProjectDesc(String projectDesc) {
		this.projectDesc = projectDesc;
	}


	public CourseType getCourseType() {
		return courseType;
	}


	public void setCourseType(CourseType courseType) {
		this.courseType = courseType;
	}


	@Override
	public String toString() {
		return "CourseTypeProjects [projectId=" + projectId + ", projectName=" + projectName + ", projectDesc="
				+ projectDesc + ", courseType=" + courseType + "]";
	}


}

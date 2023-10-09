package com.juzzIt.EducationProject.Entity;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;



@Entity
public class CourseTypeTools implements Serializable{

	
	@Id
	@Column(name="TOOL_ID")
	private String toolId;
    @Column(name="TOOL_NAME")
	private String toolName;
 
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnore
	@JoinColumn(name="COURSE_TYPE_ID")
	private CourseType courseType;

	public CourseTypeTools() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CourseTypeTools(String toolId, String toolName, CourseType courseType) {
		super();
		this.toolId = toolId;
		this.toolName = toolName;
		this.courseType = courseType;
	}

	public String getToolId() {
		return toolId;
	}

	public void setToolId(String toolId) {
		this.toolId = toolId;
	}

	public String getToolName() {
		return toolName;
	}

	public void setToolName(String toolName) {
		this.toolName = toolName;
	}

	public CourseType getCourseType() {
		return courseType;
	}

	public void setCourseType(CourseType courseType) {
		this.courseType = courseType;
	}

	@Override
	public String toString() {
		return "CourseTypeTools [toolId=" + toolId + ", toolName=" + toolName + ", courseType=" + courseType + "]";
	}


}

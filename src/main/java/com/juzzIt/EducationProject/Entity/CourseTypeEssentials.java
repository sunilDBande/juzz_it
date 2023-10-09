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
public class CourseTypeEssentials implements Serializable{

	
	@Id
	@Column(name="ESSENTIAL_ID")
	private String essentialId;
	@Column(name="ESSENTIAL_TYPE")
	private String essentialType;
	@Column(name="ESSENTIAL_DESCRIPTION",length = 4000)
	private String essentialDesc;
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnore
	@JoinColumn(name="COURSE_TYPE_ID")
	private CourseType courseType;


	public CourseTypeEssentials() {
		super();
		// TODO Auto-generated constructor stub
	}


	public CourseTypeEssentials(String essentialId, String essentialType, String essentialDesc, CourseType courseType) {
		super();
		this.essentialId = essentialId;
		this.essentialType = essentialType;
		this.essentialDesc = essentialDesc;
		this.courseType = courseType;
	}


	

	public String getEssentialId() {
		return essentialId;
	}


	public void setEssentialId(String essentialId) {
		this.essentialId = essentialId;
	}


	public String getEssentialType() {
		return essentialType;
	}


	public void setEssentialType(String essentialType) {
		this.essentialType = essentialType;
	}


	public String getEssentialDesc() {
		return essentialDesc;
	}


	public void setEssentialDesc(String essentialDesc) {
		this.essentialDesc = essentialDesc;
	}


	public CourseType getCourseType() {
		return courseType;
	}


	public void setCourseType(CourseType courseType) {
		this.courseType = courseType;
	}
	
	
	@Override
	public String toString() {
		return "CourseTypeEssentials [essentialId=" + essentialId + ", essentialType=" + essentialType
				+ ", essentialDesc=" + essentialDesc + ", courseType=" + courseType + "]";
	}

}

package com.juzzIt.EducationProject.Entity;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Course implements Serializable{

	@Id
	@Column(name="COURSE_ID")
	private String courseId;
	@Column(name="COURSE_NAME")
	private String courseName;
	@Column(name = "COURSE_ACTIVE")
	private String courseActive;
	@Column(name="FUTURE_COURSE_STATUS")
	private String futureCourseStatus;
	@Column(name="temp_delete")
	private  String tempDelete;
	@OneToMany(mappedBy = "course" ,cascade = CascadeType.REMOVE)
	private List<CourseType> courseType;
	
	@OneToMany(mappedBy = "course" ,cascade = CascadeType.REMOVE)
	private List<CourseImage> courseImage;

	public Course() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Course(String courseId, String courseName, String courseActive, String futureCourseStatus, String tempDelete,
			List<CourseType> courseType) {
		super();
		this.courseId = courseId;
		this.courseName = courseName;
		this.courseActive = courseActive;
		this.futureCourseStatus = futureCourseStatus;
		this.tempDelete = tempDelete;
		this.courseType = courseType;
	}

	public String getCourseId() {
		return courseId;
	}

	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getCourseActive() {
		return courseActive;
	}

	public void setCourseActive(String courseActive) {
		this.courseActive = courseActive;
	}

	public String getFutureCourseStatus() {
		return futureCourseStatus;
	}

	public void setFutureCourseStatus(String futureCourseStatus) {
		this.futureCourseStatus = futureCourseStatus;
	}

	public String getTempDelete() {
		return tempDelete;
	}

	public void setTempDelete(String tempDelete) {
		this.tempDelete = tempDelete;
	}

	public List<CourseType> getCourseType() {
		return courseType;
	}

	public void setCourseType(List<CourseType> courseType) {
		this.courseType = courseType;
	}

	@Override
	public String toString() {
		return "Course [courseId=" + courseId + ", courseName=" + courseName + ", courseActive=" + courseActive
				+ ", futureCourseStatus=" + futureCourseStatus + ", tempDelete=" + tempDelete + ", courseType="
				+ courseType + "]";
	}

	

	
	
}

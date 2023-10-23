package com.juzzIt.EducationProject.Entity;



import java.io.Serializable;
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
import jakarta.persistence.OneToOne;

@Entity
public class CourseType implements Serializable{

	@Id
	@Column(name="COURSE_TYPE_ID")
	private String courseTypeId;
	@Column(name="COURSE_TYPE_NAME")
	private String courseTypeName;
	@Column(name= "COURSE_LEVEL")
	private String courseLevel;
	@Column(name="ACTIVE")
	private String active_courseType;
	
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name="COURCE_ID")
	private Course course;
	
	@OneToOne(mappedBy = "courseType",fetch = FetchType.LAZY,cascade = CascadeType.REMOVE)
	private CourseTypeDetails courseTypeDetail;
	
	@OneToMany(mappedBy = "courseType",cascade = CascadeType.REMOVE ,fetch = FetchType.LAZY)
	private List< CourseTypeObjective> courseTypeObjective;
	
	@OneToMany(mappedBy = "courseType",cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
	private List<CourseTypeProjects> courseTypeProjects;
	
	@OneToMany(mappedBy = "courseType",cascade = CascadeType.REMOVE,fetch = FetchType.LAZY)
	private List<CourseTypeTools> courseTypeTools;
	
	@OneToMany(mappedBy = "courseType",cascade = CascadeType.REMOVE,fetch = FetchType.LAZY)
	private List<CourseTypeKeyHighlights> courseTypeKeyHighlights;
	
	@OneToMany(mappedBy = "courseType",cascade = CascadeType.REMOVE,fetch = FetchType.LAZY)
	private List<CourseTypeEssentials> courseTypeEssentials;
	
	@OneToMany(mappedBy = "courseType",cascade = CascadeType.REMOVE,fetch = FetchType.LAZY)
	private List <Module> module;
	@OneToMany(mappedBy = "courseType",cascade = CascadeType.REMOVE,fetch = FetchType.LAZY)
	private List <StudentCourseEnrollRequest> studentCourseEnrollRequest;
	
	@OneToMany(mappedBy = "courseType",fetch = FetchType.LAZY,cascade = CascadeType.REMOVE)
	private List<BatchCourse> batchCourse;
	
	@OneToMany(mappedBy = "courseType",fetch = FetchType.LAZY,cascade = CascadeType.REMOVE)
	private List<CourseTypeImage> courseTypeImage;
	
	@OneToMany(mappedBy = "courseType",fetch = FetchType.LAZY,cascade = CascadeType.REMOVE)
	private List<CourseTypeBagroundImage> courseTypeBagroundImage;
	
	@OneToMany(mappedBy = "courseType",fetch = FetchType.LAZY,cascade = CascadeType.REMOVE)
	private List<CourseTypeVideo> courseTypeVideo;


	public CourseType() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CourseType(String courseTypeId, String courseTypeName, String courseLevel, String active_courseType,
			Course course, CourseTypeDetails courseTypeDetail, List<CourseTypeObjective> courseTypeObjective,
			List<CourseTypeProjects> courseTypeProjects, List<CourseTypeTools> courseTypeTools,
			List<CourseTypeKeyHighlights> courseTypeKeyHighlights, List<CourseTypeEssentials> courseTypeEssentials,
			List<Module> module, List<BatchCourse> batchCourse) {
		super();
		this.courseTypeId = courseTypeId;
		this.courseTypeName = courseTypeName;
		this.courseLevel = courseLevel;
		this.active_courseType = active_courseType;
		this.course = course;
		this.courseTypeDetail = courseTypeDetail;
		this.courseTypeObjective = courseTypeObjective;
		this.courseTypeProjects = courseTypeProjects;
		this.courseTypeTools = courseTypeTools;
		this.courseTypeKeyHighlights = courseTypeKeyHighlights;
		this.courseTypeEssentials = courseTypeEssentials;
		this.module = module;
		this.batchCourse = batchCourse;
	}

	public String getCourseTypeId() {
		return courseTypeId;
	}

	public void setCourseTypeId(String courseTypeId) {
		this.courseTypeId = courseTypeId;
	}

	public String getCourseTypeName() {
		return courseTypeName;
	}

	public void setCourseTypeName(String courseTypeName) {
		this.courseTypeName = courseTypeName;
	}

	public String getCourseLevel() {
		return courseLevel;
	}

	public void setCourseLevel(String courseLevel) {
		this.courseLevel = courseLevel;
	}

	public String getActive_courseType() {
		return active_courseType;
	}

	public void setActive_courseType(String active_courseType) {
		this.active_courseType = active_courseType;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public CourseTypeDetails getCourseTypeDetail() {
		return courseTypeDetail;
	}

	public void setCourseTypeDetail(CourseTypeDetails courseTypeDetail) {
		this.courseTypeDetail = courseTypeDetail;
	}

	public List<CourseTypeObjective> getCourseTypeObjective() {
		return courseTypeObjective;
	}

	public void setCourseTypeObjective(List<CourseTypeObjective> courseTypeObjective) {
		this.courseTypeObjective = courseTypeObjective;
	}

	public List<CourseTypeProjects> getCourseTypeProjects() {
		return courseTypeProjects;
	}

	public void setCourseTypeProjects(List<CourseTypeProjects> courseTypeProjects) {
		this.courseTypeProjects = courseTypeProjects;
	}

	public List<CourseTypeTools> getCourseTypeTools() {
		return courseTypeTools;
	}

	public void setCourseTypeTools(List<CourseTypeTools> courseTypeTools) {
		this.courseTypeTools = courseTypeTools;
	}

	public List<CourseTypeKeyHighlights> getCourseTypeKeyHighlights() {
		return courseTypeKeyHighlights;
	}

	public void setCourseTypeKeyHighlights(List<CourseTypeKeyHighlights> courseTypeKeyHighlights) {
		this.courseTypeKeyHighlights = courseTypeKeyHighlights;
	}

	public List<CourseTypeEssentials> getCourseTypeEssentials() {
		return courseTypeEssentials;
	}

	public void setCourseTypeEssentials(List<CourseTypeEssentials> courseTypeEssentials) {
		this.courseTypeEssentials = courseTypeEssentials;
	}

	public List<Module> getModule() {
		return module;
	}

	public void setModule(List<Module> module) {
		this.module = module;
	}

	public List<BatchCourse> getBatchCourse() {
		return batchCourse;
	}

	public void setBatchCourse(List<BatchCourse> batchCourse) {
		this.batchCourse = batchCourse;
	}

	@Override
	public String toString() {
		return "CourseType [courseTypeId=" + courseTypeId + ", courseTypeName=" + courseTypeName + ", courseLevel="
				+ courseLevel + ", active_courseType=" + active_courseType + ", course=" + course
				+ ", courseTypeDetail=" + courseTypeDetail + ", courseTypeObjective=" + courseTypeObjective
				+ ", courseTypeProjects=" + courseTypeProjects + ", courseTypeTools=" + courseTypeTools
				+ ", courseTypeKeyHighlights=" + courseTypeKeyHighlights + ", courseTypeEssentials="
				+ courseTypeEssentials + ", module=" + module + ", batchCourse=" + batchCourse + "]";
	}

	

	
	
	
}

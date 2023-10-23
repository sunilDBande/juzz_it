package com.juzzIt.EducationProject.Entity;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class BatchCourse implements Serializable{

	@Id
	@Column(name = "BATCH_COURSE_ID")
	private String batchCourseId;
	@Column(name = "BATCH_COURCE_NAME")
	private String batchCourseName;
	@Column(name = "COURSE_NAME")
	private String courseName;
	@Column(name = "ADMITION_STATUS")
	private String admitionStatus;
	@Column(name = "BATCH_COMPLETION_STATUS")
	private String batchCompletionStatus;
	@Column(name = "ACTIVE_BATCH_COURSE")
	private String active_batchCourse;
	@Column(name = "TEMP_DELETE")
	private String temp_delete;

	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "SUBBATCH_ID")
	private SubBatch subBatch;

	@ManyToOne
	@JoinColumn(name = "COURSE_TYPE_ID")
	private CourseType courseType;

	@OneToMany(mappedBy = "batchCourse", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, orphanRemoval = true)
	private List<BatchCourseStudent> batchCourseStudent;

	@OneToMany(mappedBy = "batchCourse", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, orphanRemoval = true)
	private List<BatchCourseSubject> batchCourseSubject;

//	@OneToMany(mappedBy = "batchCourse", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, orphanRemoval = true)
//	private List<StudentEnrollDetails> studentEnrollDetails;

	@OneToMany(mappedBy = "batchCourse", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, orphanRemoval = true)
	private List<BatchCoursePlacements> batchCoursePlacements;
	
	@OneToMany(mappedBy = "batchCourse", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, orphanRemoval = true)
	private List<BatchCourseClassLinks> batchCourseClassLinks;
	


	public BatchCourse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BatchCourse(String batchCourseId, String batchCourseName, String courseName, String admitionStatus,
			String batchCompletionStatus, String active_batchCourse, String temp_delete, SubBatch subBatch,
			CourseType courseType, List<BatchCourseStudent> batchCourseStudent,
			List<BatchCourseSubject> batchCourseSubject, List<BatchCoursePlacements> batchCoursePlacements,
			List<BatchCourseClassLinks> batchCourseClassLinks) {
		super();
		this.batchCourseId = batchCourseId;
		this.batchCourseName = batchCourseName;
		this.courseName = courseName;
		this.admitionStatus = admitionStatus;
		this.batchCompletionStatus = batchCompletionStatus;
		this.active_batchCourse = active_batchCourse;
		this.temp_delete = temp_delete;
		this.subBatch = subBatch;
		this.courseType = courseType;
		this.batchCourseStudent = batchCourseStudent;
		this.batchCourseSubject = batchCourseSubject;
		this.batchCoursePlacements = batchCoursePlacements;
		this.batchCourseClassLinks = batchCourseClassLinks;
	}

	public String getBatchCourseId() {
		return batchCourseId;
	}

	public void setBatchCourseId(String batchCourseId) {
		this.batchCourseId = batchCourseId;
	}

	public String getBatchCourseName() {
		return batchCourseName;
	}

	public void setBatchCourseName(String batchCourseName) {
		this.batchCourseName = batchCourseName;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getAdmitionStatus() {
		return admitionStatus;
	}

	public void setAdmitionStatus(String admitionStatus) {
		this.admitionStatus = admitionStatus;
	}

	public String getBatchCompletionStatus() {
		return batchCompletionStatus;
	}

	public void setBatchCompletionStatus(String batchCompletionStatus) {
		this.batchCompletionStatus = batchCompletionStatus;
	}

	public String getActive_batchCourse() {
		return active_batchCourse;
	}

	public void setActive_batchCourse(String active_batchCourse) {
		this.active_batchCourse = active_batchCourse;
	}

	public String getTemp_delete() {
		return temp_delete;
	}

	public void setTemp_delete(String temp_delete) {
		this.temp_delete = temp_delete;
	}

	public SubBatch getSubBatch() {
		return subBatch;
	}

	public void setSubBatch(SubBatch subBatch) {
		this.subBatch = subBatch;
	}

	public CourseType getCourseType() {
		return courseType;
	}

	public void setCourseType(CourseType courseType) {
		this.courseType = courseType;
	}

	public List<BatchCourseStudent> getBatchCourseStudent() {
		return batchCourseStudent;
	}

	public void setBatchCourseStudent(List<BatchCourseStudent> batchCourseStudent) {
		this.batchCourseStudent = batchCourseStudent;
	}

	public List<BatchCourseSubject> getBatchCourseSubject() {
		return batchCourseSubject;
	}

	public void setBatchCourseSubject(List<BatchCourseSubject> batchCourseSubject) {
		this.batchCourseSubject = batchCourseSubject;
	}

	public List<BatchCoursePlacements> getBatchCoursePlacements() {
		return batchCoursePlacements;
	}

	public void setBatchCoursePlacements(List<BatchCoursePlacements> batchCoursePlacements) {
		this.batchCoursePlacements = batchCoursePlacements;
	}

	public List<BatchCourseClassLinks> getBatchCourseClassLinks() {
		return batchCourseClassLinks;
	}

	public void setBatchCourseClassLinks(List<BatchCourseClassLinks> batchCourseClassLinks) {
		this.batchCourseClassLinks = batchCourseClassLinks;
	}

	@Override
	public String toString() {
		return "BatchCourse [batchCourseId=" + batchCourseId + ", batchCourseName=" + batchCourseName + ", courseName="
				+ courseName + ", admitionStatus=" + admitionStatus + ", batchCompletionStatus=" + batchCompletionStatus
				+ ", active_batchCourse=" + active_batchCourse + ", temp_delete=" + temp_delete + ", subBatch="
				+ subBatch + ", courseType=" + courseType + ", batchCourseStudent=" + batchCourseStudent
				+ ", batchCourseSubject=" + batchCourseSubject + ", batchCoursePlacements=" + batchCoursePlacements
				+ ", batchCourseClassLinks=" + batchCourseClassLinks + "]";
	}

	
}

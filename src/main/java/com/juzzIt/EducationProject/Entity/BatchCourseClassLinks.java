package com.juzzIt.EducationProject.Entity;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;


@Entity
public class BatchCourseClassLinks {

	
	@Id
	@Column(name="CLASS_ID")
	private String classId;
	@Column(name="CLASS_TITLE")
	private String classTitle;
	@Column(name="SUBJECT_NAME")
	private String subjectName;
	@Column(name="TEACHER_NAME")
	private String TeacherName;
	@Column(name="CLASS_DATE")
	private LocalDateTime classDate;
	@Column(name="CLASS_TIME")
	private String classTime;
	@Column(name="CLASS_LINK")
	private String classLink;
	
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name="BATCH_COURSE_SUBJECT_ID")
	private BatchCourseSubject  batchCourseSubject;
	
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name="BATCH_COURSE_ID")
	private BatchCourse batchCourse;

	public BatchCourseClassLinks() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BatchCourseClassLinks(String classId, String classTitle, String subjectName, String teacherName,
			LocalDateTime classDate, String classTime, String classLink, BatchCourseSubject batchCourseSubject,
			BatchCourse batchCourse) {
		super();
		this.classId = classId;
		this.classTitle = classTitle;
		this.subjectName = subjectName;
		TeacherName = teacherName;
		this.classDate = classDate;
		this.classTime = classTime;
		this.classLink = classLink;
		this.batchCourseSubject = batchCourseSubject;
		this.batchCourse = batchCourse;
	}

	public String getClassId() {
		return classId;
	}

	public void setClassId(String classId) {
		this.classId = classId;
	}

	public String getClassTitle() {
		return classTitle;
	}

	public void setClassTitle(String classTitle) {
		this.classTitle = classTitle;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public String getTeacherName() {
		return TeacherName;
	}

	public void setTeacherName(String teacherName) {
		TeacherName = teacherName;
	}

	public LocalDateTime getClassDate() {
		return classDate;
	}

	public void setClassDate(LocalDateTime classDate) {
		this.classDate = classDate;
	}

	public String getClassTime() {
		return classTime;
	}

	public void setClassTime(String classTime) {
		this.classTime = classTime;
	}

	public String getClassLink() {
		return classLink;
	}

	public void setClassLink(String classLink) {
		this.classLink = classLink;
	}

	public BatchCourseSubject getBatchCourseSubject() {
		return batchCourseSubject;
	}

	public void setBatchCourseSubject(BatchCourseSubject batchCourseSubject) {
		this.batchCourseSubject = batchCourseSubject;
	}

	public BatchCourse getBatchCourse() {
		return batchCourse;
	}

	public void setBatchCourse(BatchCourse batchCourse) {
		this.batchCourse = batchCourse;
	}

	@Override
	public String toString() {
		return "BatchCourseClassLinks [classId=" + classId + ", classTitle=" + classTitle + ", subjectName="
				+ subjectName + ", TeacherName=" + TeacherName + ", classDate=" + classDate + ", classTime=" + classTime
				+ ", classLink=" + classLink + ", batchCourseSubject=" + batchCourseSubject + ", batchCourse="
				+ batchCourse + "]";
	}

	
	
	
}

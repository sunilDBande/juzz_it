package com.juzzIt.EducationProject.Entity;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;


@Entity
public class RecodedStudentClassLinks {

	@Id
	@Column(name="CLASS_ID")
	private String classId;
	@Column(name="CLASS_TITLE")
	private String classTitle;
	@Column(name="SUBJECT_NAME")
	private String subjectName;
	@Column(name="CLASS_DATE")
	private LocalDateTime classDate;
	@Column(name="CLASS_TIME")
	private String classTime;
	@Column(name="CLASS_LINK")
	private String classLink;
	
	
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name="RECODED_STUDENT_BATCH_ID")
	private RecordedStudentBatch recordedStudentBatch;


	public RecodedStudentClassLinks() {
		super();
		// TODO Auto-generated constructor stub
	}


	public RecodedStudentClassLinks(String classId, String classTitle, String subjectName, LocalDateTime classDate,
			String classTime, String classLink, RecordedStudentBatch recordedStudentBatch) {
		super();
		this.classId = classId;
		this.classTitle = classTitle;
		this.subjectName = subjectName;
		this.classDate = classDate;
		this.classTime = classTime;
		this.classLink = classLink;
		this.recordedStudentBatch = recordedStudentBatch;
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


	public RecordedStudentBatch getRecordedStudentBatch() {
		return recordedStudentBatch;
	}


	public void setRecordedStudentBatch(RecordedStudentBatch recordedStudentBatch) {
		this.recordedStudentBatch = recordedStudentBatch;
	}


	@Override
	public String toString() {
		return "RecodedStudentClassLinks [classId=" + classId + ", classTitle=" + classTitle + ", subjectName="
				+ subjectName + ", classDate=" + classDate + ", classTime=" + classTime + ", classLink=" + classLink
				+ ", recordedStudentBatch=" + recordedStudentBatch + "]";
	}


	
	
}

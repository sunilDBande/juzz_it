package com.juzzIt.EducationProject.Entity;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
@Entity
public class BatchCourseStudent implements Serializable{

	
	@Id
	@Column(name="BATCH_COURSE_STUDENT_ID")
     private String	 batchCourseStudentId;
	@Column(name="STUDENT_NAME")
	private String studentName;
	@Column(name="ENROLL_TYPE")
	private String enrollType;
	@Column(name="STUDENT_PERMITION_STATUS")
	private String studentPermitionStatus;
	@OneToOne(cascade = CascadeType.REMOVE,  orphanRemoval = true)
	@JoinColumn(name="STUDENT_ENROLL_DETAILS_ID")
	private StudentEnrollDetails studentEnrollDetails;
	
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name="STUDENT_ID")
	private Student student;
	

	@ManyToOne
	@JsonIgnore
	@JoinColumn(name="BATCH_COURSE_ID")
	private BatchCourse batchCourse;


	public BatchCourseStudent() {
		super();
		// TODO Auto-generated constructor stub
	}


	public BatchCourseStudent(String batchCourseStudentId, String studentName, String enrollType,
			String studentPermitionStatus, StudentEnrollDetails studentEnrollDetails, Student student,
			BatchCourse batchCourse) {
		super();
		this.batchCourseStudentId = batchCourseStudentId;
		this.studentName = studentName;
		this.enrollType = enrollType;
		this.studentPermitionStatus = studentPermitionStatus;
		this.studentEnrollDetails = studentEnrollDetails;
		this.student = student;
		this.batchCourse = batchCourse;
	}


	public String getBatchCourseStudentId() {
		return batchCourseStudentId;
	}


	public void setBatchCourseStudentId(String batchCourseStudentId) {
		this.batchCourseStudentId = batchCourseStudentId;
	}


	public String getStudentName() {
		return studentName;
	}


	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}


	public String getEnrollType() {
		return enrollType;
	}


	public void setEnrollType(String enrollType) {
		this.enrollType = enrollType;
	}


	public String getStudentPermitionStatus() {
		return studentPermitionStatus;
	}


	public void setStudentPermitionStatus(String studentPermitionStatus) {
		this.studentPermitionStatus = studentPermitionStatus;
	}


	public StudentEnrollDetails getStudentEnrollDetails() {
		return studentEnrollDetails;
	}


	public void setStudentEnrollDetails(StudentEnrollDetails studentEnrollDetails) {
		this.studentEnrollDetails = studentEnrollDetails;
	}


	public Student getStudent() {
		return student;
	}


	public void setStudent(Student student) {
		this.student = student;
	}


	public BatchCourse getBatchCourse() {
		return batchCourse;
	}


	public void setBatchCourse(BatchCourse batchCourse) {
		this.batchCourse = batchCourse;
	}


	@Override
	public String toString() {
		return "BatchCourseStudent [batchCourseStudentId=" + batchCourseStudentId + ", studentName=" + studentName
				+ ", enrollType=" + enrollType + ", studentPermitionStatus=" + studentPermitionStatus
				+ ", studentEnrollDetails=" + studentEnrollDetails + ", student=" + student + ", batchCourse="
				+ batchCourse + "]";
	}




} 

package com.juzzIt.EducationProject.Entity;


import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;


@Entity
public class Teacher implements Serializable{

	@Id
	@Column(name="TEACHER_ID")
	private String teacherId;
	@Column(name="TEACHER_NAME")
	private String teacherName;
	@Column(name="TEACHER_EMAIL")
	private String teacherEmail;
	@Column(name="TEACHER_PASSWORD")
	private String teacherPassword;
	@Column(name="TEACHER_MOBILE_NUMBER")
	private long teacherMobileNumber;
	@Column(name="TEACHER_ADDRESS")
    private String teacherAddress;
	@Column(name="CREATED_DATE")
	private LocalDateTime createdDate;
	@Column(name="UPDATED_DATE")
	private LocalDateTime updateDate;
	

	@Column (name="USER_ROLE")
    private String role;
	@OneToMany(mappedBy = "teacher",fetch = FetchType.LAZY)
	private List<BatchCourseSubject> batchCourseSubject;
	



	public Teacher() {
		super();
		// TODO Auto-generated constructor stub
	}




	public Teacher(String teacherId, String teacherName, String teacherEmail, String teacherPassword,
			long teacherMobileNumber, String teacherAddress, LocalDateTime createdDate, LocalDateTime updateDate,
			String role, List<BatchCourseSubject> batchCourseSubject) {
		super();
		this.teacherId = teacherId;
		this.teacherName = teacherName;
		this.teacherEmail = teacherEmail;
		this.teacherPassword = teacherPassword;
		this.teacherMobileNumber = teacherMobileNumber;
		this.teacherAddress = teacherAddress;
		this.createdDate = createdDate;
		this.updateDate = updateDate;
		this.role = role;
		this.batchCourseSubject = batchCourseSubject;
	}




	public String getTeacherId() {
		return teacherId;
	}




	public void setTeacherId(String teacherId) {
		this.teacherId = teacherId;
	}




	public String getTeacherName() {
		return teacherName;
	}




	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}




	public String getTeacherEmail() {
		return teacherEmail;
	}




	public void setTeacherEmail(String teacherEmail) {
		this.teacherEmail = teacherEmail;
	}




	public String getTeacherPassword() {
		return teacherPassword;
	}




	public void setTeacherPassword(String teacherPassword) {
		this.teacherPassword = teacherPassword;
	}




	public long getTeacherMobileNumber() {
		return teacherMobileNumber;
	}




	public void setTeacherMobileNumber(long teacherMobileNumber) {
		this.teacherMobileNumber = teacherMobileNumber;
	}




	public String getTeacherAddress() {
		return teacherAddress;
	}




	public void setTeacherAddress(String teacherAddress) {
		this.teacherAddress = teacherAddress;
	}




	public LocalDateTime getCreatedDate() {
		return createdDate;
	}




	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}




	public LocalDateTime getUpdateDate() {
		return updateDate;
	}




	public void setUpdateDate(LocalDateTime updateDate) {
		this.updateDate = updateDate;
	}




	public String getRole() {
		return role;
	}




	public void setRole(String role) {
		this.role = role;
	}




	public List<BatchCourseSubject> getBatchCourseSubject() {
		return batchCourseSubject;
	}




	public void setBatchCourseSubject(List<BatchCourseSubject> batchCourseSubject) {
		this.batchCourseSubject = batchCourseSubject;
	}




	@Override
	public String toString() {
		return "Teacher [teacherId=" + teacherId + ", teacherName=" + teacherName + ", teacherEmail=" + teacherEmail
				+ ", teacherPassword=" + teacherPassword + ", teacherMobileNumber=" + teacherMobileNumber
				+ ", teacherAddress=" + teacherAddress + ", createdDate=" + createdDate + ", updateDate=" + updateDate
				+ ", role=" + role + ", batchCourseSubject=" + batchCourseSubject + "]";
	}



	
}

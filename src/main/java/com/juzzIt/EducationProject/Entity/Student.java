package com.juzzIt.EducationProject.Entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Student implements Serializable{

	@Id
	@Column(name="STUDENT_ID")
	private String studentId;
	@Column(name="STUDENT_NAME")
	private String studentName;
	@Column(name="STUDENT_EMAIL")
	private String studentEmail;
	@Column(name="STUDENT_PASSWORD")
	private String studentPassword;
	@Column(name="STUDENT_MOBILE_NUMBER")
	private long studentMobileNumber;
	@Column(name="STUDENT_ADDRESS")
    private String studentAddress;
	@Column(name="CREATED_DATE")
	 @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	 private LocalDateTime  createdDate;
	@Column(name="UPDATED_DATE")
	 @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updatedDate;
	
	@Column(name = "Student_Enroll")
	private String student_Enroll;
	
	@OneToMany(mappedBy = "student")
	private List<BatchCourseStudent> batchCourseStudent;
	
	
	
	public Student() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	

	public Student(String studentId, String studentName, String studentEmail, String studentPassword,
			long studentMobileNumber, String studentAddress, LocalDateTime createdDate, LocalDateTime updatedDate,
			String student_Enroll, List<BatchCourseStudent> batchCourseStudent) {
		super();
		this.studentId = studentId;
		this.studentName = studentName;
		this.studentEmail = studentEmail;
		this.studentPassword = studentPassword;
		this.studentMobileNumber = studentMobileNumber;
		this.studentAddress = studentAddress;
		this.createdDate = createdDate;
		this.updatedDate = updatedDate;
		this.student_Enroll = student_Enroll;
		this.batchCourseStudent = batchCourseStudent;
	}




	public String getStudent_Enroll() {
		return student_Enroll;
	}




	public void setStudent_Enroll(String student_Enroll) {
		this.student_Enroll = student_Enroll;
	}




	public List<BatchCourseStudent> getBatchCourseStudent() {
		return batchCourseStudent;
	}




	public void setBatchCourseStudent(List<BatchCourseStudent> batchCourseStudent) {
		this.batchCourseStudent = batchCourseStudent;
	}




	public String getStudentId() {
		return studentId;
	}
	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	public String getStudentEmail() {
		return studentEmail;
	}
	public void setStudentEmail(String studentEmail) {
		this.studentEmail = studentEmail;
	}
	public String getStudentPassword() {
		return studentPassword;
	}
	public void setStudentPassword(String studentPassword) {
		this.studentPassword = studentPassword;
	}
	public long getStudentMobileNumber() {
		return studentMobileNumber;
	}
	public void setStudentMobileNumber(long studentMobileNumber) {
		this.studentMobileNumber = studentMobileNumber;
	}
	public String getStudentAddress() {
		return studentAddress;
	}
	public void setStudentAddress(String studentAddress) {
		this.studentAddress = studentAddress;
	}
	public LocalDateTime getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}
	public LocalDateTime getUpdatedDate() {
		return updatedDate;
	}
	public void setUpdatedDate(LocalDateTime updatedDate) {
		this.updatedDate = updatedDate;
	}




	@Override
	public String toString() {
		return "Student [studentId=" + studentId + ", studentName=" + studentName + ", studentEmail=" + studentEmail
				+ ", studentPassword=" + studentPassword + ", studentMobileNumber=" + studentMobileNumber
				+ ", studentAddress=" + studentAddress + ", createdDate=" + createdDate + ", updatedDate=" + updatedDate
				+ ", student_Enroll=" + student_Enroll + ", batchCourseStudent=" + batchCourseStudent + "]";
	}


	

	
}

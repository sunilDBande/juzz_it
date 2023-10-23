package com.juzzIt.EducationProject.Entity;


import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
@Entity
public class StudentEnrollDetails implements Serializable{

	@Id
	@Column(name="ENROLL_DETAILS_ID")
   private String enrollDetailsId;
	@Column(name="STUDENT_NAME")
   private String studentName;
	@Column(name="STUDENT_EMAIL")
   private String studentEmail;
	@Column(name="MOBILE_NUMBER")
   private long  mobileNubber;
	@Column(name="COUNTRY")
   private String country;
	@Column(name="STATE")
   private String state;
	@Column(name="DISTRICT")
   private String district;
@Column(name="PINCODE")
	private int pinCode;

	
	@OneToOne(mappedBy = "studentEnrollDetails")
	private BatchCourseStudent batchCourseStudent;
	
	@OneToOne(mappedBy = "studentEnrollDetails")
	private RecordedStudent recordedStudent;
	
	

	public StudentEnrollDetails() {
		super();
		// TODO Auto-generated constructor stub
	}



	public StudentEnrollDetails(String enrollDetailsId, String studentName, String studentEmail, long mobileNubber,
			String country, String state, String district, int pinCode, BatchCourseStudent batchCourseStudent) {
		super();
		this.enrollDetailsId = enrollDetailsId;
		this.studentName = studentName;
		this.studentEmail = studentEmail;
		this.mobileNubber = mobileNubber;
		this.country = country;
		this.state = state;
		this.district = district;
		this.pinCode = pinCode;
		this.batchCourseStudent = batchCourseStudent;
	}



	public String getEnrollDetailsId() {
		return enrollDetailsId;
	}



	public void setEnrollDetailsId(String enrollDetailsId) {
		this.enrollDetailsId = enrollDetailsId;
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



	public long getMobileNubber() {
		return mobileNubber;
	}



	public void setMobileNubber(long mobileNubber) {
		this.mobileNubber = mobileNubber;
	}



	public String getCountry() {
		return country;
	}



	public void setCountry(String country) {
		this.country = country;
	}



	public String getState() {
		return state;
	}



	public void setState(String state) {
		this.state = state;
	}



	public String getDistrict() {
		return district;
	}



	public void setDistrict(String district) {
		this.district = district;
	}



	public int getPinCode() {
		return pinCode;
	}



	public void setPinCode(int pinCode) {
		this.pinCode = pinCode;
	}



	public BatchCourseStudent getBatchCourseStudent() {
		return batchCourseStudent;
	}



	public void setBatchCourseStudent(BatchCourseStudent batchCourseStudent) {
		this.batchCourseStudent = batchCourseStudent;
	}



	@Override
	public String toString() {
		return "StudentEnrollDetails [enrollDetailsId=" + enrollDetailsId + ", studentName=" + studentName
				+ ", studentEmail=" + studentEmail + ", mobileNubber=" + mobileNubber + ", country=" + country
				+ ", state=" + state + ", district=" + district + ", pinCode=" + pinCode + ", batchCourseStudent="
				+ batchCourseStudent + "]";
	}

}

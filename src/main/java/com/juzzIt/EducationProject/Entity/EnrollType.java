package com.juzzIt.EducationProject.Entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class EnrollType implements Serializable{

	@Id
	@Column(name="ENROLL_TYPR_ID")
	private String enrollTypeId;
	@Column(name="ENROLL_TYPE")
	private String enrollType;
	
	
	
	public EnrollType() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	public EnrollType(String enrollTypeId, String enrollType) {
		super();
		this.enrollTypeId = enrollTypeId;
		this.enrollType = enrollType;
	}
	
	
	public String getEnrollTypeId() {
		return enrollTypeId;
	}
	public void setEnrollTypeId(String enrollTypeId) {
		this.enrollTypeId = enrollTypeId;
	}
	public String getEnrollType() {
		return enrollType;
	}
	public void setEnrollType(String enrollType) {
		this.enrollType = enrollType;
	}
	
	
	@Override
	public String toString() {
		return "EnrollType [enrollTypeId=" + enrollTypeId + ", enrollType=" + enrollType + "]";
	}
}

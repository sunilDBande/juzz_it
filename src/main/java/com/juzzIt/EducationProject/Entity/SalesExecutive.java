package com.juzzIt.EducationProject.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class SalesExecutive {
	
	@Id
	@Column(name="SALES_EXECUTIVE_ID")
	private String salesExecutiveId;
	@Column(name="SALES_EXECUTIVE_NAME")
	private String salesExecutiveName;
	@Column(name="SALES_EXECUTIVE_EMAIL")
	private String salesExecutiveEmail;
	@Column(name="SALES_EXECUTIVE_PASSWORD")
	private String salesExecutivePassWord;
	@Column(name="SALES_EXECUTIVE_MOBILE_NUMBER")
	private long salesExecutiveMobileNumber;
	@Column(name="SALES_EXECUTIVE_ADDRESS")
	private String salesExecutiveAddress;
	
	@Column (name="USER_ROLE")
    private String role;

	public SalesExecutive() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getSalesExecutiveId() {
		return salesExecutiveId;
	}

	public void setSalesExecutiveId(String salesExecutiveId) {
		this.salesExecutiveId = salesExecutiveId;
	}

	public String getSalesExecutiveName() {
		return salesExecutiveName;
	}

	public void setSalesExecutiveName(String salesExecutiveName) {
		this.salesExecutiveName = salesExecutiveName;
	}

	public String getSalesExecutiveEmail() {
		return salesExecutiveEmail;
	}

	public void setSalesExecutiveEmail(String salesExecutiveEmail) {
		this.salesExecutiveEmail = salesExecutiveEmail;
	}

	public String getSalesExecutivePassWord() {
		return salesExecutivePassWord;
	}

	public void setSalesExecutivePassWord(String salesExecutivePassWord) {
		this.salesExecutivePassWord = salesExecutivePassWord;
	}

	public long getSalesExecutiveMobileNumber() {
		return salesExecutiveMobileNumber;
	}

	public void setSalesExecutiveMobileNumber(long salesExecutiveMobileNumber) {
		this.salesExecutiveMobileNumber = salesExecutiveMobileNumber;
	}

	public String getSalesExecutiveAddress() {
		return salesExecutiveAddress;
	}

	public void setSalesExecutiveAddress(String salesExecutiveAddress) {
		this.salesExecutiveAddress = salesExecutiveAddress;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public SalesExecutive(String salesExecutiveId, String salesExecutiveName, String salesExecutiveEmail,
			String salesExecutivePassWord, long salesExecutiveMobileNumber, String salesExecutiveAddress, String role) {
		super();
		this.salesExecutiveId = salesExecutiveId;
		this.salesExecutiveName = salesExecutiveName;
		this.salesExecutiveEmail = salesExecutiveEmail;
		this.salesExecutivePassWord = salesExecutivePassWord;
		this.salesExecutiveMobileNumber = salesExecutiveMobileNumber;
		this.salesExecutiveAddress = salesExecutiveAddress;
		this.role = role;
	}

	@Override
	public String toString() {
		return "SalesExecutive [salesExecutiveId=" + salesExecutiveId + ", salesExecutiveName=" + salesExecutiveName
				+ ", salesExecutiveEmail=" + salesExecutiveEmail + ", salesExecutivePassWord=" + salesExecutivePassWord
				+ ", salesExecutiveMobileNumber=" + salesExecutiveMobileNumber + ", salesExecutiveAddress="
				+ salesExecutiveAddress + ", role=" + role + "]";
	}

	

}

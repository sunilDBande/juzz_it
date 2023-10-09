package com.juzzIt.EducationProject.Entity;

public class Admin {

	
	private String adminId;
	private String adminName;
	private String adminEmail;
	private String adminPassword;
	
	
	public Admin() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public Admin(String adminId, String adminName, String adminEmail,String adminPassword) {
		super();
		this.adminId = adminId;
		this.adminName = adminName;
		this.adminEmail = adminEmail;
		this.adminPassword = adminPassword;
	}
	
	
	public String getAdminId() {
		return adminId;
	}
	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}
	public String getAdminName() {
		return adminName;
	}
	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}
	public String getAdminPassword() {
		return adminPassword;
	}
	public void setAdminPassword(String adminPassword) {
		this.adminPassword = adminPassword;
	}
	
	
	@Override
	public String toString() {
		return "Admin [adminId=" + adminId + ", adminName=" + adminName + ", adminEmail=" + adminEmail+ ", adminPassword=" + adminPassword + "]";
	}


	public String getAdminEmail() {
		return adminEmail;
	}


	public void setAdminEmail(String adminEmail) {
		this.adminEmail = adminEmail;
	}
}

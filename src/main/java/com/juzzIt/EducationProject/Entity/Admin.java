package com.juzzIt.EducationProject.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Admin {

	@Id
	@Column(name="ADMIN_ID")
	private String adminId;
	@Column(name="ADMIN_NAME")
	private String adminName;
	@Column(name="ADMIN_EMAIL")
	private String adminEmail;
	@Column(name="ADMIN_PASSWORD")
	private String adminPassword;
	
	@Column (name="USER_ROLE")
     private String role;

	public Admin() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Admin(String adminId, String adminName, String adminEmail, String adminPassword, String role) {
		super();
		this.adminId = adminId;
		this.adminName = adminName;
		this.adminEmail = adminEmail;
		this.adminPassword = adminPassword;
		this.role = role;
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

	public String getAdminEmail() {
		return adminEmail;
	}

	public void setAdminEmail(String adminEmail) {
		this.adminEmail = adminEmail;
	}

	public String getAdminPassword() {
		return adminPassword;
	}

	public void setAdminPassword(String adminPassword) {
		this.adminPassword = adminPassword;
	}

	@Override
	public String toString() {
		return "Admin [adminId=" + adminId + ", adminName=" + adminName + ", adminEmail=" + adminEmail
				+ ", adminPassword=" + adminPassword + ", role=" + role + "]";
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}	
	
	
}

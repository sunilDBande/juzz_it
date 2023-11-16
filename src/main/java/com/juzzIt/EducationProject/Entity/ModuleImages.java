package com.juzzIt.EducationProject.Entity;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;


@Entity
public class ModuleImages {
	@Id
	@Column(name="MODULE_IMAGE_ID")
	private String moduleImageId;
	@Column(name="IMAGE_URL")
	private String imageURL;
	@Column(name="CREATED_DATE")
	private LocalDateTime createdDate;
	@Column(name="ACTIVE_STATUS")
	private String activeStatus;
	
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name="MODULE_ID")
	private Module module;

	public String getModuleImageId() {
		return moduleImageId;
	}

	public void setModuleImageId(String moduleImageId) {
		this.moduleImageId = moduleImageId;
	}

	public String getImageURL() {
		return imageURL;
	}

	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}

	public String getActiveStatus() {
		return activeStatus;
	}

	public void setActiveStatus(String activeStatus) {
		this.activeStatus = activeStatus;
	}

	public Module getModule() {
		return module;
	}

	public void setModule(Module module) {
		this.module = module;
	}

	public ModuleImages(String moduleImageId, String imageURL, LocalDateTime createdDate, String activeStatus,
			Module module) {
		super();
		this.moduleImageId = moduleImageId;
		this.imageURL = imageURL;
		this.createdDate = createdDate;
		this.activeStatus = activeStatus;
		this.module = module;
	}

	public ModuleImages() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "ModuleImages [moduleImageId=" + moduleImageId + ", imageURL=" + imageURL + ", createdDate="
				+ createdDate + ", activeStatus=" + activeStatus + ", module=" + module + "]";
	}

	

}

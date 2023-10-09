package com.juzzIt.EducationProject.Entity;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
@Entity
public class BatchCoursePlacements implements Serializable {
	
	@Id
	@Column(name="PLACEMENT_ID")
	private String placementId;
	@Column(name="COMPANY_NAME")
	private String companyName;
	@Column(name="CONPANY_INTRODUCTION")
	private String companyIntroduction;
	@Column(name="ACTIVE")
	private String  active_Placement;
	@Column(name="APPLY_LINK")
	private String applyLink;
	

	@ManyToOne
	@JsonIgnore
	@JoinColumn(name="BATCH_COURSE_ID")
	private BatchCourse batchCourse;


	public BatchCoursePlacements() {
		super();
		// TODO Auto-generated constructor stub
	}


	public BatchCoursePlacements(String placementId, String companyName, String companyIntroduction,
			String active_Placement, String applyLink, BatchCourse batchCourse) {
		super();
		this.placementId = placementId;
		this.companyName = companyName;
		this.companyIntroduction = companyIntroduction;
		this.active_Placement = active_Placement;
		this.applyLink = applyLink;
		this.batchCourse = batchCourse;
	}


	public String getPlacementId() {
		return placementId;
	}


	public void setPlacementId(String placementId) {
		this.placementId = placementId;
	}


	public String getCompanyName() {
		return companyName;
	}


	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}


	public String getCompanyIntroduction() {
		return companyIntroduction;
	}


	public void setCompanyIntroduction(String companyIntroduction) {
		this.companyIntroduction = companyIntroduction;
	}


	public String getActive_Placement() {
		return active_Placement;
	}


	public void setActive_Placement(String active_Placement) {
		this.active_Placement = active_Placement;
	}


	public String getApplyLink() {
		return applyLink;
	}


	public void setApplyLink(String applyLink) {
		this.applyLink = applyLink;
	}


	public BatchCourse getBatchCourse() {
		return batchCourse;
	}


	public void setBatchCourse(BatchCourse batchCourse) {
		this.batchCourse = batchCourse;
	}


	@Override
	public String toString() {
		return "BatchCoursePlacements [placementId=" + placementId + ", companyName=" + companyName
				+ ", companyIntroduction=" + companyIntroduction + ", active_Placement=" + active_Placement
				+ ", applyLink=" + applyLink + ", batchCourse=" + batchCourse + "]";
	}



}

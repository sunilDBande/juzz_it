package com.juzzIt.EducationProject.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class RecordedStudentPlacemants {

	
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
	@JoinColumn(name="RECODED_STUDENT_ID")
	private RecordedStudent recordedStudent;


	public RecordedStudentPlacemants() {
		super();
		// TODO Auto-generated constructor stub
	}


	public RecordedStudentPlacemants(String placementId, String companyName, String companyIntroduction,
			String active_Placement, String applyLink, RecordedStudent recordedStudent) {
		super();
		this.placementId = placementId;
		this.companyName = companyName;
		this.companyIntroduction = companyIntroduction;
		this.active_Placement = active_Placement;
		this.applyLink = applyLink;
		this.recordedStudent = recordedStudent;
	}


	@Override
	public String toString() {
		return "RecordedStudentPlacemants [placementId=" + placementId + ", companyName=" + companyName
				+ ", companyIntroduction=" + companyIntroduction + ", active_Placement=" + active_Placement
				+ ", applyLink=" + applyLink + ", recordedStudent=" + recordedStudent + "]";
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


	public RecordedStudent getRecordedStudent() {
		return recordedStudent;
	}


	public void setRecordedStudent(RecordedStudent recordedStudent) {
		this.recordedStudent = recordedStudent;
	}
}

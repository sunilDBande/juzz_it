package com.juzzIt.EducationProject.Entity;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class RecordedStudentToken {

	
	@Id
	@Column(name="TOKEN_ID")
	private String tokenId;
	@Column(name="TOKEN_NAME")
	private String tokenName;
	@Column(name="TOKEN_DESC")
	private String tokenDesc;
	@Column(name="ACTIVE_STATUS")
    private String activeStatus;
	@Column(name="CREATED_DATE_TIME")
    private LocalDateTime createdDateTime;
      
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name="RECORDED_STUDENT")
    private RecordedStudent recordedStudent;
  

	@OneToMany(mappedBy = "recordedStudentToken",cascade = CascadeType.REMOVE,fetch = FetchType.LAZY)
	private List<RecordedStudentTokenMassege> recordedStudentTokenMassege;

	public RecordedStudentToken() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getTokenId() {
		return tokenId;
	}

	public void setTokenId(String tokenId) {
		this.tokenId = tokenId;
	}

	public String getTokenName() {
		return tokenName;
	}

	public void setTokenName(String topknName) {
		this.tokenName = tokenName;
	}

	public String getTokenDesc() {
		return tokenDesc;
	}

	public void setTokenDesc(String tokenDesc) {
		this.tokenDesc = tokenDesc;
	}

	public String getActiveStatus() {
		return activeStatus;
	}

	public void setActiveStatus(String activeStatus) {
		this.activeStatus = activeStatus;
	}

	public LocalDateTime getCreatedDateTime() {
		return createdDateTime;
	}

	public void setCreatedDateTime(LocalDateTime createdDateTime) {
		this.createdDateTime = createdDateTime;
	}

	public RecordedStudent getRecordedStudent() {
		return recordedStudent;
	}

	public void setRecordedStudent(RecordedStudent recordedStudent) {
		this.recordedStudent = recordedStudent;
	}

	public List<RecordedStudentTokenMassege> getRecordedStudentTokenMassege() {
		return recordedStudentTokenMassege;
	}

	public void setRecordedStudentTokenMassege(List<RecordedStudentTokenMassege> recordedStudentTokenMassege) {
		this.recordedStudentTokenMassege = recordedStudentTokenMassege;
	}

	public RecordedStudentToken(String tokenId, String tokenName, String tokenDesc, String activeStatus,
			LocalDateTime createdDateTime, RecordedStudent recordedStudent,
			List<RecordedStudentTokenMassege> recordedStudentTokenMassege) {
		super();
		this.tokenId = tokenId;
		this.tokenName = tokenName;
		this.tokenDesc = tokenDesc;
		this.activeStatus = activeStatus;
		this.createdDateTime = createdDateTime;
		this.recordedStudent = recordedStudent;
		this.recordedStudentTokenMassege = recordedStudentTokenMassege;
	}

	@Override
	public String toString() {
		return "RecordedStudentToken [tokenId=" + tokenId + ", tokenName=" + tokenName + ", tokenDesc=" + tokenDesc
				+ ", activeStatus=" + activeStatus + ", createdDateTime=" + createdDateTime + ", recordedStudent="
				+ recordedStudent + ", recordedStudentTokenMassege=" + recordedStudentTokenMassege + "]";
	}

}

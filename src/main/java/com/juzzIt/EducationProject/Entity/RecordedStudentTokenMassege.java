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


@Entity
public class RecordedStudentTokenMassege {

	
	@Id
	@Column(name="MASSEGE_ID")
	private String massegeId;
	@Column(name="SENDER_NAME")
	private String sendername;
	@Column(name="SENDER_TYPE")
	private String senderType;
	@Column(name="SENDER_ID")
	private String senderId;
	@Column(name="MASSEGE")
	private String massege;
	@Column(name="CREATED_DATE_TIME")
	private LocalDateTime createdDateTime;
	@ManyToOne 
	@JoinColumn(name="REACRDED_STUDENT_TOKEN_ID")
	@JsonIgnore
	private RecordedStudentToken recordedStudentToken;
	public String getMassegeId() {
		return massegeId;
	}
	public void setMassegeId(String massegeId) {
		this.massegeId = massegeId;
	}
	public String getSendername() {
		return sendername;
	}
	public void setSendername(String sendername) {
		this.sendername = sendername;
	}
	public String getSenderType() {
		return senderType;
	}
	public void setSenderType(String senderType) {
		this.senderType = senderType;
	}
	public String getSenderId() {
		return senderId;
	}
	public void setSenderId(String senderId) {
		this.senderId = senderId;
	}
	public String getMassege() {
		return massege;
	}
	public void setMassege(String massege) {
		this.massege = massege;
	}
	public LocalDateTime getCreatedDateTime() {
		return createdDateTime;
	}
	public void setCreatedDateTime(LocalDateTime createdDateTime) {
		this.createdDateTime = createdDateTime;
	}
	public RecordedStudentToken getRecordedStudentToken() {
		return recordedStudentToken;
	}
	public void setRecordedStudentToken(RecordedStudentToken recordedStudentToken) {
		this.recordedStudentToken = recordedStudentToken;
	}
	@Override
	public String toString() {
		return "RecordedStudentTokenMassege [massegeId=" + massegeId + ", sendername=" + sendername + ", senderType="
				+ senderType + ", senderId=" + senderId + ", massege=" + massege + ", createdDateTime="
				+ createdDateTime + ", recordedStudentToken=" + recordedStudentToken + "]";
	}
	public RecordedStudentTokenMassege(String massegeId, String sendername, String senderType, String senderId,
			String massege, LocalDateTime createdDateTime, RecordedStudentToken recordedStudentToken) {
		super();
		this.massegeId = massegeId;
		this.sendername = sendername;
		this.senderType = senderType;
		this.senderId = senderId;
		this.massege = massege;
		this.createdDateTime = createdDateTime;
		this.recordedStudentToken = recordedStudentToken;
	}
	public RecordedStudentTokenMassege() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}

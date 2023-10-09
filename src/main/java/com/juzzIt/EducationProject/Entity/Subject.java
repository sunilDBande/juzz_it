package com.juzzIt.EducationProject.Entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Subject implements Serializable{
  @Id
  @Column(name="SUBJECT_ID")
	private String subjectId;
  @Column(name="SUBJECT_NAME")
	private String subjectName;
	
	
	public Subject() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public Subject(String subjectId, String subjectName) {
		super();
		this.subjectId = subjectId;
		this.subjectName = subjectName;
	}
	
	
	public String getSubjectId() {
		return subjectId;
	}
	public void setSubjectId(String subjectId) {
		this.subjectId = subjectId;
	}
	public String getSubjectName() {
		return subjectName;
	}
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
	
	
	@Override
	public String toString() {
		return "Subject [subjectId=" + subjectId + ", subjectName=" + subjectName + "]";
	}
}

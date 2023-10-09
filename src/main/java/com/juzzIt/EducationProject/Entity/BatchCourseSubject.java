package com.juzzIt.EducationProject.Entity;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;


@Entity
public class BatchCourseSubject implements Serializable{

	
	@Id
	@Column(name="BATCH_COURSE_SUBJECT_ID")
	private String batchCourseSubjectId;
	@Column(name="BATCH_COURSE_SUBJECT_NAME")
	private String batchCourseSubjectName;
	
	@Column(name="TEACHER_PERMITION_STATUS")
	private String teacherPermitionStutus;


	@ManyToOne
	@JsonIgnore
	@JoinColumn(name="TEACHER_ID")
	private Teacher teacher;	
	
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name="BATCH_COURSE_ID")
	private BatchCourse batchCourse;
	
	@OneToMany(mappedBy = "batchCourseSubject", fetch = FetchType.LAZY)
	private List<BatchCourseClassLinks> batchCourseClassLinks;
	
	
	
	  
	public BatchCourseSubject() {
		super();
		// TODO Auto-generated constructor stub
	}




	public BatchCourseSubject(String batchCourseSubjectId, String batchCourseSubjectName, String teacherPermitionStutus,
			Teacher teacher, BatchCourse batchCourse, List<BatchCourseClassLinks> batchCourseClassLinks) {
		super();
		this.batchCourseSubjectId = batchCourseSubjectId;
		this.batchCourseSubjectName = batchCourseSubjectName;
		this.teacherPermitionStutus = teacherPermitionStutus;
		this.teacher = teacher;
		this.batchCourse = batchCourse;
		this.batchCourseClassLinks = batchCourseClassLinks;
	}




	public String getBatchCourseSubjectId() {
		return batchCourseSubjectId;
	}




	public void setBatchCourseSubjectId(String batchCourseSubjectId) {
		this.batchCourseSubjectId = batchCourseSubjectId;
	}




	public String getBatchCourseSubjectName() {
		return batchCourseSubjectName;
	}




	public void setBatchCourseSubjectName(String batchCourseSubjectName) {
		this.batchCourseSubjectName = batchCourseSubjectName;
	}




	public String getTeacherPermitionStutus() {
		return teacherPermitionStutus;
	}




	public void setTeacherPermitionStutus(String teacherPermitionStutus) {
		this.teacherPermitionStutus = teacherPermitionStutus;
	}




	public Teacher getTeacher() {
		return teacher;
	}




	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}




	public BatchCourse getBatchCourse() {
		return batchCourse;
	}




	public void setBatchCourse(BatchCourse batchCourse) {
		this.batchCourse = batchCourse;
	}




	public List<BatchCourseClassLinks> getBatchCourseClassLinks() {
		return batchCourseClassLinks;
	}




	public void setBatchCourseClassLinks(List<BatchCourseClassLinks> batchCourseClassLinks) {
		this.batchCourseClassLinks = batchCourseClassLinks;
	}




	@Override
	public String toString() {
		return "BatchCourseSubject [batchCourseSubjectId=" + batchCourseSubjectId + ", batchCourseSubjectName="
				+ batchCourseSubjectName + ", teacherPermitionStutus=" + teacherPermitionStutus + ", teacher=" + teacher
				+ ", batchCourse=" + batchCourse + ", batchCourseClassLinks=" + batchCourseClassLinks + "]";
	}


  
}

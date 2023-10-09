package com.juzzIt.EducationProject.Entity;


import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;


@Entity
public class StudentCourseEnrollRequest implements Serializable{

	@Id
 	@Column(name="ENROLL_REQUEST_ID")
	private String enrollRequestId;
	@Column(name="STUDENT_NAME")
	private String studentName;
	@Column(name="COURSE_NAME")
    private String courseName;

	@Column(name="CourseTypeName")
	private String courseTypeName;
	@Column(name="COURSE_ENROLL_TYPE")
    private String courseEnrollType;
	
   @ManyToOne
   @JoinColumn(name="STUDENT_ID")
    private Student student; 
		
    @ManyToOne
    @JoinColumn(name="COURSE_TYPE_ID")
    private CourseType courseType;
    
    @OneToOne
    @JoinColumn(name="STUDENT_ENROLL_DATAILS")
    private StudentEnrollDetails studentEnrollDetails;

	public StudentCourseEnrollRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getEnrollRequestId() {
		return enrollRequestId;
	}

	public void setEnrollRequestId(String enrollRequestId) {
		this.enrollRequestId = enrollRequestId;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getCourseTypeName() {
		return courseTypeName;
	}

	public void setCourseTypeName(String courseTypeName) {
		this.courseTypeName = courseTypeName;
	}

	public String getCourseEnrollType() {
		return courseEnrollType;
	}

	public void setCourseEnrollType(String courseEnrollType) {
		this.courseEnrollType = courseEnrollType;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public CourseType getCourseType() {
		return courseType;
	}

	public void setCourseType(CourseType courseType) {
		this.courseType = courseType;
	}

	public StudentEnrollDetails getStudentEnrollDetails() {
		return studentEnrollDetails;
	}

	public void setStudentEnrollDetails(StudentEnrollDetails studentEnrollDetails) {
		this.studentEnrollDetails = studentEnrollDetails;
	}

	public StudentCourseEnrollRequest(String enrollRequestId, String studentName, String courseName,
			String courseTypeName, String courseEnrollType, Student student, CourseType courseType,
			StudentEnrollDetails studentEnrollDetails) {
		super();
		this.enrollRequestId = enrollRequestId;
		this.studentName = studentName;
		this.courseName = courseName;
		this.courseTypeName = courseTypeName;
		this.courseEnrollType = courseEnrollType;
		this.student = student;
		this.courseType = courseType;
		this.studentEnrollDetails = studentEnrollDetails;
	}

	@Override
	public String toString() {
		return "StudentCourseEnrollRequest [enrollRequestId=" + enrollRequestId + ", studentName=" + studentName
				+ ", courseName=" + courseName + ", courseTypeName=" + courseTypeName + ", courseEnrollType="
				+ courseEnrollType + ", student=" + student + ", courseType=" + courseType + ", studentEnrollDetails="
				+ studentEnrollDetails + "]";
	}


    
    
}

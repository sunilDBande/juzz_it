package com.juzzIt.EducationProject.Entity;

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
import jakarta.persistence.OneToOne;

@Entity
public class RecordedStudent {
	
	@Id
	@Column(name="RECORDED_STUDENT_ID")
	private String   recordedStudentId;
	@Column(name="COURSE_NAME")
	private String courseName;
	@Column(name="COURSE_TYPE")
	private String courseTypeName;
	@Column(name="STUDENT_PERMITION_STATUS")
    private String studentPermitionStatus;
	@Column(name="STUDENT_PLACEMNENT_STATUS")
    private String studentPlacementStatus;
	@Column(name="STUDENT_NAME")
	private String studentName;
	
	@Column(name="ENROLL_TYPE")
	private  String enrollTyp;
	@Column(name="MENTER_PERMITION_STATUS")
    private String menterPermitionStatus;
	
	@ManyToOne
	@JoinColumn(name="COURSE_TYPE_ID")
	@JsonIgnore
	private CourseType courseType;
	
	@ManyToOne
	@JoinColumn(name="STUDENT_ID")
	@JsonIgnore
	private Student student;
	
	@ManyToOne
	@JoinColumn(name="SALES_EXECUTIVE_ID")
	@JsonIgnore
	private SalesExecutive salesExecutive;
	
	
	@OneToOne(cascade = CascadeType.REMOVE,  orphanRemoval = true)
	@JoinColumn(name="STUDENT_ENROLL_DETAILS_ID")
	private StudentEnrollDetails studentEnrollDetails;
	
	@OneToMany(mappedBy = "recordedStudent",cascade = CascadeType.REMOVE,fetch = FetchType.LAZY)
	private List<RecordedStudentToken> recordedStudentToken;
	
	@Column(name="TEACHER_ID")
	private String teacherId;

	public RecordedStudent() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getRecordedStudentId() {
		return recordedStudentId;
	}

	public void setRecordedStudentId(String recordedStudentId) {
		this.recordedStudentId = recordedStudentId;
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

	public String getStudentPermitionStatus() {
		return studentPermitionStatus;
	}

	public void setStudentPermitionStatus(String studentPermitionStatus) {
		this.studentPermitionStatus = studentPermitionStatus;
	}

	public String getStudentPlacementStatus() {
		return studentPlacementStatus;
	}

	public void setStudentPlacementStatus(String studentPlacementStatus) {
		this.studentPlacementStatus = studentPlacementStatus;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public String getEnrollTyp() {
		return enrollTyp;
	}

	public void setEnrollTyp(String enrollTyp) {
		this.enrollTyp = enrollTyp;
	}

	public String getMenterPermitionStatus() {
		return menterPermitionStatus;
	}

	public void setMenterPermitionStatus(String menterPermitionStatus) {
		this.menterPermitionStatus = menterPermitionStatus;
	}

	public CourseType getCourseType() {
		return courseType;
	}

	public void setCourseType(CourseType courseType) {
		this.courseType = courseType;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public SalesExecutive getSalesExecutive() {
		return salesExecutive;
	}

	public void setSalesExecutive(SalesExecutive salesExecutive) {
		this.salesExecutive = salesExecutive;
	}

	public StudentEnrollDetails getStudentEnrollDetails() {
		return studentEnrollDetails;
	}

	public void setStudentEnrollDetails(StudentEnrollDetails studentEnrollDetails) {
		this.studentEnrollDetails = studentEnrollDetails;
	}

	public String getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(String teacherId) {
		this.teacherId = teacherId;
	}

	public RecordedStudent(String recordedStudentId, String courseName, String courseTypeName,
			String studentPermitionStatus, String studentPlacementStatus, String studentName, String enrollTyp,
			String menterPermitionStatus, CourseType courseType, Student student, SalesExecutive salesExecutive,
			StudentEnrollDetails studentEnrollDetails, String teacherId) {
		super();
		this.recordedStudentId = recordedStudentId;
		this.courseName = courseName;
		this.courseTypeName = courseTypeName;
		this.studentPermitionStatus = studentPermitionStatus;
		this.studentPlacementStatus = studentPlacementStatus;
		this.studentName = studentName;
		this.enrollTyp = enrollTyp;
		this.menterPermitionStatus = menterPermitionStatus;
		this.courseType = courseType;
		this.student = student;
		this.salesExecutive = salesExecutive;
		this.studentEnrollDetails = studentEnrollDetails;
		this.teacherId = teacherId;
	}

	@Override
	public String toString() {
		return "RecordedStudent [recordedStudentId=" + recordedStudentId + ", courseName=" + courseName
				+ ", courseTypeName=" + courseTypeName + ", studentPermitionStatus=" + studentPermitionStatus
				+ ", studentPlacementStatus=" + studentPlacementStatus + ", studentName=" + studentName + ", enrollTyp="
				+ enrollTyp + ", menterPermitionStatus=" + menterPermitionStatus + ", courseType=" + courseType
				+ ", student=" + student + ", salesExecutive=" + salesExecutive + ", studentEnrollDetails="
				+ studentEnrollDetails + ", teacherId=" + teacherId + "]";
	}

	
    
    
    
}

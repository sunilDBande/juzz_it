package com.juzzIt.EducationProject.Services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.juzzIt.EducationProject.DaoInterface.CourseDaoInterface;
import com.juzzIt.EducationProject.DaoInterface.CourseTypeDaoInterface;
import com.juzzIt.EducationProject.DaoInterface.RecordedStudentDaoInterface;
import com.juzzIt.EducationProject.DaoInterface.SalesExecutiveDaoInterface;
import com.juzzIt.EducationProject.DaoInterface.StudentCourseEnrollRequestDaoInterface;
import com.juzzIt.EducationProject.DaoInterface.StudentDaoInterface;
import com.juzzIt.EducationProject.DaoInterface.StudentEnrollDetailsDaoInterface;
import com.juzzIt.EducationProject.DaoInterface.TeacherDaoInterface;
import com.juzzIt.EducationProject.Entity.Course;
import com.juzzIt.EducationProject.Entity.CourseType;
import com.juzzIt.EducationProject.Entity.RecordedStudent;
import com.juzzIt.EducationProject.Entity.SalesExecutive;
import com.juzzIt.EducationProject.Entity.Student;
import com.juzzIt.EducationProject.Entity.StudentEnrollDetails;
import com.juzzIt.EducationProject.Entity.Teacher;
import com.juzzIt.EducationProject.Models.Responce;
import com.juzzIt.EducationProject.ServiceInterface.RecordedStudentServiceInterface;


@Service
public class RecordedStudentService implements RecordedStudentServiceInterface {

	@Autowired
	private RecordedStudentDaoInterface recordedStudentDaoInterface;
	
	@Autowired
	private StudentDaoInterface studentDaoInterface;
	
	@Autowired
	private TeacherDaoInterface teacherDaoInterface;
	
	@Autowired
	private CourseTypeDaoInterface courseTypeDaoInterface;
	
	@Autowired
	private CourseDaoInterface courseDaoInterface;
	
	@Autowired
	private StudentCourseEnrollRequestDaoInterface studentCourseEnrollRequestDaoInterface;
	
	@Autowired
	private StudentEnrollDetailsDaoInterface studentEnrollDetailsDaoInterface;
	
	@Autowired
	private SalesExecutiveDaoInterface salesExecutiveDaoInterface;
	
	
	@Override
	public Responce addRecordedStudent(String courseTypeId, String studentId, String requestId, String salesExecutiveId) {
		
	
		Responce responce= new Responce();
		
		
		CourseType courseType = courseTypeDaoInterface.getCourseTypeById(courseTypeId);
		
		if(courseType==null) {
			responce.setMassege("course type for the given id not found");
			responce.setStatus(false);
			return responce;
		}
		
		
		List<HashMap<String, Object>> courseData=courseDaoInterface.getCourseDataByCourseTypeId(courseType.getCourseTypeId());
		
	

		
		Student student = studentDaoInterface.getStudentById(studentId);
		
		
		if(student==null) {
			responce.setMassege("student with the given id not found");
			responce.setStatus(false);
			return responce;
		}
		
		StudentEnrollDetails enrollDetails = studentCourseEnrollRequestDaoInterface.getEnrollDetailsByRequestId(requestId);
		
		if(enrollDetails==null) {
			responce.setMassege("enroll detaile for the given request id not found");
			responce.setStatus(false);
			return responce;
		}
		
		SalesExecutive salesExecutive = salesExecutiveDaoInterface.getSalesExecutiveById(salesExecutiveId);
		
		if(salesExecutive==null) {
			responce.setMassege("seles executive for the given id not found");
			responce.setStatus(false);
			return responce;
		}
		
		
		
		RecordedStudent recordedStudent=new RecordedStudent();
		
		if(courseData.get(0).get("course_name")!=null) {
			recordedStudent.setCourseName(courseData.get(0).get("course_name").toString());
		}
		else {
			recordedStudent.setCourseName("NO");
		}
		
		recordedStudent.setCourseType(courseType);
		recordedStudent.setStudent(student);
		recordedStudent.setStudentEnrollDetails(enrollDetails);
		recordedStudent.setTeacherId("NO");
		recordedStudent.setSalesExecutive(salesExecutive);
		recordedStudent.setCourseTypeName(courseType.getCourseTypeName());
		recordedStudent.setStudentPermitionStatus("D");
		recordedStudent.setStudentPlacementStatus("D");
		recordedStudent.setMenterPermitionStatus("D");
		recordedStudent.setStudentName(enrollDetails.getStudentName());
		recordedStudent.setEnrollTyp("recorded");
	
		RecordedStudent addedRecordedStudent = recordedStudentDaoInterface.addRecordedStudent(recordedStudent);
	
		if(addedRecordedStudent==null) {
			responce.setMassege("failed to add the student");
			responce.setStatus(false);
			return responce;
		}
		studentCourseEnrollRequestDaoInterface.deleteEnrollRequestAfterAcmition(requestId);
		responce.setMassege("student added successfully");
		responce.setStatus(true);
		return responce;
	}

	@Override
	public Responce assignMenterToStudent(String recordedStudentId, String teacherId) {
		Responce responce=new Responce();
		
		Teacher teacher = teacherDaoInterface.getTeacherById(teacherId);
		
		if(teacher==null) {
			responce.setMassege("menter with the given id not found");
			responce.setStatus(false);
			return responce;
		}

		return recordedStudentDaoInterface.assignMenterToStudent(recordedStudentId, teacherId);
	}

	@Override
	public Responce deleteRecordedStudent(String recordedStudentId) {

		return recordedStudentDaoInterface.deleteRecordedStudent(recordedStudentId);
	}

	@Override
	public Responce updateRecorededStudent(String recorddStudentId, HashMap<String, Object> studentData) {

		return recordedStudentDaoInterface.updateRecorededStudent(recorddStudentId, studentData);
	}

	@Override
	public List<Map<String, Object>> getAllRecordedStudent() {
				return recordedStudentDaoInterface.getAllRecordedStudent();
	}

	@Override
	public Responce deleteRecordedStudentMenter(String recordedStudentId) {

		return recordedStudentDaoInterface.deleteMenterFromStudent(recordedStudentId);
	}

}

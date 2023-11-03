package com.juzzIt.EducationProject.Dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.juzzIt.EducationProject.DaoInterface.StudentCourseEnrollRequestDaoInterface;
import com.juzzIt.EducationProject.Entity.StudentCourseEnrollRequest;
import com.juzzIt.EducationProject.Entity.StudentEnrollDetails;
import com.juzzIt.EducationProject.Models.Responce;
import com.juzzIt.EducationProject.Repositary.StudentCourseEnrollRequestRepository;
import com.juzzIt.EducationProject.Repositary.StudentEnrollDetailsRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
@Component
public class StudentCourseEnrollRequestDao  implements StudentCourseEnrollRequestDaoInterface{

	
	@Autowired
	private StudentCourseEnrollRequestRepository studentCourseEnrollRequestRepo;


	@Autowired
	private StudentEnrollDetailsRepository studentEnrollDetailsRepository;
	
	@Autowired
	private EntityManager entityManager;
	
	@Override
	public Responce deleteRequest(String requestId) throws Exception {
		
		Optional<StudentCourseEnrollRequest> request = studentCourseEnrollRequestRepo.findById(requestId);
		
		Responce responce=new Responce();
		try {
		if(request.isPresent()) {
			
			StudentCourseEnrollRequest studentCourseEnrollRequest = request.get();
			
			StudentEnrollDetails studentEnrollDetails = studentCourseEnrollRequest.getStudentEnrollDetails();
			
		
			studentCourseEnrollRequestRepo.delete(studentCourseEnrollRequest);
			
			if(studentEnrollDetails!=null) {
				studentEnrollDetailsRepository.delete(studentEnrollDetails);
				}
			responce.setMassege("request deleted successfully");
			responce.setStatus(true);
		}else {
			responce.setMassege("request with the id not found ");
			responce.setStatus(false);
		}
		}catch (Exception e) {
			throw new Exception("Getting problem while deleting Request");
		}
		return responce;
	}


	@Override
	public StudentCourseEnrollRequest addRequest(StudentCourseEnrollRequest request) {
	
		
		
		return studentCourseEnrollRequestRepo.save(request);
	}


	@Override
	public StudentEnrollDetails getEnrollDetailsByRequestId(String requestId) {
		
		Optional<StudentCourseEnrollRequest> request = studentCourseEnrollRequestRepo.findById(requestId);
		
		if(request.isPresent()) {
			return request.get().getStudentEnrollDetails();
		}
		
		return null;
	}


	@Override
	public boolean deleteEnrollRequestAfterAcmition(String requestId) {

		
		Optional<StudentCourseEnrollRequest> request = studentCourseEnrollRequestRepo.findById(requestId);
		
		
		if(request.isPresent()) {
	 studentCourseEnrollRequestRepo.delete(request.get());
	 
	 return true;
		}
		return false;

	}


	@Override
	public HashMap<String, Object> getCourseTypeDataByRequestId(String requestId) {
String []data= {"COURSE_TYPE_ID","course_type_name"};
		
		
		String query= " select course_type.course_type_id ,course_type.course_type_name  "
				+ " from student_course_enroll_request , course_type "
				+ " where "
				+ "  student_course_enroll_request.enroll_request_id='"+requestId+"'" 
				+ "  and  student_course_enroll_request.course_type_id = course_type.course_type_id  ";
		
		Query createNativeQuery = entityManager.createNativeQuery(query);
		List<Object[]> resultList = createNativeQuery.getResultList();
         List<HashMap<String, Object>> finalOutput = new ArrayList<HashMap<String,Object>>();
		for(Object res[]:resultList) {
			  LinkedHashMap<String, Object> lh = new LinkedHashMap<String, Object>();
			for(int i=0; i<=res.length-1; i++) {
				if (res[i] == null || res[i].toString().trim().isEmpty()) {
					lh.put(data[i], "");
				} else {
					lh.put(data[i], res[i].toString());
				}
			}
			finalOutput.add(lh);
		}
		System.out.println("finalOutput--> "+finalOutput);
		if(finalOutput.isEmpty()) {
			return null;
		}
		return finalOutput.get(0);	
	}
	
	
}

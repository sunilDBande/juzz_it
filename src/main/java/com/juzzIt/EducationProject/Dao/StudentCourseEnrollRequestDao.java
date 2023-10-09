package com.juzzIt.EducationProject.Dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.juzzIt.EducationProject.DaoInterface.StudentCourseEnrollRequestDaoInterface;
import com.juzzIt.EducationProject.Entity.StudentCourseEnrollRequest;
import com.juzzIt.EducationProject.Entity.StudentEnrollDetails;
import com.juzzIt.EducationProject.Models.Responce;
import com.juzzIt.EducationProject.Repositary.StudentCourseEnrollRequestRepository;
import com.juzzIt.EducationProject.Repositary.StudentEnrollDetailsRepository;
@Component
public class StudentCourseEnrollRequestDao  implements StudentCourseEnrollRequestDaoInterface{

	
	@Autowired
	private StudentCourseEnrollRequestRepository studentCourseEnrollRequestRepo;


	@Autowired
	private StudentEnrollDetailsRepository studentEnrollDetailsRepository;
	
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
	
	
}

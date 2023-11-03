package com.juzzIt.EducationProject.DaoInterface;

import java.util.HashMap;

import com.juzzIt.EducationProject.Entity.StudentCourseEnrollRequest;
import com.juzzIt.EducationProject.Entity.StudentEnrollDetails;
import com.juzzIt.EducationProject.Models.Responce;

public interface StudentCourseEnrollRequestDaoInterface {
	
	public StudentCourseEnrollRequest addRequest(StudentCourseEnrollRequest request);
	public Responce deleteRequest(String requestId) throws Exception;
	public StudentEnrollDetails getEnrollDetailsByRequestId(String requestId);
	public boolean deleteEnrollRequestAfterAcmition(String requestId);
	public HashMap<String , Object> getCourseTypeDataByRequestId(String requestId);
	
}

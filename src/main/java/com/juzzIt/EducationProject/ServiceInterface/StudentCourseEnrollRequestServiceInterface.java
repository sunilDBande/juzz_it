package com.juzzIt.EducationProject.ServiceInterface;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.juzzIt.EducationProject.Entity.Student;
import com.juzzIt.EducationProject.Models.Responce;

public interface StudentCourseEnrollRequestServiceInterface {
	public Responce addEnrollRequest( String studentId,String courseTypeId, HashMap<String, Object> requestData);
	public Responce deleteRequest(String requestId ) throws Exception;
	
	public List<Map<String, Object>> getAllEnrollReq() throws Exception;

}

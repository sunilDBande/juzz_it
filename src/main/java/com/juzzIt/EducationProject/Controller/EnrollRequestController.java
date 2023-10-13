package com.juzzIt.EducationProject.Controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.juzzIt.EducationProject.Entity.Student;
import com.juzzIt.EducationProject.Models.Responce;
import com.juzzIt.EducationProject.ServiceInterface.StudentCourseEnrollRequestServiceInterface;


@RestController
@RequestMapping("/enroll")
@CrossOrigin(origins = "*")
public class EnrollRequestController {

	@Autowired
	private StudentCourseEnrollRequestServiceInterface studentCourseEnrollRequestServiceInterface;
	
	@PostMapping("/request/{studentId}/courses/{courseTypeId}")
	public Responce addEnrollRequest(@PathVariable("studentId") String studentId,@PathVariable("courseTypeId") String courseTypeId,@RequestBody HashMap<String, Object> requestData) {
		return studentCourseEnrollRequestServiceInterface.addEnrollRequest(studentId, courseTypeId, requestData);
	}
	
	
	@DeleteMapping("/requests/{requestId}")
	public Responce deleteRequest(@PathVariable("requestId") String requestId ) throws Exception {
		
		return studentCourseEnrollRequestServiceInterface.deleteRequest(requestId);
	}
	 
	@GetMapping("/requests")
	public List<Map<String, Object>> getAllEnrollReq() throws Exception{
		
		return studentCourseEnrollRequestServiceInterface.getAllEnrollReq();
	}
	
	
}

package com.juzzIt.EducationProject.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.juzzIt.EducationProject.Models.Responce;
import com.juzzIt.EducationProject.ServiceInterface.RecordedStudentServiceInterface;
import com.juzzIt.EducationProject.ServiceInterface.RecordedStudentTokenMassegeInterface;
import com.juzzIt.EducationProject.ServiceInterface.RecordedStudentTokenServiceInterface;

public class RecordedStudentController {
	
	@Autowired
	private RecordedStudentServiceInterface recordedStudentServiceInterface;
	
	@Autowired
	private RecordedStudentTokenServiceInterface recordedStudentTokenServiceInterface;
	
	@Autowired
	private RecordedStudentTokenMassegeInterface recordedStudentTokenMassegeInterface;
	
	
	
	@PostMapping("/courseTypes/{courseTypeId}/recordedStudents/{studentId}/{requestId}/{salesExecutiveId}")
	public Responce addRecordedStudent(@PathVariable("courseTypeId") String courseTypeId,@PathVariable("studentId")String studentId,@PathVariable("requestId")String requestId ,@PathVariable("salesExecutiveId") String salesExecutiveId) {
		return recordedStudentServiceInterface.addRecordedStudent(courseTypeId, studentId, requestId, salesExecutiveId);
	}

	
	@PostMapping("/recordedStudents/{recordedStudentId}/teacher/{teacherId}")
	public Responce assignMenterToStudent(@PathVariable("recordedStudentId") String recordedStudentId,@PathVariable("teacherId")String teacherId) {
		return recordedStudentServiceInterface.assignMenterToStudent(recordedStudentId, teacherId);
	}
	
	@DeleteMapping("/recordedStudents/{recordedStudentId}")
	public Responce deleteRecordedStudent(@PathVariable("recordedStudentId")String recordedStudentId) {
		return recordedStudentServiceInterface.deleteRecordedStudent(recordedStudentId);
	}
	
	
	@DeleteMapping("/recordedStudents/{recordedStudentId}")
	public Responce deleteRecordedStudentMenter(@PathVariable("recordedStudentId")String recordedStudentId) {
		return recordedStudentServiceInterface.deleteRecordedStudentMenter(recordedStudentId);
	}
	
	
	@PutMapping("/recordedStudents/{recordedStudentId}")
	public Responce updateRecorededStudent(@PathVariable("recordedStudentId")String recordedStudentId,HashMap<String, Object> studentData) {
		return recordedStudentServiceInterface.updateRecorededStudent(recordedStudentId, studentData);
	}
	
    @GetMapping("/recordedStudents")	
	public List<Map<String, Object>> getAllRecordedStudent(){
		return recordedStudentServiceInterface.getAllRecordedStudent();
	}
    
    
    
    //// student token
    
    @PostMapping("/recordedStudents/{recordedStudentId}/tokans")
    public Responce addNewToken(@PathVariable("recordedStudentId") String recordedStudentId , @RequestBody HashMap<String, Object> tokenData) {
    	return recordedStudentTokenServiceInterface.addNewToken(recordedStudentId, tokenData);
    }
    
    @DeleteMapping("/tokans/{tokanId}")
    public Responce deleteToken(@PathVariable("tokenId") String tokanData) {
    	return recordedStudentTokenServiceInterface.deleteToken(tokanData);
    }
    
    @PutMapping("/token/{tokanId}")
    public Responce updateToken(@PathVariable("tokanId") String tokanId, @RequestBody HashMap<String, Object> tokenData) {
    	return recordedStudentTokenServiceInterface.updateToken(tokanId, tokenData);
    }
    
    @GetMapping("/tokens")
    public List<Map<String , Object>> getAllTokensActive(){
    	return recordedStudentTokenServiceInterface.getAllTokensActive();
    }
    @GetMapping("/AllTokens")
    public List<Map<String , Object>> getAllTokens(){
    	return recordedStudentTokenServiceInterface.getAllTokens();
    }
    @GetMapping("/recordedStudents/{recordedStudentId}/tokans")
    public List<Map<String, Object>> getTokenByRecordedStudentId(@PathVariable("recordedStudentId") String recordedStudentId){
    	return recordedStudentTokenServiceInterface.getTokenByRecordedStudentId(recordedStudentId);
    }
	

			
	
	////
    
    
    @PostMapping("/tokans/{tokanId}/tokanMasseges/{senderId}")
    public Responce addTokenMassege(@PathVariable("tokanId") String tokanId,@PathVariable("senderId") String senderId,@RequestBody HashMap<String, Object> tokenMassegeData ) {
    	return recordedStudentTokenMassegeInterface.addTokenMassege(tokanId, senderId, tokenMassegeData);
    }
    
    
    @DeleteMapping("/tokanMasseges/{tokanMassegeId}")
    public Responce deleteTokenMassege(@PathVariable("tokanMassegeId") String tokanMassegeId) {
    	return recordedStudentTokenMassegeInterface.deleteTokenMassege(tokanMassegeId);
    }

    
    @PutMapping("/tokanMasseges/{tokanMassegeId}")
    public Responce updateTokenMassege(@PathVariable("tokanMassegeId") String tokanMassegeId ,@RequestBody HashMap<String, Object> tokenMassegeData ) {
    	return recordedStudentTokenMassegeInterface.updateTokenMassege(tokanMassegeId, tokenMassegeData);
    }
    
   @GetMapping("/tokans/{tokanId}/tokanMasseges") 
    public List<Map<String , Object>> getAllTokenMassege(@PathVariable("tokenId") String tokenId){
    	return recordedStudentTokenMassegeInterface.getAllTokenMassege(tokenId);
    }
}
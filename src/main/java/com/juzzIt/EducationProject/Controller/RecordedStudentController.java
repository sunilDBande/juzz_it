package com.juzzIt.EducationProject.Controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.juzzIt.EducationProject.Models.Responce;
import com.juzzIt.EducationProject.ServiceInterface.RecodedStudentClassLinkServiceInterface;
import com.juzzIt.EducationProject.ServiceInterface.RecodedStudentPlacementImageServiceInterface;
import com.juzzIt.EducationProject.ServiceInterface.RecordedStudentPlacemantsServiceInterface;
import com.juzzIt.EducationProject.ServiceInterface.RecordedStudentServiceInterface;
import com.juzzIt.EducationProject.ServiceInterface.RecordedStudentTokenMassegeInterface;
import com.juzzIt.EducationProject.ServiceInterface.RecordedStudentTokenServiceInterface;


@RestController
@RequestMapping("/recoded/bch")
@CrossOrigin(origins = "*")
public class RecordedStudentController {
	
	@Autowired
	private RecordedStudentServiceInterface recordedStudentServiceInterface;
	@Autowired
	private RecordedStudentTokenServiceInterface recordedStudentTokenServiceInterface;
	@Autowired
	private RecordedStudentTokenMassegeInterface recordedStudentTokenMassegeInterface;
	
	@Autowired
	private RecodedStudentPlacementImageServiceInterface recodedStudentPlacementImageServiceInterface;
	
	@Autowired
	private RecordedStudentPlacemantsServiceInterface recordedStudentPlacemantsServiceInterface;
	
	@Autowired
	private RecodedStudentClassLinkServiceInterface recodedStudentClassLinkServiceInterface;
	
	
	
	@PostMapping("/courseTypes/{courseTypeId}/recordedStudents/{studentId}/{requestId}")
	public Responce addRecordedStudent(@PathVariable("courseTypeId") String courseTypeId,@PathVariable("studentId")String studentId,@PathVariable("requestId")String requestId ) {
		return recordedStudentServiceInterface.addRecordedStudent(courseTypeId, studentId, requestId);
	}
	@GetMapping("/recordedStudents/{recordedStudentId}/teacher/{teacherId}")
	public Responce assignMenterToStudent(@PathVariable("recordedStudentId") String recordedStudentId,@PathVariable("teacherId")String teacherId) {
		return recordedStudentServiceInterface.assignMenterToStudent(recordedStudentId, teacherId);
	}
	@DeleteMapping("/recordedStudents/{recordedStudentId}")
	public Responce deleteRecordedStudent(@PathVariable("recordedStudentId")String recordedStudentId) {
		return recordedStudentServiceInterface.deleteRecordedStudent(recordedStudentId);
	}
	
	
	@DeleteMapping("/recordedStudents/{recordedStudentId}/menter")
	public Responce deleteRecordedStudentMenter(@PathVariable("recordedStudentId")String recordedStudentId) {
		return recordedStudentServiceInterface.deleteRecordedStudentMenter(recordedStudentId);
	}
	
	
	@PutMapping("/recordedStudents/{recordedStudentId}")
	public Responce updateRecorededStudent(@PathVariable("recordedStudentId")String recordedStudentId,@RequestBody  HashMap<String, Object> studentData) {
		return recordedStudentServiceInterface.updateRecorededStudent(recordedStudentId, studentData);
	}
	
    @GetMapping("/recordedStudents")	
	public List<Map<String, Object>> getAllRecordedStudent(){
		return recordedStudentServiceInterface.getAllRecordedStudent();
	}
    
    @GetMapping("/recordedStudents/noMenter")
    public List<Map<String, Object>> getRecordedStudentWithOutMenter(){
    	
    	
//    	return new ArrayList<Map<String,Object>>();
     return recordedStudentServiceInterface.getRecordedStudentWithOutMenter();
    }
    
    @GetMapping("/recordedStudents/withMenter")
    public List<Map<String, Object>> getRecordedStudentWithMenter(){
        return recordedStudentServiceInterface.getRecordedStudentWithMenter();
       }
    
    
    
    @GetMapping("/trainer/{trainerId}/students")
    public List<Map<String, Object>> getRecordedStudentBasedOnTrainerId(@PathVariable("trainerId") String trainerId){
    	System.out.println(trainerId);
    	return recordedStudentServiceInterface.getRecordedStudentBasedOnTrainerId(trainerId);
    }
    
    
    
    //// student side api
 
    @GetMapping("/student/{studentId}/batchs")
    public List<Map<String, Object>> getAllEnrolledRecodedStudentBatchs(@PathVariable("studentId") String studentId){
    	return recordedStudentServiceInterface.getAllEnrolledRecodedStudentBatchs(studentId);
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
   
   
   
   //// recoded student placements
   
   
   @PostMapping("/recodedStudent/{recodedStudentBatchId}/placements")
   public Responce addRecodedStudentPlacements(@PathVariable("recodedStudentBatchId")  String recodedStudentBatchId,@RequestBody  HashMap<String, Object> placementData) {
	   return recordedStudentPlacemantsServiceInterface.addRecodedStudentPlacements(recodedStudentBatchId, placementData);
   }
   
   @DeleteMapping("/recodedStudent/placements/{placementId}")
   public Responce deleteRecodedStudentPlacements(@PathVariable("placementId")  String placmentId) {
	   return recordedStudentPlacemantsServiceInterface.deleteRecodedStudentPlacements(placmentId);
   }
   
   @PutMapping("/recodedStudent/placements/{placementId}")
   public Responce updateRecodedStudentPlacements(@PathVariable("placementId") String placmentId,@RequestBody HashMap<String, Object> placmentData) {
	   return recordedStudentPlacemantsServiceInterface.updateRecodedStudentPlacements(placmentId, placmentData);
   }
   
   @GetMapping("/recodedStudent/{recodedStudentBatchId}/placements")
   public List<Map<String, Object>>  getRecodedStudentPlacements(@PathVariable("recodedStudentBatchId") String recodedStudentId){
	   return  recordedStudentPlacemantsServiceInterface.getRecodedStudentPlacements(recodedStudentId);
   }
   
   	
   
   
   ///// recodedstudent placement images
  
   
   @PostMapping("/recodedStudent/placements/{placementId}/images")
   public Responce addPlacementImage(@PathVariable("placementId")  String placementId,@RequestBody HashMap<String, Object>  imageData) {
	   return recodedStudentPlacementImageServiceInterface.addPlacementImage(placementId, imageData);
   }
   
   @DeleteMapping("/recodedStudent/placements/images/{imageId}")
   public Responce deletePlacementImage( @PathVariable("imageId")  String imageId) {
	   return recodedStudentPlacementImageServiceInterface.deletePlacementImage(imageId);
   }
   
   
   @PutMapping("/recodedStudent/placements/images/{imageId}")
   public Responce updatePlacementImages(@PathVariable("imageId")  String imageId,@RequestBody HashMap<String, Object> imageData) {
	   return recodedStudentPlacementImageServiceInterface.updatePlacementImages(imageId, imageData);
   }
   
   
   @GetMapping("/recodedStudent/placements/{placementId}/images")
   public List<Map<String, Object>> getAllPlacementImages(@PathVariable("placementId")  String placementId){
	   return recodedStudentPlacementImageServiceInterface.getAllPlacementImages(placementId);
   }
   
   
   
   //// recoded Student classLinks 
   
   
   
   @PostMapping("/recodedStudent/{recodedStudentBatchId}/classLinks")
   public Responce addRecodedStudentCLassLinks(@PathVariable("recodedStudentBatchId")  String recodedStudentBatchId,@RequestBody HashMap<String, Object> classLinkData) {
	   return recodedStudentClassLinkServiceInterface.addRecodedStudentCLassLinks(recodedStudentBatchId, classLinkData);
   }
   
   @DeleteMapping("/recodedStudent/classLinks/{classLinkId}")
   public Responce deleteRecodedStudentCLassLinks(@PathVariable("classLinkId")  String classLinkId) {
	   return recodedStudentClassLinkServiceInterface.deleteRecodedStudentCLassLinks(classLinkId);
   }
   
   @GetMapping("/recodedStudent/{recodedStudentBatchId}/classLinks")
   public List<Map<String, Object>> getRecodedStudentCLassLinks(@PathVariable("recodedStudentBatchId")  String recodedStudentBatchId){
	   
	   System.out.println("recodedStudentBatchId");
	   return recodedStudentClassLinkServiceInterface.getRecodedStudentCLassLinks(recodedStudentBatchId);
   }
   
   
   
   //// recoded videos 
   
   public List<Map<String, Object>> getModuleWithImageByCourseTypeId(String courseTypeId){
	   return null;
   }
   
   
}

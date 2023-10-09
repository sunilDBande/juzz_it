package com.juzzIt.EducationProject.Controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.juzzIt.EducationProject.Entity.EnrollType;
import com.juzzIt.EducationProject.Entity.Subject;
import com.juzzIt.EducationProject.Models.Responce;
import com.juzzIt.EducationProject.ServiceInterface.EnrollTypeServicesInterface;
import com.juzzIt.EducationProject.ServiceInterface.SubjectServiceInterface;


@RestController
@RequestMapping("/sub")
@CrossOrigin("*")
public class SubjectController {

	
	@Autowired
	SubjectServiceInterface subjectService;

	 
	  @Autowired
	  EnrollTypeServicesInterface enrollTypeServices;
	
	
	
	@PostMapping("/subjects")
   public Responce addNewSubject(@RequestBody Subject subject) throws Exception {
	  subject.setSubjectId(generateUniqueId());
	   return subjectService.addSubject(subject);
   }
   
	
	@GetMapping("/subjects")
   public List<Subject> getAllSubject(){
	   return subjectService.getSubjects();
   }
   
	
	
   @DeleteMapping("/subjects/{subjectId}")
   public Responce deleteSubjectById(@PathVariable("subjectId") String subjectId) throws Exception {
	System.out.println(subjectId);
	   
	   return subjectService.deleteSubject(subjectId);  
   }
   
   
   
   //// for Enroll  Types

	@PostMapping("/enrollTypes")
	public Responce addNewEnrollType(@RequestBody EnrollType enrollType) throws Exception {
		enrollType.setEnrollTypeId(generateUniqueId());
		return enrollTypeServices.addNewEnrollType(enrollType);
	}
	@DeleteMapping("/enrollTypes/{enrollTypeId}")
	public Responce deleteEnrollType(@PathVariable("enrollTypeId") String enrollType) throws Exception {
		return enrollTypeServices.deleteEnrollType(enrollType);
	}
	
	@GetMapping("/enrollTypes")
	public List<EnrollType> getAllEnrollType(){
		return enrollTypeServices.getAllEnrollType();
	}
	 
	
	public static String generateUniqueId() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyMM");
        String timestamp = dateFormat.format(new Date());

        Random random = new Random();
        int randomNum = random.nextInt(100);

        return timestamp + randomNum;
    }
}

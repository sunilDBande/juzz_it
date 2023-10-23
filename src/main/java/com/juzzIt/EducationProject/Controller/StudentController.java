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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.juzzIt.EducationProject.Entity.Student;
import com.juzzIt.EducationProject.Models.LogInData;
import com.juzzIt.EducationProject.Models.LogInOrSignUpResponce;
import com.juzzIt.EducationProject.Models.Responce;
import com.juzzIt.EducationProject.ServiceInterface.StudentServiceInterface;

@RestController
@RequestMapping("std")
@CrossOrigin(origins = "*")
public class StudentController {
  @Autowired
	StudentServiceInterface studentService;
  
	@PostMapping("/signUp")
	public LogInOrSignUpResponce signUp(@RequestBody HashMap<String, Object> student) throws Exception {
		System.out.println(student);
		return studentService.addNewStudent(student);
	}
	@PostMapping("/logIn")
	public Responce StudentLogIn(@RequestBody LogInData logInData) {
		System.out.println(logInData);
		return studentService.logInValidate(logInData);
	}
	
	
	
	@PutMapping("/student/{StudentId}")
	public Responce updateStudent(@PathVariable("StudentId") String studentId, @RequestBody HashMap<String, Object> student) throws Exception {
		System.out.println(student);
	  return studentService.updateStudent(studentId, student);	
	}
	
	
	@GetMapping("/students")
	public List<Map<String, Object>>  getAllStudent() throws Exception{
		return studentService.getStudents();
	}
	
	@DeleteMapping("/student/{StudentId}")
	public Responce deleteStudentById(@PathVariable("StudentId") String studentId) {
		return studentService.deleteStudentById(studentId);
	}
}

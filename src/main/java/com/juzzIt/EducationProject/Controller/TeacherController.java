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
import com.juzzIt.EducationProject.Entity.Teacher;
import com.juzzIt.EducationProject.Models.LogInData;
import com.juzzIt.EducationProject.Models.LogInOrSignUpResponce;
import com.juzzIt.EducationProject.Models.Responce;
import com.juzzIt.EducationProject.ServiceInterface.TeacherServiceInterface;

@RestController
@RequestMapping("tch")
@CrossOrigin(origins = "*")
public class TeacherController {
	
	
	@Autowired
	TeacherServiceInterface teacherService;

	@PostMapping("/signUp")
	public LogInOrSignUpResponce teacherSignUp(@RequestBody HashMap<String, Object> teacher) {
		System.out.println(teacher);
		return teacherService.addNewTeacher(teacher);
	}
	
	
	@PostMapping("/logIn")
	public Responce logIn(@RequestBody LogInData logInData) {
		
		System.out.println(logInData);
	
		return teacherService.logInValidate(logInData);
		
	}
	
	
	
	
	@GetMapping("/teachers")
	public List<Map<String, Object>> getAllTeacher(){
		return teacherService.getTeachers();
	}
	
	
	@DeleteMapping("/teacher/{teachetId}")
	public Responce deleteTeacherById(@PathVariable("teachetId") String teacherId) throws Exception {
		return teacherService.deleteTeacher(teacherId);
	}
	
	
	@PutMapping("/teacher/{teachetId}")
	public Responce updateTeacher(@PathVariable("teachetId") String teacherId, @RequestBody HashMap<String, Object> teacher) throws Exception {
		return teacherService.updateTeacher(teacherId, teacher);
	}
	@GetMapping("/teachers/{teacherId}/BatchDetails")
	public List<HashMap<String, Object>>  getAllTeacherDetails(@PathVariable("teacherId") String teacherId) throws Exception{
		return teacherService.getAllTeacherDetails(teacherId);
	}
	@GetMapping("/teachers/{teacherId}/details")
	public Teacher getTeacherDataById(@PathVariable("teacherId") String teacherId) throws Exception{
		return teacherService.getTeacherById(teacherId);
	}
	
	
	
}

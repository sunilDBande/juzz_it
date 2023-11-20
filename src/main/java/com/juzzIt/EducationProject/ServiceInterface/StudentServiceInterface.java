package com.juzzIt.EducationProject.ServiceInterface;

import java.util.Map;
import java.util.HashMap;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;


import com.juzzIt.EducationProject.Entity.Student;
import com.juzzIt.EducationProject.Models.LogInData;
import com.juzzIt.EducationProject.Models.LogInOrSignUpResponce;
import com.juzzIt.EducationProject.Models.Responce;

public interface StudentServiceInterface {


	public LogInOrSignUpResponce addNewStudent(HashMap<String, Object> student) throws Exception;
	public Student  getStudentById(String studentId);
	public Responce updateStudent(String studentId, HashMap<String, Object> student) throws Exception;
	public Responce logInValidate(LogInData logInData);
	public List<Map<String, Object>> getStudents() throws Exception;
	public Responce updateStudentAfterEnroll(String studentId);
	public Responce deleteStudentById(String studentId);
	public Student getStudentByEmail(String email);
	public List<Map<String, Object>> getStudentDetails( String studentId);
	
}

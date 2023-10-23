package com.juzzIt.EducationProject.DaoInterface;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.juzzIt.EducationProject.Entity.Teacher;
import com.juzzIt.EducationProject.Models.LogInOrSignUpResponce;
import com.juzzIt.EducationProject.Models.Responce;

public interface TeacherDaoInterface {

	public LogInOrSignUpResponce addNewTeacher(HashMap<String, Object> teacher);
	
	public Responce updateTeacher(Teacher teacher);
	
	public List<HashMap<String, Object>> getAllTeacherDetails(String teacherId) throws Exception;
	
	public Teacher getTeacherByEmail(String email);
	
	public List<Map<String , Object>> getAllTeacher();
	
	public Teacher getTeacherById(String teacherId);
}

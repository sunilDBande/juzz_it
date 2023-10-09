package com.juzzIt.EducationProject.ServiceInterface;

import java.util.HashMap;
import java.util.List;


import com.juzzIt.EducationProject.Entity.Teacher;
import com.juzzIt.EducationProject.Models.LogInData;
import com.juzzIt.EducationProject.Models.Responce;

public interface TeacherServiceInterface {
	public Responce addNewTeacher(HashMap<String, Object> teacher);
	public Responce logInValidate(LogInData logInData);
	public Teacher  getTeacherById(String teacherId) throws Exception;
	public Responce updateTeacher(String teacherId, HashMap<String, Object> teacher) throws Exception;
	public Responce deleteTeacher(String teacherId) throws Exception;
	public List<Teacher> getTeachers();
	public List<HashMap<String, Object>> getAllTeacherDetails(String teacherId) throws Exception;
	
	
}

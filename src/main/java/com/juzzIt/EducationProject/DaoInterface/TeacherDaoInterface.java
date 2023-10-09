package com.juzzIt.EducationProject.DaoInterface;

import java.util.HashMap;
import java.util.List;

import com.juzzIt.EducationProject.Entity.Teacher;
import com.juzzIt.EducationProject.Models.Responce;

public interface TeacherDaoInterface {

	public Responce addNewTeacher(HashMap<String, Object> teacher);
	
	public Responce updateTeacher(Teacher teacher);
	
	public List<HashMap<String, Object>> getAllTeacherDetails(String teacherId) throws Exception;
}

package com.juzzIt.EducationProject.DaoInterface;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.juzzIt.EducationProject.Entity.Student;
import com.juzzIt.EducationProject.Models.Responce;

public interface StudentDaoInterface {

	public Student addNewStudent(Student student);
	
	public Responce updateStudent(Student student);
	
	public Responce deleteStudentById(String sudentId);
	
	public List<Map<String, Object>> getAllStudent() throws Exception;
}

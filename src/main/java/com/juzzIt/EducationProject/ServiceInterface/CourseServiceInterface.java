package com.juzzIt.EducationProject.ServiceInterface;

import java.util.HashMap;
import java.util.List;
import org.springframework.stereotype.Service;
import com.juzzIt.EducationProject.Models.Responce;

@Service
public interface CourseServiceInterface {

	public Responce addCourse(HashMap<String, Object> course) throws Exception;
	
	public Responce updateCourse(String courseId,HashMap<String , Object> course) throws Exception;
	
	public List<HashMap<String, Object>>  getAllCourse() throws Exception;
	public List<HashMap<String, Object>> getAllCoursesAndItsTypes() throws Exception;
	public List<HashMap<String, Object>>  getAllTempDeletedCourse() throws Exception;
	
	public Responce deleteCourse(String courseId);
	
}

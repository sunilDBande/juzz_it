package com.juzzIt.EducationProject.DaoInterface;

import java.util.HashMap;
import java.util.List;

import com.juzzIt.EducationProject.Entity.Course;
import com.juzzIt.EducationProject.Models.Responce;

public interface CourseDaoInterface {

	
	public Course addCourse(Course course);
	
	public boolean courseExist(String courseName);
	
	public Responce updateCourse(String courseId,HashMap<String , Object> course) throws Exception;
	
	public Course getCourseById(String courseId);
	
	public List<HashMap<String, Object>> getCourses() throws Exception;
	
	public List<HashMap<String, Object>> getTempDeletedCourses() throws Exception;
	
	public Responce deleteCourse(String courseId);
	
	public List<HashMap<String, Object>>  getCourseDataByCourseTypeId(String CourseTypeId);
}

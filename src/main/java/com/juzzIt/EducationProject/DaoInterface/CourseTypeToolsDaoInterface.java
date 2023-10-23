package com.juzzIt.EducationProject.DaoInterface;

import java.util.List;
import java.util.Map;

import com.juzzIt.EducationProject.Entity.CourseTypeTools;
import com.juzzIt.EducationProject.Models.Responce;

public interface CourseTypeToolsDaoInterface {

	
	public CourseTypeTools addTools(CourseTypeTools tool);
	public Responce deleteTools(String toolId) throws Exception;
	public List<Map<String , Object>> getAllToolsByCourseTypeId(String courseTypeId) throws Exception;
	public CourseTypeTools getCourseTypeToolsById(String toolId);
}

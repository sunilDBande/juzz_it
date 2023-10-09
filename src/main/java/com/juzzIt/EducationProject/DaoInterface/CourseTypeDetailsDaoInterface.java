package com.juzzIt.EducationProject.DaoInterface;

import java.util.HashMap;
import java.util.List;

import com.juzzIt.EducationProject.Entity.CourseTypeDetails;


public interface CourseTypeDetailsDaoInterface {

	
	public CourseTypeDetails addCourseTypeDetails(CourseTypeDetails courseDetails);
	
	public List<HashMap<String, Object>> getCourseTypeDetails(String courseTypeId) throws Exception;
	
	}

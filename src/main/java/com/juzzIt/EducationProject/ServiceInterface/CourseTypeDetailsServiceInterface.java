package com.juzzIt.EducationProject.ServiceInterface;


import java.util.HashMap;
import java.util.List;

import com.juzzIt.EducationProject.Entity.CourseTypeDetails;
import com.juzzIt.EducationProject.Models.Responce;

public interface CourseTypeDetailsServiceInterface {
	public Responce addCourseTypeDetails( String courseTypeId, HashMap<String , Object> courseTypeDetails) throws Exception;
	public List<HashMap<String, Object>> getCourseTypeDetails(String courseTypeId) throws Exception;
	
	public Responce updateCourseDetails(String courseDetailId, HashMap<String , Object> coursetypeDetails) throws Exception;
}

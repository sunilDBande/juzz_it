package com.juzzIt.EducationProject.ServiceInterface;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.juzzIt.EducationProject.Models.Responce;

public interface CourseTypeImageServiceInterface {

	
	public Responce addNewCourseTypeImage( String courseTypeId, HashMap<String, Object> courseTypeImageData);
	public Responce deleteCourseTypeImage( String courseTypeImageId);
	public Responce updateCourseTypeImage( String courseTypeImageId, HashMap<String, Object> courseTypeImageData);
	public List<Map<String, Object>> getCourseTypeImage(String courseTypeId );
}

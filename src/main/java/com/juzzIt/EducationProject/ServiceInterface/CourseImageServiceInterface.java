package com.juzzIt.EducationProject.ServiceInterface;

import java.util.HashMap;
import java.util.List;
import java.util.Map;



import com.juzzIt.EducationProject.Models.Responce;

public interface CourseImageServiceInterface {

	
	public Responce addNewCourseImage(String courseId, HashMap<String, Object> courseImageData);
	public Responce deleteCourseImage( String courseTypeImageId);
	public Responce updateCourseImage( String courseTypeImageId,HashMap<String, Object> courseImageData) ;
	public List<Map<String , Object>> getCoueseImage(String courseId) ;
	
	
}

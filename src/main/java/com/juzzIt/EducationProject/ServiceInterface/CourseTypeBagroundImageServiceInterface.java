package com.juzzIt.EducationProject.ServiceInterface;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


import com.juzzIt.EducationProject.Models.Responce;

public interface CourseTypeBagroundImageServiceInterface {	
	public Responce addNewCourseTypeBagroundImage(String courseTypeId, HashMap<String, Object> courseTypeBagroundImageData) ;
	public Responce deleteCourseTypeBagroundImage( String courseTypeBagroundImage);
	public Responce updateCourseTypeBagroundImage(String courseTypeBagroundImageId, HashMap<String, Object> courseTypeBagroundImageData);
	public List<Map<String, Object>> getCourseTypeBagroundImage(String courseTypeId );
}

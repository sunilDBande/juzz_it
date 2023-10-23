package com.juzzIt.EducationProject.ServiceInterface;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.juzzIt.EducationProject.Models.Responce;

public interface CourseTypeVideoServiceInterface {
	
	public Responce addNewCourseTypeVideo( String courseTypeId, HashMap<String, Object> courseTypeVideoData) ;
	public Responce deleteCourseTypeVideo(String courseTypeVideoId);
	public Responce updateCourseTypeVideo( String courseTypeVideoId, HashMap<String, Object> courseTypeVideoData);
	public List<Map<String, Object>> getCourseTypeVideo(String courseTypeId );
}

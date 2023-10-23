package com.juzzIt.EducationProject.DaoInterface;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.juzzIt.EducationProject.Entity.CourseType;
import com.juzzIt.EducationProject.Entity.CourseTypeVideo;
import com.juzzIt.EducationProject.Models.Responce;

public interface CourseTypeVideoDaoInterface {

	

	public CourseTypeVideo addNewCourseTypeVideo( CourseTypeVideo courseTypeVideo) ;
	public CourseTypeVideo getCourseTypeVideoById(String courseTypeVideoId);
	public Responce deleteCourseTypeVideo(String courseTypeVideoId);
	public Responce updateCourseTypeVideo( String courseTypeVideoId, HashMap<String, Object> courseTypeVideoData);
	public List<Map<String, Object>> getCourseTypeVideo(CourseType courseType );

}

package com.juzzIt.EducationProject.DaoInterface;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.juzzIt.EducationProject.Entity.Course;
import com.juzzIt.EducationProject.Entity.CourseImage;
import com.juzzIt.EducationProject.Models.Responce;

public interface CourseImageDaoInterface {

	
	
	public CourseImage addNewCourseImage( CourseImage courseImage);
	public Responce deleteCourseImage( String courseTypeImageId);
	public Responce updateCourseImage( String courseTypeImageId,HashMap<String, Object> courseImageData) ;
	public List<Map<String , Object>> getCoueseImage(Course course) ;
	public CourseImage getCourseImageById(String courseImageId);
}

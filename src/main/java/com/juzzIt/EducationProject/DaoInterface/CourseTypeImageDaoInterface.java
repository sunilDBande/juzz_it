package com.juzzIt.EducationProject.DaoInterface;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.juzzIt.EducationProject.Entity.CourseType;
import com.juzzIt.EducationProject.Entity.CourseTypeImage;
import com.juzzIt.EducationProject.Models.Responce;

public interface CourseTypeImageDaoInterface {

	
	
	public CourseTypeImage addNewCourseTypeImage(CourseTypeImage CourseTypeImage);
	public Responce deleteCourseTypeImage(String courseTypeImageId);
	public CourseTypeImage getCourseTypeImageById(String courseTypeImageId);
	public Responce updateCourseTypeImage(String courseTypeImageId,HashMap<String, Object> courseTypeImageData);
	public List<Map<String, Object>> getAllCourseTypeImage(CourseType courseType);	
	
}

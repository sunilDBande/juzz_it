package com.juzzIt.EducationProject.DaoInterface;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.juzzIt.EducationProject.Entity.CourseType;
import com.juzzIt.EducationProject.Entity.CourseTypeBagroundImage;
import com.juzzIt.EducationProject.Models.Responce;

public interface CourseTypeBagroundImageDaoInterface {

	
	public CourseTypeBagroundImage addNewCourseTypeBagroundImage(CourseTypeBagroundImage CourseTypeBagroundImage) ;
	public CourseTypeBagroundImage getCourseTypeBagroundImageById(String CourseTypeBagroundImageId);
	public Responce deleteCourseTypeBagroundImage( String courseTypeBagroundImage);
	public Responce updateCourseTypeBagroundImage(String courseTypeBagroundImageId, HashMap<String, Object> courseTypeBagroundImageData);
	public List<Map<String, Object>> getCourseTypeBagroundImage(CourseType courseType );
}

package com.juzzIt.EducationProject.DaoInterface;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.juzzIt.EducationProject.Entity.BatchCourse;
import com.juzzIt.EducationProject.Entity.CourseType;
import com.juzzIt.EducationProject.Models.Responce;

public interface CourseTypeDaoInterface {

	
	
	public boolean IsExists(String courseTypeId);
	
	public CourseType addCourseType(CourseType courseType);
	
	public List<Map<String, Object>>  getCourseTypeByCourseId(String courseId);
	
	public CourseType getCourseTypeById(String courseTypeId);
	public Responce updateCourseType(String courseTypeId,HashMap<String, Object> courseType) throws Exception;
	
	public Responce deleteCourseTypeById(String courseTypeId) throws Exception;
	
	public List<HashMap<String, Object>> CourseTypeByBatchCourse(BatchCourse batchCourse);
	
	public List<HashMap<String, Object>> getAllCourseTypeDetailsById(String courseTypeId);
}

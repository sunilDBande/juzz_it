package com.juzzIt.EducationProject.ServiceInterface;

import java.util.HashMap;
import java.util.List;
import com.juzzIt.EducationProject.Models.Responce;

public interface CourseTypeServicesInterface {

	public Responce addCourseType(String courseId,HashMap<String , Object>courseType) throws Exception;
	public List<HashMap<String, Object>>  getAllCourseByCourseId( String courseId) throws Exception;
	public Responce updateCourseType(String courseTypeId,HashMap<String, Object> courseType) throws Exception;
	public Responce deleteCourseType( String courseTypeId ) throws Exception;
	public List<HashMap<String , Object>> getAllCourseTypeDetailsById( String courseTypeId) throws Exception;
}

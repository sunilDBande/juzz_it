package com.juzzIt.EducationProject.ServiceInterface;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


import com.juzzIt.EducationProject.Models.Responce;

/**
 * 
 */
public interface BatchCourseServiceInterface {
	public Responce addBatchCourse( String subBatchId, String courseTypeId ,HashMap<String ,Object> batchCourse) throws Exception;
	public Responce deleteBatchCourse( String batchCourseId) throws Exception ;
	public List<Map<String , Object>>  getAllBatchCourses( String subBatchId) throws Exception;
	public Responce updateBatchCourseDetaile( String batchCourseId ,HashMap<String, Object> batchCourseData  ) throws Exception ;
}

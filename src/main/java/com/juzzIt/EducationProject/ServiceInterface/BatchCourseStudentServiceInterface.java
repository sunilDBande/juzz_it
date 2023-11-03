package com.juzzIt.EducationProject.ServiceInterface;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.juzzIt.EducationProject.Models.Responce;

public interface BatchCourseStudentServiceInterface {

	public Responce addStudentToBatch( String batchCourseId,String studentId,String requestId, HashMap<String , Object> details) throws Exception;
	public Responce deleteBatchCourseStudentById(String batchCourseStudentId) throws Exception;
	public List<Map<String, Object>>  getAllstudentsByBatchCourse(String batchCourseId);
	public Responce updateBatchCourseStudentDetailes( String batchCourseStudentId, HashMap<String, Object> batchCourseStudentDetails) throws Exception;
	public List<HashMap<String, Object>>   getAllStudentEnrollBatchsByStudentId( String studentId);
}

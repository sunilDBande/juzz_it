package com.juzzIt.EducationProject.DaoInterface;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.juzzIt.EducationProject.Entity.BatchCourseStudent;
import com.juzzIt.EducationProject.Models.Responce;

public interface BatchCourseStudentDaoInterface {

	
	public BatchCourseStudent addStodentTobatch(BatchCourseStudent student);
	public Responce deleteStudent(String StudentBatchId) throws Exception;
	public List<Map<String, Object>> getAllStudent(String batchCourseId);
	public List<Map<String, Object>> getStudentDetailsByStudentBatchId(String StudentBatchId) throws Exception;
	public Responce updateBatchCourseStudentDetailes(String batchCourseStudentId,	HashMap<String, Object> batchCourseStudentDetails) throws Exception ;
	public List<HashMap<String, Object>> getAllStudentEnrollBatchsByStudentId(String studentId);
}

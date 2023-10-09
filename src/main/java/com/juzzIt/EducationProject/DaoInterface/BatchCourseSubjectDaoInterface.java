package com.juzzIt.EducationProject.DaoInterface;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.juzzIt.EducationProject.Entity.BatchCourseSubject;
import com.juzzIt.EducationProject.Models.Responce;

public interface BatchCourseSubjectDaoInterface {

	
	public BatchCourseSubject addSubjectToBatch(BatchCourseSubject batchCourseSubject);
	public BatchCourseSubject getbatchCourseSubjectById(String batchCourseSubjectId);
	public Responce deleteSubjectFromBatch(String subjectId) throws Exception;
	public List<Map<String, Object>> getAllSubjectByBatchCourseId(String batchCourseId) throws Exception;
	public Responce updateBatchCourseSubject(String batchCourseSubjectId, HashMap<String, Object> batchCourseSubjectDetails) throws Exception ;
}

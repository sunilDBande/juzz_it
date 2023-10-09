package com.juzzIt.EducationProject.ServiceInterface;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.juzzIt.EducationProject.Models.Responce;

public interface BatchCourseSubjectServiceInterface {

	
	public Responce addSubjectToBatch(String batchCourseId,String teacherId,HashMap<String, Object> subjctDetails) throws Exception ;

	public Responce deleteBatchCourseSubject(String batchCourseSubjectId) throws Exception;
	public List<Map<String , Object>> getAllSubjectByBatchCourseId(String batchCourseId) throws Exception;
	public Responce updateBatchCourseSubject(String batchCourseSubjectId, HashMap<String, Object> batchCourseSubjectDetails) throws Exception ;
}

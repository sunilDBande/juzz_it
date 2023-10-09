package com.juzzIt.EducationProject.ServiceInterface;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.PathVariable;

import com.juzzIt.EducationProject.Models.Responce;

public interface BatchCourseClassLinksServiceServicesInterface {

	public Responce addnewClassLink(String batchCourseId, String batchCourseSubjectId, HashMap<String, Object> classLinkData) throws Exception;
	 public Responce deleteClassLink(String classId) throws Exception;
	 public List<Map<String, Object>> getAllClassLinksByBatchCourseId(String batchCourseId) throws Exception;
	 public List<Map<String , Object>> getAllClassLinksByBatchCourseIdAndBatchCoourseSubjectId(String batchCourseId,String batchCourseSubjectId) throws Exception;
}

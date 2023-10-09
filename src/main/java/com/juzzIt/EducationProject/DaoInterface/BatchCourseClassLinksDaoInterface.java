package com.juzzIt.EducationProject.DaoInterface;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.PathVariable;

import com.juzzIt.EducationProject.Entity.BatchCourse;
import com.juzzIt.EducationProject.Entity.BatchCourseClassLinks;
import com.juzzIt.EducationProject.Entity.BatchCourseSubject;
import com.juzzIt.EducationProject.Models.Responce;

public interface BatchCourseClassLinksDaoInterface {

	
	public BatchCourseClassLinks addNewClassLink(BatchCourseClassLinks batchCourseClassLinks);
	 public Responce deleteClassLink(String classId) throws Exception;
	 public List<Map<String, Object>> getAllClassLinksByBatchCourseId(BatchCourse batchCourseId) throws Exception;
	 public List<Map<String , Object>> getAllClassLinksByBatchCourseIdAndBatchCoourseSubjectId(BatchCourse batchCourse,BatchCourseSubject batchCourseSubject) throws Exception;

}

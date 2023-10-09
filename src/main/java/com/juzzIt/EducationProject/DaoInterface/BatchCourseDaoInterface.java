package com.juzzIt.EducationProject.DaoInterface;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.juzzIt.EducationProject.Entity.BatchCourse;
import com.juzzIt.EducationProject.Entity.SubBatch;
import com.juzzIt.EducationProject.Models.Responce;

public interface BatchCourseDaoInterface {

	
	public BatchCourse addBatchCourse(BatchCourse batchCourse);
	public BatchCourse getBatchCourseById(String batchCourseId);
	public Responce deleteBatchCourse(String batchCourseId) throws Exception;
	public List<Map<String, Object>>  getAllBatchCourseBySubBatchId(String subBatchId) throws Exception;
	public boolean existsByBatchCourseNameAndSubBatch(String batchCourseName,SubBatch subBatch);
	public Responce updateBatchCourseDetaile( String batchCourseId ,HashMap<String, Object> batchCourseData  ) throws Exception ;
}

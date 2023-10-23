package com.juzzIt.EducationProject.DaoInterface;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.juzzIt.EducationProject.Entity.BatchCourse;
import com.juzzIt.EducationProject.Entity.BatchCoursePlacements;
import com.juzzIt.EducationProject.Models.Responce;

public interface BatchCoursePlacementsDaoInterface {

	
	public BatchCoursePlacements addNewPlacement(BatchCoursePlacements batchCoursePlacements);

	public  Responce deleteBatchCoursePlacement( String placementId) throws Exception ;
	
	public BatchCoursePlacements getBatchCoursePlacementsById(String batchCoursePlacementsId);
	public List<Map<String, Object>> getAllPlacementByBatchCourseId(BatchCourse batchCourse) throws Exception;
	public  Responce updateBatchCoursePlacement(String placementId,HashMap<String, Object> placementData) throws Exception;
}

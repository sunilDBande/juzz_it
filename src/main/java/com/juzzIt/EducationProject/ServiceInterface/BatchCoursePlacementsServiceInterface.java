package com.juzzIt.EducationProject.ServiceInterface;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.PathVariable;

import com.juzzIt.EducationProject.Models.Responce;

public interface BatchCoursePlacementsServiceInterface {
	public Responce addNewPlacement( String batchCourseId,HashMap<String , Object>placementData) throws Exception;
	public  Responce deleteBatchCoursePlacement( String placementId) throws Exception ;
	public List<Map<String , Object>> getAllPlacementByBatchCourseId(String batchCourseId) throws Exception;
	public  Responce updateBatchCoursePlacement(String placementId,HashMap<String, Object> placementData) throws Exception;	
	
}

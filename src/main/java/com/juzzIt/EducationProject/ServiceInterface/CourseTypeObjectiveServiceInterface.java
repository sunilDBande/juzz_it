package com.juzzIt.EducationProject.ServiceInterface;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.juzzIt.EducationProject.Models.Responce;

public interface CourseTypeObjectiveServiceInterface {


	public Responce addObjective(String courseTypeId, HashMap<String , Object> objective) throws Exception; 
	
	public Responce deleteObjective(String objectiveId) throws Exception;
	public List<Map<String , Object>> getAllObjective(String courseTypeId) throws Exception;
}

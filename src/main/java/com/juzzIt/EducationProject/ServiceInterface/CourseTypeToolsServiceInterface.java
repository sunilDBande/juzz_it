package com.juzzIt.EducationProject.ServiceInterface;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.juzzIt.EducationProject.Models.Responce;

public interface CourseTypeToolsServiceInterface {

	public Responce addTools(String courseTypeId, HashMap<String , Object> tool) throws Exception;
	public Responce deleteTools(String toolId) throws Exception;
	public List<Map<String , Object>> getAllTools(String courseTypeId) throws Exception;
}

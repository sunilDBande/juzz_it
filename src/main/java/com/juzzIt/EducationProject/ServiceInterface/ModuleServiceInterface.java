package com.juzzIt.EducationProject.ServiceInterface;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.juzzIt.EducationProject.Models.Responce;

public interface ModuleServiceInterface {

	
	public Responce addModels(String courseTypeId, HashMap<String , Object> model) throws Exception;
	public Responce deleteModels (String modelId) throws Exception;
	public List<Map<String , Object>> getAllModels(String courseTypeId) throws Exception;
	
	public Responce updateModule(String moduleId,HashMap<String, Object> module) throws Exception;
}

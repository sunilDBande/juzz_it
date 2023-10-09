package com.juzzIt.EducationProject.DaoInterface;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.juzzIt.EducationProject.Entity.Module;
import com.juzzIt.EducationProject.Models.Responce;

public interface ModuleDaoInterface {

	public Module addModels(Module model);
	public Responce deleteModels( String modelId) throws Exception;
	public List<Map<String , Object>> getAllModels(String courseTypeId) throws Exception;
	public Module getModuleByModuleId(String moduleId);
	public Responce updateModule(String moduleId,HashMap<String, Object> module) throws Exception;
}

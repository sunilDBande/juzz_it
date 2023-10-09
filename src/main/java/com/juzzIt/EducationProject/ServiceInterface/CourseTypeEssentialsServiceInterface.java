package com.juzzIt.EducationProject.ServiceInterface;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.juzzIt.EducationProject.Models.Responce;

public interface CourseTypeEssentialsServiceInterface {

	public Responce addEssential(String courseTypeId, HashMap<String , Object> essential) throws Exception;
	public Responce deleteEssential( String essentialId) ;
	public List<Map<String , Object>> getAllEssentials(String courseTypeId) throws Exception;
}

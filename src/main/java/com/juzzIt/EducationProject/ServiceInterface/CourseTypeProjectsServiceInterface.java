package com.juzzIt.EducationProject.ServiceInterface;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.juzzIt.EducationProject.Models.Responce;

public interface CourseTypeProjectsServiceInterface {
	public Responce addProjects(String courseTypeId, HashMap<String , Object> project) throws Exception ;
	public Responce deleteProjects( String projectsId) throws Exception;
	public List<Map<String , Object>> getAllProjects(String courseTypeId) throws Exception;
}

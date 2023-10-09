package com.juzzIt.EducationProject.DaoInterface;

import java.util.List;
import java.util.Map;

import com.juzzIt.EducationProject.Entity.CourseTypeProjects;
import com.juzzIt.EducationProject.Models.Responce;

public interface CourseTypeProjectsDaoInterface {

	public CourseTypeProjects addProjects(CourseTypeProjects project);
	public Responce deleteProjects( String projectsId) throws Exception;
	public List<Map<String , Object>> getAllProjectsByCourseTypeId(String courseTypeId) throws Exception;
}

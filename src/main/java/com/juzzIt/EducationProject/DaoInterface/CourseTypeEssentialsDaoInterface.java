package com.juzzIt.EducationProject.DaoInterface;

import java.util.List;
import java.util.Map;
import com.juzzIt.EducationProject.Entity.CourseTypeEssentials;
import com.juzzIt.EducationProject.Models.Responce;

public interface CourseTypeEssentialsDaoInterface {

	
	public CourseTypeEssentials addEssential(CourseTypeEssentials essential);
	public Responce deleteEssential(String essentialId);
	public List<Map<String , Object>> getAllEssentialsByCourseTypeId(String courseTypeId) throws Exception;

}

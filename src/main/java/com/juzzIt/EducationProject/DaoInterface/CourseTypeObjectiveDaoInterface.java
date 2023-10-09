package com.juzzIt.EducationProject.DaoInterface;

import java.util.List;
import java.util.Map;
import com.juzzIt.EducationProject.Entity.CourseTypeObjective;
import com.juzzIt.EducationProject.Models.Responce;

public interface CourseTypeObjectiveDaoInterface {

	
	public CourseTypeObjective addObjective(CourseTypeObjective objective);
	public Responce deleteObjective(String objectiveId) throws Exception;
	public List<Map<String , Object>> getAllObjectiveByCourseTypeId(String courseTypeId) throws Exception;
}

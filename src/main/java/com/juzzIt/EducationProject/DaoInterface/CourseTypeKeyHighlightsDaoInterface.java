package com.juzzIt.EducationProject.DaoInterface;

import java.util.List;
import java.util.Map;



import com.juzzIt.EducationProject.Entity.CourseTypeKeyHighlights;
import com.juzzIt.EducationProject.Models.Responce;

public interface CourseTypeKeyHighlightsDaoInterface {

	
	public CourseTypeKeyHighlights addKeyHighlight(CourseTypeKeyHighlights KeyHighlight);
	public Responce deleteKeyHighlight(String keyHighlightId) throws Exception ;
	public List<Map<String , Object>> getAllKeyHighlightByCourseTypeId(String courseTypeId) throws Exception;
}

package com.juzzIt.EducationProject.ServiceInterface;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.juzzIt.EducationProject.Models.Responce;

public interface CourseTypeKeyHighlightsServiceInterface {

	public Responce addKeyHighlight(String courseTypeId,HashMap<String , Object> KeyHighlight) throws Exception; 
	
	public Responce deleteKeyHighlight(String keyHighlightId) throws Exception ;
	public List<Map<String , Object>> getAllKeyHighlight(String courseTypeId) throws Exception;
	
}


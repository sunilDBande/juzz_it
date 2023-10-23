package com.juzzIt.EducationProject.ServiceInterface;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.juzzIt.EducationProject.Models.Responce;
public interface ToolImageServiceInterface {

	public Responce addNewToolImage( String toolId, HashMap<String, Object> toolImageData);
	public Responce deleteToolImage( String toolImageId) ;
	public Responce updateToolImage( String toolImageId, HashMap<String, Object> toolImageData);
	public List<Map<String, Object>> getToolImage(String toolId );

}

package com.juzzIt.EducationProject.DaoInterface;

import java.util.HashMap;
import java.util.List;
import java.util.Map;



import com.juzzIt.EducationProject.Entity.CourseTypeTools;
import com.juzzIt.EducationProject.Entity.ToolImage;
import com.juzzIt.EducationProject.Models.Responce;

public interface ToolImageDaoInterface {

	
	public ToolImage addNewToolImage( ToolImage toolImage);
	public ToolImage getToolImageById(String toolImageId);
	public Responce deleteToolImage( String toolImageId) ;
	public Responce updateToolImage( String toolImageId, HashMap<String, Object> toolImageData);
	public List<Map<String, Object>> getToolImage( CourseTypeTools courseTypeTool );
}

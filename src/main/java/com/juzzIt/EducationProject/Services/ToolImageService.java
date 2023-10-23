package com.juzzIt.EducationProject.Services;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.juzzIt.EducationProject.DaoInterface.CourseTypeToolsDaoInterface;
import com.juzzIt.EducationProject.DaoInterface.ToolImageDaoInterface;
import com.juzzIt.EducationProject.Entity.CourseTypeTools;
import com.juzzIt.EducationProject.Entity.ToolImage;
import com.juzzIt.EducationProject.Models.Responce;
import com.juzzIt.EducationProject.ServiceInterface.ToolImageServiceInterface;

@Service
public class ToolImageService  implements ToolImageServiceInterface{

	@Autowired
	private ToolImageDaoInterface toolImageDaoInterface;
	
	@Autowired
	private CourseTypeToolsDaoInterface courseTypeToolsDaoInterface;

	@Override
	public Responce addNewToolImage(String toolId, HashMap<String, Object> toolImageData) {
	Responce responce=new Responce();
	
	if(toolImageData.get("image_URL")==null) {
		responce.setMassege("image is requied");
		responce.setStatus(false);
		return responce;
	}
	
	CourseTypeTools courseTypeTool = courseTypeToolsDaoInterface.getCourseTypeToolsById(toolId);
	
	
	if(courseTypeTool==null) {
		responce.setMassege("tool with the given id nort found");
		responce.setStatus(false);
		return responce;
	}
	
	ToolImage toolImage=new ToolImage();
	
	toolImage.setCourseTypeTools(courseTypeTool);
	toolImage.setImageURL(toolImageData.get("image_URL").toString());
	toolImage.setCreatedDate(LocalDateTime.now());
	toolImage.setToolImageId(toolImageData.get("id").toString());
	toolImage.setActiveStatus("D");
	
	ToolImage addedToolImage = toolImageDaoInterface.addNewToolImage(toolImage);
	
	if(addedToolImage==null) {
		responce.setMassege("failed to add the image");
		responce.setStatus(false);
		return responce;
	}

	responce.setMassege("image added successfully");
	responce.setStatus(true);
		return responce;
	}

	@Override
	public Responce deleteToolImage(String toolImageId) {
		// TODO Auto-generated method stub
		return toolImageDaoInterface.deleteToolImage(toolImageId);
	}

	@Override
	public Responce updateToolImage(String toolImageId, HashMap<String, Object> toolImageData) {
		// TODO Auto-generated method stub
		return toolImageDaoInterface.updateToolImage(toolImageId, toolImageData);
	}

	@Override
	public List<Map<String, Object>> getToolImage(String toolId) {
		CourseTypeTools courseTypeTool = courseTypeToolsDaoInterface.getCourseTypeToolsById(toolId);
		if(courseTypeTool==null) {
			return new ArrayList<Map<String,Object>>();
		}
		return toolImageDaoInterface.getToolImage(courseTypeTool);
	}
	
}

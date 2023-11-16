package com.juzzIt.EducationProject.ServiceInterface;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.juzzIt.EducationProject.Models.Responce;

public interface ModuleImagesServiceInterface {
    public Responce addModuleImage(String moduleId,HashMap<String, Object> imageData) ;
	public Responce deleteModuleImage(String imageId) ;
	public Responce updateModuleImageData(String imageId,HashMap<String, Object> imageData ) ;
	public List<Map<String, Object>> getModuleImageByModuleId(String moduleId);
}

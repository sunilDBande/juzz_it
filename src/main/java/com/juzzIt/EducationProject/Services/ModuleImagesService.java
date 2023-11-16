package com.juzzIt.EducationProject.Services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.juzzIt.EducationProject.DaoInterface.ModuleDaoInterface;
import com.juzzIt.EducationProject.DaoInterface.ModuleImagesDaoInterface;
import com.juzzIt.EducationProject.Entity.Module;
import com.juzzIt.EducationProject.Entity.ModuleImages;
import com.juzzIt.EducationProject.Models.Responce;
import com.juzzIt.EducationProject.ServiceInterface.ModuleImagesServiceInterface;


@Service
public class ModuleImagesService implements ModuleImagesServiceInterface {

	@Autowired 
	private  ModuleImagesDaoInterface moduleImagesDaoInterface;
	
	@Autowired
	private ModuleDaoInterface moduleDaoInterface;
	
	@Override
	public Responce addModuleImage(String moduleId, HashMap<String, Object> imageData) {
		
		Responce responce=new Responce();
		
		if(imageData.get("image_URL")==null) {
		responce.setMassege("image is requided");
		responce.setStatus(false);
		return responce;
		}
		
		Module module = moduleDaoInterface.getModuleByModuleId(moduleId);

		if(module==null) {
		responce.setMassege("module with given id not found");
		responce.setStatus(false);
		return responce;
		}
		
		
		UUID id=UUID.randomUUID();
		
		
		ModuleImages image=new ModuleImages();
		
		image.setActiveStatus("D");
		image.setCreatedDate(LocalDateTime.now());
		image.setImageURL(imageData.get("image_URL").toString());
		image.setModule(module);
		image.setModuleImageId(id.toString());
		ModuleImages moduleImages = moduleImagesDaoInterface.addModuleImages(image);
		
		
		if(moduleImages==null) {
			responce.setMassege("failed tom add image");
			responce.setStatus(false);
			return responce;
		}
		responce.setMassege("image added successfully");
		responce.setStatus(true);
		return responce;
	}
	@Override
	public Responce deleteModuleImage(String imageId) {
		return moduleImagesDaoInterface.deleteModuleImages(imageId);
	}
	@Override
	public Responce updateModuleImageData(String imageId, HashMap<String, Object> imageData) {
		return moduleImagesDaoInterface.updateModuleImages(imageId, imageData);
	}
	@Override
	public List<Map<String, Object>> getModuleImageByModuleId(String moduleId) {
		Module module = moduleDaoInterface.getModuleByModuleId(moduleId);
		if(module==null) {
		return new ArrayList<Map<String,Object>>();
		}
		return moduleImagesDaoInterface.getAllImageByModule(module);
	}

}

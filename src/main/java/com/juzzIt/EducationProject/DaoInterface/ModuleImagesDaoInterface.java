package com.juzzIt.EducationProject.DaoInterface;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.juzzIt.EducationProject.Entity.Module;
import com.juzzIt.EducationProject.Entity.ModuleImages;
import com.juzzIt.EducationProject.Models.Responce;

public interface ModuleImagesDaoInterface {
	
	public ModuleImages addModuleImages(ModuleImages moduleImages);
	public ModuleImages getModuleImagesById(String moduleImagesId);
	public Responce  deleteModuleImages(String imageId);
	public Responce updateModuleImages(String imageId,HashMap<String, Object> imageData);
	public List<Map<String, Object>>  getAllImageByModule(Module module);

}

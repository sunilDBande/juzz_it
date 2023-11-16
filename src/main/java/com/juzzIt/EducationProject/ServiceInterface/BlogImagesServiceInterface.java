package com.juzzIt.EducationProject.ServiceInterface;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.juzzIt.EducationProject.Models.Responce;

public interface BlogImagesServiceInterface {

	public Responce addBlogImage(String blogId, HashMap<String, Object> imageData) ;
	
	public Responce deleteBlogImage(String imageId) ;
	
	public Responce updateBlogImageData(String blogId, HashMap<String, Object> imageData) ;
	public List<Map<String, Object>> getBlogImages(String blogId);
}

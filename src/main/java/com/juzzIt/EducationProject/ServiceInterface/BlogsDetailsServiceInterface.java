package com.juzzIt.EducationProject.ServiceInterface;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.juzzIt.EducationProject.Models.Responce;

public interface BlogsDetailsServiceInterface {

	
	 public Responce addNewBlogDetails(String blogId,HashMap<String, Object> blogDetailsData);
     public Responce deleteBlogDetails(String blogDetailsId) ;
     public Responce updateBlogDetails(String blogId,HashMap<String, Object> blogDetailsData) ;
     public List<Map<String, Object>> getAllBlogDetails(String blogId);

}

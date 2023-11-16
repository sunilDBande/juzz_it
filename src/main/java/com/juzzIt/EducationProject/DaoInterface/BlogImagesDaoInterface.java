package com.juzzIt.EducationProject.DaoInterface;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.juzzIt.EducationProject.Entity.BlogImages;
import com.juzzIt.EducationProject.Entity.Blogs;
import com.juzzIt.EducationProject.Models.Responce;

public interface BlogImagesDaoInterface {
public BlogImages addBlogImages(BlogImages blogImages);
public BlogImages getBlogImagesById(String imageId);
public Responce deleteBlogImages(String imageId);
public Responce updateBlogImages(String imageId,HashMap<String, Object> imageData);
public List<Map<String, Object>> getAllBlogImages( Blogs blogs);
	
	
}

package com.juzzIt.EducationProject.DaoInterface;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.juzzIt.EducationProject.Entity.Blogs;
import com.juzzIt.EducationProject.Entity.BlogsDetailsBadroundImage;
import com.juzzIt.EducationProject.Models.Responce;

public interface BlogsDetailsBadroundImageDaoInterface {

	
	public BlogsDetailsBadroundImage addBlogsDetailsBadroundImage(BlogsDetailsBadroundImage blogsDetailsBadroundImage);
	public BlogsDetailsBadroundImage getBlogsDetailsBadroundImageById(String imageId);
	public Responce updateBlogsDetailsBadroundImage(String imageId,HashMap<String, Object> imageData);
	public Responce deleteBlogsDetailsBadroundImage(String imageId);
	public List<Map<String, Object>> getBlogsDetailsBadroundImage(Blogs blogs); 
}

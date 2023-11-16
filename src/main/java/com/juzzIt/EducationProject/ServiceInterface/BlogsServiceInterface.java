package com.juzzIt.EducationProject.ServiceInterface;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.PathVariable;

import com.juzzIt.EducationProject.Models.Responce;

public interface BlogsServiceInterface {
	public Responce addNewBlogs(HashMap<String, Object> blogsData) ;
	public Responce deleteBlog(String  blogId) ;
	public Responce updateBlog(String blogId,HashMap<String, Object> blogData) ;
	public List<Map<String, Object>> getAllBlogs();
	public List<Map<String, Object>> getAllBlogsWithImages();
	public List<Map<String, Object>> getAllBlogWithDetails(String blogId);
}

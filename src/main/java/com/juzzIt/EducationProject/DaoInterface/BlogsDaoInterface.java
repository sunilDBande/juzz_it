package com.juzzIt.EducationProject.DaoInterface;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.juzzIt.EducationProject.Entity.Blogs;
import com.juzzIt.EducationProject.Models.Responce;

public interface BlogsDaoInterface {
	public Blogs addNewBloge(Blogs blogs);
	public Blogs getBlogsById(String blogId);
	public Responce deleteBlogeById(String blogId);
	public Responce updateBlogs(String blogId,HashMap<String, Object> blogData);
	public List<Map<String, Object>> getAllBloges();
}

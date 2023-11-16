package com.juzzIt.EducationProject.DaoInterface;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.juzzIt.EducationProject.Entity.Blogs;
import com.juzzIt.EducationProject.Entity.BlogsDetails;
import com.juzzIt.EducationProject.Models.Responce;

public interface BlogsDetailsDaoInterface {
	public BlogsDetails addBlogDetails(BlogsDetails blogsDetails);
	public BlogsDetails getBlogsDetailsById(String blogsDetailsId);
	public Responce deleteBlogsDetailsById(String blogsDetailsId);
	public Responce updateBlogsDetails(String BlogsDetailsId,HashMap<String, Object> blogsDetailsId);
	public List<Map<String, Object>> getBlogsDetails(Blogs blogs);

}

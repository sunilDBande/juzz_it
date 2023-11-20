package com.juzzIt.EducationProject.Services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.juzzIt.EducationProject.DaoInterface.BlogsDaoInterface;
import com.juzzIt.EducationProject.Entity.Blogs;
import com.juzzIt.EducationProject.Models.Responce;
import com.juzzIt.EducationProject.ServiceInterface.BlogImagesServiceInterface;
import com.juzzIt.EducationProject.ServiceInterface.BlogsDetailsServiceInterface;
import com.juzzIt.EducationProject.ServiceInterface.BlogsServiceInterface;


@Service
public class BlogsService implements BlogsServiceInterface {
	@Autowired
	private BlogsDaoInterface blogsDaoInterface;
	
	@Autowired
	private BlogImagesServiceInterface blogImagesServiceInterface;
	
	@Autowired
	private BlogsDetailsServiceInterface blogsDetailsServiceInterface;

	@Override
	public Responce addNewBlogs( HashMap<String, Object> blogsData) {
		Responce responce=new Responce();
		if(blogsData.get("blog_Heading")==null||blogsData.get("blog_Intruduction")==null||blogsData.get("blog_Desc")==null) {
			responce.setMassege("blog heading , blog intruduction ,blog desc");
			responce.setStatus(false);
			return responce;
		}
		UUID id=UUID.randomUUID();
		Blogs blogs=new Blogs();
		blogs.setActiveStatus("D");
		blogs.setBlogDesc(blogsData.get("blog_Desc").toString());
        blogs.setBlogIntruduction(blogsData.get("blog_Intruduction").toString());
        blogs.setBlogHeading(blogsData.get("blog_Heading").toString());
        blogs.setBlogId(id.toString());
        blogs.setBlogOrder(0);
        Blogs addedBloge = blogsDaoInterface.addNewBloge(blogs);
         if(addedBloge==null) {
             	responce.setMassege("failed to add the blog");
	            responce.setStatus(false);
	            return responce;
             }
          responce.setMassege("blog added successfully");
          responce.setStatus(true);
		   return  responce;
	      }

	@Override
	public Responce deleteBlog(String blogId) {
		return blogsDaoInterface.deleteBlogeById(blogId);
	}

	@Override
	public Responce updateBlog(String blogId, HashMap<String, Object> blogData) {
			return blogsDaoInterface.updateBlogs(blogId, blogData);
	}

	@Override
	public List<Map<String, Object>> getAllBlogs() {
		return blogsDaoInterface.getAllBloges();
	}

	@Override
	public List<Map<String, Object>> getAllBlogsWithImages() {
		List<Map<String, Object>> allBloges = blogsDaoInterface.getAllBloges();
		return	allBloges.stream().map(result->{
		result.put("blog_Image",blogImagesServiceInterface.getBlogImages(result.get("BLOG_ID").toString()));
			return result;
		}).collect(Collectors.toList());
		
		 
	}

	@Override
	public List<Map<String, Object>> getAllBlogWithDetails(String blogId) {
		List<Map<String, Object>> allBloges = new ArrayList<Map<String,Object>>();
		Blogs blogs = blogsDaoInterface.getBlogsById(blogId);
		if(blogs==null) {
			return allBloges;
		}
		HashMap<String, Object> map=new HashMap<String,Object>();
		map.put("blog_Id", blogs.getBlogId());
		map.put("blog_heading", blogs.getBlogHeading());
		map.put("blog_Intruduction", blogs.getBlogIntruduction());
		map.put("blog_desc", blogs.getBlogDesc());
		map.put("blog_Image",blogImagesServiceInterface.getBlogImages(blogs.getBlogId()));
		map.put("blog_Details",blogsDetailsServiceInterface.getAllBlogDetails(blogs.getBlogId()));
			allBloges.add(map);
			return allBloges;
		
	}

}

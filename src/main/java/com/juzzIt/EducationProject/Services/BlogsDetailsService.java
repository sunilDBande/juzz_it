package com.juzzIt.EducationProject.Services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.juzzIt.EducationProject.DaoInterface.BlogsDaoInterface;
import com.juzzIt.EducationProject.DaoInterface.BlogsDetailsDaoInterface;
import com.juzzIt.EducationProject.Entity.Blogs;
import com.juzzIt.EducationProject.Entity.BlogsDetails;
import com.juzzIt.EducationProject.Models.Responce;
import com.juzzIt.EducationProject.ServiceInterface.BlogsDetailsServiceInterface;

@Service
public class BlogsDetailsService implements BlogsDetailsServiceInterface {
	
	@Autowired
	private BlogsDetailsDaoInterface blogsDetailsDaoInterface;
	
	@Autowired 
	private BlogsDaoInterface blogsDaoInterface;

	@Override
	public Responce addNewBlogDetails(String blogId, HashMap<String, Object> blogDetailsData) {
	Responce responce=new Responce();
	if(blogDetailsData.get("blogDetails_Heading")==null||blogDetailsData.get("blogDetaild_Desc")==null) {
	responce.setMassege("");
	responce.setStatus(false);
	return responce;
	}
	
	Blogs blogs = blogsDaoInterface.getBlogsById(blogId);
	
	
	if(blogs==null) {
		responce.setMassege("");
		responce.setStatus(false);
		return responce;
	}
	
	BlogsDetails blogsDetails=new BlogsDetails();
	UUID id=UUID.randomUUID();
	
	blogsDetails.setActiveStatus("D");
	blogsDetails.setBlogDetaildDesc(blogDetailsData.get("blogDetaild_Desc").toString());
	blogsDetails.setBlogDetailsHeading(blogDetailsData.get("blogDetails_Heading").toString());
	blogsDetails.setBlogDetailsId(id.toString());
	blogsDetails.setBlogs(blogs);
	blogsDetails.setBlogDetailsOrder(0);
	
	
	BlogsDetails addBlogDetails = blogsDetailsDaoInterface.addBlogDetails(blogsDetails);
	
	if(addBlogDetails==null) {
		responce.setMassege("");
		responce.setStatus(false);
return responce;
	}
	
	responce.setMassege("blogdetails added successfuly");
	responce.setStatus(true);
		return responce;
	}

	@Override
	public Responce deleteBlogDetails(String blogDetailsId) {
		// TODO Auto-generated method stub
		return blogsDetailsDaoInterface.deleteBlogsDetailsById(blogDetailsId);
	}

	@Override
	public Responce updateBlogDetails(String blogId, HashMap<String, Object> blogDetailsData) {
		// TODO Auto-generated method stub
		return blogsDetailsDaoInterface.updateBlogsDetails(blogId, blogDetailsData);
	}

	@Override
	public List<Map<String, Object>> getAllBlogDetails(String blogId) {
		Blogs blogs = blogsDaoInterface.getBlogsById(blogId);
		if(blogs==null) {
			return new ArrayList<Map<String,Object>>();
		}
		return blogsDetailsDaoInterface.getBlogsDetails(blogs);
	}

}

package com.juzzIt.EducationProject.Services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.juzzIt.EducationProject.DaoInterface.BlogImagesDaoInterface;
import com.juzzIt.EducationProject.DaoInterface.BlogsDaoInterface;
import com.juzzIt.EducationProject.Entity.BlogImages;
import com.juzzIt.EducationProject.Entity.Blogs;
import com.juzzIt.EducationProject.Models.Responce;
import com.juzzIt.EducationProject.ServiceInterface.BlogImagesServiceInterface;


@Service
public class BlogImagesService implements BlogImagesServiceInterface {
	
	@Autowired
	private BlogImagesDaoInterface blogImagesDaoInterface;
	
	@Autowired
	private BlogsDaoInterface blogsDaoInterface;

	@Override
	public Responce addBlogImage(String blogId, HashMap<String, Object> imageData) {
Responce responce=new Responce();
if(imageData.get("image_URL")==null) {
	responce.setMassege("image id requided");
	responce.setStatus(false);
	return responce;
}
UUID id=UUID.randomUUID();

Blogs blogs = blogsDaoInterface.getBlogsById(blogId);

if(blogs==null) {
	responce.setMassege("blog with the given id not found");
	responce.setStatus(false);
	return responce;
}
BlogImages blogImages=new BlogImages();
blogImages.setActiveStatus("D");
blogImages.setImageId(id.toString());
blogImages.setImageURL(imageData.get("image_URL").toString());
blogImages.setBlogs(blogs);
BlogImages addedBlogImages = blogImagesDaoInterface.addBlogImages(blogImages);


if(addedBlogImages==null) {
	responce.setMassege("failed to add the image");
	responce.setStatus(false);
return responce;
}

responce.setMassege("image added successfully");
responce.setStatus(true);

		return responce;
	}

	@Override
	public Responce deleteBlogImage(String imageId) {
		// TODO Auto-generated method stub
		return blogImagesDaoInterface.deleteBlogImages(imageId);
	}

	@Override
	public Responce updateBlogImageData(String blogId, HashMap<String, Object> imageData) {
		// TODO Auto-generated method stub
		return blogImagesDaoInterface.updateBlogImages(blogId, imageData);
	}

	@Override
	public List<Map<String, Object>> getBlogImages(String blogId) {
       Blogs blogs = blogsDaoInterface.getBlogsById(blogId);
           if(blogs==null) {
	            return new ArrayList<Map< String,Object>>();
              }
		return blogImagesDaoInterface.getAllBlogImages(blogs);
	}

}

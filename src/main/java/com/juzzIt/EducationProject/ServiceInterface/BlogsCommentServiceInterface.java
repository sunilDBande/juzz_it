package com.juzzIt.EducationProject.ServiceInterface;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.juzzIt.EducationProject.Models.Responce;

public interface BlogsCommentServiceInterface {

	
	 public Responce addNewComment( String studentId, String blogId, HashMap<String, Object> blogCommentData );
     public Responce deleteBlogComment(String blogCommentId) ;
     public Responce updateBlogComment(String blogCommnetId,HashMap<String, Object> blogCommentData) ;
     public List<Map<String, Object>> getBlogCommentData(String blogId);
}

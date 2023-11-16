package com.juzzIt.EducationProject.Services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.juzzIt.EducationProject.DaoInterface.BlogsCommentDaoInterface;
import com.juzzIt.EducationProject.DaoInterface.BlogsDaoInterface;
import com.juzzIt.EducationProject.Entity.Blogs;
import com.juzzIt.EducationProject.Entity.BlogsComment;
import com.juzzIt.EducationProject.Entity.Student;
import com.juzzIt.EducationProject.Models.Responce;
import com.juzzIt.EducationProject.ServiceInterface.BlogsCommentServiceInterface;
import com.juzzIt.EducationProject.ServiceInterface.StudentServiceInterface;


@Service
public class BlogsCommentService implements BlogsCommentServiceInterface {

	@Autowired
	private BlogsCommentDaoInterface blogsCommentDaoInterface;
	
	@Autowired
	private BlogsDaoInterface blogsDaoInterface;
	
	@Autowired
	private StudentServiceInterface studentServiceInterface;
	

	
	@Override
	public Responce addNewComment(String studentId, String blogId, HashMap<String, Object> blogCommentData) {
		Responce responce=new Responce();
		if(blogCommentData.get("user_Name")==null||blogCommentData.get("user_Email")==null||blogCommentData.get("comment")==null) {	
			responce.setMassege("user name ,user email or comment is required");
			responce.setStatus(false);
	        return responce;
		}
		Blogs blogs = blogsDaoInterface.getBlogsById(blogId);
		if(blogs==null) {
			responce.setMassege("blog with the given id not found");
			responce.setStatus(false);
			return responce;
		}
		Student student = studentServiceInterface.getStudentById(studentId);
		if(student==null) {
			responce.setMassege("user with the given id not found");
			responce.setStatus(false);
			return responce;
		}
		UUID id=UUID.randomUUID();
		BlogsComment blogsComment=new BlogsComment();
		blogsComment.setComment(blogCommentData.get("comment").toString());
		blogsComment.setUserEmail(blogCommentData.get("user_Email").toString());
		blogsComment.setUserName(blogCommentData.get("user_Name").toString());
		blogsComment.setBlogCommentId(id.toString());
		blogsComment.setBlogs(blogs);
		blogsComment.setStudent(student);
		BlogsComment addedComment = blogsCommentDaoInterface.addNewComment(blogsComment);
		if(addedComment==null) {
            responce.setMassege("failed to add the comment");
            responce.setStatus(false);
            return responce;
		}
		responce.setMassege("comment added successfully");
		responce.setStatus(true);
		return responce;
	}
	@Override
	public Responce deleteBlogComment(String blogCommentId) {
		return blogsCommentDaoInterface.deleteBlogsComment(blogCommentId);
	}
	@Override
	public Responce updateBlogComment(String blogCommnetId, HashMap<String, Object> blogCommentData) {
		return blogsCommentDaoInterface.updateBlogsComment(blogCommnetId);
	}
	@Override
	public List<Map<String, Object>> getBlogCommentData(String blogId) {
        Blogs blogs = blogsDaoInterface.getBlogsById(blogId);
		if(blogs==null) {
			return new ArrayList<Map<String ,Object>>();
		}
		return blogsCommentDaoInterface.getBlogsCommentByBlog(blogs);
	}

}

package com.juzzIt.EducationProject.DaoInterface;

import java.util.List;
import java.util.Map;

import com.juzzIt.EducationProject.Entity.Blogs;
import com.juzzIt.EducationProject.Entity.BlogsComment;
import com.juzzIt.EducationProject.Models.Responce;

public interface BlogsCommentDaoInterface {

	public BlogsComment addNewComment(BlogsComment blogsComment);
	public BlogsComment getBlogsCommentById(String blogsCommentId);
	public Responce deleteBlogsComment(String blogsCommentId);
	public Responce updateBlogsComment(String blogsComment);
	public List<Map<String, Object>> getBlogsCommentByBlog(Blogs blogs);
}

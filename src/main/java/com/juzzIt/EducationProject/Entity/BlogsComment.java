package com.juzzIt.EducationProject.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class BlogsComment {

	@Id
	@Column(name="BLOG_COMMENT_ID")
	
	private String blogCommentId;
	@Column(name="USER_NAME")
	private String userName;
	@Column(name="USER_EMAIL")
	private String userEmail;
	@Column(name="COMMENT",length = 40000)
	private String comment;
	
	@ManyToOne 
	@JoinColumn(name="BLOGS_ID")
	@JsonIgnore
	private Blogs blogs;
	
	
	@ManyToOne
	@JoinColumn(name="STUDENT_ID")
	@JsonIgnore
	private Student student;


	public String getBlogCommentId() {
		return blogCommentId;
	}


	public void setBlogCommentId(String blogCommentId) {
		this.blogCommentId = blogCommentId;
	}


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public String getUserEmail() {
		return userEmail;
	}


	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}


	public String getComment() {
		return comment;
	}


	public void setComment(String comment) {
		this.comment = comment;
	}


	public Blogs getBlogs() {
		return blogs;
	}


	public void setBlogs(Blogs blogs) {
		this.blogs = blogs;
	}


	public Student getStudent() {
		return student;
	}


	public void setStudent(Student student) {
		this.student = student;
	}


	@Override
	public String toString() {
		return "BlogsComment [blogCommentId=" + blogCommentId + ", userName=" + userName + ", userEmail=" + userEmail
				+ ", comment=" + comment + ", blogs=" + blogs + ", student=" + student + "]";
	}


	public BlogsComment() {
		super();
		// TODO Auto-generated constructor stub
	}



}

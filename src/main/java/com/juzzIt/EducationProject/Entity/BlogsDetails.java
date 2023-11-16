package com.juzzIt.EducationProject.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class BlogsDetails {
	
	@Id
	@Column(name="BLOG_DETAILS_ID")
	private String blogDetailsId;
	@Column(name="BLOG_DETAILS_HEADING",length = 40000)
	private String blogDetailsHeading;
	@Column(name="BLOG_DETAILS_DESC",length = 40000)
	private String blogDetaildDesc;
	@Column(name="BLOG_DETAILS_ORDER")
	private int blogDetailsOrder;
	@Column(name="ACTIVE_STATUS")
	private String activeStatus;
	
	@ManyToOne 
	@JoinColumn(name="BLOGS_ID")
	@JsonIgnore
	private Blogs blogs;

	public BlogsDetails() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getBlogDetailsId() {
		return blogDetailsId;
	}

	public void setBlogDetailsId(String blogDetailsId) {
		this.blogDetailsId = blogDetailsId;
	}

	public String getBlogDetailsHeading() {
		return blogDetailsHeading;
	}

	public void setBlogDetailsHeading(String blogDetailsHeading) {
		this.blogDetailsHeading = blogDetailsHeading;
	}

	public String getBlogDetaildDesc() {
		return blogDetaildDesc;
	}

	public void setBlogDetaildDesc(String blogDetaildDesc) {
		this.blogDetaildDesc = blogDetaildDesc;
	}

	public int getBlogDetailsOrder() {
		return blogDetailsOrder;
	}

	public void setBlogDetailsOrder(int blogDetailsOrder) {
		this.blogDetailsOrder = blogDetailsOrder;
	}

	public String getActiveStatus() {
		return activeStatus;
	}

	public void setActiveStatus(String activeStatus) {
		this.activeStatus = activeStatus;
	}

	public Blogs getBlogs() {
		return blogs;
	}

	public void setBlogs(Blogs blogs) {
		this.blogs = blogs;
	}

	@Override
	public String toString() {
		return "BlogsDetails [blogDetailsId=" + blogDetailsId + ", blogDetailsHeading=" + blogDetailsHeading
				+ ", blogDetaildDesc=" + blogDetaildDesc + ", blogDetailsOrder=" + blogDetailsOrder + ", activeStatus="
				+ activeStatus + ", blogs=" + blogs + "]";
	}

	public BlogsDetails(String blogDetailsId, String blogDetailsHeading, String blogDetaildDesc, int blogDetailsOrder,
			String activeStatus, Blogs blogs) {
		super();
		this.blogDetailsId = blogDetailsId;
		this.blogDetailsHeading = blogDetailsHeading;
		this.blogDetaildDesc = blogDetaildDesc;
		this.blogDetailsOrder = blogDetailsOrder;
		this.activeStatus = activeStatus;
		this.blogs = blogs;
	}

	

}

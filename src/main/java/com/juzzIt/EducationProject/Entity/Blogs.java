package com.juzzIt.EducationProject.Entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity

public class Blogs {

	
	@Id
	@Column(name="BLOG_ID")
	private String blogId;
	@Column(name="BLOG_HEADING",length = 40000)
	private String blogHeading;
	@Column(name="BLOG_INTRUDUCTION",length = 40000)
	private String blogIntruduction;
	@Column(name="BLOG_DESC",length = 40000)
	private  String blogDesc;
	@Column(name="BLOG_ORDER")
	private int blogOrder;
	@Column(name="ACTIVE_STATUS")
	private String activeStatus;
	@OneToMany(mappedBy = "blogs",cascade = CascadeType.REMOVE)
	private List<BlogsDetails> blogsDetails;
	
	@OneToMany(mappedBy = "blogs",cascade = CascadeType.REMOVE)
	private List<BlogsComment> blogsComment;
	@OneToMany(mappedBy = "blogs",cascade = CascadeType.REMOVE)
	private List<BlogImages> blogImages;
	@OneToMany(mappedBy = "blogs",cascade = CascadeType.REMOVE)
	private List<BlogsDetailsBadroundImage> blogsDetailsBadroundImage;

	
	public Blogs() {
		super();
		// TODO Auto-generated constructor stub
	}


	public String getBlogId() {
		return blogId;
	}


	public void setBlogId(String blogId) {
		this.blogId = blogId;
	}


	public String getBlogHeading() {
		return blogHeading;
	}


	public void setBlogHeading(String blogHeading) {
		this.blogHeading = blogHeading;
	}


	public String getBlogIntruduction() {
		return blogIntruduction;
	}


	public void setBlogIntruduction(String blogIntruduction) {
		this.blogIntruduction = blogIntruduction;
	}


	public String getBlogDesc() {
		return blogDesc;
	}


	public void setBlogDesc(String blogDesc) {
		this.blogDesc = blogDesc;
	}


	public int getBlogOrder() {
		return blogOrder;
	}


	public void setBlogOrder(int blogOrder) {
		this.blogOrder = blogOrder;
	}


	public String getActiveStatus() {
		return activeStatus;
	}


	public void setActiveStatus(String activeStatus) {
		this.activeStatus = activeStatus;
	}


	public List<BlogsDetails> getBlogsDetails() {
		return blogsDetails;
	}


	public void setBlogsDetails(List<BlogsDetails> blogsDetails) {
		this.blogsDetails = blogsDetails;
	}


	public List<BlogsComment> getBlogsComment() {
		return blogsComment;
	}


	public void setBlogsComment(List<BlogsComment> blogsComment) {
		this.blogsComment = blogsComment;
	}


	public List<BlogImages> getBlogImages() {
		return blogImages;
	}


	public void setBlogImages(List<BlogImages> blogImages) {
		this.blogImages = blogImages;
	}


	public Blogs(String blogId, String blogHeading, String blogIntruduction, String blogDesc, int blogOrder,
			String activeStatus, List<BlogsDetails> blogsDetails, List<BlogsComment> blogsComment,
			List<BlogImages> blogImages) {
		super();
		this.blogId = blogId;
		this.blogHeading = blogHeading;
		this.blogIntruduction = blogIntruduction;
		this.blogDesc = blogDesc;
		this.blogOrder = blogOrder;
		this.activeStatus = activeStatus;
		this.blogsDetails = blogsDetails;
		this.blogsComment = blogsComment;
		this.blogImages = blogImages;
	}


	@Override
	public String toString() {
		return "Blogs [blogId=" + blogId + ", blogHeading=" + blogHeading + ", blogIntruduction=" + blogIntruduction
				+ ", blogDesc=" + blogDesc + ", blogOrder=" + blogOrder + ", activeStatus=" + activeStatus
				+ ", blogsDetails=" + blogsDetails + ", blogsComment=" + blogsComment + ", blogImages=" + blogImages
				+ "]";
	}


	



	
	
}

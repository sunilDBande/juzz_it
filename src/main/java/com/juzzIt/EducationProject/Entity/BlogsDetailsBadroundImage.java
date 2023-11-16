package com.juzzIt.EducationProject.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity	
public class BlogsDetailsBadroundImage {
	
	
	@Id
	@Column(name="IMAGE_ID")
	private String imageId;
	@Column(name="IMAGE_URL")
	private String imageURL;
	@Column(name="ACTIVE_STATUS")
	private String activeStatus;
	
	@ManyToOne
	@JoinColumn(name="BLOG_ID")
	@JsonIgnore
	private Blogs blogs;

	public String getImageId() {
		return imageId;
	}

	public void setImageId(String imageId) {
		this.imageId = imageId;
	}

	public String getImageURL() {
		return imageURL;
	}

	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
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

	public BlogsDetailsBadroundImage() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BlogsDetailsBadroundImage(String imageId, String imageURL, String activeStatus, Blogs blogs) {
		super();
		this.imageId = imageId;
		this.imageURL = imageURL;
		this.activeStatus = activeStatus;
		this.blogs = blogs;
	}

	@Override
	public String toString() {
		return "BlogsDetailsBadroundImage [imageId=" + imageId + ", imageURL=" + imageURL + ", activeStatus="
				+ activeStatus + ", blogs=" + blogs + "]";
	}

}

package com.juzzIt.EducationProject.Entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class CourseCategory implements Serializable{

	@Id
	@Column(name="CATAGURY_ID")
	private String categoryId;
	@Column(name="CATAGURY_NAME")
	private String categoryName;
	public CourseCategory() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CourseCategory(String categoryId, String categoryName) {
		super();
		this.categoryId = categoryId;
		this.categoryName = categoryName;
	}
	public String getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	@Override
	public String toString() {
		return "CourseCategory [categoryId=" + categoryId + ", categoryName=" + categoryName + "]";
	}
	

	
}

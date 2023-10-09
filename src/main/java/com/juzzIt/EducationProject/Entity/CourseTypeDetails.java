package com.juzzIt.EducationProject.Entity;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;


@Entity
public class CourseTypeDetails implements Serializable{

	
	@Id
	@Column(name="DETAIL_ID")
	private String detailId;
	@Column(name="ACTUAL_PRICE")
	private int actualPrice;
  @Column(name="COURSE_LEVEL")
	private int discountPrice;
	@Column(name="COURSE_DESCRIPTION",length = 40000)
	private String courseDesc;
	@Column(name="COURSE_OVERVIEW",length = 40000)
	private String   courseOverview;
	@Column(name="COURSE_DURIATION_DEACRIPTION",length = 4000)
	private String courseDuriationDesc;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JsonIgnore
	@JoinColumn(name="COURSE_TYPE_ID")
	private CourseType courseType;

	public CourseTypeDetails() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CourseTypeDetails(String detailId, int actualPrice, int discountPrice, String courseDesc,
			String courseOverview, String courseDuriationDesc, CourseType courseType) {
		super();
		this.detailId = detailId;
		this.actualPrice = actualPrice;
		this.discountPrice = discountPrice;
		this.courseDesc = courseDesc;
		this.courseOverview = courseOverview;
		this.courseDuriationDesc = courseDuriationDesc;
		this.courseType = courseType;
	}

	public String getDetailId() {
		return detailId;
	}

	public void setDetailId(String detailId) {
		this.detailId = detailId;
	}

	public int getActualPrice() {
		return actualPrice;
	}

	public void setActualPrice(int actualPrice) {
		this.actualPrice = actualPrice;
	}

	public int getDiscountPrice() {
		return discountPrice;
	}

	public void setDiscountPrice(int discountPrice) {
		this.discountPrice = discountPrice;
	}

	public String getCourseDesc() {
		return courseDesc;
	}

	public void setCourseDesc(String courseDesc) {
		this.courseDesc = courseDesc;
	}

	public String getCourseOverview() {
		return courseOverview;
	}

	public void setCourseOverview(String courseOverview) {
		this.courseOverview = courseOverview;
	}

	public String getCourseDuriationDesc() {
		return courseDuriationDesc;
	}

	public void setCourseDuriationDesc(String courseDuriationDesc) {
		this.courseDuriationDesc = courseDuriationDesc;
	}

	public CourseType getCourseType() {
		return courseType;
	}

	public void setCourseType(CourseType courseType) {
		this.courseType = courseType;
	}

	@Override
	public String toString() {
		return "CourseTypeDetails [detailId=" + detailId + ", actualPrice=" + actualPrice + ", discountPrice="
				+ discountPrice + ", courseDesc=" + courseDesc + ", courseOverview=" + courseOverview
				+ ", courseDuriationDesc=" + courseDuriationDesc + ", courseType=" + courseType + "]";
	}

	
}

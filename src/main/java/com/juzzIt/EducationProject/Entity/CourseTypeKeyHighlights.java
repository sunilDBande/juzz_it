package com.juzzIt.EducationProject.Entity;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class CourseTypeKeyHighlights implements Serializable{

	
	@Id
	@Column(name="HIGHLIGHT_ID")
	private String  highlightId;
	@Column(name="HIGHLIGHT")
	private String highlight;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnore
	@JoinColumn(name="COURSE_TYPE_ID")
	private CourseType courseType;

	public CourseTypeKeyHighlights() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CourseTypeKeyHighlights(String highlightId, String highlight, CourseType courseType) {
		super();
		this.highlightId = highlightId;
		this.highlight = highlight;
		this.courseType = courseType;
	}

	public String getHighlightId() {
		return highlightId;
	}

	public void setHighlightId(String highlightId) {
		this.highlightId = highlightId;
	}

	public String getHighlight() {
		return highlight;
	}

	public void setHighlight(String highlight) {
		this.highlight = highlight;
	}

	@Override
	public String toString() {
		return "CourseTypeKeyHighlights [highlightId=" + highlightId + ", highlight=" + highlight + ", courseType="
				+ courseType + "]";
	}

	public CourseType getCourseType() {
		return courseType;
	}

	public void setCourseType(CourseType courseType) {
		this.courseType = courseType;
	}

}

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
public class CourseTypeObjective implements Serializable{

	@Id
	@Column(name="OBJECTIVE_ID")
	private String objectiveId;
	@Column(name="OBJECTIVE")
	private String objective;
	
	@ManyToOne(fetch =  FetchType.LAZY)
	@JsonIgnore
	@JoinColumn(name="COURSE_TYPE_ID")
	private CourseType courseType;

	public CourseTypeObjective() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getObjectiveId() {
		return objectiveId;
	}

	public void setObjectiveId(String objectiveId) {
		this.objectiveId = objectiveId;
	}

	public String getObjective() {
		return objective;
	}

	public void setObjective(String objective) {
		this.objective = objective;
	}

	public CourseType getCourseType() {
		return courseType;
	}

	public void setCourseType(CourseType courseType) {
		this.courseType = courseType;
	}

	public CourseTypeObjective(String objectiveId, String objective, CourseType courseType) {
		super();
		this.objectiveId = objectiveId;
		this.objective = objective;
		this.courseType = courseType;
	}

	@Override
	public String toString() {
		return "CourseTypeObjective [objectiveId=" + objectiveId + ", objective=" + objective + ", courseType="
				+ courseType + "]";
	}

	
}

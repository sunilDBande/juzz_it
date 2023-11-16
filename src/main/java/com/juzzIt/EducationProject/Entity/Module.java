package com.juzzIt.EducationProject.Entity;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Module implements Serializable{

	@Id
	@Column(name="MODULE_ID")
	private String moduleId;
	@Column(name="MODULE_TITLE")
	private String moduleTitle;
	@Column(name="ACTIVE")
	private String  activeModule;
	
	@Column(name="MODULE_ORDER")
	private int moduleOrder;
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnore
	@JoinColumn(name="COURSE_TYPE")
	private CourseType courseType;
	
	@OneToMany(mappedBy = "module",cascade = CascadeType.REMOVE,fetch = FetchType.LAZY)
	private List<Lesson> lesson;
	@OneToMany(mappedBy = "module",cascade = CascadeType.REMOVE,fetch = FetchType.LAZY)
	private List<ModuleImages> moduleImages;

	public Module() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getModuleId() {
		return moduleId;
	}

	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}

	public String getModuleTitle() {
		return moduleTitle;
	}

	public void setModuleTitle(String moduleTitle) {
		this.moduleTitle = moduleTitle;
	}

	public String getActiveModule() {
		return activeModule;
	}

	public void setActiveModule(String activeModule) {
		this.activeModule = activeModule;
	}

	public int getModuleOrder() {
		return moduleOrder;
	}

	public void setModuleOrder(int moduleOrder) {
		this.moduleOrder = moduleOrder;
	}

	public CourseType getCourseType() {
		return courseType;
	}

	public void setCourseType(CourseType courseType) {
		this.courseType = courseType;
	}

	public List<Lesson> getLesson() {
		return lesson;
	}

	public void setLesson(List<Lesson> lesson) {
		this.lesson = lesson;
	}

	public List<ModuleImages> getModuleImages() {
		return moduleImages;
	}

	public void setModuleImages(List<ModuleImages> moduleImages) {
		this.moduleImages = moduleImages;
	}

	public Module(String moduleId, String moduleTitle, String activeModule, int moduleOrder, CourseType courseType,
			List<Lesson> lesson, List<ModuleImages> moduleImages) {
		super();
		this.moduleId = moduleId;
		this.moduleTitle = moduleTitle;
		this.activeModule = activeModule;
		this.moduleOrder = moduleOrder;
		this.courseType = courseType;
		this.lesson = lesson;
		this.moduleImages = moduleImages;
	}

	@Override
	public String toString() {
		return "Module [moduleId=" + moduleId + ", moduleTitle=" + moduleTitle + ", activeModule=" + activeModule
				+ ", moduleOrder=" + moduleOrder + ", courseType=" + courseType + ", lesson=" + lesson
				+ ", moduleImages=" + moduleImages + "]";
	}

	
	
	
}

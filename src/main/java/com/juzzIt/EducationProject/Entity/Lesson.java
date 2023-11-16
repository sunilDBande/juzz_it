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
public class Lesson implements Serializable{

	@Id
	@Column(name="LEASSON_ID")
	private String lessonId;
	@Column(name="LEASON_TITLE")
	private String leassonTitle;
	@Column(name="ACTIVE_LESSON")
	private String activeLesson;
	
	@	Column(name="LESSON_ORDER")
	private int lessonOrder;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnore
	@JoinColumn(name="MODULE_ID")
	private Module module;
	
	@OneToMany(mappedBy = "lesson",cascade = CascadeType.REMOVE,orphanRemoval = true,fetch = FetchType.LAZY)
	private List<Topic> topic;

	public Lesson() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getLessonId() {
		return lessonId;
	}

	public void setLessonId(String lessonId) {
		this.lessonId = lessonId;
	}

	public String getLeassonTitle() {
		return leassonTitle;
	}

	public void setLeassonTitle(String leassonTitle) {
		this.leassonTitle = leassonTitle;
	}

	public String getActiveLesson() {
		return activeLesson;
	}

	public void setActiveLesson(String activeLesson) {
		this.activeLesson = activeLesson;
	}

	public int getLessonOrder() {
		return lessonOrder;
	}

	public void setLessonOrder(int lessonOrder) {
		this.lessonOrder = lessonOrder;
	}

	public Module getModule() {
		return module;
	}

	public void setModule(Module module) {
		this.module = module;
	}

	public List<Topic> getTopic() {
		return topic;
	}

	public void setTopic(List<Topic> topic) {
		this.topic = topic;
	}

	public Lesson(String lessonId, String leassonTitle, String activeLesson, int lessonOrder, Module module,
			List<Topic> topic) {
		super();
		this.lessonId = lessonId;
		this.leassonTitle = leassonTitle;
		this.activeLesson = activeLesson;
		this.lessonOrder = lessonOrder;
		this.module = module;
		this.topic = topic;
	}

	@Override
	public String toString() {
		return "Lesson [lessonId=" + lessonId + ", leassonTitle=" + leassonTitle + ", activeLesson=" + activeLesson
				+ ", lessonOrder=" + lessonOrder + ", module=" + module + ", topic=" + topic + "]";
	}

	
}

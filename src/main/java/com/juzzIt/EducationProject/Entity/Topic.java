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
public class Topic implements Serializable{

	
	@Id
	@Column(name="TOPIC_ID")
	private String topicId;
	@Column(name="TOPIC_TITLE")
	private String topicTitle;
	@Column(name="ACTIVE_TOPIC")
	private String active_Topic;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnore
	@JoinColumn(name="LESSON_ID")
	private Lesson lesson;

	public Topic() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Topic(String topicId, String topicTitle, String active_Topic, Lesson lesson) {
		super();
		this.topicId = topicId;
		this.topicTitle = topicTitle;
		this.active_Topic = active_Topic;
		this.lesson = lesson;
	}

	public String getTopicId() {
		return topicId;
	}

	public void setTopicId(String topicId) {
		this.topicId = topicId;
	}

	public String getTopicTitle() {
		return topicTitle;
	}

	public void setTopicTitle(String topicTitle) {
		this.topicTitle = topicTitle;
	}

	public String getActive_Topic() {
		return active_Topic;
	}

	public void setActive_Topic(String active_Topic) {
		this.active_Topic = active_Topic;
	}

	public Lesson getLesson() {
		return lesson;
	}

	public void setLesson(Lesson lesson) {
		this.lesson = lesson;
	}

	@Override
	public String toString() {
		return "Topic [topicId=" + topicId + ", topicTitle=" + topicTitle + ", active_Topic=" + active_Topic
				+ ", lesson=" + lesson + "]";
	}

}

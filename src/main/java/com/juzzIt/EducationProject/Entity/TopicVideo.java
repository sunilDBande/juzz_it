package com.juzzIt.EducationProject.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class TopicVideo {
	
	@Id
	@Column(name="TOPIC_VIDEO_ID")
	private String topicVideoId;
	@Column(name = "VIDEO_HEADING")
	private String vedioHeading;
	@Column(name="VIDEO_URL")
	private String videoURL;
	@Column(name="VIDEO_ORDER")
	private int order;
	@Column(name="CREATED_DATE")
	private String createdDate;
	@Column(name="ACTIVE_STATUS")
	private String activeStatus;
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name="TOPIC_ID")
	private Topic topic;

	public TopicVideo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getTopicVideoId() {
		return topicVideoId;
	}

	public void setTopicVideoId(String topicVideoId) {
		this.topicVideoId = topicVideoId;
	}

	public String getVedioHeading() {
		return vedioHeading;
	}

	public void setVedioHeading(String vedioHeading) {
		this.vedioHeading = vedioHeading;
	}

	public String getVideoURL() {
		return videoURL;
	}

	public void setVideoURL(String videoURL) {
		this.videoURL = videoURL;
	}

	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	public String getActiveStatus() {
		return activeStatus;
	}

	public void setActiveStatus(String activeStatus) {
		this.activeStatus = activeStatus;
	}

	public Topic getTopic() {
		return topic;
	}

	public void setTopic(Topic topic) {
		this.topic = topic;
	}

	@Override
	public String toString() {
		return "TopicVideo [topicVideoId=" + topicVideoId + ", vedioHeading=" + vedioHeading + ", videoURL=" + videoURL
				+ ", order=" + order + ", createdDate=" + createdDate + ", activeStatus=" + activeStatus + ", topic="
				+ topic + "]";
	}

	public TopicVideo(String topicVideoId, String vedioHeading, String videoURL, int order, String createdDate,
			String activeStatus, Topic topic) {
		super();
		this.topicVideoId = topicVideoId;
		this.vedioHeading = vedioHeading;
		this.videoURL = videoURL;
		this.order = order;
		this.createdDate = createdDate;
		this.activeStatus = activeStatus;
		this.topic = topic;
	}

}

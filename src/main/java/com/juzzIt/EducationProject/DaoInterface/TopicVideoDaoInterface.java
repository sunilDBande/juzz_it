package com.juzzIt.EducationProject.DaoInterface;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.juzzIt.EducationProject.Entity.Topic;
import com.juzzIt.EducationProject.Entity.TopicVideo;
import com.juzzIt.EducationProject.Models.Responce;

public interface TopicVideoDaoInterface {

	public TopicVideo addNewTopicVideo( TopicVideo topicVideo) ;
	public TopicVideo getTopicVideoById(String topicVideoId);
	public Responce deleteTopicVideo( String topicVideoId);
	public Responce updateTopicVideo( String topicVideoId, HashMap<String, Object> topicVideoData);
	public List<Map<String, Object>> getTopicVideo( Topic topic);
}

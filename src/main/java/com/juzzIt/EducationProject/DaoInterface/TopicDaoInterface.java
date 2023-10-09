package com.juzzIt.EducationProject.DaoInterface;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.juzzIt.EducationProject.Entity.Topic;
import com.juzzIt.EducationProject.Models.Responce;

public interface TopicDaoInterface {

	
	public Topic addTopic(Topic topic);
	public Responce deleteTopicById(String topicId) throws Exception;
	public List<Map<String , Object>> getAllTopicsByLessonId(String lessonId) throws Exception;
	
	public Responce updeteTopic(String topicId,HashMap<String, Object> topicData) throws Exception;
	
	public Topic getTopicById(String topicIt);
}

package com.juzzIt.EducationProject.ServiceInterface;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.juzzIt.EducationProject.Models.Responce;

public interface TopicServiceInterface {
	public Responce addTopic(String lessonId,HashMap<String , Object> topic) throws Exception;
	public Responce deleteTopic(String topicId) throws Exception;
	public List<Map<String , Object>> getAllTopics(String lessonId) throws Exception;
	public Responce updeteTopic(String topicId, HashMap<String, Object> topicData) throws Exception;
}

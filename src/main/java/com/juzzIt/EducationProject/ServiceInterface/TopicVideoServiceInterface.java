package com.juzzIt.EducationProject.ServiceInterface;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.juzzIt.EducationProject.Models.Responce;
public interface TopicVideoServiceInterface {
	
	public Responce addNewTopicVideo( String topicId, HashMap<String, Object> topicVideoData) ;
	public Responce deleteTopicVideo( String topicVideoId);
	public Responce updateTopicVideo( String topicVideoId, HashMap<String, Object> topicVideoData);
	public List<Map<String, Object>> getTopicVideo(String topicId );

}

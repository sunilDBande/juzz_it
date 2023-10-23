package com.juzzIt.EducationProject.Services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.juzzIt.EducationProject.DaoInterface.TopicDaoInterface;
import com.juzzIt.EducationProject.DaoInterface.TopicVideoDaoInterface;
import com.juzzIt.EducationProject.Entity.Topic;
import com.juzzIt.EducationProject.Entity.TopicVideo;
import com.juzzIt.EducationProject.Models.Responce;
import com.juzzIt.EducationProject.ServiceInterface.TopicVideoServiceInterface;

@Service
public class TopicVideoService implements TopicVideoServiceInterface{

	
	
	@Autowired
	private TopicVideoDaoInterface topicVideoDaoInterface;
	
	@Autowired
	private TopicDaoInterface topicDaoInterface;
	
	
	@Override
	public Responce addNewTopicVideo(String topicId, HashMap<String, Object> topicVideoData) {
	
		Responce responce=new Responce();
		
		if(topicVideoData.get("vedio_Heading")==null||topicVideoData.get("video_URL")==null) {
			
			responce.setMassege("vedio Heading and videoURL are Required");
			responce.setStatus(false);
			return responce;
		}
		
		
		Topic topic = topicDaoInterface.getTopicById(topicId);
		
if(topic==null) {
	responce.setMassege("topic with the given it not found");
	responce.setStatus(false);
	return responce;
}

TopicVideo newTopicVideo=new TopicVideo();

newTopicVideo.setVedioHeading(topicVideoData.get("vedio_Heading").toString());
newTopicVideo.setVideoURL(topicVideoData.get("video_URL").toString());
newTopicVideo.setTopic(topic);
newTopicVideo.setTopicVideoId(topicVideoData.get("id").toString());
newTopicVideo.setActiveStatus("D");
		
TopicVideo addedTopicVideo = topicVideoDaoInterface.addNewTopicVideo(newTopicVideo);

if(addedTopicVideo==null) {
	responce.setMassege("failed to add the video");
	responce.setStatus(true);
	return responce;
}

responce.setMassege("video added successfully");
responce.setStatus(true);
		return responce;
	}

	@Override
	public Responce deleteTopicVideo(String topicVideoId) {
		return topicVideoDaoInterface.deleteTopicVideo(topicVideoId);
	}

	@Override
	public Responce updateTopicVideo(String topicVideoId, HashMap<String, Object> topicVideoData) {
		return topicVideoDaoInterface.updateTopicVideo(topicVideoId, topicVideoData);
	}

	@Override
	public List<Map<String, Object>> getTopicVideo(String topicId) {
		Topic topic = topicDaoInterface.getTopicById(topicId);
		
		if(topic==null) {
	return new ArrayList<Map<String,Object>>();
		}
		return topicVideoDaoInterface.getTopicVideo(topic);
	}

}

package com.juzzIt.EducationProject.Dao;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.juzzIt.EducationProject.DaoInterface.TopicVideoDaoInterface;

import com.juzzIt.EducationProject.Entity.Topic;
import com.juzzIt.EducationProject.Entity.TopicVideo;
import com.juzzIt.EducationProject.Models.Responce;
import com.juzzIt.EducationProject.Repositary.TopicVideoRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
@Component
public class TopicVideoDao implements TopicVideoDaoInterface {

	@Autowired
	private TopicVideoRepository topicVideoRepository;
	@Autowired 
	private EntityManager entityManager;
	@Override
	public TopicVideo addNewTopicVideo(TopicVideo topicVideo) {
		return topicVideoRepository.save(topicVideo);
	}

	@Override
	public TopicVideo getTopicVideoById(String topicVideoId) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<TopicVideo> createQuery = criteriaBuilder.createQuery(TopicVideo.class);
		Root<TopicVideo> root = createQuery.from(TopicVideo.class);
		
		Predicate predicate = criteriaBuilder.equal(root.get("topicVideoId"), topicVideoId);
		
		createQuery.select(root).where(predicate);
		
		List<TopicVideo> resultList = entityManager.createQuery(createQuery).getResultList();
		
	if(resultList.isEmpty()) {
		return null;
	}
		return resultList.get(0);
	}

	@Override
	public Responce deleteTopicVideo(String topicVideoId) {
	
		 TopicVideo topicVideo = getTopicVideoById( topicVideoId);
		 Responce responce=new Responce();
		 
		 if(topicVideo==null) {
			 responce.setMassege("topic video with the given id not found");
			 responce.setStatus(false);
			 return responce;
		 }else {
			 
			 topicVideoRepository.delete(topicVideo);
			 responce.setMassege("topic deleted successfully");
			 responce.setStatus(true);
			 
		 }
		 
		 return responce ;
	}

	@Override
	public Responce updateTopicVideo(String topicVideoId, HashMap<String, Object> topicVideoData) {
		 TopicVideo topicVideo = getTopicVideoById( topicVideoId);
		 Responce responce=new Responce();
		 
		 if(topicVideo==null) {
			 responce.setMassege("topic video with the given id not found");
			 responce.setStatus(false);
			 return responce;
		 }else {
			 
			 if(topicVideoData.get("vedio_Heading")!=null) {
				 
				 topicVideo.setVedioHeading(topicVideoData.get("vedio_Heading").toString());
			 }

 if(topicVideoData.get("video_URL")!=null) {
	 topicVideo.setVideoURL(topicVideoData.get("video_URL").toString());
			 }

 if(topicVideoData.get("active_Status")!=null) {
	 if(topicVideo.getActiveStatus().equalsIgnoreCase("D")) {
		 topicVideo.setActiveStatus("A");
	 }else {
		 topicVideo.setActiveStatus("D");
	 }
 }
 
 TopicVideo UpdatedTopicVideo = topicVideoRepository.save(topicVideo);
 
 if(UpdatedTopicVideo==null) {
	 
	 responce.setMassege("failed to update the data");
	 responce.setStatus(false);
	 return responce;
 }
 
 responce.setMassege("details updated successfully");
 responce.setStatus(true);
return responce; 
			 
		 }
		
	}

	@Override
	public List<Map<String, Object>> getTopicVideo(Topic topic) {
		
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<TopicVideo> createQuery = criteriaBuilder.createQuery(TopicVideo.class);
		Root<TopicVideo> root = createQuery.from(TopicVideo.class);
		Predicate predicate = criteriaBuilder.equal(root.get("topic"), topic);
		createQuery.select(root).where(predicate);
		List<TopicVideo> resultList = entityManager.createQuery(createQuery).getResultList();
		List<Map<String, Object>> collect = resultList.stream().map(result->{
			Map<String, Object> map=new LinkedHashMap<String, Object>();
			map.put("topic_VideoId", result.getTopicVideoId());
			map.put("topic_Video_Heading", result.getVedioHeading());
			map.put("video_URL", result.getVideoURL());
			map.put("active_Status", result.getActiveStatus());
			map.put("created_Date", result.getCreatedDate());
			return map;
		}).collect(Collectors.toList());
		return collect;
	}
}

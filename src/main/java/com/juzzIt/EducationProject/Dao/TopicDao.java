package com.juzzIt.EducationProject.Dao;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.juzzIt.EducationProject.DaoInterface.LessonDaoInterface;
import com.juzzIt.EducationProject.DaoInterface.TopicDaoInterface;
import com.juzzIt.EducationProject.Entity.Lesson;
import com.juzzIt.EducationProject.Entity.Topic;
import com.juzzIt.EducationProject.Models.Responce;
import com.juzzIt.EducationProject.Repositary.TopicRepositary;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
@Component
public class TopicDao implements TopicDaoInterface {

	
	@Autowired
	private TopicRepositary topicRepositary;
	
	
	@Autowired
	private LessonDaoInterface lessonDaoInterface;
	
	@Autowired
	private EntityManager entityManager;

	@Override
	public Topic addTopic(Topic topic) {
		// TODO Auto-generated method stub
		return topicRepositary.save(topic);
	}
	
	
	
	
	

	@Override
	public Responce deleteTopicById(String topicId) throws Exception {
		
		
		
	Topic topic = getTopicById(topicId);
Responce responce=new Responce();
try {
		if(topic !=null) {
			topicRepositary.delete(topic);
			responce.setMassege("topic deleted successfully");
			responce.setStatus(true);
		}else {
			responce.setMassege("topic with the given id not found");
			responce.setStatus(false);
		}
}catch (Exception e) {
	throw new Exception("Getting problem while deleting Topic By Id");
}
		return responce;
	}

	@Override
	public List<Map<String, Object>> getAllTopicsByLessonId(String lessonId) throws Exception {
		List<Map<String, Object>> collect=null;
		try {
		Lesson lesson = lessonDaoInterface.getLessonById(lessonId);
		
		
		if(lesson==null) {
			return null;
		}
		
		
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		
		CriteriaQuery<Topic> createQuery = criteriaBuilder.createQuery(Topic.class);
		
		Root<Topic> root = createQuery.from(Topic.class);
		
		
		Predicate predicate = criteriaBuilder.equal(root.get("lesson"), lesson);
		
		createQuery.select(root).where(predicate);
		
		List<Topic> resultList = entityManager.createQuery(createQuery).getResultList();
		
		
		 collect = resultList.stream().map(result->{
			
			Map<String , Object> map=new LinkedHashMap<String, Object>();
			
			map.put("topic_id", result.getTopicId());
			map.put("topic_title", result.getTopicTitle());
			map.put("active_Topic", result.getActive_Topic());
			
			return map;
		}).collect(Collectors.toList());
		}catch (Exception e) {
			throw new Exception("Getting problem while getting All Topics By LessonId");
		}
		return collect;
	}

	@Override
	public Responce updeteTopic(String topicId, HashMap<String, Object> topicData) throws Exception {
		
		
	Topic topic = getTopicById(topicId);
		Responce responce=new Responce();
		try {
				if(topic!=null) {
			
					
					if(topicData.get("topic_title")!=null) {
						topic.setTopicTitle(topicData.get("topic_title").toString());
					}
					
					System.out.println(topicData);
					
					if(topicData.get("active_title")!=null){
						System.out.println("topic actic 1===>"+ topic.getActive_Topic());
						if(topic.getActive_Topic().equalsIgnoreCase("D")) {
							topic.setActive_Topic("A");
						}else {
							topic.setActive_Topic("D");
						}
						System.out.println("topic actic 2===>"+ topic.getActive_Topic());
					}
					
					
					Topic updatedTopic = topicRepositary.save(topic);
					
					
					System.out.println("topic actic 3===>"+ updatedTopic.getActive_Topic());
					if(updatedTopic==null) {
						responce.setMassege("failed to update the detailes");
						responce.setStatus(false);
					}
				responce.setMassege("detailes updated succesfully");
				responce.setStatus(true);
				
				}else {
					responce.setMassege("details updated successfully");
					responce.setStatus(true);
				}
				
		}catch (Exception e) {
			throw new Exception("Getting problem while updating updating Topic");
		}
		return responce;
	}






	@Override
	public Topic getTopicById(String topicIt) {
CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		
		CriteriaQuery<Topic> createQuery = criteriaBuilder.createQuery(Topic.class);
		Root<Topic> root = createQuery.from(Topic.class);
		
		Predicate predicate = criteriaBuilder.equal(root.get("topicId"), topicIt);
		
		createQuery.select(root).where(predicate);
		
		List<Topic> resultList = entityManager.createQuery(createQuery).getResultList();
		
		
		if(!resultList.isEmpty()) {
			return resultList.get(0);
		}
		
		return null;
	}
}

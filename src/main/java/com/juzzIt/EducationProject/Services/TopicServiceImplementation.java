package com.juzzIt.EducationProject.Services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.juzzIt.EducationProject.Dao.EntityDao;
import com.juzzIt.EducationProject.DaoInterface.LessonDaoInterface;
import com.juzzIt.EducationProject.DaoInterface.TopicDaoInterface;
import com.juzzIt.EducationProject.Entity.Lesson;
import com.juzzIt.EducationProject.Entity.Topic;
import com.juzzIt.EducationProject.Models.Responce;
import com.juzzIt.EducationProject.Models.UniqueIdGenerations;
import com.juzzIt.EducationProject.ServiceInterface.TopicServiceInterface;

@Service
public class TopicServiceImplementation implements TopicServiceInterface {

	@Autowired
	private LessonDaoInterface lessonDaoInterface;

	@Autowired
	private TopicDaoInterface topicDaoInterface;

	@Autowired
	private UniqueIdGenerations uniqueIdGenerations;

	@Autowired
	private EntityDao entityDao;

	@Override
	public Responce addTopic(String lessonId, HashMap<String, Object> topic) throws Exception {
		Responce responce = new Responce();
try {
		if (topic.get("topic_Title") == null) {
			responce.setMassege("topic title needed");
			responce.setStatus(false);
			return responce;
		}

		Lesson lesson = lessonDaoInterface.getLessonById(lessonId);

		if (lesson == null) {
			responce.setMassege("lesson with the given id not found");
			responce.setStatus(false);
			return responce;
		}

		Topic newTopic = new Topic();

		newTopic.setLesson(lesson);
		HashMap<String, Object> data = new HashMap<String, Object>();
		data.put("Entity_Name", "Topic");
		data.put("Entity_SubName", "TPIC");
		data.put("Entity_Count", 0);
		HashMap<String, Object> uniqueIdGeneration = uniqueIdGenerations.uniqueIdGeneration(data);
// *******
		System.out.println("uniqueIdGeneration--> "+uniqueIdGeneration);
		String subName = (String) uniqueIdGeneration.get("Entity_SubName");
		System.out.println("uniqueIdGeneration--> "+uniqueIdGeneration.get("count"));
		long count = (long) uniqueIdGeneration.get("count");
		if (count < 10) {
			newTopic.setTopicId(subName + "00" + count);
		} else if (count < 100 && count > 10) {
			newTopic.setTopicId(subName + "0" + count);
		} else {
			newTopic.setTopicId(subName + "" + count);
		}
// *******

		newTopic.setActive_Topic("D");
		newTopic.setTopicTitle(topic.get("topic_Title").toString());
		newTopic.setTopic_Order(0);

		Topic addedTopic = topicDaoInterface.addTopic(newTopic);

		if (addedTopic == null) {
			responce.setMassege("failed to added the topic");
			responce.setStatus(false);
			return responce;
		} else {
			entityDao.updateCountForCourseTable(data);
		}

		responce.setMassege("topic added successfully");
		responce.setStatus(true);
}catch (Exception e) {
	throw new Exception("Getting problem while adding Topic");
}
		return responce;
	}

	@Override
	public Responce deleteTopic(String topicId) throws Exception {
		// TODO Auto-generated method stub
		return topicDaoInterface.deleteTopicById(topicId);
	}

	@Override
	public List<Map<String, Object>> getAllTopics(String lessonId) throws Exception {
		// TODO Auto-generated method stub
		return topicDaoInterface.getAllTopicsByLessonId(lessonId);
	}

	@Override
	public Responce updeteTopic(String topicId, HashMap<String, Object> topicData) throws Exception {
		// TODO Auto-generated method stub
		return topicDaoInterface.updeteTopic(topicId, topicData);
	}

}

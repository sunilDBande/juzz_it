package com.juzzIt.EducationProject.Services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.juzzIt.EducationProject.Dao.EntityDao;
import com.juzzIt.EducationProject.DaoInterface.LessonDaoInterface;
import com.juzzIt.EducationProject.DaoInterface.ModuleDaoInterface;
import com.juzzIt.EducationProject.Entity.Lesson;
import com.juzzIt.EducationProject.Entity.Module;
import com.juzzIt.EducationProject.Models.Responce;
import com.juzzIt.EducationProject.Models.UniqueIdGenerations;
import com.juzzIt.EducationProject.ServiceInterface.LessonServiceInterface;

@Service
public class LessonServiceImplementation implements LessonServiceInterface{
	
	
	@Autowired
	private ModuleDaoInterface moduleDaoInterface;
	@Autowired
	private LessonDaoInterface lessonDaoInterface;
	@Autowired
	private UniqueIdGenerations uniqueIdGenerations;
	@Autowired
	private EntityDao entityDao;
	
	@Autowired
	private TopicServiceImplementation topicServiceImplementation;
	@Autowired
	private TopicVideoService topicVideoService;
	
	@Override
	public Responce addLesson(String modelId, HashMap<String, Object> lesson) throws Exception {
	
		Responce responce=new Responce();
		try {
		if(lesson.get("leasson_Title")==null) {
			responce.setMassege("lesson titale is needed");
			responce.setStatus(false);
			return responce;
		}	
		Module module = moduleDaoInterface.getModuleByModuleId(modelId);
		if(module==null) {
			responce.setMassege("module with the given id not found");
			responce.setStatus(false);
			return responce;
		}	
		Lesson newLesson=new Lesson();
		newLesson.setModule(module);
		newLesson.setActiveLesson("D");
		newLesson.setLeassonTitle(lesson.get("leasson_Title").toString());
		newLesson.setLessonOrder(0);
		 HashMap<String, Object> data = new HashMap<String, Object>();
			data.put("Entity_Name", "Lesson");
			data.put("Entity_SubName", "LSSN");
			data.put("Entity_Count", 0);
			HashMap<String, Object> uniqueIdGeneration = uniqueIdGenerations.uniqueIdGeneration(data);
			// *******
			String subName = (String) uniqueIdGeneration.get("Entity_SubName");
			long count = (long) uniqueIdGeneration.get("count");
			if (count < 10) {
				newLesson.setLessonId(subName + "00" + count);
			} else if (count < 100 && count > 10) {
				newLesson.setLessonId(subName + "0" + count);
			} else {
				newLesson.setLessonId(subName + "" + count);
			}
			// *******
		Lesson addedLesson = lessonDaoInterface.addLesson(newLesson);
		if(addedLesson==null) {
			responce.setMassege("failed to add the lesson");
			responce.setStatus(false);
			return responce;
		}else {
			 entityDao.updateCountForCourseTable(data);		
		}
		responce.setMassege("lesson added successfully");
		responce.setStatus(true);
		}catch (Exception e) {
			throw new Exception("Getting problem while adding Lesson");
		}
		return responce;
	}

	@Override
	public Responce deleteLesson(String lessonId) throws Exception {
		return lessonDaoInterface.deleteLessonById(lessonId);
	}

	@Override
	public List<Map<String, Object>> getAllLessons(String modelId) throws Exception {
		return lessonDaoInterface.getAllLessonsByModuleId(modelId);
	}

	@Override
	public Responce updateLessons(String lessonId, HashMap<String, Object> lessonData) throws Exception {		
		return lessonDaoInterface.updateLessons(lessonId, lessonData);
	}

	@Override
	public List<Map<String, Object>> getLessonTopicwithvideos(String moduleId) throws Exception {
		List<Map<String, Object>> lessons = lessonDaoInterface.getAllLessonsByModuleId(moduleId);
	
		return	lessons.stream().map(result->{
			
			
			try {
				List<Map<String, Object>> allTopics = topicServiceImplementation.getAllTopics(result.get("lesson_Id").toString());
				List<Object> collect = allTopics.stream().map(result1->{
					List<Map<String, Object>> topicVideo = topicVideoService.getTopicVideo(result1.get("topic_id").toString());
					result1.put("topic_videos", topicVideo);
					return result1;
				}).collect(Collectors.toList());
				
				result.put("lesson_topic", collect);
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
			return result;
		}).collect(Collectors.toList());
		
		
		
	}
	

}

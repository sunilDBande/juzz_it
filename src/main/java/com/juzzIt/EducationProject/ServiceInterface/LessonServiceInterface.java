package com.juzzIt.EducationProject.ServiceInterface;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.PathVariable;

import com.juzzIt.EducationProject.Models.Responce;

public interface LessonServiceInterface {

	public Responce addLesson(String modelId, HashMap<String , Object> lesson) throws Exception;
	public Responce deleteLesson(String lessonId) throws Exception;
	public List<Map<String , Object>> getAllLessons(String modelId) throws Exception;
	public Responce updateLessons(String lessonId,HashMap<String,Object> lessonData) throws Exception;
	public List<Map<String, Object>> getLessonTopicwithvideos(String moduleId)throws Exception;
}

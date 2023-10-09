package com.juzzIt.EducationProject.DaoInterface;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.juzzIt.EducationProject.Entity.Lesson;
import com.juzzIt.EducationProject.Models.Responce;

public interface LessonDaoInterface {

	
	public Lesson addLesson(Lesson lesson);
	
	public Lesson getLessonById(String lessonId);
	
	public Responce deleteLessonById(String lessonId) throws Exception;
	
	public List<Map<String , Object>> getAllLessonsByModuleId(String modelId) throws Exception;
	
	public Responce updateLessons(String lessonId,HashMap<String,Object> lessonData) throws Exception;
	
}

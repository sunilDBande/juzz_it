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
import com.juzzIt.EducationProject.DaoInterface.ModuleDaoInterface;
import com.juzzIt.EducationProject.Entity.Lesson;
import com.juzzIt.EducationProject.Entity.Module;
import com.juzzIt.EducationProject.Models.Responce;
import com.juzzIt.EducationProject.Repositary.LessonRepositary;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
@Component
public class LessonDao implements LessonDaoInterface {

	@Autowired
	private LessonRepositary lessonRepositary;
	
	@Autowired
	private ModuleDaoInterface moduleDaoInterface;
	
	
	@Autowired
	private EntityManager entityManager;

	@Override
	public Lesson addLesson(Lesson lesson) {
		return lessonRepositary.save(lesson);
	}

	@Override
	public Lesson getLessonById(String lessonId) {
		
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		
		CriteriaQuery<Lesson> createQuery = criteriaBuilder.createQuery(Lesson.class);
		
		Root<Lesson> root = createQuery.from(Lesson.class);
		Predicate predicate = criteriaBuilder.equal(root.get("lessonId"), lessonId);
		createQuery.select(root).where(predicate);
		
		List<Lesson> resultList = entityManager.createQuery(createQuery).getResultList();
		
		
		
		
		Optional<Lesson> lesson = lessonRepositary.findById(lessonId);
		
		if(!resultList.isEmpty()) {
			return resultList.get(0);
		}
		return null;
	}

	@Override
	public Responce deleteLessonById(String lessonId) throws Exception {
		Lesson lesson = getLessonById(lessonId);
		Responce responce=new Responce();
		try {
		if(lesson!=null) {
			lessonRepositary.delete(lesson);
			responce.setMassege("lesson deleted successfully");
			responce.setStatus(true);
		}else {
			responce.setMassege("lesson with the given id not found");
		}
		}catch (Exception e) {
			throw new Exception("Getting problem while deleting Lesson By Id");
		}
		return responce;
	}

	@Override
	public List<Map<String, Object>> getAllLessonsByModuleId(String modelId) throws Exception {
		List<Map<String, Object>> collect = null;
		try {
		Module module = moduleDaoInterface.getModuleByModuleId(modelId);
		
		if(module==null) {
			return null;
		}
		
		
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Lesson> createQuery = criteriaBuilder.createQuery(Lesson.class);
		
		Root<Lesson> root = createQuery.from(Lesson.class);
		
		Predicate predicate = criteriaBuilder.equal(root.get("module"), module);
		createQuery.select(root).where(predicate);
		
		
		
		List<Lesson> resultList = entityManager.createQuery(createQuery).getResultList();
		
		 collect = resultList.stream().map(result->{
			
			Map<String , Object> map=new LinkedHashMap<String, Object>();
			map.put("lesson_Id", result.getLessonId());
			map.put("lesson_Tites", result.getLeassonTitle());
			map.put("active_lesson", result.getActiveLesson());
			return map;
		}).collect(Collectors.toList());
		}catch (Exception e) {
			throw new Exception("Getting problem while getting All Lessons By ModuleId");
		}
		return collect;
	}

	@Override
	public Responce updateLessons(String lessonId, HashMap<String, Object> lessonData) throws Exception {
		
		Lesson lesson = getLessonById(lessonId);
		Responce responce=new Responce();
		try {
		if(lesson!=null) {
			
		
			if(lessonData.get("lesson_title")!=null) {
				lesson.setLeassonTitle(lessonData.get("lesson_title").toString());
			}
			
			
			if(lessonData.get("active_lesson")!=null) {
				if(lesson.getActiveLesson().equalsIgnoreCase("D")) {
					lesson.setActiveLesson("A");
				}else {
					lesson.setActiveLesson("D");
				}
			}
			
			
			Lesson updeted_Lesson = lessonRepositary.save(lesson);
			
			if(updeted_Lesson==null) {
				responce.setMassege("failed to update the data");
				responce.setStatus(false);
			}
			
			responce.setMassege("details updeted successfully");
			responce.setStatus(true);
			
			
		}
		else {
			responce.setMassege("lesson with the given id not found");
            responce.setStatus(false);
		}
		}catch (Exception e) {
			throw new Exception("Getting problem while updating Lessons");
		}
		return responce;
	}
	
}

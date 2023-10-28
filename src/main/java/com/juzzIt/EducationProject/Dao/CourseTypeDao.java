package com.juzzIt.EducationProject.Dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.juzzIt.EducationProject.DaoInterface.CourseDaoInterface;
import com.juzzIt.EducationProject.DaoInterface.CourseTypeDaoInterface;
import com.juzzIt.EducationProject.DaoInterface.CourseTypeDetailsDaoInterface;
import com.juzzIt.EducationProject.DaoInterface.CourseTypeEssentialsDaoInterface;
import com.juzzIt.EducationProject.DaoInterface.CourseTypeImageDaoInterface;
import com.juzzIt.EducationProject.DaoInterface.CourseTypeKeyHighlightsDaoInterface;
import com.juzzIt.EducationProject.DaoInterface.CourseTypeObjectiveDaoInterface;
import com.juzzIt.EducationProject.DaoInterface.CourseTypeProjectsDaoInterface;
import com.juzzIt.EducationProject.DaoInterface.CourseTypeToolsDaoInterface;
import com.juzzIt.EducationProject.DaoInterface.LessonDaoInterface;
import com.juzzIt.EducationProject.DaoInterface.ModuleDaoInterface;
import com.juzzIt.EducationProject.DaoInterface.TopicDaoInterface;
import com.juzzIt.EducationProject.Entity.BatchCourse;
import com.juzzIt.EducationProject.Entity.Course;
import com.juzzIt.EducationProject.Entity.CourseType;
import com.juzzIt.EducationProject.Models.Responce;
import com.juzzIt.EducationProject.Repositary.CourseTypeRepositary;
import com.juzzIt.EducationProject.ServiceInterface.CourseTypeBagroundImageServiceInterface;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

@Component
public class CourseTypeDao implements CourseTypeDaoInterface{

	@Autowired
	private CourseTypeRepositary courseTypeRepo;
	
	@Autowired
	private CourseDaoInterface courseDaoInterface;
	
	@Autowired
	private EntityManager entityManager;

	

	@Override
	public boolean IsExists(String courseTypeId) {
		// TODO Auto-generated method stub
		return courseTypeRepo.existsByCourseTypeName(courseTypeId);
	}


	@Override
	public CourseType addCourseType(CourseType courseType) {
		return courseTypeRepo.save(courseType);
	}


	@Override
	public List<Map<String, Object>> getCourseTypeByCourseId(String courseId) {
		Course course= courseDaoInterface.getCourseById(courseId);
		if(course==null) {
			return null;
		}
		
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		
		CriteriaQuery<CourseType> createQuery = criteriaBuilder.createQuery(CourseType.class);
		
		Root<CourseType> root= createQuery.from(CourseType.class);
	
		Predicate condition = criteriaBuilder.equal(root.get("course"),course );
		
		
		createQuery.select(root).where(condition);
		
		
		List<CourseType> resultList = entityManager.createQuery(createQuery).getResultList();
		
		List<Map<String, Object>>  courseType  = resultList.stream().map(result->{
			Map<String , Object> map=new LinkedHashMap<String, Object>();
			map.put("course_typeId", result.getCourseTypeId());
			map.put("course_typeName", result.getCourseTypeName());
			map.put("course_level", result.getCourseLevel());
			map.put("active_courseType", result.getActive_courseType());
			return map;
		}).collect(Collectors.toList());
		return courseType;
	}


	@Override
	public CourseType getCourseTypeById(String courseTypeId) {
		
		
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();		
		
		CriteriaQuery<CourseType> createQuery = criteriaBuilder.createQuery(CourseType.class);
		
		Root<CourseType> root = createQuery.from(CourseType.class);
		Predicate predicate = criteriaBuilder.equal(root.get("courseTypeId"), courseTypeId);
		createQuery.select(root).where(predicate);
		
		List<CourseType> resultList = entityManager.createQuery(createQuery).getResultList();
		
		if(!resultList.isEmpty()) {
			return resultList.get(0);
		}
		
		
		
//		Optional<CourseType> courseType = courseTypeRepo.findById(courseTypeId);
//		
//		if(courseType.isPresent()) {
//			return courseType.get();
//		}
	return null;
	}


	@Override
	public Responce updateCourseType(String courseTypeId, HashMap<String, Object> courseType) throws Exception {
		
		Responce responce=new Responce();
		CourseType curentCourseType = getCourseTypeById(courseTypeId);
		try {
		if(curentCourseType==null) {
			responce.setMassege("course type withe given id not found");
			responce.setStatus(false);
			return responce;
		}
		
		if(courseType.get("course_Level")!=null) {
			
			curentCourseType.setCourseLevel(courseType.get("course_Level").toString());

		}	
		
		
		if(courseType.get("active_courseType")!=null) {
			
			if(curentCourseType.getActive_courseType().equalsIgnoreCase("D")) {
				curentCourseType.setActive_courseType("A");
			}else {
				curentCourseType.setActive_courseType("D");
			}
		}	
		
		CourseType updatedCourseType = courseTypeRepo.save(curentCourseType);
		
		if(updatedCourseType==null) {
			responce.setMassege("failed to update the details");
			responce.setStatus(false);
			return responce;
		}
		
		responce.setStatus(true);
	responce.setMassege("details updated successfully");
		}catch (Exception e) {
			throw new Exception("Getting problem while updating CourseType");
		}
		return responce; 
	}


	@Override
	public Responce deleteCourseTypeById(String courseTypeId) throws Exception {
		CourseType curentCourseType = getCourseTypeById(courseTypeId);
		Responce responce =new Responce();
		try {
if(curentCourseType==null) {
	responce.setMassege("'course type with the given id not found");
	responce.setStatus(false);
	return responce;
}

courseTypeRepo.delete(curentCourseType);
responce.setMassege("course deleted successfully");
responce.setStatus(true);
		}catch (Exception e) {
			throw new Exception("Getting problem while deleting CourseTypeById");
		}
		return responce;
	}


	@Override
	public List<HashMap<String, Object>> CourseTypeByBatchCourse(BatchCourse batchCourse) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<CourseType> createQuery = criteriaBuilder.createQuery(CourseType.class);
		Root<CourseType> root = createQuery.from(CourseType.class);
		
		Predicate predicate = criteriaBuilder.equal(root.get("batchCourse"), batchCourse);
		
		createQuery.select(root).where(predicate);
		
		List<CourseType> resultList = (List<CourseType>) entityManager.createQuery(createQuery).getSingleResult();
		List<HashMap<String, Object>> collect = resultList.stream().map(result->{
			
			HashMap<String ,Object> map=new HashMap<String, Object>();
			map.put("courseType_Id", result.getCourseTypeId());
			map.put("courseType_Name", result.getCourseTypeName());
			map.put("courseType_Level", result.getCourseLevel());
			return map;
		}).collect(Collectors.toList());
		
		return collect;
	}


	@Override
	public List<HashMap<String, Object>> getAllCourseTypeDetailsById(String courseTypeId) {
		// TODO Auto-generated method stub
		return null;
	}

//
//	@Override
//	public List<HashMap<String, Object>> getAllCourseTypeDetailsById(String courseTypeId) {
//		List<HashMap<String,Object>> ListMap=new ArrayList<HashMap<String,Object>>();
//		CourseType courseType = getCourseTypeById(courseTypeId);
//		
//		if(courseType!= null) {
//			
//			
//			HashMap<String, Object> map=new HashMap<String, Object>();
//		
//			map.put("course_typeId", courseType.getCourseTypeId());
//			map.put("course_typeName", courseType.getCourseTypeName());
//			map.put("course_level", courseType.getCourseLevel());
//			map.put("active_courseType", courseType.getActive_courseType());
//			map.put("course_Type_Detailes", courseTypeDetailsDaoInterface.getCourseTypeDetails(courseTypeId));
//			map.put("course_Type_Essentials", courseTypeEssentialsDaoInterface.getAllEssentialsByCourseTypeId(courseTypeId));
//			map.put("course_Type_Objective", courseTypeObjectiveDaoInterface.getAllObjectiveByCourseTypeId(courseTypeId));
//			map.put("course_Type_KeyHighLight", courseTypeKeyHighlightsDaoInterface.getAllKeyHighlightByCourseTypeId(courseTypeId));
//			map.put("course_Type_Projects", courseTypeProjectsDaoInterface.getAllProjectsByCourseTypeId(courseTypeId));
//			map.put("course_Type_Tools", courseTypeToolsDaoInterface.getAllToolsByCourseTypeId(courseTypeId));
//			
//			List<Map<String, Object>> models = moduleDaoInterface.getAllModels(courseTypeId);
//			
//			
//			if(models!=null) {
//				
//			List<Object> Allmodules = models.stream().map(module->{
//				
//				
//				HashMap<String, Object> module_map=new HashMap<String, Object>();
//				
//				module_map.put("module_Id",module.get("module_Id"));
//				module_map.put("module_Title",module.get("module_Title"));
//				module_map.put("active_Module",module.get("active_Module"));
//				
//				if(module.get("module_Id")!=null) {
//				List<Map<String, Object>> lessons = lessonDaoInterface.getAllLessonsByModuleId(courseTypeId);
//				
//		
//				
//				
//				List<Object>  AllLesson = lessons.stream().map(lesson->{
//					HashMap<String, Object> lesson_Map=new HashMap<String, Object>();
//					
//					lesson_Map.put("lesson_Id", lesson.get("lesson_Id"));
//					lesson_Map.put("lesson_Tites", lesson.get("lesson_Tites"));
//					lesson_Map.put("active_lesson", lesson.get("active_lesson"));
//					
//					
//					List<Map<String, Object>> topics = topicDaoInterface.getAllTopicsByLessonId(lesson.get("lesson_Id").toString());
//					
//					
//					List<Object> AllTopics = topics.stream().map(topic->{
//						
//						
//						HashMap<String , Object> topic_map=new HashMap<String, Object>();
//						
//						topic_map.put("topic_id", topic.get("topic_id"));
//						topic_map.put("topic_title", topic.get("topic_title"));
//						topic_map.put("active_Topic", topic.get("active_Topic"));
//						
//						
//						
//						return topic_map;
//					}).collect(Collectors.toList());
//					
//					
//					lesson_Map.put("topics", AllTopics);
//					
//					return lesson_Map;
//				}).collect(Collectors.toList());
//				
//				
//				
//				module_map.put("lessons",AllLesson);
//				
//				}
//				
//				
//				
//				
//				
//				
//				
//			
//				
//				return module_map;
//			}).collect(Collectors.toList());
//			
//			
//			map.put("course_Type_modules", Allmodules);
//			ListMap.add(map);
//			
//		}
//	}
//		
//	
//		return ListMap;
//	}

}

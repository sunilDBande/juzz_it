package com.juzzIt.EducationProject.Services;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.juzzIt.EducationProject.Dao.EntityDao;
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
import com.juzzIt.EducationProject.DaoInterface.ToolImageDaoInterface;
import com.juzzIt.EducationProject.DaoInterface.TopicDaoInterface;
import com.juzzIt.EducationProject.Entity.Course;
import com.juzzIt.EducationProject.Entity.CourseType;
import com.juzzIt.EducationProject.Entity.Entities;
import com.juzzIt.EducationProject.Models.Responce;
import com.juzzIt.EducationProject.Models.UniqueIdGenerations;
import com.juzzIt.EducationProject.ServiceInterface.CourseImageServiceInterface;
import com.juzzIt.EducationProject.ServiceInterface.CourseTypeBagroundImageServiceInterface;
import com.juzzIt.EducationProject.ServiceInterface.CourseTypeServicesInterface;
import com.juzzIt.EducationProject.ServiceInterface.CourseTypeVideoServiceInterface;
import com.juzzIt.EducationProject.ServiceInterface.ToolImageServiceInterface;

@Service
public class CourseTypeServicesImplementation implements CourseTypeServicesInterface {

	@Autowired
	private CourseDaoInterface courseDaoInterface;
	@Autowired
	private CourseTypeDaoInterface courseTypeDaoInterface;

	@Autowired
	private UniqueIdGenerations uniqueIdGenerations;
	
	@Autowired
	private EntityDao entityDao;
	
	@Autowired
	private CourseTypeDetailsDaoInterface courseTypeDetailsDaoInterface;
	
	@Autowired
	private CourseTypeEssentialsDaoInterface courseTypeEssentialsDaoInterface;
	
	@Autowired
	private CourseTypeObjectiveDaoInterface courseTypeObjectiveDaoInterface;
	
	@Autowired
	private CourseTypeKeyHighlightsDaoInterface courseTypeKeyHighlightsDaoInterface;
	
	
	@Autowired
	private CourseTypeProjectsDaoInterface courseTypeProjectsDaoInterface;
	
	@Autowired
	private CourseTypeToolsDaoInterface courseTypeToolsDaoInterface;
	
	
	@Autowired
	private ModuleDaoInterface moduleDaoInterface;
	
	@Autowired
	private LessonDaoInterface lessonDaoInterface;
	
	@Autowired
	private TopicDaoInterface topicDaoInterface;
	
	@Autowired
	private ToolImageDaoInterface toolImageDaoInterface;
	
	@Autowired
	private CourseTypeBagroundImageServiceInterface courseTypeBagroundImageServiceInterface;
	
	@Autowired
	private CourseTypeImageDaoInterface courseTypeImageDaoInterface;
	@Autowired
	private CourseTypeVideoServiceInterface courseTypeVideoServiceInterface;
	@Autowired
	private CourseImageServiceInterface courseImageServiceInterface;
	
	@Autowired
	private ToolImageServiceInterface toolImageServiceInterface;
	
	@Autowired
	private CourseTypeImageService courseTypeImageService;
	
	
	@Override
	public Responce addCourseType(String courseId, HashMap<String, Object> courseType) throws Exception {

		Responce responce = new Responce();
      try {
		if (courseType.get("course_TypeName") == null || courseType.get("course_Level") == null) {
			responce.setMassege("course type 'name' or 'course level'  is need create the coursetype");
			responce.setStatus(false);
			return responce;
		}
		
		Course course = courseDaoInterface.getCourseById(courseId);

		if (course == null) {
			responce.setMassege("course with the given id not prasent");
			responce.setStatus(false);
			return responce;
		}

		List<Map<String, Object>> existingCourseType = courseTypeDaoInterface.getCourseTypeByCourseId(courseId);

		System.out.println(existingCourseType);
		List<Map<String, Object>> filter = existingCourseType.stream().filter(type -> {

			return ((String) type.get("course_typeName")).equalsIgnoreCase((String) courseType.get("course_TypeName"));
		}).collect(Collectors.toList());

		System.out.println(filter);

		if (!filter.isEmpty()) {
			responce.setMassege("course type for this course is alredy added");
			responce.setStatus(false);
			return responce;
		}
		HashMap<String, Object> data = new HashMap<String, Object>();
		data.put("Entity_Name", "CourseType");
		data.put("Entity_SubName", "COURTYP");
		data.put("Entity_Count", 0);
		
		 HashMap<String, Object> uniqueIdGeneration = uniqueIdGenerations.uniqueIdGeneration(data);
		 CourseType newCourseType = new CourseType();
		 newCourseType.setCourse(course);
		
			// *******
			System.out.println("IMP---> " + uniqueIdGeneration);
			System.out.println("IMP3---> " + uniqueIdGeneration.get("count"));
			String subName = (String) uniqueIdGeneration.get("Entity_SubName");
			long count = (long) uniqueIdGeneration.get("count");
			if (count < 10) {
				newCourseType.setCourseTypeId(subName + "00" + count);
			} else if(count <100 && count>10){
				newCourseType.setCourseTypeId(subName + "0" + count);
			}else {
				newCourseType.setCourseTypeId(subName + "" + count);
			}
			// *******
		newCourseType.setCourseLevel((String) courseType.get("course_Level"));
		newCourseType.setCourseTypeName((String) courseType.get("course_TypeName"));
		newCourseType.setActive_courseType("D");

		CourseType addedCourseType = courseTypeDaoInterface.addCourseType(newCourseType);
		if (addedCourseType == null) {
			responce.setMassege("course type is not added to database");
			responce.setStatus(false);
			return responce;
		}else {
			 entityDao.updateCountForCourseTable(data);		
		}

		responce.setMassege("course type added succesfully");
		responce.setStatus(true);
      }catch (Exception e) {
		throw new Exception("Getting problem while adding CourseType");
	}
		return responce;
	}

	@Override
	public List<HashMap<String, Object>> getAllCourseByCourseId(String courseId) throws Exception {
		
		List<HashMap<String , Object>> listMap=new ArrayList<HashMap<String , Object>>();
		try {
			
		Course course = courseDaoInterface.getCourseById(courseId);
		HashMap<String, Object> map=new HashMap<String, Object>();
		map.put("course_Id", course.getCourseId());
		map.put("course_name", course.getCourseName());
		map.put("course_active", course.getCourseActive());
		map.put("future_course", course.getFutureCourseStatus());
		map.put("course_Image",courseImageServiceInterface.getCoueseImage(course.getCourseId()));
		List<Map<String, Object>> courseTypes = courseTypeDaoInterface.getCourseTypeByCourseId(course.getCourseId());
		List<Map<String, Object>> collect = courseTypes.stream().map(result->{
		result.put("courseType_bagroundImage", courseTypeBagroundImageServiceInterface.getCourseTypeBagroundImage(result.get("course_typeId").toString()));
		result.put("courseTypeImage", courseTypeImageService.getCourseTypeImage(result.get("course_typeId").toString()));	
					return result;
		}).collect(Collectors.toList());
		map.put("Course_type", collect);
		listMap.add(map);
		}catch (Exception e) {
			throw new Exception("Getting problem while getting All Courses By CourseId");
		}
		return listMap;
	}

	@Override
	public Responce updateCourseType(String courseTypeId, HashMap<String, Object> courseType) throws Exception {
		return courseTypeDaoInterface.updateCourseType(courseTypeId, courseType);
	}

	@Override
	public Responce deleteCourseType(String courseTypeId) throws Exception {
		return courseTypeDaoInterface.deleteCourseTypeById(courseTypeId);
	}

	
	@Override
	public List<HashMap<String, Object>> getAllCourseTypeDetailsById(String courseTypeId) throws Exception {
		List<HashMap<String,Object>> ListMap=new ArrayList<HashMap<String,Object>>();
		CourseType courseType =courseTypeDaoInterface.getCourseTypeById(courseTypeId);
		try {
		if(courseType!= null) {
			
			
			HashMap<String, Object> map=new HashMap<String, Object>();
		
			map.put("course_typeId", courseType.getCourseTypeId());
			map.put("course_typeName", courseType.getCourseTypeName());
			map.put("course_level", courseType.getCourseLevel());
			map.put("active_courseType", courseType.getActive_courseType());
			map.put("courseType_video", courseTypeVideoServiceInterface.getCourseTypeVideo(courseType.getCourseTypeId()));
			map.put("courseType_bagroundImage", courseTypeBagroundImageServiceInterface.getCourseTypeBagroundImage(courseType.getCourseTypeId()));
			map.put("courseType_Image", courseTypeImageService.getCourseTypeImage(courseType.getCourseTypeId()));	
			List<HashMap<String, Object>> courseDataByCourseTypeId = courseDaoInterface.getCourseDataByCourseTypeId(courseTypeId);
			map.put("course", courseDataByCourseTypeId);
			map.put("course_Type_Detailes", courseTypeDetailsDaoInterface.getCourseTypeDetails(courseTypeId));
			map.put("course_Type_Essentials", courseTypeEssentialsDaoInterface.getAllEssentialsByCourseTypeId(courseTypeId));
			map.put("course_Type_Objective", courseTypeObjectiveDaoInterface.getAllObjectiveByCourseTypeId(courseTypeId));
			map.put("course_Type_KeyHighLight", courseTypeKeyHighlightsDaoInterface.getAllKeyHighlightByCourseTypeId(courseTypeId));
			map.put("course_Type_Projects", courseTypeProjectsDaoInterface.getAllProjectsByCourseTypeId(courseTypeId));
			 List<Map<String, Object>> tools = courseTypeToolsDaoInterface.getAllToolsByCourseTypeId(courseTypeId);
			 List<Map<String, Object>> collect = tools.stream().map(result->{
				 result.put("tool_images", toolImageServiceInterface.getToolImage(result.get("tool_id").toString()));
				 return result;
			 }).collect(Collectors.toList());
				map.put("course_Type_Tools", collect);	
			List<Map<String, Object>> models = moduleDaoInterface.getAllModels(courseTypeId);
			
			
			if(models!=null) {
				
			List<Object> Allmodules = models.stream().map(module->{
				HashMap<String, Object> module_map=new HashMap<String, Object>();
				module_map.put("module_Id",module.get("module_Id"));
				module_map.put("module_Title",module.get("module_Title"));
				module_map.put("active_Module",module.get("active_Module"));
				if(module.get("module_Id")!=null) {
				List<Map<String, Object>> lessons = null;
				try {
					lessons = lessonDaoInterface.getAllLessonsByModuleId(module.get("module_Id").toString());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
		
				
				
				List<Object>  AllLesson = lessons.stream().map(lesson->{
					HashMap<String, Object> lesson_Map=new HashMap<String, Object>();
					
					lesson_Map.put("lesson_Id", lesson.get("lesson_Id"));
					lesson_Map.put("lesson_Tites", lesson.get("lesson_Tites"));
					lesson_Map.put("active_lesson", lesson.get("active_lesson"));
					
					
					List<Map<String, Object>> topics = null;
					try {
						topics = topicDaoInterface.getAllTopicsByLessonId(lesson.get("lesson_Id").toString());
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					
					List<Object> AllTopics = topics.stream().map(topic->{
						
						
						HashMap<String , Object> topic_map=new HashMap<String, Object>();
						
						topic_map.put("topic_id", topic.get("topic_id"));
						topic_map.put("topic_title", topic.get("topic_title"));
						topic_map.put("active_Topic", topic.get("active_Topic"));
						
						
						
						return topic_map;
					}).collect(Collectors.toList());
					
					
					lesson_Map.put("topics", AllTopics);
					
					return lesson_Map;
				}).collect(Collectors.toList());
				
				
				
				module_map.put("lessons",AllLesson);
				
				}
				
				return module_map;
			}).collect(Collectors.toList());
			
			
			map.put("course_Type_modules", Allmodules);
			ListMap.add(map);
			
		}
	}
		
		}catch (Exception e) {
			throw new Exception("Getting problem while getting All CourseType DetailsById");
		}
		return ListMap;
	}

}

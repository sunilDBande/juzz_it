package com.juzzIt.EducationProject.Dao;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.juzzIt.EducationProject.DaoInterface.CourseDaoInterface;
import com.juzzIt.EducationProject.Entity.Course;
import com.juzzIt.EducationProject.Models.Responce;
import com.juzzIt.EducationProject.Repositary.CourseRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;


@Component
public class CourseDao implements CourseDaoInterface{

	@Autowired
private	CourseRepository courseRepo;
	
	
	@Autowired
	private EntityManager entityManager;
	
	
	@Override
	public Course addCourse(Course course) {
		return courseRepo.save(course);
	}

	@Override
	public boolean courseExist(String courseName) {
		return courseRepo.existsByCourseName(courseName);
	}

	@Override
	public Responce updateCourse(String courseId,HashMap<String, Object> course) throws Exception {
	Responce responce =new Responce();
		Course currentCourse=getCourseById(courseId);System.out.println(course);
		try {
		if(currentCourse==null) {
			responce.setMassege("course with the given id not prasent");
			responce.setStatus(false);
			return responce;
		}
		
		
		if(((String)course.get("course_name"))!=null) {
			currentCourse.setCourseName((String)course.get("course_name"));
			
			System.out.println("123");
		}
		
		if(((String)course.get("course_active"))!=null) {
			String  course_active=	currentCourse.getCourseActive();

			System.out.println("1234");
			if(course_active.equalsIgnoreCase("D")) {
				currentCourse.setCourseActive("A");
			}else {
				currentCourse.setCourseActive("D");
			}
				}
		
		
		
		if(course.get("future_course")!=null) {
			
			if(currentCourse.getFutureCourseStatus().equalsIgnoreCase("D")) {
				currentCourse.setFutureCourseStatus("A");
			}else {
				currentCourse.setFutureCourseStatus("D");
			}
			
		}
		if(((String)course.get("temp_delete"))!=null) {
			
			String temp_delete=currentCourse.getTempDelete();

			System.out.println("12345");
			if(temp_delete.equalsIgnoreCase("D")) {
				currentCourse.setTempDelete("A");
			}else {
				currentCourse.setTempDelete("D");
			}
			
		}
		
		Course outPutCourse = courseRepo.save(currentCourse);
		
		if(outPutCourse==null) {
			
			responce.setMassege("course is not updated");
			responce.setStatus(false);
			return responce;
		}
		
		responce.setMassege("detail updated successfully");
		responce.setStatus(true);
		}catch (Exception e) {
		   throw new Exception("Getting problem while updating Course");
		}
		return responce;
	}

	@Override
	public Course getCourseById(String courseId) {
		
		
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		
		CriteriaQuery<Course> createQuery = criteriaBuilder.createQuery(Course.class);
		
		Root<Course> root = createQuery.from(Course.class);

		Predicate predicate = criteriaBuilder.equal(root.get("courseId"), courseId);
		
		createQuery.select(root).where(predicate);
		
		
		List<Course> resultList = entityManager.createQuery(createQuery).getResultList();
		
		Optional<Course> course = courseRepo.findById(courseId);
		
		if(!resultList.isEmpty()) {
			return resultList.get(0);
	}
		return null ;
	}

	
	
	@Override
	public List<HashMap<String, Object>> getCourses() throws Exception {
          CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
          CriteriaQuery<Course> createQuery = criteriaBuilder.createQuery(Course.class);
          List<HashMap<String, Object>> collect = null;
          try {
          Root<Course> root = createQuery.from(Course.class);
          
          Predicate condition1 = criteriaBuilder.equal(root.get("tempDelete"), "A");
   
          
          createQuery.select(root).where(condition1);
          
          List<Course> resultList = entityManager.createQuery(createQuery).getResultList();
          
           collect = resultList.stream().map(currentCourse->{
        	  
        	  
        	  HashMap<String , Object> map=new HashMap<String, Object>();
        	  
        	  map.put("course_Id",(Object)currentCourse.getCourseId() );
        	  
        	  map.put("course_name",(Object)currentCourse.getCourseName() );
        	  map.put("course_active",(Object)currentCourse.getCourseActive() );
        	  map.put("temp_delete",(Object)currentCourse.getTempDelete() );
        	  map.put("future_course",(Object)currentCourse.getFutureCourseStatus() );
        	 
        	  return map;
          }).collect(Collectors.toList());
          }catch (Exception e) {
			throw new Exception("Getting problem while getting Courses");
		}
		
		return collect;
	}

	@Override
	public List<HashMap<String, Object>> getTempDeletedCourses() throws Exception {
		  CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
          CriteriaQuery<Course> createQuery = criteriaBuilder.createQuery(Course.class);
          List<HashMap<String, Object>> collect = null;
          try {
          Root<Course> root = createQuery.from(Course.class);
          
          Predicate condition1 = criteriaBuilder.equal(root.get("tempDelete"), "D");
   
          
          createQuery.select(root).where(condition1);
          
          List<Course> resultList = entityManager.createQuery(createQuery).getResultList();
          
           collect = resultList.stream().map(currentCourse->{
        	  
        	  
        	  HashMap<String , Object> map=new HashMap<String, Object>();
        	  
        	  map.put("course_Id",(Object)currentCourse.getCourseId() );
        	  map.put("course_name",(Object)currentCourse.getCourseId() );
        	  map.put("course_active",(Object)currentCourse.getCourseActive() );
        	  map.put("temp_delete",(Object)currentCourse.getTempDelete() );
        	  map.put("future_course",(Object)currentCourse.getFutureCourseStatus() );
        	  return map;
          }).collect(Collectors.toList());
          }catch (Exception e) {
			throw new Exception("Getting problem while getting TempDeletedCourses");
		}
		
		return collect;
	}

	@Override
	public Responce deleteCourse(String courseId) {
		Course course = getCourseById(courseId);
		Responce responce=new Responce();
		if(course!=null) {
			courseRepo.delete(course);
			responce.setMassege("course deleted successfully");
			responce.setStatus(true);
		}else {
			responce.setMassege("course with the given id not found");
			responce.setStatus(false);
		}
		return responce;
	}
	
	
	

	@Override
	public List<HashMap<String, Object>> getCourseDataByCourseTypeId(String CourseTypeId) {
		
		String data[]= {"course_id","course_name","course_active"};
		
		String query=" select course.course_id,course.course_name,course.course_active  "
				+" from course, course_type "
				+"  where "
				+"  course_type.course_type_id= '"+CourseTypeId+"'"
				+"  and course_type.cource_id = course.course_id";
		
		Query createNamedQuery = entityManager.createNativeQuery(query);
		
		List<Object[]> resultList = createNamedQuery.getResultList();
		List<HashMap<String,Object>> finalData= new ArrayList<HashMap<String,Object>>()	;	
		
		for(Object res[]:resultList) {
			
			LinkedHashMap<String , Object> map=new LinkedHashMap<String, Object>();
			for(int i=0;i<res.length;i++) {
				
				if(res[i]==null||res[i].toString().trim().isEmpty()) {
					map.put(data[i], "");
				}else {
					map.put(data[i], res[i].toString());
				}
			}
			finalData.add(map);
		}
		return finalData;
	}

	

	
}

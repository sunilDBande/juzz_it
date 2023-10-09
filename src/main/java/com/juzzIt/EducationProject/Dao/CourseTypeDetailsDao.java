package com.juzzIt.EducationProject.Dao;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.juzzIt.EducationProject.DaoInterface.CourseTypeDetailsDaoInterface;
import com.juzzIt.EducationProject.Entity.CourseType;
import com.juzzIt.EducationProject.Entity.CourseTypeDetails;
import com.juzzIt.EducationProject.Repositary.CourseTypeDetailsRepositary;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;


@Component
public class CourseTypeDetailsDao implements CourseTypeDetailsDaoInterface {

	
	@Autowired
	private EntityManager entityManager;
	
	@Autowired
	private CourseTypeDetailsRepositary courseTypeDetailsRepo;
	
	 private CourseTypeDao courseTypeDao;

	    @Autowired
	    public CourseTypeDetailsDao(CourseTypeDao courseTypeDao) {
	        this.courseTypeDao = courseTypeDao;
	    }
	@Autowired
	private CourseTypeDetailsRepositary courseTypeDetailsRepositary;
	
	@Override
	public CourseTypeDetails addCourseTypeDetails(CourseTypeDetails courseDetails) {
		return courseTypeDetailsRepo.save(courseDetails);
	}

	@Override
	public List<HashMap<String, Object>> getCourseTypeDetails(String courseTypeId) throws Exception {
	    
		List<HashMap<String, Object>> collect = null;
		try {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<CourseTypeDetails> createQuery = criteriaBuilder.createQuery(CourseTypeDetails.class);
		
		
		Root<CourseTypeDetails> root = createQuery.from(CourseTypeDetails.class);
		
		CourseType courseType= courseTypeDao.getCourseTypeById(courseTypeId);
		
		if(courseType==null) {
			return null;
		}
		
		Predicate predicate = criteriaBuilder.equal(root.get("courseType"), courseType);
		
		createQuery.select(root).where(predicate);
		
		
		List<CourseTypeDetails> resultList = entityManager.createQuery(createQuery).getResultList();
		
		 collect = resultList.stream().map(result->{
			
			HashMap<String ,Object> map=new HashMap<String, Object>();
			map.put("detail_Id", result.getDetailId());
			map.put("actual_Price", result.getActualPrice());
			map.put("discount_Price", result.getDiscountPrice());
			map.put("course_Desc", result.getCourseDesc());
			map.put("course_Overview", result.getCourseOverview());
			map.put("course_DuriationDesc", result.getCourseDuriationDesc());
			
			
			return map;
		}).collect(Collectors.toList());
		
		System.out.println(collect);
		}catch (Exception e) {
			throw new Exception("Getting problem while getting CourseTypeDetails");
		}
		return collect;
	}
	
	

}

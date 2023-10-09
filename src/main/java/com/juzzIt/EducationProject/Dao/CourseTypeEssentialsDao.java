package com.juzzIt.EducationProject.Dao;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.juzzIt.EducationProject.DaoInterface.CourseTypeDaoInterface;
import com.juzzIt.EducationProject.DaoInterface.CourseTypeEssentialsDaoInterface;
import com.juzzIt.EducationProject.Entity.CourseType;
import com.juzzIt.EducationProject.Entity.CourseTypeEssentials;
import com.juzzIt.EducationProject.Models.Responce;
import com.juzzIt.EducationProject.Repositary.CourseTypeEssentialsRepositary;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;


@Component
public class CourseTypeEssentialsDao implements CourseTypeEssentialsDaoInterface {

	@Autowired
	private CourseTypeDaoInterface courseTypeDaoInterface;
	
	@Autowired
	private EntityManager entityManager;
	
	@Autowired
	private CourseTypeEssentialsRepositary courseTypeEssentialsRepo;
	
	
	
	
	
	
	
	@Override
	public CourseTypeEssentials addEssential(CourseTypeEssentials essential) {
		
		return courseTypeEssentialsRepo.save(essential);
	}
	@Override
	public Responce deleteEssential(String essentialId) {
		
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		
		CriteriaQuery<CourseTypeEssentials> createQuery = criteriaBuilder.createQuery(CourseTypeEssentials.class);		
		
		Root<CourseTypeEssentials> root = createQuery.from(CourseTypeEssentials.class);
		
		Predicate predicate = criteriaBuilder.equal(root.get("essentialId"), essentialId);
		createQuery.select(root).where(predicate);
		List<CourseTypeEssentials> resultList = entityManager.createQuery(createQuery).getResultList();
		
		
//		Optional<CourseTypeEssentials> essential = courseTypeEssentialsRepo.findById(essentialId);
		Responce responce=new Responce();
		if(!resultList.isEmpty()) {
			courseTypeEssentialsRepo.delete(resultList.get(0));
   responce.setMassege("essential deleted successfully");
   responce.setStatus(true);

		}else {
			responce.setMassege("essential ewithe the given id not found");
			responce.setStatus(false);
		}
		return responce;
	}
	@Override
	public List<Map<String, Object>> getAllEssentialsByCourseTypeId(String courseTypeId) throws Exception {
		CourseType courseType = courseTypeDaoInterface.getCourseTypeById(courseTypeId);
		
		List<Map<String, Object>> collect = null;
		try {
		if(courseType==null) {
			return null;
		}
		
		
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<CourseTypeEssentials> createQuery = criteriaBuilder.createQuery(CourseTypeEssentials.class);
		
		Root<CourseTypeEssentials> root = createQuery.from(CourseTypeEssentials.class);
		
		
		Predicate condetion = criteriaBuilder.equal(root.get("courseType"),courseType);
		
		createQuery.select(root).where(condetion);
		
		List<CourseTypeEssentials> resultList = entityManager.createQuery(createQuery).getResultList();
		 collect = resultList.stream().map(result->{
			
			Map<String, Object> map=new LinkedHashMap<String, Object>();
			
			map.put("essential_id", result.getEssentialId());
			map.put("essential_Type", result.getEssentialType());
			map.put("essentil_Desc", result.getEssentialDesc());
			return map;
		}).collect(Collectors.toList());
		}catch (Exception e) {
			throw new Exception("Getting problem while getting All Essentials By CourseTypeId");
		}
		
		return collect;
	}

}

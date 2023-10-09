package com.juzzIt.EducationProject.Dao;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.juzzIt.EducationProject.DaoInterface.CourseTypeDaoInterface;
import com.juzzIt.EducationProject.DaoInterface.CourseTypeToolsDaoInterface;
import com.juzzIt.EducationProject.Entity.CourseType;
import com.juzzIt.EducationProject.Entity.CourseTypeTools;
import com.juzzIt.EducationProject.Models.Responce;
import com.juzzIt.EducationProject.Repositary.CourseTypeToolsRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
@Component
public class CourseTypeToolsDao implements CourseTypeToolsDaoInterface{


	@Autowired
	private CourseTypeDaoInterface courseTypeDaoInterface;
	
	@Autowired
	private EntityManager entityManager;
	
	
	@Autowired
	private CourseTypeToolsRepository courseTypeToolsRepository;
	
	@Override
	public CourseTypeTools addTools(CourseTypeTools tool) {
	
		return courseTypeToolsRepository.save(tool);
	}

	@Override
	public Responce deleteTools(String toolId) throws Exception {
		Responce responce=new Responce();
		try {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		
		
		CriteriaQuery<CourseTypeTools> createQuery = criteriaBuilder.createQuery(CourseTypeTools.class);
		
		Root<CourseTypeTools> root = createQuery.from(CourseTypeTools.class);
		Predicate predicate = criteriaBuilder.equal(root.get("toolId"), toolId);
		
		
		createQuery.select(root).where(predicate);
		
		List<CourseTypeTools> resultList = entityManager.createQuery(createQuery).getResultList();
		
		
		Optional<CourseTypeTools> tool = courseTypeToolsRepository.findById(toolId);
		
		if(!resultList.isEmpty()) {
			courseTypeToolsRepository.delete(resultList.get(0));
			responce.setMassege("tool deleted successfully");
			responce.setStatus(true);
		}else {
			responce.setMassege("tool with the given id not found");
			responce.setStatus(false);
		}
		}catch (Exception e) {
			throw new Exception("Getting problem while deleting Tools");
		}
		return responce;
	}

	@Override
	public List<Map<String, Object>> getAllToolsByCourseTypeId(String courseTypeId) throws Exception {
		List<Map<String, Object>> collect = null;
		try {
CourseType courseType = courseTypeDaoInterface.getCourseTypeById(courseTypeId);
		
		
		if(courseType==null) {
			return null;
		}
		
		
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<CourseTypeTools> createQuery = criteriaBuilder.createQuery(CourseTypeTools.class);
		
		Root<CourseTypeTools> root = createQuery.from(CourseTypeTools.class);
		
		Predicate condetion = criteriaBuilder.equal(root.get("courseType"),courseType);
		
		createQuery.select(root).where(condetion);
		
		List<CourseTypeTools> resultList = entityManager.createQuery(createQuery).getResultList();
		 collect = resultList.stream().map(result->{
			
			Map<String, Object> map=new LinkedHashMap<String, Object>();
			
			map.put("tool_id", result.getToolId());
			map.put("tool_name", result.getToolName());
			return map;
		}).collect(Collectors.toList());
		
		}catch (Exception e) {
			throw new Exception("Getting problem while getting All Tools By CourseTypeId");
		}
		return collect;
	}

}

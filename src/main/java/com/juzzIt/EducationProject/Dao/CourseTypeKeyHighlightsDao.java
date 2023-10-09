package com.juzzIt.EducationProject.Dao;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.juzzIt.EducationProject.DaoInterface.CourseTypeDaoInterface;
import com.juzzIt.EducationProject.DaoInterface.CourseTypeKeyHighlightsDaoInterface;
import com.juzzIt.EducationProject.Entity.CourseType;
import com.juzzIt.EducationProject.Entity.CourseTypeKeyHighlights;
import com.juzzIt.EducationProject.Models.Responce;
import com.juzzIt.EducationProject.Repositary.CourseTypeKeyHighlightsRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
@Component
public class CourseTypeKeyHighlightsDao implements CourseTypeKeyHighlightsDaoInterface {

	
	@Autowired
	private CourseTypeKeyHighlightsRepository courseTypeKeyHighlightsRepo;
	
	@Autowired
	private CourseTypeDaoInterface courseTypeDaoInterface;
	
	@Autowired
	private EntityManager entityManager;
	
	@Override
	public CourseTypeKeyHighlights addKeyHighlight(CourseTypeKeyHighlights KeyHighlight) {
		
		return courseTypeKeyHighlightsRepo.save(KeyHighlight);
	}

	@Override
	public Responce deleteKeyHighlight(String keyHighlightId) throws Exception {
		
		
		Responce responce =new Responce();
		try {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		
		
		CriteriaQuery<CourseTypeKeyHighlights> createQuery = criteriaBuilder.createQuery(CourseTypeKeyHighlights.class);
		
		
		Root<CourseTypeKeyHighlights> root = createQuery.from(CourseTypeKeyHighlights.class);
		
		Predicate predicate = criteriaBuilder.equal(root.get("highlightId"), keyHighlightId);
		createQuery.select(root).where(predicate);
		List<CourseTypeKeyHighlights> resultList = entityManager.createQuery(createQuery).getResultList();
		
		
//		Optional<CourseTypeKeyHighlights> highlight = courseTypeKeyHighlightsRepo.findById(keyHighlightId);
	
		if(!resultList.isEmpty()) {
			courseTypeKeyHighlightsRepo.delete(resultList.get(0));
			responce.setMassege("key highlight deleted successfully");
			responce.setStatus(true);
		}else {
			
			responce.setMassege("Key hiligth withe the given id not found");
			responce.setStatus(false);
		}
		}catch (Exception e) {
			throw new Exception("Getting problem while deleting KeyHighlight");
		}
		return responce;
	}

	@Override
	public List<Map<String, Object>> getAllKeyHighlightByCourseTypeId(String courseTypeId) throws Exception {
		List<Map<String, Object>> collect = null;
		try {
CourseType courseType = courseTypeDaoInterface.getCourseTypeById(courseTypeId);
		
		
		if(courseType==null) {
			return null;
		}
		
		
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<CourseTypeKeyHighlights> createQuery = criteriaBuilder.createQuery(CourseTypeKeyHighlights.class);
		
		Root<CourseTypeKeyHighlights> root = createQuery.from(CourseTypeKeyHighlights.class);
		
		Predicate condetion = criteriaBuilder.equal(root.get("courseType"),courseType);
		
		createQuery.select(root).where(condetion);
		
		List<CourseTypeKeyHighlights> resultList = entityManager.createQuery(createQuery).getResultList();
		 collect = resultList.stream().map(result->{
			
			Map<String, Object> map=new LinkedHashMap<String, Object>();
			
			map.put("highlight_Id", result.getHighlightId());
			map.put("highlight", result.getHighlight());
			return map;
		}).collect(Collectors.toList());
		}catch (Exception e) {
			throw new Exception("Getting problem while getting All KeyHighlight By CourseTypeId");
		}
		
		return collect;
	}

}

package com.juzzIt.EducationProject.Dao;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.juzzIt.EducationProject.DaoInterface.CourseTypeDaoInterface;
import com.juzzIt.EducationProject.DaoInterface.CourseTypeObjectiveDaoInterface;
import com.juzzIt.EducationProject.Entity.CourseType;
import com.juzzIt.EducationProject.Entity.CourseTypeObjective;
import com.juzzIt.EducationProject.Models.Responce;
import com.juzzIt.EducationProject.Repositary.CourseTypeObjectiveRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
@Component
public class CourseTypeObjectiveDao implements CourseTypeObjectiveDaoInterface{


	@Autowired
	private CourseTypeDaoInterface courseTypeDaoInterface;
	
	@Autowired
	private EntityManager entityManager;
	
	@Autowired
private CourseTypeObjectiveRepository courseTypeObjectiveRepos;
	
	
	@Override
	public CourseTypeObjective addObjective(CourseTypeObjective objective) {
		
		
		
		return courseTypeObjectiveRepos.save(objective);
	}


	@Override
	public Responce deleteObjective(String objectiveId) throws Exception {
		Responce responce=new Responce();
		try {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		
		CriteriaQuery<CourseTypeObjective> createQuery = criteriaBuilder.createQuery(CourseTypeObjective.class);
		
		Root<CourseTypeObjective> root = createQuery.from(CourseTypeObjective.class);
		
		Predicate equal = criteriaBuilder.equal(root.get("objectiveId"), objectiveId);
		createQuery.select(root).where(equal);
		
		List<CourseTypeObjective> resultList = entityManager.createQuery(createQuery).getResultList();
		
//		Optional<CourseTypeObjective> objective = courseTypeObjectiveRepos.findById(objectiveId);
		
		if(!resultList.isEmpty()) {
			courseTypeObjectiveRepos.delete(resultList.get(0));
			responce.setMassege("object deleted successfully");
			responce.setStatus(true);
		}else {
			responce.setMassege("objective with the given id nopt found");
			responce.setStatus(false);
		}
		}catch (Exception e) {
			throw new Exception("Getting problem while deleting Objective");
		}
		return responce;
	}


	@Override
	public List<Map<String, Object>> getAllObjectiveByCourseTypeId(String courseTypeId) throws Exception {
		List<Map<String, Object>> collect = null;
		try {
CourseType courseType = courseTypeDaoInterface.getCourseTypeById(courseTypeId);
		
		
		if(courseType==null) {
			return null;
		}
		
		
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<CourseTypeObjective> createQuery = criteriaBuilder.createQuery(CourseTypeObjective.class);
		
		Root<CourseTypeObjective> root = createQuery.from(CourseTypeObjective.class);
		
		Predicate condetion = criteriaBuilder.equal(root.get("courseType"),courseType);
		
		createQuery.select(root).where(condetion);
		
		List<CourseTypeObjective> resultList = entityManager.createQuery(createQuery).getResultList();
		 collect = resultList.stream().map(result->{
			
			Map<String, Object> map=new LinkedHashMap<String, Object>();
			
			map.put("objective_id", result.getObjectiveId());
			map.put("objective", result.getObjective());
			return map;
		}).collect(Collectors.toList());
		}catch (Exception e) {
			throw new Exception("Getting problem while getting All Objective By CourseTypeId");
		}
		
		return collect;
	}

}

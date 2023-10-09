package com.juzzIt.EducationProject.Dao;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.juzzIt.EducationProject.DaoInterface.CourseTypeDaoInterface;
import com.juzzIt.EducationProject.DaoInterface.CourseTypeProjectsDaoInterface;
import com.juzzIt.EducationProject.Entity.CourseType;
import com.juzzIt.EducationProject.Entity.CourseTypeProjects;
import com.juzzIt.EducationProject.Models.Responce;
import com.juzzIt.EducationProject.Repositary.CourseTypeProjectsRepositary;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
@Component
public class CourseTypeProjectsDao implements CourseTypeProjectsDaoInterface{


	@Autowired
	private CourseTypeDaoInterface courseTypeDaoInterface;
	
	@Autowired
	private EntityManager entityManager;
	
	
	@Autowired
	private CourseTypeProjectsRepositary courseTypeProjectsRepo;
	
	
	
	
	@Override
	public CourseTypeProjects addProjects(CourseTypeProjects project) {
		// TODO Auto-generated method stub
		return courseTypeProjectsRepo.save(project);
	}
	@Override
	public Responce deleteProjects(String projectsId) throws Exception {
		
		Responce responce=new Responce();
		try {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		
		CriteriaQuery<CourseTypeProjects> createQuery = criteriaBuilder.createQuery(CourseTypeProjects.class);
		Root<CourseTypeProjects> root = createQuery.from(CourseTypeProjects.class);
		
Predicate equal = criteriaBuilder.equal(root.get("projectId"), projectsId);
		
createQuery.select(root).where(equal);
List<CourseTypeProjects> resultList = entityManager.createQuery(createQuery).getResultList();
		
		
		Optional<CourseTypeProjects> project = courseTypeProjectsRepo.findById(projectsId);
	
		if(!resultList.isEmpty()) {
			courseTypeProjectsRepo.delete(resultList.get(0));
			responce.setMassege("project deleted successfully");
			responce.setStatus(true);
		}else {
			responce.setMassege("project with the given id not found");
			responce.setStatus(false);
		}
		}catch (Exception e) {
			throw new Exception("Getting problem while deleting Projects");
		}
		return responce;
	}
	@Override
	public List<Map<String, Object>> getAllProjectsByCourseTypeId(String courseTypeId) throws Exception {
		List<Map<String, Object>> collect = null;
		try {
CourseType courseType = courseTypeDaoInterface.getCourseTypeById(courseTypeId);
		
		
		if(courseType==null) {
			return null;
		}
		
		
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<CourseTypeProjects> createQuery = criteriaBuilder.createQuery(CourseTypeProjects.class);
		
		Root<CourseTypeProjects> root = createQuery.from(CourseTypeProjects.class);
		
		Predicate condetion = criteriaBuilder.equal(root.get("courseType"),courseType);
		
		createQuery.select(root).where(condetion);
		
		List<CourseTypeProjects> resultList = entityManager.createQuery(createQuery).getResultList();
		collect = resultList.stream().map(result->{
			
			Map<String, Object> map=new LinkedHashMap<String, Object>();
			
			map.put("project_id", result.getProjectId());
			map.put("project_name", result.getProjectName());
			map.put("project_desc", result.getProjectDesc());
			return map;
		}).collect(Collectors.toList());
		}catch (Exception e) {
			throw new Exception("Getting problem while getting All Projects By CourseTypeId");
		}
		
		return collect;
	}

}

	package com.juzzIt.EducationProject.Dao;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.juzzIt.EducationProject.DaoInterface.CourseCategoryDaoInterface;
import com.juzzIt.EducationProject.Entity.CourseCategory;
import com.juzzIt.EducationProject.Models.Responce;
import com.juzzIt.EducationProject.Repositary.CourseCategoryRepositary;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

@Repository
public class CourseCategoryDao implements CourseCategoryDaoInterface{

	
	@Autowired
	private CourseCategoryRepositary courseCategoryRepo;
	
	
	@Autowired
	private EntityManager entityManager;

	@Override
	public CourseCategory addCategory(CourseCategory category) {
		return courseCategoryRepo.save(category);
	}

	@Override
	public Responce deleteCategory(String categoryId) throws Exception {
		Optional<CourseCategory> category = courseCategoryRepo.findById(categoryId);
		
		Responce responce =new Responce();
		try {
		if(category.isPresent()) {
			courseCategoryRepo.delete(category.get());
			responce.setMassege("category deleted successfully");
			responce.setStatus(true);
		}else {
			responce.setMassege("category withe the given id not found");
			responce.setStatus(false);
		}
		}catch (Exception e) {
			throw new Exception("Getting problem while deleting Category");
		}
		return responce;
	}

	@Override
	public List<Map<String, Object>> getCategorys() throws Exception {
		
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		List<Map<String, Object>> collect = null;
		try {
		CriteriaQuery<CourseCategory> createQuery = builder.createQuery(CourseCategory.class);
		
		Root<CourseCategory> root = createQuery.from(CourseCategory.class);
		
		createQuery.select(root);
		
		List<CourseCategory> resultList = entityManager.createQuery(createQuery).getResultList();
		 collect = resultList.stream().map(result->{
			Map<String, Object> map=new LinkedHashMap<String, Object>();
			
			map.put("category_Id", result.getCategoryId());
			map.put("category_Name", result.getCategoryName());
			
			return map;
		}).collect(Collectors.toList());
		}catch (Exception e) {
			throw new Exception("Getting problem while getting Categorys");
		}
		
		return collect;
	}

	@Override
	public boolean existsByCategoryName(String cataegoryName) {
		return courseCategoryRepo.existsByCategoryName(cataegoryName);
	}
	
}

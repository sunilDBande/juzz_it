package com.juzzIt.EducationProject.Dao;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.juzzIt.EducationProject.DaoInterface.BatchCoursePlacementsDaoInterface;
import com.juzzIt.EducationProject.Entity.BatchCourse;
import com.juzzIt.EducationProject.Entity.BatchCoursePlacements;
import com.juzzIt.EducationProject.Models.Responce;
import com.juzzIt.EducationProject.Repositary.BatchCoursePlacementsRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

@Component
public class BatchCoursePlacementsDaoImplementation implements BatchCoursePlacementsDaoInterface {
	@Autowired

	private BatchCoursePlacementsRepository batchCoursePlacementsRepository;

	@Autowired
	private EntityManager entityManager;

	public BatchCoursePlacements addNewPlacement(BatchCoursePlacements batchCoursePlacements) {
		// TODO Auto-generated method stub
		return batchCoursePlacementsRepository.save(batchCoursePlacements);
	}

	@Override
	public Responce deleteBatchCoursePlacement(String placementId) throws Exception {
		Responce responce = new Responce();
		try {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		
		CriteriaQuery<BatchCoursePlacements> Query = criteriaBuilder.createQuery(BatchCoursePlacements.class);
		
		Root<BatchCoursePlacements> root = Query.from(BatchCoursePlacements.class);
		
		
		Predicate predicate = criteriaBuilder.equal(root.get("placementId"), placementId);
		
		Query.select(root).where(predicate);
		
		List<BatchCoursePlacements> resultList = entityManager.createQuery(Query).getResultList();
		
		if(resultList.isEmpty()) {
			responce.setMassege("placement with the given id not found");
			responce.setStatus(false);
		}else {
			batchCoursePlacementsRepository.delete(resultList.get(0));
			responce.setMassege("placement deleted successfully");
			responce.setStatus(true);
		}
			
//		
//		Optional<BatchCoursePlacements> placenment = batchCoursePlacementsRepository.findById(placementId);
//		Responce responce = new Responce();
//
//		if (placenment.isPresent()) {
//			batchCoursePlacementsRepository.delete(placenment.get());
//			responce.setMassege("placement deleted successfully");
//			responce.setStatus(true);
//
//		} else {
//			responce.setMassege("placement with the given id not found");
//			responce.setStatus(false);
//		}
		}catch (Exception e) {
			throw new Exception("Getting problem while deleting BatchCoursePlacement");
		}
		return responce;
	}

	@Override
	public List<Map<String, Object>> getAllPlacementByBatchCourseId(BatchCourse batchCourse) throws Exception {
System.out.println("*****123");
List<Map<String, Object>> collect = null;
try {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<BatchCoursePlacements> createQuery = criteriaBuilder.createQuery(BatchCoursePlacements.class);
		Root<BatchCoursePlacements> root = createQuery.from(BatchCoursePlacements.class);

		Predicate predicate = criteriaBuilder.equal(root.get("batchCourse"), batchCourse);

		createQuery.select(root).where(predicate);

		List<BatchCoursePlacements> resultList = entityManager.createQuery(createQuery).getResultList();

		 collect = resultList.stream().map((result) -> {
			LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();

			map.put("placement_Id", result.getPlacementId());
			map.put("company_Name", result.getCompanyName());
			map.put("company_Intruduction", result.getCompanyIntroduction());
			map.put("active_placement", result.getActive_Placement());
			map.put("apply_link", result.getApplyLink());
			return map;
		}).collect(Collectors.toList());
		System.out.println("***** "+collect);
}catch (Exception e) {
	throw new Exception("Getting problem while getting All Placement By BatchCourseId");
}
		return collect;
	}

	@Override
	public Responce updateBatchCoursePlacement(String placementId, HashMap<String, Object> placementData) throws Exception {
		// TODO Auto-generated method stub
		Responce responce = new Responce();
		
		try {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<BatchCoursePlacements> createQuery = criteriaBuilder.createQuery(BatchCoursePlacements.class);
		Root<BatchCoursePlacements> root = createQuery.from(BatchCoursePlacements.class);
		Predicate predicate = criteriaBuilder.equal(root.get("placementId"), placementId);
		createQuery.select(root).where(predicate);
		List<BatchCoursePlacements> resultList = entityManager.createQuery(createQuery).getResultList();
		BatchCoursePlacements batchCoursePlacements = resultList.get(0);

		if (placementData.get("active_placement") != null) {

			if (batchCoursePlacements.getActive_Placement().equalsIgnoreCase("D")) {
				batchCoursePlacements.setActive_Placement("A");
			} else {
				batchCoursePlacements.setActive_Placement("D");
			}
		}
		if (placementData.get("company_Name") != null) {
			batchCoursePlacements.setCompanyName((String) placementData.get("company_Name"));
		}
		if (placementData.get("company_Intruduction") != null) {
			batchCoursePlacements.setCompanyIntroduction((String) placementData.get("company_Intruduction"));
		}
		if (placementData.get("applyLink") != null) {
			batchCoursePlacements.setApplyLink((String) placementData.get("applyLink"));
		}
		BatchCoursePlacements addNewPlacement = addNewPlacement(batchCoursePlacements);
		

		if (addNewPlacement!=null) {
			responce.setMassege("Details updated successfully");
			responce.setStatus(true);

		} else {
			responce.setMassege(" Fails to update details!!!");
			responce.setStatus(false);
		}
		}catch (Exception e) {
			throw new Exception("Getting problem while updating BatchCoursePlacement");
		}
		return responce;
	}

	@Override
	public BatchCoursePlacements getBatchCoursePlacementsById(String batchCoursePlacementsId) {
	CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		
		CriteriaQuery<BatchCoursePlacements> Query = criteriaBuilder.createQuery(BatchCoursePlacements.class);
		
		Root<BatchCoursePlacements> root = Query.from(BatchCoursePlacements.class);
		
		
		Predicate predicate = criteriaBuilder.equal(root.get("placementId"), batchCoursePlacementsId);
		
		Query.select(root).where(predicate);
		
		List<BatchCoursePlacements> resultList = entityManager.createQuery(Query).getResultList();
		
		if(resultList.isEmpty()) {
			return null;
		}
		
		return resultList.get(0);
	}

}

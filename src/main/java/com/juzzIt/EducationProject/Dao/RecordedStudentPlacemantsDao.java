package com.juzzIt.EducationProject.Dao;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.juzzIt.EducationProject.DaoInterface.RecordedStudentPlacemantsDaoInterface;
import com.juzzIt.EducationProject.Entity.RecordedStudentBatch;
import com.juzzIt.EducationProject.Entity.RecordedStudentPlacemants;
import com.juzzIt.EducationProject.Models.Responce;
import com.juzzIt.EducationProject.Repositary.RecordedStudentPlacemantsRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
@Component
public class RecordedStudentPlacemantsDao implements RecordedStudentPlacemantsDaoInterface {
@Autowired
private RecordedStudentPlacemantsRepository recordedStudentPlacemantsRepository;
	
	
@Autowired
private EntityManager entityManager;

//@Autowired
//private RecodedStudentPlacementImageServiceInterface recodedStudentPlacementImageServiceInterface;

	
	@Override
	public RecordedStudentPlacemants addNewRecordedStudentPlacemants(
			RecordedStudentPlacemants recordedStudentPlacemants) {
		// TODO Auto-generated method stub
		return recordedStudentPlacemantsRepository.save(recordedStudentPlacemants);
	}

	@Override
	public RecordedStudentPlacemants getRecordedStudentPlacemantsById(String recordedStudentPlacemantsId) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<RecordedStudentPlacemants> createQuery = criteriaBuilder.createQuery(RecordedStudentPlacemants.class);
		Root<RecordedStudentPlacemants> root = createQuery.from(RecordedStudentPlacemants.class);
		Predicate predicate = criteriaBuilder.equal(root.get(""), recordedStudentPlacemantsId);
		createQuery.select(root).where(predicate);
		List<RecordedStudentPlacemants> resultList = entityManager.createQuery(createQuery).getResultList();
		if(resultList.isEmpty()) {
			return null;
		}
		return resultList.get(0);
	}

	@Override
	public Responce updateRecordedStudentPlacemants(String placementId, HashMap<String, Object> placementData) {
        Responce responce=new Responce();
		RecordedStudentPlacemants recordedStudentPlacemants = getRecordedStudentPlacemantsById(placementId);
		if(recordedStudentPlacemants==null) {
			responce.setMassege("placement with the given id not found");
			responce.setStatus(false);
			return responce;
		}
		
		if(placementData.get("active_status")!=null) {
          if(recordedStudentPlacemants.getActive_Placement().equalsIgnoreCase("D")) {
	recordedStudentPlacemants.setActive_Placement("A");
           }else {
	recordedStudentPlacemants.setActive_Placement("D");
           }
		}
		RecordedStudentPlacemants updatedRecordedStudentPlacemants = recordedStudentPlacemantsRepository.save(recordedStudentPlacemants);
		if(updatedRecordedStudentPlacemants==null) {
			responce.setMassege("failed to update the placement");
			responce.setStatus(false);
			return responce;
		}
		responce.setMassege("successfully updated details");
		responce.setStatus(true);
		return responce;
	}

	@Override
	public Responce deleteRecordedStudentPlacemants(String placementId) {
	    Responce responce=new Responce();
			RecordedStudentPlacemants recordedStudentPlacemants = getRecordedStudentPlacemantsById(placementId);
			if(recordedStudentPlacemants==null) {
				responce.setMassege("placement with the given id not found");
				responce.setStatus(false);
				return responce;
			}
			
			recordedStudentPlacemantsRepository.delete(recordedStudentPlacemants);
			responce.setMassege("placement deleted successfully");
			responce.setStatus(true);
			
		return responce;
	}

	@Override
	public List<Map<String, Object>> getRecordedStudentPlacemants(RecordedStudentBatch recordedStudentBatch) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<RecordedStudentPlacemants> createQuery = criteriaBuilder.createQuery(RecordedStudentPlacemants.class);
		Root<RecordedStudentPlacemants> root = createQuery.from(RecordedStudentPlacemants.class);
		Predicate predicate = criteriaBuilder.equal(root.get("recordedStudentBatch"), recordedStudentBatch);
		createQuery.select(root).where(predicate);
		List<RecordedStudentPlacemants> resultList = entityManager.createQuery(createQuery).getResultList();
		
		   List<Map<String, Object>> collect = resultList.stream().map(result->{
			Map<String , Object> map=new LinkedHashMap<String, Object>();
			map.put("placement_Id", result.getPlacementId());
			map.put("placement_CompanyName", result.getCompanyName());
			map.put("placement_ConmpanyIntruduction", result.getCompanyIntroduction());
			map.put("apply_Link", result.getApplyLink());
			map.put("active_status", result.getActive_Placement());
			return map;
		}).collect(Collectors.toList());
		return collect;
	}

	
	
	
}

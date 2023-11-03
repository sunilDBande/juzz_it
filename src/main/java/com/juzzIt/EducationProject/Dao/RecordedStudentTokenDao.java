package com.juzzIt.EducationProject.Dao;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.juzzIt.EducationProject.DaoInterface.RecordedStudentTokenDaoInterface;
import com.juzzIt.EducationProject.Entity.RecordedStudentBatch;
import com.juzzIt.EducationProject.Entity.RecordedStudentToken;
import com.juzzIt.EducationProject.Models.Responce;
import com.juzzIt.EducationProject.Repositary.RecordedStudentTokenRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

@Component
public class RecordedStudentTokenDao implements RecordedStudentTokenDaoInterface {
;
	
	@Autowired
	private RecordedStudentTokenRepository recordedStudentTokenRepository;
	
	@Autowired
	private EntityManager entityManager;

	@Override
	public RecordedStudentToken addNewToken(RecordedStudentToken recordedStudentToken) {
		return recordedStudentTokenRepository.save(recordedStudentToken);
	}

	@Override
	public RecordedStudentToken getRecordedStudentTokenById(String recoredStudentstudentId) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<RecordedStudentToken> createQuery = criteriaBuilder.createQuery(RecordedStudentToken.class);
	
		Root<RecordedStudentToken> root = createQuery.from(RecordedStudentToken.class);
		
		Predicate predicate = criteriaBuilder.equal(root.get("tokenId"), recoredStudentstudentId);
		createQuery.select(root).where(predicate);
		List<RecordedStudentToken> resultList = entityManager.createQuery(createQuery).getResultList();
		
		
		if(resultList.isEmpty()) {
			return null;
		}else 
			return resultList.get(0);
	}

	@Override
	public Responce deleteToken(String tokanId) {
		Responce responce=new Responce();
		RecordedStudentToken recordedStudentToken = getRecordedStudentTokenById(tokanId);
		if(recordedStudentToken==null) {
			
			responce.setMassege("token with the given in not found");
			responce.setStatus(false);
			return responce;
		}
		
		recordedStudentTokenRepository.delete(recordedStudentToken);
		responce.setMassege("token delete successfully");
		responce.setStatus(true);
		
		
		return responce;
	}

	@Override
	public Responce updateToken(String tokanId, HashMap<String, Object> tokenData) {
		Responce responce=new Responce();
		RecordedStudentToken recordedStudentToken = getRecordedStudentTokenById(tokanId);
		if(recordedStudentToken==null) {
			
			responce.setMassege("token with the given in not found");
			responce.setStatus(false);
			return responce;
		}
		
		
		if(tokenData.get("active_Status")!=null) {
			if(recordedStudentToken.getActiveStatus().equalsIgnoreCase("D")) {
				recordedStudentToken.setActiveStatus("A");
			}else {
				recordedStudentToken.setActiveStatus("D");
			}
		}
		
		RecordedStudentToken updatedData = recordedStudentTokenRepository.save(recordedStudentToken);
		if(updatedData==null) {
			responce.setMassege("failed to update the data");
			responce.setStatus(false);
			return responce;
		}
		responce.setMassege("detailes updated successfully");
		responce.setStatus(true);
		return responce;
	}

	@Override
	public List<Map<String, Object>> getAllTokensActive() {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<RecordedStudentToken> createQuery = criteriaBuilder.createQuery(RecordedStudentToken.class);
	
		Root<RecordedStudentToken> root = createQuery.from(RecordedStudentToken.class);
		
		Predicate predicate1 = criteriaBuilder.equal(root.get("activeStatus"), "A");
		Predicate predicate2 = criteriaBuilder.equal(root.get("activeStatus"), "a");
		Predicate predicate = criteriaBuilder.and(predicate1,predicate2);
		createQuery.select(root).where(predicate);
		List<RecordedStudentToken> resultList = entityManager.createQuery(createQuery).getResultList();
		
		List<Map<String, Object>> collect = resultList.stream().map(result->{
			
			Map<String, Object> map=new LinkedHashMap<String, Object>();
			map.put("token_Id", result);
			map.put("token_Name", result);
			map.put("token_Desc", result);
			map.put("active_Status", result);
			map.put("created_DateTime", result);
		
			return map;
		}).collect(Collectors.toList());
		
		return collect;
	}

	@Override
	public List<Map<String, Object>> getTokenByRecordedStudentId(RecordedStudentBatch recordedStudentBatch  ) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<RecordedStudentToken> createQuery = criteriaBuilder.createQuery(RecordedStudentToken.class);
	
		Root<RecordedStudentToken> root = createQuery.from(RecordedStudentToken.class);
		
		Predicate predicate = criteriaBuilder.equal(root.get("recordedStudentBatch"), recordedStudentBatch);
		createQuery.select(root).where(predicate);
		List<RecordedStudentToken> resultList = entityManager.createQuery(createQuery).getResultList();
		

		
		List<Map<String, Object>> collect = resultList.stream().map(result->{
			
			Map<String, Object> map=new LinkedHashMap<String, Object>();
			map.put("token_Id", result);
			map.put("token_Name", result);
			map.put("token_Desc", result);
			map.put("active_Status", result);
			map.put("created_DateTime", result);
		
			return map;
		}).collect(Collectors.toList());
		
		return collect;
	}

	@Override
	public List<Map<String, Object>> getAllTokens() {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<RecordedStudentToken> createQuery = criteriaBuilder.createQuery(RecordedStudentToken.class);
	
		Root<RecordedStudentToken> root = createQuery.from(RecordedStudentToken.class);
		
		createQuery.select(root);

List<RecordedStudentToken> resultList = entityManager.createQuery(createQuery).getResultList();
		
		List<Map<String, Object>> collect = resultList.stream().map(result->{
			
			Map<String, Object> map=new LinkedHashMap<String, Object>();
			map.put("token_Id", result);
			map.put("token_Name", result);
			map.put("token_Desc", result);
			map.put("active_Status", result);
			map.put("created_DateTime", result);
		
			return map;
		}).collect(Collectors.toList());
		
		return collect;
	}
}

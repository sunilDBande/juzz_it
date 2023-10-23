package com.juzzIt.EducationProject.Dao;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.juzzIt.EducationProject.DaoInterface.RecordedStudentTokenMassegeDaoInterface;
import com.juzzIt.EducationProject.Entity.RecordedStudentToken;
import com.juzzIt.EducationProject.Entity.RecordedStudentTokenMassege;
import com.juzzIt.EducationProject.Models.Responce;
import com.juzzIt.EducationProject.Repositary.RecordedStudentTokenMassegeRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
@Component
public class RecordedStudentTokenMassegeDao implements RecordedStudentTokenMassegeDaoInterface {
	
	
	@Autowired
	private RecordedStudentTokenMassegeRepository recordedStudentTokenMassegeRepository;
	
	@Autowired
	private EntityManager entityManager;

	@Override
	public RecordedStudentTokenMassege addTokenMassege(RecordedStudentTokenMassege recordedStudentTokenMassege) {
	
		return recordedStudentTokenMassegeRepository.save(recordedStudentTokenMassege);
	}

	@Override
	public RecordedStudentTokenMassege getRecordedStudentTokenMassegeById(String massegeId) {
	CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
	CriteriaQuery<RecordedStudentTokenMassege> createQuery = criteriaBuilder.createQuery(RecordedStudentTokenMassege.class);
		
	Root<RecordedStudentTokenMassege> root = createQuery.from(RecordedStudentTokenMassege.class);

	Predicate predicate = criteriaBuilder.equal(root.get("massegeId"), massegeId);
	createQuery.select(root).where(predicate);
	List<RecordedStudentTokenMassege> resultList = entityManager.createQuery(createQuery).getResultList();
	if(resultList.isEmpty()) {
		return null;
	}
	return resultList.get(0);
	}

	@Override
	public Responce deleteTokenMassege(String tokanMassegeId) {
		RecordedStudentTokenMassege recordedStudentTokenMassege = getRecordedStudentTokenMassegeById(tokanMassegeId);
Responce responce=new Responce();
		
		if(recordedStudentTokenMassege==null) {
                responce.setMassege("massege with the given id not found");
                responce.setStatus(false);
                return responce;
	}

		recordedStudentTokenMassegeRepository.delete(recordedStudentTokenMassege);
		responce.setMassege("massege deleted successfully");
		responce.setStatus(true);
		return responce;
	}

	@Override
	public Responce updateTokenMassege(String tokanMassegeId, HashMap<String, Object> tokenMassegeData) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Map<String, Object>> getAllTokenMassege(RecordedStudentToken recordedStudentToken) {
	
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<RecordedStudentTokenMassege> createQuery = criteriaBuilder.createQuery(RecordedStudentTokenMassege.class);
			
		Root<RecordedStudentTokenMassege> root = createQuery.from(RecordedStudentTokenMassege.class);

		Predicate predicate = criteriaBuilder.equal(root.get("recordedStudentToken"), recordedStudentToken);
		createQuery.select(root).where(predicate);
		List<RecordedStudentTokenMassege> resultList = entityManager.createQuery(createQuery).getResultList();
		
		List<Map<String, Object>> collect = resultList.stream().map(result->{
			
			Map<String, Object> map=new LinkedHashMap<String, Object>();
			map.put("MASSEGE_ID", result.getMassegeId());
			map.put("sender_Type", result.getSenderType());
			map.put("SENDER_NAME", result.getSendername());
			map.put("SENDER_TYPE", result.getSenderType());
			map.put("SENDER_ID", result.getSenderId());
			map.put("MASSEGE", result.getMassege());
			map.put("CREATED_DATE_TIME", result.getCreatedDateTime());
			
			return map;
		}).collect(Collectors.toList());
		
		
		return collect;
	}

}

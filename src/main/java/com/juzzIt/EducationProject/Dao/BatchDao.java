package com.juzzIt.EducationProject.Dao;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.juzzIt.EducationProject.DaoInterface.BatchDaoInterface;
import com.juzzIt.EducationProject.Entity.Batch;
import com.juzzIt.EducationProject.Models.Responce;
import com.juzzIt.EducationProject.Repositary.BatchRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;


@Repository
public class BatchDao implements BatchDaoInterface {

	
	@Autowired
	private BatchRepository batchRepository;
	
	@Autowired
	private EntityManager entityManager;

	@Override
	public boolean existsByBatchName(String batchName) {
		return batchRepository.existsByBatchName(batchName);
	}

	@Override
	public Batch addBatch(Batch batch) {
		return batchRepository.save(batch);
	}

	@Override
	public Responce deleteBatch(String batchId) throws Exception {
		Responce responce=new Responce();
		try {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		
		CriteriaQuery<Batch> createQuery = criteriaBuilder.createQuery(Batch.class);
		
		Root<Batch> root = createQuery.from(Batch.class);
		Predicate predicate = criteriaBuilder.equal(root.get("batchId"), batchId);
		
		createQuery.select(root).where(predicate);
		
		List<Batch> resultList = entityManager.createQuery(createQuery).getResultList();
		
		
		
//		Optional<Batch> batch = batchRepository.findById(batchId);
	if(!resultList.isEmpty()) {
		batchRepository.delete(resultList.get(0));
		responce.setMassege("batch deleted successfully");
		responce.setStatus(true);
	}else {
		responce.setMassege("batch with  the given id not found");
		responce.setStatus(false);
	}
		}catch (Exception e) {
			throw new Exception("Getting problem while deleting Batch.");
		}
		return responce;
		
	}

	@Override
	public List<Map<String, Object>> getAllBatch() throws Exception {
		List<Map<String, Object>> collect = null;
		try {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		
		CriteriaQuery<Batch> createQuery = criteriaBuilder.createQuery(Batch.class);
		
		Root<Batch> root = createQuery.from(Batch.class);
		
		
		createQuery.select(root);
		
		List<Batch> resultList = entityManager.createQuery(createQuery).getResultList();
		
		
		   collect = resultList.stream().map(result->{
			Map<String , Object> map=new LinkedHashMap<String, Object>();
			map.put("batch_Id", result.getBatchId());
			map.put("batch_Name", result.getBatchName());
			map.put("temp_delete", result.getTemp_delete());
			return map;
		}).collect(Collectors.toList());
		}catch (Exception e) {
			throw new Exception("Getting problem while getting batch");
		}
		return collect;
		
	}

	@Override
	public Batch getBatchById(String batchId) {
	

		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		
		CriteriaQuery<Batch> createQuery = criteriaBuilder.createQuery(Batch.class);
		
		Root<Batch> root = createQuery.from(Batch.class);
		Predicate predicate = criteriaBuilder.equal(root.get("batchId"), batchId);
		
		createQuery.select(root).where(predicate);
		
		List<Batch> resultList = entityManager.createQuery(createQuery).getResultList();
		
		
		
		Optional<Batch> batch = batchRepository.findById(batchId);
		
		if(!resultList.isEmpty()) {
			return resultList.get(0);
		}
		
		return null;
	}
	
}

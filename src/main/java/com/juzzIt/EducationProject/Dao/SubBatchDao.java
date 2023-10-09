package com.juzzIt.EducationProject.Dao;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.juzzIt.EducationProject.DaoInterface.BatchDaoInterface;
import com.juzzIt.EducationProject.DaoInterface.SubBatchDaoInterface;
import com.juzzIt.EducationProject.Entity.Batch;
import com.juzzIt.EducationProject.Entity.SubBatch;
import com.juzzIt.EducationProject.Models.Responce;
import com.juzzIt.EducationProject.Repositary.SubBatchRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
@Component
public class SubBatchDao  implements SubBatchDaoInterface{

	
	
	@Autowired
	private SubBatchRepository subBatchRepo;
	
	@Autowired
	private EntityManager entityManager;
	
	@Autowired
	private BatchDaoInterface batchDaoInterface;
	
	@Override
	public SubBatch addSubBatch(SubBatch subBatch) {
		// TODO Auto-generated method stub
		return subBatchRepo.save(subBatch);
	}

	@Override
	public Responce deleteSubBatch(String subBatchId) throws Exception {
		Optional<SubBatch> subBatch = subBatchRepo.findById(subBatchId);
		Responce responce=new Responce();
		try {
		if(subBatch.isPresent()) {
			subBatchRepo.delete(subBatch.get());
			responce.setMassege("sub batch deleted successfully");
			responce.setStatus(true);
			
		}else {
			responce.setMassege("subBatch withe the given id not found");
			responce.setStatus(false);
		}
		}catch (Exception e) {
			throw new Exception("Getting problem while deleting Sub-Batch");
		}
		return responce;
	}

	@Override
	public SubBatch getSubBatchById(String subBatchId) {
		Optional<SubBatch> subBatch = subBatchRepo.findById(subBatchId);
		
		if(subBatch.isPresent()) {
			return subBatch.get();
		}
		return null;
	}

	@Override
	public List<Map<String, Object>> getAllSubBatchByBatchId(String batchId) throws Exception {
		List<Map<String, Object>> collect = null;
		try {
		Batch batch = batchDaoInterface.getBatchById(batchId);
		if(batch==null) {
			return null;
		}
		
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<SubBatch> createQuery = builder.createQuery(SubBatch.class);
		
		Root<SubBatch> root = createQuery.from(SubBatch.class);
		
		Predicate predicate = builder.equal(root.get("batch"), batch);
		createQuery.select(root).where(predicate);
		
		List<SubBatch> resultList = entityManager.createQuery(createQuery).getResultList();
		
		 collect = resultList.stream().map(result->{
			Map<String, Object>map=new LinkedHashMap<String, Object>();
			
			map.put("subBatch_Id", result.getSubBatchId());
			map.put("subBatch_Name", result.getSubBatchName());
			return map;
		}).collect(Collectors.toList());
		
		}catch (Exception e) {
			throw new Exception("Getting problem while getting all Sub-Batchs");
		}
		return collect;
	}

	@Override
	public boolean existsBySubBatchNameAndBatch(String subBatchName,Batch batch) {
		// TODO Auto-generated method stub
		return subBatchRepo.existsBySubBatchNameAndBatch(subBatchName,batch);
	}

}

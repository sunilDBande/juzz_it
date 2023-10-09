package com.juzzIt.EducationProject.DaoInterface;

import java.util.List;
import java.util.Map;

import com.juzzIt.EducationProject.Entity.Batch;
import com.juzzIt.EducationProject.Models.Responce;

public interface BatchDaoInterface {

	public boolean existsByBatchName(String batchName);
	
	public Batch addBatch(Batch batch);
	
	public Responce deleteBatch(String batchId) throws Exception ;
	public List<Map<String , Object>> getAllBatch() throws Exception;
	
	public Batch getBatchById(String batchId);
	
}

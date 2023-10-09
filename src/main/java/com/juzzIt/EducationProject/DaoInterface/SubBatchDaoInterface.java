package com.juzzIt.EducationProject.DaoInterface;

import java.util.List;
import java.util.Map;

import com.juzzIt.EducationProject.Entity.Batch;
import com.juzzIt.EducationProject.Entity.SubBatch;
import com.juzzIt.EducationProject.Models.Responce;

public interface SubBatchDaoInterface {

	
	public SubBatch addSubBatch(SubBatch subBatch);
	public Responce deleteSubBatch(String subBatchId) throws Exception;
	public SubBatch getSubBatchById(String subBatchId);
	public List<Map<String , Object>> getAllSubBatchByBatchId(String batchId) throws Exception;
	public boolean existsBySubBatchNameAndBatch(String subBatchName,Batch batch);
}

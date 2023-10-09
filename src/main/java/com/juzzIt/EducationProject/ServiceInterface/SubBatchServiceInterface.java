package com.juzzIt.EducationProject.ServiceInterface;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.juzzIt.EducationProject.Models.Responce;

public interface SubBatchServiceInterface {

	public Responce  addSubBatch(String batchId, HashMap<String,Object> subBatch) throws Exception;
	public Responce deleteSubBatch( String subBatchId) throws Exception ;
	public List<Map<String , Object>>  getAllSubBatch(String batchId) throws Exception;
}

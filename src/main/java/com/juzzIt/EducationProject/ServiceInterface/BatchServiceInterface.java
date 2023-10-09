package com.juzzIt.EducationProject.ServiceInterface;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.juzzIt.EducationProject.Models.Responce;

public interface BatchServiceInterface {

	public Responce addBatch(HashMap<String, Object> batch) throws Exception ;
	public Responce deleteBatch( String batchId) throws Exception ;
	public List<Map<String , Object>> getAllBatch() throws Exception;
}

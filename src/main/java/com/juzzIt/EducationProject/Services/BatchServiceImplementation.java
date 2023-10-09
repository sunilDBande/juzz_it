package com.juzzIt.EducationProject.Services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.juzzIt.EducationProject.Dao.EntityDao;
import com.juzzIt.EducationProject.DaoInterface.BatchDaoInterface;
import com.juzzIt.EducationProject.Entity.Batch;
import com.juzzIt.EducationProject.Models.Responce;
import com.juzzIt.EducationProject.Models.UniqueIdGenerations;
import com.juzzIt.EducationProject.ServiceInterface.BatchServiceInterface;


@Service
public class BatchServiceImplementation implements BatchServiceInterface {

	@Autowired
	private BatchDaoInterface batchDaoInterface;
	
	    @Autowired
		private UniqueIdGenerations uniqueIdGenerations;

		@Autowired
		private EntityDao entityDao;
		
	@Override
	public Responce addBatch(HashMap<String, Object> batch) throws Exception {

		Responce responce=new Responce();
		try {
		if(batch.get("batch_Name")==null) {
			responce.setMassege("batch name is needed");
			responce.setStatus(false);
			return responce;
		}
		
		
		if(batchDaoInterface.existsByBatchName(batch.get("batch_Name").toString())) {
			responce.setMassege("batch with the name is alrady exists");
			responce.setStatus(false);
			return responce;
		}
			
		Batch newBatch=new Batch();
		
		  HashMap<String, Object> data = new HashMap<String, Object>();
			data.put("Entity_Name", "Batch");
			data.put("Entity_SubName", "BTCH");
			HashMap<String, Object> uniqueIdGeneration = uniqueIdGenerations.uniqueIdGeneration(data);
			// *******
			String subName = (String) uniqueIdGeneration.get("Entity_SubName");
			long count = (long) uniqueIdGeneration.get("count");
			if (count < 10) {
				newBatch.setBatchId(subName + "00" + count);
			} else if (count < 100 && count > 10) {
				newBatch.setBatchId(subName + "0" + count);
			} else {
				newBatch.setBatchId(subName + "" + count);
			}
			// *******
		newBatch.setBatchName(batch.get("batch_Name").toString());
		newBatch.setTemp_delete("A");
	
		Batch addedBatch = batchDaoInterface.addBatch(newBatch);
		
		if(addedBatch==null) {
			responce.setMassege("failed to create batch");
			responce.setStatus(false);
			return responce;
		} else {
			 entityDao.updateCountForCourseTable(data);		
		}
		
		responce.setMassege("batch created successfully");
		responce.setStatus(true);
		}catch (Exception e) {
			throw new Exception("Getting problem while adding Batch.");
		}
		return responce;
	}

	@Override
	public Responce deleteBatch(String batchId) throws Exception {
		return batchDaoInterface.deleteBatch(batchId);
	}

	@Override
	public List<Map<String, Object>> getAllBatch() throws Exception {
		return batchDaoInterface.getAllBatch();
	}
	
	
}

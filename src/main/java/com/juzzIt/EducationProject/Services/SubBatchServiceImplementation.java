package com.juzzIt.EducationProject.Services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.juzzIt.EducationProject.Dao.EntityDao;
import com.juzzIt.EducationProject.DaoInterface.BatchDaoInterface;
import com.juzzIt.EducationProject.DaoInterface.SubBatchDaoInterface;
import com.juzzIt.EducationProject.Entity.Batch;
import com.juzzIt.EducationProject.Entity.SubBatch;
import com.juzzIt.EducationProject.Models.Responce;
import com.juzzIt.EducationProject.Models.UniqueIdGenerations;
import com.juzzIt.EducationProject.ServiceInterface.SubBatchServiceInterface;

@Service
public class SubBatchServiceImplementation implements SubBatchServiceInterface {

	@Autowired
	private BatchDaoInterface batchDaoInterface;
	
	@Autowired
	private SubBatchDaoInterface subBatchDaoInterface;
	
	 @Autowired
		private UniqueIdGenerations uniqueIdGenerations;

		@Autowired
		private EntityDao entityDao;
		
	@Override
	public Responce addSubBatch(String batchId, HashMap<String, Object> subBatch) throws Exception {	
		
		Responce responce=new Responce();
		try {
		if(subBatch.get("subBatch_Name")==null) {
			responce.setMassege("subBatch Name is needed");
			responce.setStatus(false);
			return responce;
		}
		

		Batch batch = batchDaoInterface.getBatchById(batchId);
		
		if(batch==null) {
			responce.setMassege("batch with the given id not found ");
			responce.setStatus(false);
			return responce;
		}
		
		
		if(subBatchDaoInterface.existsBySubBatchNameAndBatch(subBatch.get("subBatch_Name").toString(),batch)) {
			
			responce.setMassege("sub batch with the name alredy created");
			responce.setStatus(false);
   return responce;
		}
		
		
		
		
		SubBatch newSubBatch=new SubBatch();
		
		newSubBatch.setBatch(batch);
		newSubBatch.setSubBatchName(subBatch.get("subBatch_Name").toString());
		HashMap<String, Object> data = new HashMap<String, Object>();
		data.put("Entity_Name", "SubBatch");
		data.put("Entity_SubName", "SUBTCH");
		HashMap<String, Object> uniqueIdGeneration = uniqueIdGenerations.uniqueIdGeneration(data);
		// *******
		String subName = (String) uniqueIdGeneration.get("Entity_SubName");
		long count = (long) uniqueIdGeneration.get("count");
		if (count < 10) {
			newSubBatch.setSubBatchId(subName + "00" + count);
		} else if (count < 100 && count > 10) {
			newSubBatch.setSubBatchId(subName + "0" + count);
		} else {
			newSubBatch.setSubBatchId(subName + "" + count);
		}
		// *******
		
		
		SubBatch addedSubBatch = subBatchDaoInterface.addSubBatch(newSubBatch);
		
		if(addedSubBatch==null) {
			responce.setMassege("failed to create a subBatch");
			responce.setStatus(false);
			return responce;
		}else {
			 entityDao.updateCountForCourseTable(data);		
		}
		
		responce.setMassege("sub batch created successfully");
		responce.setStatus(true);
		}catch (Exception e) {
			throw new Exception("Getting problem while adding Sub-Batch");
		}
		return responce;
	}
	
		@Override
		public Responce deleteSubBatch(String subBatchId) throws Exception {
			// TODO Auto-generated method stub
			return subBatchDaoInterface.deleteSubBatch(subBatchId);
		}
	
		@Override
		public List<Map<String, Object>> getAllSubBatch(String batchId) throws Exception {
			return subBatchDaoInterface.getAllSubBatchByBatchId(batchId);
		}
	
		
		

	

}

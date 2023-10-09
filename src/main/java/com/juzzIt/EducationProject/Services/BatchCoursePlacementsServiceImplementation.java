package com.juzzIt.EducationProject.Services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.juzzIt.EducationProject.Dao.EntityDao;
import com.juzzIt.EducationProject.DaoInterface.BatchCourseDaoInterface;
import com.juzzIt.EducationProject.DaoInterface.BatchCoursePlacementsDaoInterface;
import com.juzzIt.EducationProject.Entity.BatchCourse;
import com.juzzIt.EducationProject.Entity.BatchCoursePlacements;
import com.juzzIt.EducationProject.Models.Responce;
import com.juzzIt.EducationProject.Models.UniqueIdGenerations;
import com.juzzIt.EducationProject.ServiceInterface.BatchCoursePlacementsServiceInterface;


@Service
public class BatchCoursePlacementsServiceImplementation implements BatchCoursePlacementsServiceInterface {

	@Autowired
	private BatchCourseDaoInterface batchCourseDaoInterface;
	
	@Autowired
	private BatchCoursePlacementsDaoInterface batchCoursePlacementsDaoInterface;
	
	@Autowired
	private UniqueIdGenerations uniqueIdGenerations;

	@Autowired
	private EntityDao entityDao;
	@Override
	public Responce addNewPlacement(String batchCourseId, HashMap<String, Object> placementData) throws Exception {
		
		
		Responce responce=new Responce();
		try {
		if(placementData.get("company_name")==null||placementData.get("company_Intruduction")==null||placementData.get("apply_link")==null) {
			responce.setMassege("company name  , company overview and apply link is needed");
			responce.setStatus(false);
			return responce;
		}
		
		BatchCourse batchCourse = batchCourseDaoInterface.getBatchCourseById(batchCourseId);
		
		if(batchCourse==null) {
			responce.setMassege("batch course with the given id not found");
			responce.setStatus(false);
			return null;
		}
		
		
		BatchCoursePlacements newPlacement=new BatchCoursePlacements();
		
		newPlacement.setBatchCourse(batchCourse);
		newPlacement.setCompanyName(placementData.get("company_name").toString());
		newPlacement.setCompanyIntroduction(placementData.get("company_Intruduction").toString());
		newPlacement.setActive_Placement("D");
		newPlacement.setApplyLink(placementData.get("apply_link").toString());
		HashMap<String, Object> data = new HashMap<String, Object>();
		data.put("Entity_Name", "BatchCoursePlacements");
		data.put("Entity_SubName", "BAPLCE");
		data.put("Entity_Count", 0);
		HashMap<String, Object> uniqueIdGeneration = uniqueIdGenerations.uniqueIdGeneration(data);
		// *******
		System.out.println("IMP---> " + uniqueIdGeneration);
		System.out.println("IMP3---> " + uniqueIdGeneration.get("count"));
		String subName = (String) uniqueIdGeneration.get("Entity_SubName");
		long count = (long) uniqueIdGeneration.get("count");
		if (count < 10) {
			newPlacement.setPlacementId(subName + "00" + count);
		} else if (count < 100 && count > 10) {
			newPlacement.setPlacementId(subName + "0" + count);
		} else {
			newPlacement.setPlacementId(subName + "" + count);
		}
		// *******
	
		BatchCoursePlacements addedPlacement = batchCoursePlacementsDaoInterface.addNewPlacement(newPlacement);
		
		
		if(addedPlacement==null) {
				responce.setMassege("failed to add the details");
				responce.setStatus(false);
				return responce;
		}else {
			entityDao.updateCountForCourseTable(data);
		}
		
		responce.setMassege("placement addedsuccessfully");
		responce.setStatus(true);
		
		
		
		}catch (Exception e) {
			throw new Exception("Getting problem while adding New Placement");
		}
		return responce;
	}

	@Override
	public Responce deleteBatchCoursePlacement(String placementId) throws Exception {
		return batchCoursePlacementsDaoInterface.deleteBatchCoursePlacement(placementId);
	}

	@Override
	public List<Map<String, Object>> getAllPlacementByBatchCourseId(String batchCourseId) throws Exception {
BatchCourse batchCourse = batchCourseDaoInterface.getBatchCourseById(batchCourseId);
		Responce responce=new Responce();
		if(batchCourse==null) {
			return null;
		}
		
		
		return batchCoursePlacementsDaoInterface.getAllPlacementByBatchCourseId(batchCourse);
		
	
	}

	@Override
	public Responce updateBatchCoursePlacement(String placementId, HashMap<String, Object> placementData) throws Exception {
		// TODO Auto-generated method stub
		return batchCoursePlacementsDaoInterface.updateBatchCoursePlacement(placementId, placementData);
	}

	


}

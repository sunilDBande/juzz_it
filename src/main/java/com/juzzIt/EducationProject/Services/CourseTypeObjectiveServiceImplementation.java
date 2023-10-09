package com.juzzIt.EducationProject.Services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.juzzIt.EducationProject.Dao.EntityDao;
import com.juzzIt.EducationProject.DaoInterface.CourseTypeDaoInterface;
import com.juzzIt.EducationProject.DaoInterface.CourseTypeObjectiveDaoInterface;
import com.juzzIt.EducationProject.Entity.CourseType;
import com.juzzIt.EducationProject.Entity.CourseTypeObjective;
import com.juzzIt.EducationProject.Models.Responce;
import com.juzzIt.EducationProject.Models.UniqueIdGenerations;
import com.juzzIt.EducationProject.ServiceInterface.CourseTypeObjectiveServiceInterface;

@Service
public class CourseTypeObjectiveServiceImplementation  implements CourseTypeObjectiveServiceInterface{

	
	@Autowired
	private CourseTypeDaoInterface courseTypeDaoInterface;
	
	@Autowired
	private CourseTypeObjectiveDaoInterface courseTypeObjectiveDaoInterface;
	
	  @Autowired
		private UniqueIdGenerations uniqueIdGenerations;

		@Autowired
		private EntityDao entityDao;
		
	@Override
	public Responce addObjective(String courseTypeId, HashMap<String, Object> objective) throws Exception {
		Responce responce=new Responce();
		
		try {
		if(objective.get("objective")==null) {
			responce.setMassege("objective is needed");
			responce.setStatus(false);
			return null;
		}
		
		
		CourseType courseType = courseTypeDaoInterface.getCourseTypeById(courseTypeId);
		
		if(courseType==null) {
			responce.setMassege("courseType with the given id not found");
			responce.setStatus(false);
			return responce;
		}
		
		CourseTypeObjective newObjective=new CourseTypeObjective();

		newObjective.setCourseType(courseType);
		HashMap<String, Object> data = new HashMap<String, Object>();
		data.put("Entity_Name", "CourseTypeObjective");
		data.put("Entity_SubName", "CTYPOBJ");
		data.put("Entity_Count", 0);
		HashMap<String, Object> uniqueIdGeneration = uniqueIdGenerations.uniqueIdGeneration(data);
		// *******
		String subName = (String) uniqueIdGeneration.get("Entity_SubName");
		long count = (long) uniqueIdGeneration.get("count");
		if (count < 10) {
			newObjective.setObjectiveId(subName + "00" + count);
		} else if (count < 100 && count > 10) {
			newObjective.setObjectiveId(subName + "0" + count);
		} else {
			newObjective.setObjectiveId(subName + "" + count);
		}
		// *******
		newObjective.setObjective(objective.get("objective").toString());
		
		
		CourseTypeObjective addedObjective = courseTypeObjectiveDaoInterface.addObjective(newObjective);
		
		if(addedObjective==null) {
			responce.setMassege("failed to add the objective");
			responce.setStatus(false);
			return responce;
		}else {
			 entityDao.updateCountForCourseTable(data);		
		}
		responce.setMassege("objective added successfully");
		responce.setStatus(true);
		}catch (Exception e) {
			throw new Exception("Getting problem while adding Objective");
		}
		return responce;
		
	
	}

	@Override
	public Responce deleteObjective(String objectiveId) throws Exception {
		
		return courseTypeObjectiveDaoInterface.deleteObjective(objectiveId);
	}

	@Override
	public List<Map<String, Object>> getAllObjective(String courseTypeId) throws Exception {
		// TODO Auto-generated method stub
		return courseTypeObjectiveDaoInterface.getAllObjectiveByCourseTypeId(courseTypeId);
	}

	
	
	
   
}

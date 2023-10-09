package com.juzzIt.EducationProject.Services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.juzzIt.EducationProject.Dao.EntityDao;
import com.juzzIt.EducationProject.DaoInterface.CourseTypeDaoInterface;
import com.juzzIt.EducationProject.DaoInterface.CourseTypeEssentialsDaoInterface;
import com.juzzIt.EducationProject.Entity.CourseType;
import com.juzzIt.EducationProject.Entity.CourseTypeEssentials;
import com.juzzIt.EducationProject.Models.Responce;
import com.juzzIt.EducationProject.Models.UniqueIdGenerations;
import com.juzzIt.EducationProject.ServiceInterface.CourseTypeEssentialsServiceInterface;

@Service
public class CourseTypeEssentialsServiceImplementation implements CourseTypeEssentialsServiceInterface {


@Autowired
private CourseTypeDaoInterface courseTypeDaoInterface;
	
@Autowired
private UniqueIdGenerations uniqueIdGenerations;

@Autowired
private EntityDao entityDao;

@Autowired
private CourseTypeEssentialsDaoInterface courseTypeEssentialsDaoInterface;
	
	public Responce addEssential(String courseTypeId, HashMap<String, Object> essential) throws Exception {
		Responce responce=new Responce();
		try {
		if(essential.get("essential_Type")==null||essential.get("essential_Desc")==null) {
			responce.setMassege("essential name ande description is required");
			responce.setStatus(false);
			return responce;
		}
		
		CourseType courseType = courseTypeDaoInterface.getCourseTypeById(courseTypeId);
		
if(courseType==null) {
	responce.setMassege("courseType with the given id not found");
	responce.setStatus(false);
	return responce;
}
		
		
		CourseTypeEssentials newEssential=new CourseTypeEssentials();
		newEssential.setCourseType(courseType);
		  HashMap<String, Object> data = new HashMap<String, Object>();
			data.put("Entity_Name", "CourseTypeEssentials");
			data.put("Entity_SubName", "CTESSEN");
			data.put("Entity_Count", 0);
			HashMap<String, Object> uniqueIdGeneration = uniqueIdGenerations.uniqueIdGeneration(data);
			// *******
			String subName = (String) uniqueIdGeneration.get("Entity_SubName");
			long count = (long) uniqueIdGeneration.get("count");
			if (count < 10) {
				newEssential.setEssentialId(subName + "00" + count);
			} else if (count < 100 && count > 10) {
				newEssential.setEssentialId(subName + "0" + count);
			} else {
				newEssential.setEssentialId(subName + "" + count);
			}
			// *******
		
		newEssential.setEssentialDesc(essential.get("essential_Desc").toString());
		
		newEssential.setEssentialType(essential.get("essential_Type").toString());
		
		CourseTypeEssentials addEssential = courseTypeEssentialsDaoInterface.addEssential(newEssential);
		if(addEssential==null) {
			responce.setMassege("failed to add the essential");
			responce.setStatus(false);
			return responce;
		} else {
			 entityDao.updateCountForCourseTable(data);		
		}
		responce.setMassege("essential added successully");
		responce.setStatus(true);
		}catch (Exception e) {
			throw new Exception("Getting problem while adding Essential");
		}
		return responce;
		
	
	}

	@Override
	public Responce deleteEssential(String essentialId) {
		// TODO Auto-generated method stub
		return courseTypeEssentialsDaoInterface.deleteEssential(essentialId);
	}

	@Override
	public List<Map<String, Object>> getAllEssentials(String courseTypeId) throws Exception {
		// TODO Auto-generated method stub
		return courseTypeEssentialsDaoInterface.getAllEssentialsByCourseTypeId(courseTypeId);
	}

	

}

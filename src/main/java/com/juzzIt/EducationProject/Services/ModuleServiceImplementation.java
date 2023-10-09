package com.juzzIt.EducationProject.Services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.juzzIt.EducationProject.Dao.EntityDao;
import com.juzzIt.EducationProject.DaoInterface.CourseTypeDaoInterface;
import com.juzzIt.EducationProject.DaoInterface.ModuleDaoInterface;
import com.juzzIt.EducationProject.Entity.CourseType;
import com.juzzIt.EducationProject.Entity.Module;
import com.juzzIt.EducationProject.Models.Responce;
import com.juzzIt.EducationProject.Models.UniqueIdGenerations;
import com.juzzIt.EducationProject.ServiceInterface.ModuleServiceInterface;

@Service
public class ModuleServiceImplementation implements ModuleServiceInterface{

	
	@Autowired
	private CourseTypeDaoInterface courseTypeDaoInterface;
	
	@Autowired
	private ModuleDaoInterface moduleDaoInterface;
	
	  @Autowired
		private UniqueIdGenerations uniqueIdGenerations;

		@Autowired
		private EntityDao entityDao;
	
	@Override
	public Responce addModels(String courseTypeId, HashMap<String, Object> model) throws Exception {
Responce responce=new Responce();
		
		try {
		if(model.get("module_Title")==null) {
			responce.setMassege("module Title is needed");
			responce.setStatus(false);
			return null;
		}
		
		
		CourseType courseType = courseTypeDaoInterface.getCourseTypeById(courseTypeId);
		
		if(courseType==null) {
			responce.setMassege("courseType with the given id not found");
			responce.setStatus(false);
			return responce;
		}
		
		Module newModel=new Module();
		newModel.setCourseType(courseType);
		newModel.setActiveModule("D");
		newModel.setModuleTitle(model.get("module_Title").toString());
		
		 HashMap<String, Object> data = new HashMap<String, Object>();
			data.put("Entity_Name", "Module");
			data.put("Entity_SubName", "MODUL");
			data.put("Entity_Count", 0);
			HashMap<String, Object> uniqueIdGeneration = uniqueIdGenerations.uniqueIdGeneration(data);
			// *******
			System.out.println("sep---> "+uniqueIdGeneration);
			String subName = (String) uniqueIdGeneration.get("Entity_SubName");
			long count = (long) uniqueIdGeneration.get("count");
			if (count < 10) {
				newModel.setModuleId(subName + "00" + count);
			} else if (count < 100 && count > 10) {
				newModel.setModuleId(subName + "0" + count);
			} else {
				newModel.setModuleId(subName + "" + count);
			}
			// *******
		
		
		Module addedModels = moduleDaoInterface.addModels(newModel);
		
		if(addedModels==null) {
			responce.setMassege("failed to add the module");
			responce.setStatus(false);
			return responce;
		} else {
			 entityDao.updateCountForCourseTable(data);		
		}
		responce.setMassege("module added successfully");
		responce.setStatus(true);
		}catch (Exception e) {
			throw new Exception("Getting problem while adding Models");
		}
		return responce;
	}


	@Override
	public Responce deleteModels(String modelId) throws Exception {
		// TODO Auto-generated method stub
		return moduleDaoInterface.deleteModels(modelId);
	}


	@Override
	public List<Map<String, Object>> getAllModels(String courseTypeId) throws Exception {
		// TODO Auto-generated method stub
		return moduleDaoInterface.getAllModels(courseTypeId);
	}


	@Override
	public Responce updateModule(String ModuleId,HashMap<String, Object> module) throws Exception {
		// TODO Auto-generated method stub
		return moduleDaoInterface.updateModule(ModuleId, module);
	}
	

}

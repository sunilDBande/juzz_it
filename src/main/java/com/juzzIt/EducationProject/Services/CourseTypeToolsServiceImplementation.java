package com.juzzIt.EducationProject.Services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.juzzIt.EducationProject.Dao.EntityDao;
import com.juzzIt.EducationProject.DaoInterface.CourseTypeDaoInterface;
import com.juzzIt.EducationProject.DaoInterface.CourseTypeToolsDaoInterface;
import com.juzzIt.EducationProject.Entity.CourseType;
import com.juzzIt.EducationProject.Entity.CourseTypeTools;
import com.juzzIt.EducationProject.Models.Responce;
import com.juzzIt.EducationProject.Models.UniqueIdGenerations;
import com.juzzIt.EducationProject.ServiceInterface.CourseTypeToolsServiceInterface;


@Service
public class CourseTypeToolsServiceImplementation implements CourseTypeToolsServiceInterface {

	@Autowired
	private CourseTypeDaoInterface courseTypeDaoInterface;
	
	@Autowired
	private CourseTypeToolsDaoInterface  courseTypeToolsDaoInterface;
	
	  @Autowired
		private UniqueIdGenerations uniqueIdGenerations;

		@Autowired
		private EntityDao entityDao;
		
	@Override
	public Responce addTools(String courseTypeId, HashMap<String, Object> tool) throws Exception {

Responce responce=new Responce();
try {
		if(tool.get("tool_Name")==null ) {
			responce.setMassege("tool Name is needed");
			responce.setStatus(false);
			return responce;
		}
		
		
		CourseType courseType = courseTypeDaoInterface.getCourseTypeById(courseTypeId);
		
		if(courseType==null) {
			responce.setMassege("courseType with the given id not found");
			responce.setStatus(false);
			return responce;
		}
		
		CourseTypeTools newTool=new CourseTypeTools();
		newTool.setCourseType(courseType);
		newTool.setToolName(tool.get("tool_Name").toString());
		  HashMap<String, Object> data = new HashMap<String, Object>();
			data.put("Entity_Name", "CourseTypeTools");
			data.put("Entity_SubName", "CTYTOOLS");
			data.put("Entity_Count", 0);
			HashMap<String, Object> uniqueIdGeneration = uniqueIdGenerations.uniqueIdGeneration(data);
			// *******
			String subName = (String) uniqueIdGeneration.get("Entity_SubName");
			long count = (long) uniqueIdGeneration.get("count");
			if (count < 10) {
				newTool.setToolId(subName + "00" + count);
			} else if (count < 100 && count > 10) {
				newTool.setToolId(subName + "0" + count);
			} else {
				newTool.setToolId(subName + "" + count);
			}
			// *******
		
		
		CourseTypeTools addedTools = courseTypeToolsDaoInterface.addTools(newTool);
		
		if(addedTools==null) {
			responce.setMassege("failed to add the toole");
			responce.setStatus(false);
			return responce;
		} else {
			 entityDao.updateCountForCourseTable(data);		
		}
		responce.setMassege("tool edded successfully");
		responce.setStatus(true);
}catch (Exception e) {
	throw new Exception("Getting problem while adding Tools");
}
		return responce;
		
	}

	@Override
	public Responce deleteTools(String toolId) throws Exception {
		// TODO Auto-generated method stub
		return courseTypeToolsDaoInterface.deleteTools(toolId);
	}

	@Override
	public List<Map<String, Object>> getAllTools(String courseTypeId) throws Exception {
		// TODO Auto-generated method stub
		return courseTypeToolsDaoInterface.getAllToolsByCourseTypeId(courseTypeId);
	}

	
}

package com.juzzIt.EducationProject.Services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.juzzIt.EducationProject.Dao.EntityDao;
import com.juzzIt.EducationProject.DaoInterface.CourseTypeDaoInterface;
import com.juzzIt.EducationProject.DaoInterface.CourseTypeProjectsDaoInterface;
import com.juzzIt.EducationProject.Entity.CourseType;
import com.juzzIt.EducationProject.Entity.CourseTypeProjects;
import com.juzzIt.EducationProject.Models.Responce;
import com.juzzIt.EducationProject.Models.UniqueIdGenerations;
import com.juzzIt.EducationProject.ServiceInterface.CourseTypeProjectsServiceInterface;


@Service
public class CourseTypeProjectsServiceImplementation implements CourseTypeProjectsServiceInterface {

	@Autowired
	private CourseTypeDaoInterface courseTypeDaoInterface;
	
	@Autowired
	private CourseTypeProjectsDaoInterface  courseTypeProjectsDaoInterface;
	
	@Autowired
	private UniqueIdGenerations uniqueIdGenerations;

	@Autowired
	private EntityDao entityDao;
	
	@Override
	public Responce addProjects(String courseTypeId, HashMap<String, Object> project) throws Exception {

Responce responce=new Responce();
try {
		if(project.get("project_Name")==null ||project.get("project_Desc")==null) {
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
		
		CourseTypeProjects newProject=new CourseTypeProjects();
		newProject.setCourseType(courseType);
		
		newProject.setProjectName(project.get("project_Name").toString());
		  HashMap<String, Object> data = new HashMap<String, Object>();
			data.put("Entity_Name", "CourseTypeProjects");
			data.put("Entity_SubName", "CTYPRO");
			data.put("Entity_Count", 0);
			HashMap<String, Object> uniqueIdGeneration = uniqueIdGenerations.uniqueIdGeneration(data);
			// *******
			String subName = (String) uniqueIdGeneration.get("Entity_SubName");
			long count = (long) uniqueIdGeneration.get("count");
			if (count < 10) {
				newProject.setProjectId(subName + "00" + count);
			} else if (count < 100 && count > 10) {
				newProject.setProjectId(subName + "0" + count);
			} else {
				newProject.setProjectId(subName + "" + count);
			}
			// *******
		newProject.setProjectDesc(project.get("project_Desc").toString());
		
		
		CourseTypeProjects addedProjects = courseTypeProjectsDaoInterface.addProjects(newProject);
		
		if(addedProjects==null) {
			responce.setMassege("failed to add the project");
			responce.setStatus(false);
			return responce;
		}else {
			 entityDao.updateCountForCourseTable(data);		
		}
		responce.setMassege("project added successfully");
		responce.setStatus(true);
}catch (Exception e) {
	throw new Exception("Getting problem while adding Projects");
}
		return responce;
	}

	@Override
	public Responce deleteProjects(String projectsId) throws Exception {
		
		return courseTypeProjectsDaoInterface.deleteProjects(projectsId);
	}

	@Override
	public List<Map<String, Object>> getAllProjects(String courseTypeId) throws Exception {
		return courseTypeProjectsDaoInterface.getAllProjectsByCourseTypeId(courseTypeId);
	}

	

}

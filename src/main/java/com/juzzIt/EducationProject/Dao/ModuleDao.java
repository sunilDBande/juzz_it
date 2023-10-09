package com.juzzIt.EducationProject.Dao;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.juzzIt.EducationProject.DaoInterface.CourseTypeDaoInterface;
import com.juzzIt.EducationProject.DaoInterface.ModuleDaoInterface;
import com.juzzIt.EducationProject.Entity.CourseType;
import com.juzzIt.EducationProject.Entity.Module;
import com.juzzIt.EducationProject.Models.Responce;
import com.juzzIt.EducationProject.Repositary.ModuleRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;



@Component
public class ModuleDao implements ModuleDaoInterface{


	@Autowired
	private CourseTypeDaoInterface courseTypeDaoInterface;
	
	@Autowired
	private EntityManager entityManager;
	
	
	@Autowired
	private ModuleRepository moduleRepository;


	@Override
	public Module addModels(Module model) {
		// TODO Auto-generated method stub
		return moduleRepository.save(model);
	}


	@Override
	public Responce deleteModels(String modelId) throws Exception {
		Responce responce=new Responce();
		try {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		
		CriteriaQuery<Module> createQuery = criteriaBuilder.createQuery(Module.class);		
		
		
		Root<Module> root = createQuery.from(Module.class);
		
		Predicate predicate = criteriaBuilder.equal(root.get("moduleId"), modelId);
		
		
		createQuery.select(root).where(predicate);
		
		List<Module> resultList = entityManager.createQuery(createQuery).getResultList();
		
		
		
		
//		Optional<Module> model = moduleRepository.findById(modelId);
		if(!resultList.isEmpty()) {
			moduleRepository.delete(resultList.get(0));
			responce.setMassege("module deleted successfully");
			responce.setStatus(true);
		}else {
			responce.setMassege("model with the given id not found");
			responce.setStatus(false);
			
		}
		}catch (Exception e) {
			throw new Exception("Getting problem while deleting Models");
		}
		return responce;
	}


	@Override
	public List<Map<String, Object>> getAllModels(String courseTypeId) throws Exception {
		List<Map<String, Object>> collect = null;
		try {
		
		CourseType courseType = courseTypeDaoInterface.getCourseTypeById(courseTypeId);

		if(courseType==null) {
			return null;
		}
		
CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

CriteriaQuery<Module> createQuery = criteriaBuilder.createQuery(Module.class);		
Root<Module> root = createQuery.from(Module.class);

Predicate predicate = criteriaBuilder.equal(root.get("courseType"), courseType);

createQuery.select(root).where(predicate);

List<Module> resultList = entityManager.createQuery(createQuery).getResultList();

   collect = resultList.stream().map(result->{
	
	Map<String, Object> map=new LinkedHashMap<String, Object>();
	map.put("module_Id", result.getModuleId());
	map.put("module_Title", result.getModuleTitle());
	map.put("active_Module", result.getActiveModule());
	return map;
}).collect(Collectors.toList());
		}catch (Exception e) {
			throw new Exception("Getting problem while getting All Models");
		}

		return collect;
	}


	@Override
	public Module getModuleByModuleId(String moduleId) {
		
CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		
		CriteriaQuery<Module> createQuery = criteriaBuilder.createQuery(Module.class);		
		
		
		Root<Module> root = createQuery.from(Module.class);
		
		Predicate predicate = criteriaBuilder.equal(root.get("moduleId"), moduleId);
		
		
		createQuery.select(root).where(predicate);
		
		List<Module> resultList = entityManager.createQuery(createQuery).getResultList();
		
//		Optional<Module> model = moduleRepository.findById(moduleId);
		if(!resultList.isEmpty()) {
			return resultList.get(0);
			}
		return null;
	}


	@Override
	public Responce updateModule(String moduleId,HashMap<String, Object> module) throws Exception {

		
		Responce responce= new Responce();
		try {
		Module model = getModuleByModuleId(moduleId);
		if(model!=null) {
			if(module.get("module_title")!=null) {
				model.setModuleTitle(module.get("module_title").toString());	
			}
			if(module.get("active_module")!=null) {
				if(model.getActiveModule().equalsIgnoreCase("D")) {
					model.setActiveModule("A");
				}else {
					model.setActiveModule("D");
				}
			}
	
			Module updatedModule = moduleRepository.save(model);
			if(updatedModule==null) {
				responce.setStatus(false);
				responce.setMassege("failed to save the details");
			}
			
			responce.setStatus(true);

			responce.setMassege("details updated successfully");
		
			}
		else {
			
			responce.setStatus(false);
            responce.setMassege("module with the given id not found");
		}
		}catch (Exception e) {
			throw new Exception("Getting problem while updating Module");
		}
		return responce;
	}

	
}

package com.juzzIt.EducationProject.Dao;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.juzzIt.EducationProject.DaoInterface.ModuleImagesDaoInterface;
import com.juzzIt.EducationProject.Entity.Module;
import com.juzzIt.EducationProject.Entity.ModuleImages;
import com.juzzIt.EducationProject.Models.Responce;
import com.juzzIt.EducationProject.Repositary.ModuleImagesRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;


@Component
public class ModuleImagesDao implements ModuleImagesDaoInterface {
	
	@Autowired
	private ModuleImagesRepository moduleImagesRepository;
	
	@Autowired
	private EntityManager entityManager;

	@Override
	public ModuleImages addModuleImages(ModuleImages moduleImages) {
		// TODO Auto-generated method stub
		return moduleImagesRepository.save(moduleImages);
	}

	@Override
	public ModuleImages getModuleImagesById(String moduleImagesId) {
		
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		
		CriteriaQuery<ModuleImages> createQuery = criteriaBuilder.createQuery(ModuleImages.class);
		Root<ModuleImages> root = createQuery.from(ModuleImages.class);
		
		Predicate equal = criteriaBuilder.equal(root.get("moduleImageId"), moduleImagesId);
		createQuery.select(root).where(equal);
		
		List<ModuleImages> resultList = entityManager.createQuery(createQuery).getResultList();
		
		if(resultList.isEmpty()) {
			return null;
		}
		return resultList.get(0);
	}

	@Override
	public Responce deleteModuleImages(String imageId) {
		ModuleImages moduleImage = getModuleImagesById(imageId);
		Responce responce=new Responce();
		
		if(moduleImage==null) {
			responce.setMassege("image with the given id not found");
			responce.setStatus(false);
			return responce;
		}
		
		
		moduleImagesRepository.delete(moduleImage);
		responce.setMassege("image deleted successfully");
		responce.setStatus(true);
		
		
		return responce;
	}

	@Override
	public Responce updateModuleImages(String imageId, HashMap<String, Object> imageData) {
		ModuleImages moduleImage = getModuleImagesById(imageId);
		Responce responce=new Responce();
		
		if(moduleImage==null) {
			responce.setMassege("image with the given id not found");
			responce.setStatus(false);
			return responce;
		}
		
		if(imageData.get("active_Status")!=null) {
			if(moduleImage.getActiveStatus().equalsIgnoreCase("D")) {
				moduleImage.setActiveStatus("A");
			}else {
				moduleImage.setActiveStatus("D");
			}
		}
		
		ModuleImages moduleImages = moduleImagesRepository.save(moduleImage);
		if(moduleImages==null) {
			responce.setMassege("failed to update the data");
			responce.setStatus(false);
			return responce;
		}
		
		
		responce.setMassege("image data updated successfully");
		responce.setStatus(true);
		
		return responce;
	}

	@Override
	public List<Map<String, Object>> getAllImageByModule(Module module) {
CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		
		CriteriaQuery<ModuleImages> createQuery = criteriaBuilder.createQuery(ModuleImages.class);
		Root<ModuleImages> root = createQuery.from(ModuleImages.class);
		
		Predicate equal = criteriaBuilder.equal(root.get("module"), module);
		createQuery.select(root).where(equal);
		
		List<ModuleImages> resultList = entityManager.createQuery(createQuery).getResultList();
		
		List<Map<String, Object>> collect = resultList.stream().map(result->{
			Map<String, Object> map=new LinkedHashMap<String, Object>();
			map.put("IMAGE_ID", result.getModuleImageId());
			map.put("IMAGE_URL", result.getImageURL());
			map.put("CREATED_DATE", result.getCreatedDate());
			map.put("active_status", result.getActiveStatus());
			return map;
		}).collect(Collectors.toList());
		
		return collect;
	}

	
}

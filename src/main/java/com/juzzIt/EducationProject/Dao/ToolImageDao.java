package com.juzzIt.EducationProject.Dao;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.juzzIt.EducationProject.DaoInterface.ToolImageDaoInterface;
import com.juzzIt.EducationProject.Entity.CourseTypeTools;
import com.juzzIt.EducationProject.Entity.ToolImage;
import com.juzzIt.EducationProject.Models.Responce;
import com.juzzIt.EducationProject.Repositary.ToolImageRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

@Component
public class ToolImageDao  implements ToolImageDaoInterface{

	
	@Autowired
	private ToolImageRepository  toolImageRepository;
	
	@Autowired
	private EntityManager entityManager;
	
	
	@Override
	public ToolImage addNewToolImage(ToolImage toolImage) {
		// TODO Auto-generated method stub
		return toolImageRepository.save(toolImage);
	}

	@Override
	public ToolImage getToolImageById(String toolImageId) {
		
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<ToolImage> createQuery = criteriaBuilder.createQuery(ToolImage.class);
		Root<ToolImage> root = createQuery.from(ToolImage.class);
		Predicate predicate = criteriaBuilder.equal(root.get("toolImageId"), toolImageId);
		
		createQuery.select(root).where(predicate);
		List<ToolImage> resultList = entityManager.createQuery(createQuery).getResultList();
		
	if(resultList.isEmpty()) {
			return null;
	}
		return resultList.get(0);
	}


	@Override
	public Responce deleteToolImage(String toolImageById) {
		ToolImage toolImage = getToolImageById(toolImageById);
		Responce responce=new Responce();
		
		if(toolImage==null) {
			responce.setMassege("tool Image with the given id not found");
			responce.setStatus(false);
			
		}else {
			toolImageRepository.delete(toolImage);
			responce.setMassege("tool Image deleted sucessfully");
			responce.setStatus(false);
		}
		return responce;
	}

	@Override
	public Responce updateToolImage(String toolImageId, HashMap<String, Object> toolImageData) {
		ToolImage toolImage = getToolImageById(toolImageId);
		Responce responce=new Responce();
		
		if(toolImage==null) {
			responce.setMassege("tool Image with the given id not found");
			responce.setStatus(false);
			
		}else {
			
			if(toolImageData.get("image_URL")!=null) {
				toolImage.setImageURL(toolImageData.get("image_URL").toString());
			}
			
	if(toolImageData.get("active_status")!=null) {
				
		
if(toolImage.getActiveStatus().equalsIgnoreCase("D")) {
	toolImage.setActiveStatus("A");
}else {
	toolImage.setActiveStatus("D");
}
			}
	
	ToolImage updatedToolImage = toolImageRepository.save(toolImage);
			
			if(updatedToolImage==null) {
				responce.setMassege("failed to  update the detail");
				responce.setStatus(false);
				return responce;
			}
			
			responce.setMassege("details updated successfully");
			responce.setStatus(false);
			return responce;
			
		}
		
		return responce;
	}

	@Override
	public List<Map<String, Object>> getToolImage(CourseTypeTools courseTypeTool) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<ToolImage> createQuery = criteriaBuilder.createQuery(ToolImage.class);
		Root<ToolImage> root = createQuery.from(ToolImage.class);
		Predicate predicate = criteriaBuilder.equal(root.get("CourseTypeTools"), courseTypeTool);
		createQuery.select(root).where(predicate);
		List<ToolImage> resultList = entityManager.createQuery(createQuery).getResultList();
		
		List<Map<String, Object>> collect = resultList.stream().map(result->{
			Map<String, Object> map=new LinkedHashMap<String, Object>();
			map.put("tool_ImageId", result.getToolImageId());
			map.put("image_URL", result.getImageURL());
			map.put("active_Status", result.getActiveStatus());
			map.put("created_Date", result.getCreatedDate());
			return map;
		}).collect(Collectors.toList());
		return collect;
		
	}

}

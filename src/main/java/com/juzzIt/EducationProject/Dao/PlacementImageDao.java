package com.juzzIt.EducationProject.Dao;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.juzzIt.EducationProject.DaoInterface.PlacementImageDaoInterface;
import com.juzzIt.EducationProject.Entity.BatchCoursePlacements;
import com.juzzIt.EducationProject.Entity.PlacementImage;
import com.juzzIt.EducationProject.Models.Responce;
import com.juzzIt.EducationProject.Repositary.PlacementImageRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

@Component
public class PlacementImageDao implements PlacementImageDaoInterface {

	@Autowired
	private PlacementImageRepository  placementImageRepository;
	
	@Autowired
	private EntityManager entityManager;
	
	
	@Override
	public PlacementImage addNewPlacementImage(PlacementImage placementImage) {
		return placementImageRepository.save(placementImage);
	}

	
	@Override
	public PlacementImage getPlacementImageById(String placementImageId) {
	
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<PlacementImage> createQuery = criteriaBuilder.createQuery(PlacementImage.class);
		Root<PlacementImage> root = createQuery.from(PlacementImage.class);
		Predicate predicate = criteriaBuilder.equal(root.get("palcementImageId"), placementImageId);
		createQuery.select(root).where(predicate);
		List<PlacementImage> resultList = entityManager.createQuery(createQuery).getResultList();
		if(resultList.isEmpty()) {
			return null;
		}
		return resultList.get(0);
	}

	@Override
	public Responce deletePlacementImage(String placementimageId) {
		PlacementImage placementImage= getPlacementImageById(placementimageId);
		
		Responce responce=new Responce();
		if(placementImage==null) {
			responce.setMassege("placcement with the the given id not found");
			responce.setStatus(false);
			return responce;
		}
		
		placementImageRepository.delete(placementImage);
		responce.setMassege("placement image deleted successfully");
		responce.setStatus(true);
		return responce;
	}

	@Override
	public Responce updatePlacementImage(String placementImageId, HashMap<String, Object> placementImageData) {
	PlacementImage placementImage= getPlacementImageById(placementImageId);
		
		Responce responce=new Responce();
		if(placementImage==null) {
			responce.setMassege("placcement with the the given id not found");
			responce.setStatus(false);
			return responce;
		}
		if(placementImageData.get("image_URL")!=null) {
			placementImage.setImageURL(placementImageData.get("image_URL").toString());
		}
		if(placementImageData.get("active_Status")!=null) {
			
			if(placementImage.getActiveStatus().equalsIgnoreCase("D")) {
				placementImage.setActiveStatus("A");
			}else {
				placementImage.setActiveStatus("D");
			}	
		}
		PlacementImage updatedplacementImage = placementImageRepository.save(placementImage);
		if(updatedplacementImage==null) {
			responce.setMassege("pailed to update the details");
			responce.setStatus(false);
			return responce;
		}
		responce.setMassege("details updated successfully");
		responce.setStatus(true);
		return responce;
	}

	@Override
	public List<Map<String, Object>> getPlacementImate(BatchCoursePlacements placement) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<PlacementImage> createQuery = criteriaBuilder.createQuery(PlacementImage.class);
		Root<PlacementImage> root = createQuery.from(PlacementImage.class);
		Predicate predicate = criteriaBuilder.equal(root.get("batchCoursePlacements"), placement);
		createQuery.select(root).where(predicate);
		List<PlacementImage> resultList = entityManager.createQuery(createQuery).getResultList();
		List<Map<String, Object>> collect = resultList.stream().map(result->{
			Map<String, Object> map=new LinkedHashMap<String, Object>();
			map.put("image_id", result.getPalcementImageId());
			map.put("Image_URL", result.getImageURL());
			map.put("active_Status", result.getActiveStatus());
			return map;
		}).collect(Collectors.toList());
		return collect;
	}

}

package com.juzzIt.EducationProject.Dao;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.juzzIt.EducationProject.DaoInterface.RecodedStudentPlacementImageDaoInterface;
import com.juzzIt.EducationProject.Entity.RecodedStudentPlacementImage;
import com.juzzIt.EducationProject.Entity.RecordedStudentPlacemants;
import com.juzzIt.EducationProject.Models.Responce;
import com.juzzIt.EducationProject.Repositary.RecodedStudentPlacementImageRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

@Component
public class RecodedStudentPlacementImageDao implements RecodedStudentPlacementImageDaoInterface {

	
	@Autowired
	private RecodedStudentPlacementImageRepository recodedStudentPlacementImageRepository;
	
	
	@Autowired
	private EntityManager entityManager;
	
	@Override
	public RecodedStudentPlacementImage addRecodedStudentPlacementImage(
			RecodedStudentPlacementImage recodedStudentPlacementImage) {
		// TODO Auto-generated method stub
		return recodedStudentPlacementImageRepository.save(recodedStudentPlacementImage);
	}

	@Override
	public RecodedStudentPlacementImage getRecodedStudentPlacementImageById(String imageId) {
	CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
	CriteriaQuery<RecodedStudentPlacementImage> createQuery = criteriaBuilder.createQuery(RecodedStudentPlacementImage.class);
	Root<RecodedStudentPlacementImage> root = createQuery.from(RecodedStudentPlacementImage.class);
	Predicate predicate = criteriaBuilder.equal(root.get(""), imageId);
	createQuery.select(root).where(predicate);
	
	List<RecodedStudentPlacementImage> resultList = entityManager.createQuery(createQuery).getResultList();
	if(resultList.isEmpty()) {
		return null;
	}
	return resultList.get(0);
	}

	@Override
	public Responce deleteRecodedStudentPlacementImage(String imageId) {
		RecodedStudentPlacementImage recodedStudentPlacementImage = getRecodedStudentPlacementImageById(imageId);
     	Responce responce=new Responce();
        if(recodedStudentPlacementImage==null) {
	         responce.setMassege("image with the given id not found");
	         responce.setStatus(false);
	         return responce;
          }
        recodedStudentPlacementImageRepository.delete(recodedStudentPlacementImage);
        responce.setMassege("image deleted successfully");
        responce.setStatus(true);
		return responce;
	}

	@Override
	public Responce updateRecodedStudentPlacementImage(String imageId, HashMap<String, Object> imageData) {
		RecodedStudentPlacementImage recodedStudentPlacementImage = getRecodedStudentPlacementImageById(imageId);
     	Responce responce=new Responce();
        if(recodedStudentPlacementImage==null) {
	         responce.setMassege("image with the given id not found");
	         responce.setStatus(false);
	         return responce;
          }
        if(imageData.get("actuve_status")!=null) {	
        	if(recodedStudentPlacementImage.getActiveStatus().equalsIgnoreCase("D")) {
        		recodedStudentPlacementImage.setActiveStatus("A");
        	}else {
        		recodedStudentPlacementImage.setActiveStatus("D");
        	}  	
        }  
        RecodedStudentPlacementImage updatedRecodedStudentPlacementImage = recodedStudentPlacementImageRepository.save(recodedStudentPlacementImage);
	if(updatedRecodedStudentPlacementImage==null) {
		responce.setMassege("failed to update the details");
		responce.setStatus(false);
		return responce;
	}
	responce.setMassege("details updated successfully");
	responce.setStatus(true);
        return responce;
	}

	@Override
	public List<Map<String, Object>> getRecodedStudentPlacementImage(RecordedStudentPlacemants recordedStudentPlacemants) {
	     	CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
	    	CriteriaQuery<RecodedStudentPlacementImage> createQuery = criteriaBuilder.createQuery(RecodedStudentPlacementImage.class);
	     	Root<RecodedStudentPlacementImage> root = createQuery.from(RecodedStudentPlacementImage.class);
	    	Predicate predicate = criteriaBuilder.equal(root.get("recordedStudentPlacemants"), recordedStudentPlacemants);
		    createQuery.select(root).where(predicate);
		
		    List<RecodedStudentPlacementImage> resultList = entityManager.createQuery(createQuery).getResultList();
		    List<Map<String, Object>> collect = resultList.stream().map(result->{
			Map<String, Object> map=new LinkedHashMap<String, Object>();
			map.put("", result.getImageId());
			map.put("", result.getImageURL());
			map.put("", result.getActiveStatus());
			return map;
		}).collect(Collectors.toList());
		return collect;
	}

}

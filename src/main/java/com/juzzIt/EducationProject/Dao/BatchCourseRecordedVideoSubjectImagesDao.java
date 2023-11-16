package com.juzzIt.EducationProject.Dao;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.juzzIt.EducationProject.DaoInterface.BatchCourseRecordedVideoSubjectImagesDaoInterface;
import com.juzzIt.EducationProject.Entity.BatchCourseRecordedVideoSubject;
import com.juzzIt.EducationProject.Entity.BatchCourseRecordedVideoSubjectImages;
import com.juzzIt.EducationProject.Models.Responce;
import com.juzzIt.EducationProject.Repositary.BatchCourseRecordedVideoSubjectImagesRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;


@Component
public class BatchCourseRecordedVideoSubjectImagesDao  implements BatchCourseRecordedVideoSubjectImagesDaoInterface{

	
	@Autowired
	private BatchCourseRecordedVideoSubjectImagesRepository batchCourseRecordedVideoSubjectImagesRepository;
	
	@Autowired
	private EntityManager entityManager;

	@Override
	public BatchCourseRecordedVideoSubjectImages addBatchCourseRecordedVideoSubjectImages(
			BatchCourseRecordedVideoSubjectImages batchCourseRecordedVideoSubjectImages) {
		// TODO Auto-generated method stub
		return batchCourseRecordedVideoSubjectImagesRepository.save(batchCourseRecordedVideoSubjectImages);
	}
	
	
	@Override
	public BatchCourseRecordedVideoSubjectImages getBatchCourseRecordedVideoSubjectImagesById(String imageId) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<BatchCourseRecordedVideoSubjectImages> createQuery = criteriaBuilder.createQuery(BatchCourseRecordedVideoSubjectImages.class);
		Root<BatchCourseRecordedVideoSubjectImages> root = createQuery.from(BatchCourseRecordedVideoSubjectImages.class);
		Predicate predicate = criteriaBuilder.equal(root.get("subjectImageId"), imageId);
		createQuery.select(root).where(predicate);
		List<BatchCourseRecordedVideoSubjectImages> resultList = entityManager.createQuery(createQuery).getResultList();
		if(resultList.isEmpty()) {
			return null;
		}
		return resultList.get(0);
	}

	@Override
	public Responce deleteBatchCourseRecordedVideoSubjectImages(String imageId) {
		BatchCourseRecordedVideoSubjectImages batchCourseRecordedVideoSubjectImages = getBatchCourseRecordedVideoSubjectImagesById(imageId);
		
		Responce responce =new Responce();
		
		
		if(batchCourseRecordedVideoSubjectImages==null) {
			responce.setMassege("image with the given id not found");
			responce.setStatus(false);
			return responce;
		}
		
		batchCourseRecordedVideoSubjectImagesRepository.delete(batchCourseRecordedVideoSubjectImages);
		responce.setMassege("image deleted suceccfully");
		responce.setStatus(true);
		return responce;
	}

	@Override
	public Responce updateBatchCourseRecordedVideoSubjectImages(String imageId, HashMap<String, Object> imageData) {
		BatchCourseRecordedVideoSubjectImages batchCourseRecordedVideoSubjectImages = getBatchCourseRecordedVideoSubjectImagesById(imageId);
		Responce responce =new Responce();		
		if(batchCourseRecordedVideoSubjectImages==null) {
			responce.setMassege("image with the given id not found");
			responce.setStatus(false);
			return responce;
		}
		
		if(imageData.get("active_status")!=null) {
			
			if(batchCourseRecordedVideoSubjectImages.getActiveStatus().equalsIgnoreCase("A")) {
				batchCourseRecordedVideoSubjectImages.setActiveStatus("D");
			}else {
				batchCourseRecordedVideoSubjectImages.setActiveStatus("A");
			}
		}
		
		
		
		BatchCourseRecordedVideoSubjectImages batchCourseRecordedVideoSubjectImages2 = batchCourseRecordedVideoSubjectImagesRepository.save(batchCourseRecordedVideoSubjectImages);
	
		if(batchCourseRecordedVideoSubjectImages2==null) {
			responce.setMassege("failed to update the details");
			responce.setStatus(false);
			return responce;
		}
		
		responce.setMassege("suceessfully updated the data");
		responce.setStatus(true);
		
		return responce;
	}

	@Override
	public List<Map<String, Object>> getAllSubjectImagesBySubject(
			BatchCourseRecordedVideoSubject batchCourseRecordedVideoSubject) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<BatchCourseRecordedVideoSubjectImages> createQuery = criteriaBuilder.createQuery(BatchCourseRecordedVideoSubjectImages.class);
		Root<BatchCourseRecordedVideoSubjectImages> root = createQuery.from(BatchCourseRecordedVideoSubjectImages.class);
		Predicate predicate = criteriaBuilder.equal(root.get("batchCourseRecordedVideoSubject"), batchCourseRecordedVideoSubject);
		createQuery.select(root).where(predicate);
		List<BatchCourseRecordedVideoSubjectImages> resultList = entityManager.createQuery(createQuery).getResultList();
		List<Map<String, Object>> collect = resultList.stream().map(result->{
			Map<String, Object> map=new LinkedHashMap<String, Object>();
			map.put("image_Id", result.getSubjectImageId());
			map.put("image_URL", result.getImageURL());
			map.put("active_status", result.getActiveStatus());			
			map.put("created_Date", result.getCreatedDate());
			return map;
		}).collect(Collectors.toList());
		return collect;
	}

}

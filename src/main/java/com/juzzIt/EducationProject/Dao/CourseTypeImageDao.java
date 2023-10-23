package com.juzzIt.EducationProject.Dao;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.juzzIt.EducationProject.DaoInterface.CourseTypeImageDaoInterface;
import com.juzzIt.EducationProject.Entity.CourseType;
import com.juzzIt.EducationProject.Entity.CourseTypeImage;
import com.juzzIt.EducationProject.Models.Responce;
import com.juzzIt.EducationProject.Repositary.CourseTypeImageRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;


@Component
public class CourseTypeImageDao implements CourseTypeImageDaoInterface{
	
	
	@Autowired 
	private CourseTypeImageRepository courseTypeImageRepository;
	
	@Autowired
	private EntityManager entityManager;

	@Override
	public CourseTypeImage addNewCourseTypeImage(CourseTypeImage CourseTypeImage) {
	
		return courseTypeImageRepository.save(CourseTypeImage);
	}
	
	
	
	@Override
	public CourseTypeImage getCourseTypeImageById(String courseTypeImageId) {
	
		
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		
		CriteriaQuery<CourseTypeImage> createQuery = criteriaBuilder.createQuery(CourseTypeImage.class);
		Root<CourseTypeImage> root = createQuery.from(CourseTypeImage.class);
		
		Predicate predicate = criteriaBuilder.equal(root.get("courseTypeImageId"), courseTypeImageId);
		
		createQuery.select(root).where(predicate);
		
		List<CourseTypeImage> resultList = entityManager.createQuery(createQuery).getResultList();
		
	if(resultList.isEmpty()) {
		return null;
	}
		
		
		
		return resultList.get(0);
	}

	@Override
	public Responce deleteCourseTypeImage(String courseTypeImageId) {
		
		 CourseTypeImage courseTypeImage = getCourseTypeImageById(courseTypeImageId);
		 Responce responce =new Responce();
		 
		 if(courseTypeImage==null) {
			 
			 responce.setMassege("courseType image withe the gicven id not found");
			 responce.setStatus(false);
			 
			 return responce;
		 }
		 
		 courseTypeImageRepository.delete(courseTypeImage);
		 responce.setMassege("courseType Image deleted successfully");
		 responce.setStatus(true);
		 
		 
		
		
		return responce;
	}



	@Override
	public Responce updateCourseTypeImage(String courseTypeImageId, HashMap<String, Object> courseTypeImageData) {
		 CourseTypeImage courseTypeImage = getCourseTypeImageById(courseTypeImageId);
		 Responce responce =new Responce();
		 
	 if(courseTypeImage==null) {
			 
			 responce.setMassege("courseType image withe the gicven id not found");
			 responce.setStatus(false);
			 
			 return responce;
		 }
	 
	 
	 
	 
	 if(courseTypeImageData.get("image_URL")!=null) {
		 courseTypeImage.setImageURL(courseTypeImageData.get("image_URL").toString());
	 }
	 
	 if(courseTypeImageData.get("active_status")!=null) {
		 
		 if(courseTypeImage.getActiveStatus().equalsIgnoreCase("D")) {
			 courseTypeImage.setActiveStatus("A");
		 }else {
			 courseTypeImage.setActiveStatus("D");
		 }
	 }
	 
	 CourseTypeImage updatedcourseTypeImage = courseTypeImageRepository.save(courseTypeImage);
	 if(updatedcourseTypeImage==null) {
		 
		 responce.setMassege("failed to update  the details");
		 responce.setStatus(false);
		 
		 return responce;
	 }
	 
		 
	 responce.setMassege("details updated successfully");
	 responce.setStatus(false);
			 
		return responce;
	}

	@Override
	public List<Map<String, Object>> getAllCourseTypeImage(CourseType courseType) {
		
CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		
		CriteriaQuery<CourseTypeImage> createQuery = criteriaBuilder.createQuery(CourseTypeImage.class);
		Root<CourseTypeImage> root = createQuery.from(CourseTypeImage.class);
		
		Predicate predicate = criteriaBuilder.equal(root.get("courseType"), courseType);
		
		createQuery.select(root).where(predicate);
		
		List<CourseTypeImage> resultList = entityManager.createQuery(createQuery).getResultList();
		
		List<Map<String, Object>> collect = resultList.stream().map(result->{
			Map<String, Object> map=new LinkedHashMap<String, Object>();
			map.put("courseType_image_Id", result.getCourseTypeImageId());
			map.put("image_URL", result.getImageURL());
			map.put("active_Status", result.getActiveStatus());
			map.put("created_Date", result.getCreatedDate());
			return map;
		}).collect(Collectors.toList());

		return collect;
	}

}

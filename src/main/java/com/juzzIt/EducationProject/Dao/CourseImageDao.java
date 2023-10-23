package com.juzzIt.EducationProject.Dao;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List; 
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.juzzIt.EducationProject.DaoInterface.CourseImageDaoInterface;
import com.juzzIt.EducationProject.Entity.Course;
import com.juzzIt.EducationProject.Entity.CourseImage;
import com.juzzIt.EducationProject.Models.Responce;
import com.juzzIt.EducationProject.Repositary.CourseImageRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;


@Component
public class CourseImageDao implements CourseImageDaoInterface{

	
	@Autowired
	private CourseImageRepository courseImageRepository;
	
	@Autowired
	private EntityManager entityManager;

	@Override
	public CourseImage addNewCourseImage(CourseImage courseImage) {
		return courseImageRepository.save(courseImage);
	}

	@Override
	public CourseImage getCourseImageById(String courseImageId) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();		
		CriteriaQuery<CourseImage> createQuery = criteriaBuilder.createQuery(CourseImage.class);
		Root<CourseImage> root = createQuery.from(CourseImage.class);
		Predicate predicate = criteriaBuilder.equal(root.get("courseImageId"), courseImageId);
		createQuery.select(root).where(predicate);
		List<CourseImage> resultList = entityManager.createQuery(createQuery).getResultList();
		
		if(resultList.isEmpty()) {
			return null;
		}
		return resultList.get(0);
	}
	
	@Override
	public Responce deleteCourseImage(String courseTypeImageId) {
		
		CourseImage courseImage = getCourseImageById(courseTypeImageId);
		Responce responce=new Responce();
		if(courseImage!=null) {
			courseImageRepository.delete(courseImage);
			responce.setMassege("course image deleted successfully");
			responce.setStatus(true);	
		}else {
			responce.setMassege("course image withe the givan id not found");
			responce.setStatus(false);
		}
		return responce;
	}

	@Override
	public Responce updateCourseImage(String courseTypeImageId, HashMap<String, Object> courseImageData) {
		CourseImage courseImage = getCourseImageById(courseTypeImageId);
		Responce responce=new Responce();
		if(courseImage!=null) {
			
			
			if(courseImageData.get("image_url")!=null) {
				courseImage.setImageURL(courseImageData.get("image_url").toString());
			}
			
			if(courseImageData.get("active_Status")!=null) {
						if(courseImage.getActiveStatus().equalsIgnoreCase("D")) {
							courseImage.setActiveStatus("A");
						}else {
							courseImage.setActiveStatus("D");
						}
			}
			CourseImage updatedcourseImage = courseImageRepository.save(courseImage);
			if(updatedcourseImage==null) {
				responce.setMassege("failed to update the data");
				responce.setStatus(false);
			}
			responce.setMassege("successfully updated the data");
			responce.setStatus(true);
		}else {	
			responce.setMassege("");
			responce.setStatus(false);
		}		
		return responce;
	}

	@Override
	public List<Map<String, Object>> getCoueseImage(Course course) {
		
	CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		
		
		CriteriaQuery<CourseImage> createQuery = criteriaBuilder.createQuery(CourseImage.class);
		Root<CourseImage> root = createQuery.from(CourseImage.class);
		
		Predicate predicate = criteriaBuilder.equal(root.get("course"), course);
		
		createQuery.select(root).where(predicate);
		List<CourseImage> resultList = entityManager.createQuery(createQuery).getResultList();
		
		List<Map<String, Object>> collect = resultList.stream().map(result->{
			
			Map<String , Object> map=new LinkedHashMap<String, Object>();
			map.put("image_Id", result.getCourseImageId());
			map.put("image_URL", result.getImageURL());
			map.put("created_Data", result.getCreatedDate());
			map.put("active_Status", result.getActiveStatus());
			return map;
		}).collect(Collectors.toList());
		
		return collect;
	}

	
}

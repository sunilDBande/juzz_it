package com.juzzIt.EducationProject.Dao;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.juzzIt.EducationProject.DaoInterface.CourseTypeBagroundImageDaoInterface;
import com.juzzIt.EducationProject.Entity.CourseType;
import com.juzzIt.EducationProject.Entity.CourseTypeBagroundImage;
import com.juzzIt.EducationProject.Models.Responce;
import com.juzzIt.EducationProject.Repositary.CourseTypeBagroundImageRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

@Component
public class CourseTypeBagroundImageDao  implements CourseTypeBagroundImageDaoInterface{

	
	@Autowired
	private CourseTypeBagroundImageRepository courseTypeBagroundImageRepository;
	
	@Autowired
	private EntityManager entityManager;
	
	
	@Override
	public CourseTypeBagroundImage addNewCourseTypeBagroundImage(CourseTypeBagroundImage CourseTypeBagroundImage) {
		// TODO Auto-generated method stub
		return courseTypeBagroundImageRepository.save(CourseTypeBagroundImage);
	}

	@Override
	public CourseTypeBagroundImage getCourseTypeBagroundImageById(String CourseTypeBagroundImageId) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();		
		CriteriaQuery<CourseTypeBagroundImage> createQuery = criteriaBuilder.createQuery(CourseTypeBagroundImage.class);
		Root<CourseTypeBagroundImage> root = createQuery.from(CourseTypeBagroundImage.class);
		Predicate predicate = criteriaBuilder.equal(root.get("courseTypeBagroundImageId"), CourseTypeBagroundImageId);
		createQuery.select(root).where(predicate);
		List<CourseTypeBagroundImage> resultList = entityManager.createQuery(createQuery).getResultList();
		
		if(resultList.isEmpty()) {
			return null;
		}
	
		return resultList.get(0);
	}

	@Override
	public Responce deleteCourseTypeBagroundImage(String CourseTypeBagroundImageId) {
		CourseTypeBagroundImage courseTypeBagroundImage = getCourseTypeBagroundImageById(CourseTypeBagroundImageId);
		Responce responce=new Responce();
		if(courseTypeBagroundImage==null) {
			responce.setMassege("courseTypeBaground Image with the given id not found");
			responce.setStatus(false);
		
			
		}else {
			courseTypeBagroundImageRepository.delete(courseTypeBagroundImage);
			
			responce.setMassege("courseTypeBaground Image deleted successfully");
			responce.setStatus(false);
		}
		return responce;
	}

	@Override
	public Responce updateCourseTypeBagroundImage(String courseTypeBagroundImageId,
			HashMap<String, Object> courseTypeBagroundImageData) {
		CourseTypeBagroundImage courseTypeBagroundImage = getCourseTypeBagroundImageById(courseTypeBagroundImageId);
		Responce responce=new Responce();
		if(courseTypeBagroundImage!=null) {
			
			
			if(courseTypeBagroundImageData.get("image_url")!=null) {
				courseTypeBagroundImage.setImageURL(courseTypeBagroundImageData.get("image_url").toString());
			}
			
			if(courseTypeBagroundImageData.get("active_status")!=null) {
						if(courseTypeBagroundImage.getActiveStatus().equalsIgnoreCase("D")) {
							courseTypeBagroundImage.setActiveStatus("A");
						}else {
							courseTypeBagroundImage.setActiveStatus("D");
						}
			}
			CourseTypeBagroundImage updatedcourseTypeBagroundImage = courseTypeBagroundImageRepository.save(courseTypeBagroundImage);
			if(updatedcourseTypeBagroundImage==null) {
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
	public List<Map<String, Object>> getCourseTypeBagroundImage(CourseType courseType) {
CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		
		CriteriaQuery<CourseTypeBagroundImage> createQuery = criteriaBuilder.createQuery(CourseTypeBagroundImage.class);
		Root<CourseTypeBagroundImage> root = createQuery.from(CourseTypeBagroundImage.class);
		
		Predicate predicate = criteriaBuilder.equal(root.get("courseType"), courseType);
		
		createQuery.select(root).where(predicate);
		
		List<CourseTypeBagroundImage> resultList = entityManager.createQuery(createQuery).getResultList();
		
		List<Map<String, Object>> collect = resultList.stream().map(result->{
			Map<String, Object> map=new LinkedHashMap<String, Object>();
			map.put("courseType_image_Id", result.getCourseTypeBagroundImageId());
			map.put("image_URL", result.getImageURL());
			map.put("active_Status", result.getActiveStatus());
			map.put("created_Date", result.getCreatedDate());
			return map;
		}).collect(Collectors.toList());

		return collect;
	}

}

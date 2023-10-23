package com.juzzIt.EducationProject.Dao;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.juzzIt.EducationProject.DaoInterface.CourseTypeVideoDaoInterface;
import com.juzzIt.EducationProject.Entity.CourseType;
import com.juzzIt.EducationProject.Entity.CourseTypeVideo;
import com.juzzIt.EducationProject.Models.Responce;
import com.juzzIt.EducationProject.Repositary.CourseTypeVideoRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
@Component
public class CourseTypeVideoDao implements CourseTypeVideoDaoInterface {
	
	@Autowired
	private CourseTypeVideoRepository courseTypeVideoRepository;
	
	@Autowired
	private EntityManager entityManager;

	@Override
	public CourseTypeVideo addNewCourseTypeVideo(CourseTypeVideo courseTypeVideo) {
		// TODO Auto-generated method stub
		return courseTypeVideoRepository.save(courseTypeVideo);
	}

	@Override
	public CourseTypeVideo getCourseTypeVideoById(String courseTypeVideoId) {
CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		
		CriteriaQuery<CourseTypeVideo> createQuery = criteriaBuilder.createQuery(CourseTypeVideo.class);
		Root<CourseTypeVideo> root = createQuery.from(CourseTypeVideo.class);
		
		Predicate predicate = criteriaBuilder.equal(root.get("courseTypeVideoId"), courseTypeVideoId);
		
		createQuery.select(root).where(predicate);
		
		List<CourseTypeVideo> resultList = entityManager.createQuery(createQuery).getResultList();
		
	if(resultList.isEmpty()) {
		return null;
	}
		return resultList.get(0);
	}

	@Override
	public Responce deleteCourseTypeVideo(String courseTypeVideoId) {
		
		CourseTypeVideo courseTypeVideo = getCourseTypeVideoById(courseTypeVideoId);
		 Responce responce =new Responce();
		 
		 if(courseTypeVideo==null) {
			 responce.setMassege("courseType video withe the gicven id not found");
			 responce.setStatus(false); 
			 return responce;
		 }
		 courseTypeVideoRepository.delete(courseTypeVideo);
		 responce.setMassege("courseType video deleted successfully");
		 responce.setStatus(true);		
		return responce;
	}

	@Override
	public Responce updateCourseTypeVideo(String courseTypeVideoId, HashMap<String, Object> courseTypeVideoData) {
		CourseTypeVideo courseTypeVideo = getCourseTypeVideoById(courseTypeVideoId);
		 Responce responce =new Responce();
		 
		 if(courseTypeVideo==null) {
			 responce.setMassege("courseType video withe the gicven id not found");
			 responce.setStatus(false); 
			 return responce;
		 }
		 
		 
		 
		 
		 
		 
		 if(courseTypeVideoData.get("video_URL")!=null) {
			 courseTypeVideo.setVideoURL(courseTypeVideoData.get("video_URL").toString());
		 }
		 
		 if(courseTypeVideoData.get("active_status")!=null) {
			 
			 if(courseTypeVideo.getActiveStatus().equalsIgnoreCase("D")) {
				 courseTypeVideo.setActiveStatus("A");
			 }else {
				 courseTypeVideo.setActiveStatus("D");
			 }
		 }
		 
		 CourseTypeVideo updatedcourseTypeVideo = courseTypeVideoRepository.save(courseTypeVideo);
		 if(courseTypeVideo==null) {
			 
			 responce.setMassege("failed to update  the details");
			 responce.setStatus(false);
			 
			 return responce;
		 }
		 
			 
		 responce.setMassege("details updated successfully");
		 responce.setStatus(false);
				 
			return responce;
		
	}

	@Override
	public List<Map<String, Object>> getCourseTypeVideo(CourseType courseType) {
		
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
				
				CriteriaQuery<CourseTypeVideo> createQuery = criteriaBuilder.createQuery(CourseTypeVideo.class);
				Root<CourseTypeVideo> root = createQuery.from(CourseTypeVideo.class);
				
				Predicate predicate = criteriaBuilder.equal(root.get("courseType"), courseType);
				
				createQuery.select(root).where(predicate);
				
				List<CourseTypeVideo> resultList = entityManager.createQuery(createQuery).getResultList();
				
				List<Map<String, Object>> collect = resultList.stream().map(result->{
					Map<String, Object> map=new LinkedHashMap<String, Object>();
					map.put("courseType_Video_Id", result.getCourseTypeVideoId());
					map.put("video_URL", result.getVideoURL());
					map.put("active_Status", result.getActiveStatus());
					map.put("created_Date", result.getCreatedDate());
					return map;
				}).collect(Collectors.toList());

				return collect;
	}

}

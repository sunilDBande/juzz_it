package com.juzzIt.EducationProject.Services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.juzzIt.EducationProject.DaoInterface.CourseTypeDaoInterface;
import com.juzzIt.EducationProject.DaoInterface.CourseTypeImageDaoInterface;
import com.juzzIt.EducationProject.Entity.CourseType;
import com.juzzIt.EducationProject.Entity.CourseTypeImage;
import com.juzzIt.EducationProject.Models.Responce;
import com.juzzIt.EducationProject.ServiceInterface.CourseTypeImageServiceInterface;

@Service
public class CourseTypeImageService implements CourseTypeImageServiceInterface {
	
	@Autowired
private CourseTypeImageDaoInterface courseTypeImageDaoInterface;
	@Autowired
	private CourseTypeDaoInterface courseTypeDaoInterface;

	@Override
	public Responce addNewCourseTypeImage(String courseTypeId, HashMap<String, Object> courseTypeImageData) {
	
		
		Responce responce=new Responce();
		
		if(courseTypeImageData.get("image_URL")==null) {
			responce.setMassege("image is required");
			responce.setStatus(true);
			return responce;
		}
		
		
		CourseType courseType = courseTypeDaoInterface.getCourseTypeById(courseTypeId);
		
		if(courseType==null) {
			responce.setMassege("courseType with the given id not found");
			responce.setStatus(false);
			return null;
		}
		
		
		CourseTypeImage newCourseTypeImage=new CourseTypeImage();
		
		UUID id=UUID.randomUUID();
		
		newCourseTypeImage.setCourseType(courseType);
		newCourseTypeImage.setActiveStatus("D");
		newCourseTypeImage.setCreatedDate(LocalDateTime.now());
		newCourseTypeImage.setCourseTypeImageId(id.toString());
		newCourseTypeImage.setImageURL(courseTypeImageData.get("image_URL").toString());
		
		CourseTypeImage addedCourseTypeImage = courseTypeImageDaoInterface.addNewCourseTypeImage(newCourseTypeImage);
		
		if(addedCourseTypeImage==null) {
			responce.setMassege("failed to add the image");
			responce.setStatus(false);
			return responce;
		}
		
		responce.setMassege("image added successfully");
		responce.setStatus(true);
		
		
		return responce;
	}

	@Override
	public Responce deleteCourseTypeImage(String courseTypeImageId) {
		// TODO Auto-generated method stub
		return courseTypeImageDaoInterface.deleteCourseTypeImage(courseTypeImageId);
	}

	@Override
	public Responce updateCourseTypeImage(String courseTypeImageId, HashMap<String, Object> courseTypeImageData) {
		// TODO Auto-generated method stub
		return courseTypeImageDaoInterface.updateCourseTypeImage(courseTypeImageId, courseTypeImageData);
	}

	@Override
	public List<Map<String, Object>> getCourseTypeImage(String courseTypeId) {
		CourseType courseType = courseTypeDaoInterface.getCourseTypeById(courseTypeId);
		if(courseType==null) {
			return new ArrayList<Map<String,Object>>();
		}
		return courseTypeImageDaoInterface.getAllCourseTypeImage(courseType);
	}

}

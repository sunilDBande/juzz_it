package com.juzzIt.EducationProject.Services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.juzzIt.EducationProject.DaoInterface.CourseTypeBagroundImageDaoInterface;
import com.juzzIt.EducationProject.DaoInterface.CourseTypeDaoInterface;
import com.juzzIt.EducationProject.Entity.CourseType;
import com.juzzIt.EducationProject.Entity.CourseTypeBagroundImage;

import com.juzzIt.EducationProject.Models.Responce;
import com.juzzIt.EducationProject.ServiceInterface.CourseTypeBagroundImageServiceInterface;


@Service
public class CourseTypeBagroundImageService implements CourseTypeBagroundImageServiceInterface {

	@Autowired
	private CourseTypeBagroundImageDaoInterface courseTypeBagroundImageDaoInterface;
	
	
	
	@Autowired
	private CourseTypeDaoInterface courseTypeDaoInterface;
	
	
	@Override
	public Responce addNewCourseTypeBagroundImage(String courseTypeId,
			HashMap<String, Object> courseTypeBagroundImageData) {

		Responce responce=new Responce();
		
		if(courseTypeBagroundImageData.get("image_URL")==null) {
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
		
		
		CourseTypeBagroundImage newCourseTypeBagroundImage=new CourseTypeBagroundImage();
		
		newCourseTypeBagroundImage.setCourseType(courseType);
		newCourseTypeBagroundImage.setActiveStatus("D");
		newCourseTypeBagroundImage.setCreatedDate(LocalDateTime.now());
		newCourseTypeBagroundImage.setCourseTypeBagroundImageId(courseTypeBagroundImageData.get("id").toString());
		newCourseTypeBagroundImage.setImageURL(courseTypeBagroundImageData.get("image_URL").toString());
		
		CourseTypeBagroundImage addedCourseTypeBagroundImage = courseTypeBagroundImageDaoInterface.addNewCourseTypeBagroundImage(newCourseTypeBagroundImage);
		
		if(addedCourseTypeBagroundImage==null) {
			responce.setMassege("failed to add the image");
			responce.setStatus(false);
			return responce;
		}
		
		responce.setMassege("image added successfully");
		responce.setStatus(true);
		
		
		return responce;
	}

	@Override
	public Responce deleteCourseTypeBagroundImage(String courseTypeBagroundImage) {
		// TODO Auto-generated method stub
		return courseTypeBagroundImageDaoInterface.deleteCourseTypeBagroundImage(courseTypeBagroundImage);
	}

	@Override
	public Responce updateCourseTypeBagroundImage(String courseTypeBagroundImageId,
			HashMap<String, Object> courseTypeBagroundImageData) {
		// TODO Auto-generated method stub
		return courseTypeBagroundImageDaoInterface.updateCourseTypeBagroundImage(courseTypeBagroundImageId, courseTypeBagroundImageData);
	}

	@Override
	public List<Map<String, Object>> getCourseTypeBagroundImage(String courseTypeId) {
		CourseType courseType = courseTypeDaoInterface.getCourseTypeById(courseTypeId);
		if(courseType==null) {
			return new ArrayList<Map<String,Object>>();
		}
		return courseTypeBagroundImageDaoInterface.getCourseTypeBagroundImage(courseType);
	}

}

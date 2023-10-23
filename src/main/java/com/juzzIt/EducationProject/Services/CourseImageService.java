package com.juzzIt.EducationProject.Services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.juzzIt.EducationProject.DaoInterface.CourseDaoInterface;
import com.juzzIt.EducationProject.DaoInterface.CourseImageDaoInterface;
import com.juzzIt.EducationProject.Entity.Course;
import com.juzzIt.EducationProject.Entity.CourseImage;
import com.juzzIt.EducationProject.Models.Responce;
import com.juzzIt.EducationProject.ServiceInterface.CourseImageServiceInterface;


@Service
public class CourseImageService implements CourseImageServiceInterface {
	
	@Autowired
	private CourseImageDaoInterface courseImageDaoInterface;
	
	
	@Autowired
	private CourseDaoInterface courseDaoInterface;

	@Override
	public Responce addNewCourseImage(String courseId, HashMap<String, Object> courseImageData) {
		Responce responce=new Responce();

		if(courseImageData.get("image_URL")==null) {
			responce.setMassege("image url is required");
			responce.setStatus(false);
			return responce;
		}
		
		Course course = courseDaoInterface.getCourseById(courseId);
		if(course==null) {
			responce.setMassege("course with the given id not found");
			responce.setStatus(false);
			return responce;
		}
		CourseImage  newCourseImage=new CourseImage();
		newCourseImage.setActiveStatus("D");
		newCourseImage.setCourse(course);
		newCourseImage.setImageURL(courseImageData.get("image_URL").toString());
        newCourseImage.setCourseImageId(courseImageData.get("id").toString());
        newCourseImage.setCreatedDate(LocalDateTime.now());
        CourseImage addedCourseImage = courseImageDaoInterface.addNewCourseImage(newCourseImage);
	        
	    if(addedCourseImage==null) {
	    	responce.setMassege("failed to add the image");
	    	responce.setStatus(false);
	    	return responce;
	    }
	    responce.setMassege("image added successfully");
	    responce.setStatus(false);
		
		return  responce;
	}

	@Override
	public Responce deleteCourseImage(String courseTypeImageId) {

		return courseImageDaoInterface.deleteCourseImage(courseTypeImageId);
	}

	@Override
	public Responce updateCourseImage(String courseTypeImageId, HashMap<String, Object> courseImageData) {
		// TODO Auto-generated method stub
		return courseImageDaoInterface.updateCourseImage(courseTypeImageId, courseImageData);
	}

	@Override
	public List<Map<String, Object>> getCoueseImage(String courseId) {
		Course course = courseDaoInterface.getCourseById(courseId);
		if(course==null) {
			return new ArrayList<Map<String, Object>>();
		}
		return courseImageDaoInterface.getCoueseImage(course);
	}
	
	

}

package com.juzzIt.EducationProject.Services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.juzzIt.EducationProject.DaoInterface.CourseTypeDaoInterface;
import com.juzzIt.EducationProject.DaoInterface.CourseTypeVideoDaoInterface;
import com.juzzIt.EducationProject.Entity.CourseType;
import com.juzzIt.EducationProject.Entity.CourseTypeImage;
import com.juzzIt.EducationProject.Entity.CourseTypeVideo;
import com.juzzIt.EducationProject.Models.Responce;
import com.juzzIt.EducationProject.ServiceInterface.CourseTypeVideoServiceInterface;


@Service
public class CourseTypeVideoService implements CourseTypeVideoServiceInterface {

	@Autowired
	private CourseTypeVideoDaoInterface courseTypeVideoDaoInterface;
	
	@Autowired
	private CourseTypeDaoInterface courseTypeDaoInterface;

	@Override
	public Responce addNewCourseTypeVideo(String courseTypeId, HashMap<String, Object> courseTypeVideoData) {
		
		Responce responce=new Responce();
		
		if(courseTypeVideoData.get("video_URL")==null) {
			responce.setMassege("video is required");
			responce.setStatus(true);
			return responce;
		}
		
		
		CourseType courseType = courseTypeDaoInterface.getCourseTypeById(courseTypeId);
		
		if(courseType==null) {
			responce.setMassege("courseType with the given id not found");
			responce.setStatus(false);
			return responce;
		}
		
		
		CourseTypeVideo newCourseTypeVideo=new CourseTypeVideo();
		newCourseTypeVideo.setCourseType(courseType);
		newCourseTypeVideo.setActiveStatus("D");
		newCourseTypeVideo.setCreatedDate(LocalDateTime.now());
		newCourseTypeVideo.setCourseTypeVideoId(courseTypeVideoData.get("id").toString());
		newCourseTypeVideo.setVideoURL(courseTypeVideoData.get("video_URL").toString());
		CourseTypeVideo addedCourseTypeVideo = courseTypeVideoDaoInterface.addNewCourseTypeVideo(newCourseTypeVideo);
		
		if(addedCourseTypeVideo==null) {
			responce.setMassege("failed to add the video");
			responce.setStatus(false);
			return responce;
		}
		responce.setMassege("image added successfully");
		responce.setStatus(true);
		return responce;
	}

	@Override
	public Responce deleteCourseTypeVideo(String courseTypeVideoId) {
		// TODO Auto-generated method stub
		return courseTypeVideoDaoInterface.deleteCourseTypeVideo(courseTypeVideoId);
	}

	@Override
	public Responce updateCourseTypeVideo(String courseTypeVideoId, HashMap<String, Object> courseTypeVideoData) {
		// TODO Auto-generated method stub
		return courseTypeVideoDaoInterface.updateCourseTypeVideo(courseTypeVideoId, courseTypeVideoData);
	}

	@Override
	public List<Map<String, Object>> getCourseTypeVideo(String courseTypeId) {
		CourseType courseType = courseTypeDaoInterface.getCourseTypeById(courseTypeId);
		if(courseType==null) {
			return new ArrayList<Map<String,Object>>();
		}
		return courseTypeVideoDaoInterface.getCourseTypeVideo(courseType);
	}
	
	
}

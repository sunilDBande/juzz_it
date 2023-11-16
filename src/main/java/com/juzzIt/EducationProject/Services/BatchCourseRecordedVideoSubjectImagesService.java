package com.juzzIt.EducationProject.Services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.juzzIt.EducationProject.DaoInterface.BatchCourseRecordedVideoSubjectDaoInterface;
import com.juzzIt.EducationProject.DaoInterface.BatchCourseRecordedVideoSubjectImagesDaoInterface;
import com.juzzIt.EducationProject.Entity.BatchCourseRecordedVideoSubject;
import com.juzzIt.EducationProject.Entity.BatchCourseRecordedVideoSubjectImages;
import com.juzzIt.EducationProject.Models.Responce;
import com.juzzIt.EducationProject.ServiceInterface.BatchCourseRecordedVideoSubjectImagesServiceInterface;


@Service
public class BatchCourseRecordedVideoSubjectImagesService  implements BatchCourseRecordedVideoSubjectImagesServiceInterface{

	@Autowired
	private BatchCourseRecordedVideoSubjectImagesDaoInterface batchCourseRecordedVideoSubjectImagesDaoInterface;
	
	
	@Autowired
	private BatchCourseRecordedVideoSubjectDaoInterface batchCourseRecordedVideoSubjectDaoInterface;
	
	@Override
	public Responce addRecodedSubjectImage(String SubjectId, HashMap<String, Object> imageData) {

		Responce responce=new Responce();
		
		if(imageData.get("image_URL")==null) {
			responce.setMassege("image is required");
			responce.setStatus(false);
			return responce;
		}
		
		BatchCourseRecordedVideoSubject batchCourseRecordedVideoSubject = batchCourseRecordedVideoSubjectDaoInterface.getBatchCourseRecordedVideoSubjectById(SubjectId);
		
		
		if(batchCourseRecordedVideoSubject==null) {
			responce.setMassege("subject with the given id not found");
			responce.setStatus(false);
			return responce;
		}
		
		
		BatchCourseRecordedVideoSubjectImages subjectImages=new BatchCourseRecordedVideoSubjectImages();
		
		UUID id=UUID.randomUUID();
		
		subjectImages.setBatchCourseRecordedVideoSubject(batchCourseRecordedVideoSubject);
		subjectImages.setSubjectImageId(id.toString());
		subjectImages.setActiveStatus("D");
		subjectImages.setCreatedDate(LocalDateTime.now());
		subjectImages.setImageURL(imageData.get("image_URL").toString());
		
		BatchCourseRecordedVideoSubjectImages images = batchCourseRecordedVideoSubjectImagesDaoInterface.addBatchCourseRecordedVideoSubjectImages(subjectImages);
		if(images==null) {
			responce.setMassege("failed to add the image");
			responce.setStatus(false);
			return responce;
		}
		
		responce.setMassege("image added successfully");
		responce.setStatus(true);
		return responce;
	}

	@Override
	public Responce deleteRecodedSubjectImage(String imageId) {
		// TODO Auto-generated method stub
		return  batchCourseRecordedVideoSubjectImagesDaoInterface.deleteBatchCourseRecordedVideoSubjectImages(imageId);
	}

	@Override
	public Responce updateRecodedSubjectImage(String imageId, HashMap<String, Object> imageData) {
		// TODO Auto-generated method stub
		return batchCourseRecordedVideoSubjectImagesDaoInterface.updateBatchCourseRecordedVideoSubjectImages(imageId, imageData);
	}

	@Override
	public List<Map<String, Object>> getAllRecodedVideoImages(String subjectId) {
		
		BatchCourseRecordedVideoSubject batchCourseRecordedVideoSubject = batchCourseRecordedVideoSubjectDaoInterface.getBatchCourseRecordedVideoSubjectById(subjectId);
		
		if(batchCourseRecordedVideoSubject==null) {
			return  new ArrayList<Map<String,Object>>();
		}
		return batchCourseRecordedVideoSubjectImagesDaoInterface.getAllSubjectImagesBySubject(batchCourseRecordedVideoSubject);
	}

}

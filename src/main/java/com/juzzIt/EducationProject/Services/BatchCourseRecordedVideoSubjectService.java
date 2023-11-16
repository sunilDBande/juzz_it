package com.juzzIt.EducationProject.Services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.juzzIt.EducationProject.DaoInterface.BatchCourseDaoInterface;
import com.juzzIt.EducationProject.DaoInterface.BatchCourseRecordedVideoSubjectDaoInterface;
import com.juzzIt.EducationProject.Entity.BatchCourse;
import com.juzzIt.EducationProject.Entity.BatchCourseRecordedVideoSubject;
import com.juzzIt.EducationProject.Models.Responce;
import com.juzzIt.EducationProject.ServiceInterface.BatchCourseRecordedVideoSubjectServiceInterface;



@Service
public class BatchCourseRecordedVideoSubjectService implements BatchCourseRecordedVideoSubjectServiceInterface {

	@Autowired
	private BatchCourseDaoInterface batchCourseDaoInterface;
	
	@Autowired
	private BatchCourseRecordedVideoSubjectDaoInterface batchCourseRecordedVideoSubjectDaoInterface;
	
	
	@Autowired
	private BatchCourseRecordedVideoSubjectImagesService batchCourseRecordedVideoSubjectImagesService;
	
	@Override
	public Responce addNewbatchCourseRecordedVideoSubject(String batchCourseId, Map<String, Object> subjectData) {
        Responce responce=new  Responce();
        System.out.println(subjectData);
		
		if(subjectData.get("video_Subject_Name")==null) {
			responce.setMassege("subject name is requirded");
			responce.setStatus(false);
			return responce;
		}
		BatchCourse batchCourse = batchCourseDaoInterface.getBatchCourseById(batchCourseId);
		if(batchCourse==null) {
			responce.setMassege("batch course with the given id not found");
			responce.setStatus(false);
			return responce;
		}
		BatchCourseRecordedVideoSubject batchCourseRecordedVideoSubject=new BatchCourseRecordedVideoSubject();
		
		UUID id=UUID.randomUUID();
		
		System.out.println(subjectData.get("video_Subject_Name").toString());
		
		
		batchCourseRecordedVideoSubject.setBatchCourse(batchCourse);
		batchCourseRecordedVideoSubject.setActiveStatus("D");
		batchCourseRecordedVideoSubject.setVideoSubjectId(id.toString());
		batchCourseRecordedVideoSubject.setVideoSubjectName(subjectData.get("video_Subject_Name").toString());
		
		

		BatchCourseRecordedVideoSubject addedbatchCourseRecordedVideoSubject = batchCourseRecordedVideoSubjectDaoInterface.addNewbatchCourseRecordedVideoSubject(batchCourseRecordedVideoSubject);
		
		if(addedbatchCourseRecordedVideoSubject==null) {
			responce.setMassege("failed to add the details");
			responce.setStatus(false);
			return responce;
		}
		responce.setMassege("subject added successfully");
		responce.setStatus(true);
		return responce;
	}

	@Override
	public Responce deletebatchCourseRecordedVideoSubject(String recordedVideoSubjectsId) {
		return batchCourseRecordedVideoSubjectDaoInterface.deletebatchCourseRecordedVideoSubject(recordedVideoSubjectsId);
	}

	@Override
	public Responce updatebatchCourseRecordedVideoSubject(String recordedVideoSubjectsId,
			Map<String, Object> subjectData) {
		return batchCourseRecordedVideoSubjectDaoInterface.updatebatchCourseRecordedVideoSubject(recordedVideoSubjectsId, subjectData);
	}

	@Override
	public List<Map<String, Object>> getAllbatchCourseRecordedVideoSubjectByBatchCourseId(String batchCourseId) {

		BatchCourse batchCourse = batchCourseDaoInterface.getBatchCourseById(batchCourseId);
		
		
		if(batchCourse==null) {
		return new ArrayList<Map<String,Object>>();
		}
		return batchCourseRecordedVideoSubjectDaoInterface.getAllbatchCourseRecordedVideoSubjectByBatchCourseId(batchCourse);
	}

	@Override
	public List<Map<String, Object>> getAllCourseRecidedVideoWithImages(String batchCourseId) {

		BatchCourse batchCourse = batchCourseDaoInterface.getBatchCourseById(batchCourseId);
		
		
		if(batchCourse==null) {
		return new ArrayList<Map<String,Object>>();
		}
		
		List<Map<String, Object>>  recodedVideoSubject = batchCourseRecordedVideoSubjectDaoInterface.getAllbatchCourseRecordedVideoSubjectByBatchCourseId(batchCourse);
		
		
		return		recodedVideoSubject.stream().map(result->{
			
			List<Map<String, Object>> allRecodedVideoImages = batchCourseRecordedVideoSubjectImagesService.getAllRecodedVideoImages(result.get("video_Subject_Id").toString());
		
			result.put("recodedVideo_Image", allRecodedVideoImages);
			
			return result;
		}).collect(Collectors.toList());
		
		
	
	}

}

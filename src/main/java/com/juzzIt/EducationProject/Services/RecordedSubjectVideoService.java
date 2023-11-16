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
import com.juzzIt.EducationProject.DaoInterface.RecordedSubjectVideoDaoInterface;
import com.juzzIt.EducationProject.Entity.BatchCourseRecordedVideoSubject;
import com.juzzIt.EducationProject.Entity.RecordedSubjectVideo;
import com.juzzIt.EducationProject.Models.Responce;
import com.juzzIt.EducationProject.ServiceInterface.RecordedSubjectVideoServiceInterface;
@Service
public class RecordedSubjectVideoService  implements RecordedSubjectVideoServiceInterface{

	@Autowired
	private RecordedSubjectVideoDaoInterface recordedSubjectVideoDaoInterface;
	
	@Autowired
	private BatchCourseRecordedVideoSubjectDaoInterface batchCourseRecordedVideoSubjectDaoInterface;
	
	@Override
	public Responce addNewRecordedSubjectVideo(String recordedVideoSubjectsId,
			HashMap<String, Object> recordedVideoData) {
	
		Responce responce =new Responce();
		if(recordedVideoData.get("vedio_Topic")==null|| recordedVideoData.get("video_Desc")==null||recordedVideoData.get("video_URL")==null) {
			responce.setMassege("vedio_Topic  ,  video_Desc  ,video_URL  are requied");
			responce.setStatus(false);
			return responce;	
		}
		
		
		BatchCourseRecordedVideoSubject batchCourseRecordedVideoSubject = batchCourseRecordedVideoSubjectDaoInterface.getBatchCourseRecordedVideoSubjectById(recordedVideoSubjectsId);
	
		if(batchCourseRecordedVideoSubject==null) {
			responce.setMassege("VideoSubject for the given id not found");
			responce.setStatus(false);
			return responce;	
		}
		
		RecordedSubjectVideo recordedSubjectVideo=new RecordedSubjectVideo();
		UUID id=UUID.randomUUID();
		
		
		recordedSubjectVideo.setActiveStatus("D");
		recordedSubjectVideo.setBatchCourseRecordedVideoSubject(batchCourseRecordedVideoSubject);
		recordedSubjectVideo.setCreatedDate(LocalDateTime.now());
		recordedSubjectVideo.setVedioTopic(recordedVideoData.get("vedio_Topic").toString());
		recordedSubjectVideo.setRecordedVideoId(id.toString());
		recordedSubjectVideo.setVideoDesc(recordedVideoData.get("video_Desc").toString());
		recordedSubjectVideo.setVideoURL(recordedVideoData.get("video_URL").toString());		
		
		RecordedSubjectVideo addedRecordedSubjectVideo = recordedSubjectVideoDaoInterface.addNewRecordedSubjectVideo(recordedSubjectVideo);
		if(addedRecordedSubjectVideo==null) {
			responce.setMassege("failed to add the video");
			responce.setStatus(false);
			return null;
		}
		
		responce.setMassege("video added successfully");
		responce.setStatus(true);
		
		
		return responce;
	}

	@Override
	public Responce deleteRecordedSubjectVideo(String recordedVideoId) {

		return recordedSubjectVideoDaoInterface.deleteRecordedSubjectVideo(recordedVideoId);
	}

	@Override
	public Responce updateRecodedSubjectVideo(String recordedVideoId, HashMap<String, Object> RecordedVideoData) {
		// TODO Auto-generated method stub
		return recordedSubjectVideoDaoInterface.updateRecodedSubjectVideo(recordedVideoId, RecordedVideoData);
	}

	@Override
	public List<Map<String, Object>> getAllRecordedSubjectVideo(String recordedVideoSubjectsId) {
		
		BatchCourseRecordedVideoSubject batchCourseRecordedVideoSubject = batchCourseRecordedVideoSubjectDaoInterface.getBatchCourseRecordedVideoSubjectById(recordedVideoSubjectsId);
	
		if(batchCourseRecordedVideoSubject==null) {
		return new ArrayList<Map<String,Object>>();
		}
		return recordedSubjectVideoDaoInterface.getAllRecordedSubjectVideo(batchCourseRecordedVideoSubject);
	}

}

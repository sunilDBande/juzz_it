package com.juzzIt.EducationProject.DaoInterface;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.juzzIt.EducationProject.Entity.BatchCourseRecordedVideoSubject;
import com.juzzIt.EducationProject.Entity.RecordedSubjectVideo;
import com.juzzIt.EducationProject.Models.Responce;

public interface RecordedSubjectVideoDaoInterface {

	public RecordedSubjectVideo addNewRecordedSubjectVideo( RecordedSubjectVideo recordedSubjectVideo) ;
	public RecordedSubjectVideo getRecordedSubjectVideoById(String recordedSubjectVideoId);
	public Responce deleteRecordedSubjectVideo( String recordedVideoId);
	public Responce updateRecodedSubjectVideo(String recordedVideoId,HashMap<String, Object>  RecordedVideoData);
	public List<Map<String, Object>> getAllRecordedSubjectVideo(BatchCourseRecordedVideoSubject batchCourseRecordedVideoSubject);
}

package com.juzzIt.EducationProject.ServiceInterface;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.juzzIt.EducationProject.Models.Responce;


public interface RecordedSubjectVideoServiceInterface {	
	public Responce addNewRecordedSubjectVideo( String recordedVideoSubjectsId, HashMap<String, Object> recordedVideoData) ;
	public Responce deleteRecordedSubjectVideo( String recordedVideoId);
	public Responce updateRecodedSubjectVideo(String recordedVideoId,HashMap<String, Object>  RecordedVideoData);
	public List<Map<String, Object>> getAllRecordedSubjectVideo(String recordedVideoSubjectsId);
}

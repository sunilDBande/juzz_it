package com.juzzIt.EducationProject.ServiceInterface;

import java.util.List;
import java.util.Map;
import com.juzzIt.EducationProject.Models.Responce;
public interface BatchCourseRecordedVideoSubjectServiceInterface {
	
	public Responce addNewbatchCourseRecordedVideoSubject(String batchCourseId,Map<String, Object> subjectData);
	public Responce deletebatchCourseRecordedVideoSubject(String recordedVideoSubjectsId);
	public Responce updatebatchCourseRecordedVideoSubject(String recordedVideoSubjectsId,Map<String, Object> subjectData);
	public List<Map<String , Object>> getAllbatchCourseRecordedVideoSubjectByBatchCourseId(String batchCourseId);
}

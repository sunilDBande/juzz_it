package com.juzzIt.EducationProject.DaoInterface;

import java.util.List;
import java.util.Map;

import com.juzzIt.EducationProject.Entity.BatchCourse;
import com.juzzIt.EducationProject.Entity.BatchCourseRecordedVideoSubject;
import com.juzzIt.EducationProject.Models.Responce;

public interface BatchCourseRecordedVideoSubjectDaoInterface {

	public BatchCourseRecordedVideoSubject addNewbatchCourseRecordedVideoSubject(BatchCourseRecordedVideoSubject batchCourseRecordedVideoSubject);
	public BatchCourseRecordedVideoSubject getBatchCourseRecordedVideoSubjectById(String batchCourseRecordedVideoSubjectId);
	public Responce deletebatchCourseRecordedVideoSubject(String recordedVideoSubjectsId);
	public Responce updatebatchCourseRecordedVideoSubject(String recordedVideoSubjectsId,Map<String, Object> subjectData);
	public List<Map<String , Object>> getAllbatchCourseRecordedVideoSubjectByBatchCourseId(BatchCourse batchCourse);

}

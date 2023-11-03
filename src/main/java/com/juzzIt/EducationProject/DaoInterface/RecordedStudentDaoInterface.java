package com.juzzIt.EducationProject.DaoInterface;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.juzzIt.EducationProject.Entity.RecordedStudentBatch;
import com.juzzIt.EducationProject.Models.Responce;

public interface RecordedStudentDaoInterface {
	
	public RecordedStudentBatch addRecordedStudent( RecordedStudentBatch recordedStudentBatch);
	public RecordedStudentBatch getRecordedStudentById(String recordedStudentBatchId);
	public Responce assignMenterToStudent( String recordedStudentId,String teacherId);
	public Responce deleteRecordedStudent(String recordedStudentId);
	public Responce updateRecorededStudent(String recorddStudentId,HashMap<String, Object> studentData) ;
	public List<Map<String, Object>> getAllRecordedStudent();
	public Responce deleteMenterFromStudent(String recordedStudentId);
	public List<Map<String, Object>> getRecordedStudentWithOutMenter();
	public List<Map<String, Object>> getRecordedStudentWithMenter();
	
}

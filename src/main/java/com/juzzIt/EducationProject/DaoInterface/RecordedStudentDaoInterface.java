package com.juzzIt.EducationProject.DaoInterface;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.juzzIt.EducationProject.Entity.RecordedStudent;
import com.juzzIt.EducationProject.Models.Responce;

public interface RecordedStudentDaoInterface {
	
	public RecordedStudent addRecordedStudent( RecordedStudent recordedStudent);
	public RecordedStudent getRecordedStudentById(String recordedStudentId);
	public Responce assignMenterToStudent( String recordedStudentId,String teacherId);
	public Responce deleteRecordedStudent(String recordedStudentId);
	public Responce updateRecorededStudent(String recorddStudentId,HashMap<String, Object> studentData) ;
	public List<Map<String, Object>> getAllRecordedStudent();
	
	public Responce deleteMenterFromStudent(String recordedStudentId);
	
}

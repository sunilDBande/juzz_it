package com.juzzIt.EducationProject.ServiceInterface;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.PathVariable;

import com.juzzIt.EducationProject.Models.Responce;

public interface RecordedStudentServiceInterface {
	public Responce addRecordedStudent( String courseTypeId,String studentId,String requestId );
	public Responce assignMenterToStudent( String recordedStudentId,String teacherId);
	public Responce deleteRecordedStudent(String recordedStudentId);
	public Responce updateRecorededStudent(String recorddStudentId,HashMap<String, Object> studentData) ;
	public List<Map<String, Object>> getAllRecordedStudent();
	public Responce deleteRecordedStudentMenter(String recordedStudentId) ;
	public List<Map<String, Object>> getRecordedStudentWithOutMenter();
	public List<Map<String, Object>> getRecordedStudentWithMenter();
}

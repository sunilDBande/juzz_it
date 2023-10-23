package com.juzzIt.EducationProject.Dao;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.juzzIt.EducationProject.DaoInterface.RecordedStudentDaoInterface;
import com.juzzIt.EducationProject.DaoInterface.TeacherDaoInterface;
import com.juzzIt.EducationProject.Entity.RecordedStudent;
import com.juzzIt.EducationProject.Models.Responce;
import com.juzzIt.EducationProject.Repositary.RecordedStudentRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
@Component
public class RecordedStudentDao implements RecordedStudentDaoInterface {

	@Autowired
	private RecordedStudentRepository recordedStudentRepository;
	 
	@Autowired
	private EntityManager entityManager;
	
	@Autowired
	private TeacherDaoInterface teacherDaoInterface;

	@Override
	public RecordedStudent addRecordedStudent(RecordedStudent recordedStudent) {
		return recordedStudentRepository.save(recordedStudent);
	}

	@Override
	public RecordedStudent getRecordedStudentById(String recordedStudentId) {
		
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<RecordedStudent> createQuery = criteriaBuilder.createQuery(RecordedStudent.class);
		Root<RecordedStudent> root = createQuery.from(RecordedStudent.class);
		Predicate predicate = criteriaBuilder.equal(root.get("recordedStudentId"), recordedStudentId);
		createQuery.select(root).where(predicate);
		List<RecordedStudent> resultList = entityManager.createQuery(createQuery).getResultList();
		if(resultList.isEmpty()) {
			return null;
			}
		return resultList.get(0);
		
		
	}

	@Override
	public Responce assignMenterToStudent(String recordedStudentId, String teacherId) {
	
		RecordedStudent recordedStudent = getRecordedStudentById(recordedStudentId);
		
		Responce responce=new Responce();
if(recordedStudent==null) {

	responce.setMassege("recordedStudent for the given id not found");
	responce.setStatus(false);
	return responce;
}

if(recordedStudent.getTeacherId().equalsIgnoreCase("NO")) {
	responce.setMassege("menter is alrady assigned for this student");
	responce.setStatus(false);
	return responce;
}
recordedStudent.setTeacherId(teacherId);
RecordedStudent updatedRecordedStudent = recordedStudentRepository.save(recordedStudent);
if(updatedRecordedStudent==null) {
	responce.setMassege("failed to add the menter");
	responce.setStatus(false);
	return responce;
}
responce.setMassege("successfully assigned menter");
responce.setStatus(true);
		return responce;
	}

	@Override
	public Responce deleteRecordedStudent(String recordedStudentId) {

		RecordedStudent recordedStudent = getRecordedStudentById(recordedStudentId);
		
		Responce responce=new Responce();
if(recordedStudent==null) {

	responce.setMassege("recordedStudent for the given id not found");
	responce.setStatus(false);
	return responce;
}
recordedStudentRepository.delete(recordedStudent);

responce.setMassege("successfully deleted recorded student");
responce.setStatus(true);
		return responce;
	}

	@Override
	public Responce updateRecorededStudent(String recoredStudentId, HashMap<String, Object> studentData) {
		

		RecordedStudent recordedStudent = getRecordedStudentById(recoredStudentId);
		
		Responce responce=new Responce();
if(recordedStudent==null) {

	responce.setMassege("recordedStudent for the given id not found");
	responce.setStatus(false);
	return responce;
}

if(studentData.get("course_Name")!=null) {
	
	recordedStudent.setCourseName("");
}
if(studentData.get("courseType_Name")!=null) {
	recordedStudent.setCourseTypeName(recoredStudentId);
}
if(studentData.get("student_Permition_Status")!=null) {
if(recordedStudent.getStudentPermitionStatus().equalsIgnoreCase("D")) {
	recordedStudent.setStudentPermitionStatus("A");
}else {
	recordedStudent.setStudentPermitionStatus("D");
}
}
if(studentData.get("student_Placement_Status")!=null) {
	if(recordedStudent.getStudentPlacementStatus().equalsIgnoreCase("D")) {
		recordedStudent.setStudentPlacementStatus("A");
	}else {
		recordedStudent.setStudentPlacementStatus("D");
	}
}
if(studentData.get("student_Name")!=null) {
	recordedStudent.setStudentName("");
}

if(studentData.get("menter_Permition_Status")!=null) {
	if(recordedStudent.getMenterPermitionStatus().equalsIgnoreCase("D")) {
		recordedStudent.setMenterPermitionStatus("A");
	}else {
		recordedStudent.setMenterPermitionStatus("D");
	}
}

RecordedStudent updatedRecordedStudent = recordedStudentRepository.save(recordedStudent);
if(updatedRecordedStudent==null) {
	responce.setMassege("failed to update the student data");
	responce.setStatus(false);
 return null;
}
responce.setMassege("details updated successfully");
responce.setStatus(true);
		return responce;
	}

	@Override
	public List<Map<String, Object>> getAllRecordedStudent() {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<RecordedStudent> createQuery = criteriaBuilder.createQuery(RecordedStudent.class);
		Root<RecordedStudent> root = createQuery.from(RecordedStudent.class);
	;
		createQuery.select(root);
		   List<RecordedStudent> resultList = entityManager.createQuery(createQuery).getResultList();

		   List<Map<String, Object>> collect = resultList.stream().map(result->{
			Map<String, Object> map=new LinkedHashMap<String,Object>();
		
			map.put("RECORDED_STUDENT_ID", result);
			map.put("COURSE_NAME", result);
			map.put("COURSE_TYPE", result);
			map.put("STUDENT_PERMITION_STATUS", result);
			map.put("STUDENT_PLACEMNENT_STATUS", result);
			map.put("STUDENT_NAME", result);
			map.put("ENROLL_TYPE", result);
			map.put("MENTER_PERMITION_STATUS", result);
			if( result.getTeacherId().equalsIgnoreCase("NO")) {
				map.put("TEACHER","");
			}else {
				map.put("TEACHER", teacherDaoInterface.getTeacherById(result.getTeacherId()));
			}	
			return map;
		}).collect(Collectors.toList());
		
		return collect;
	}
	
	
	


	@Override
	public Responce deleteMenterFromStudent(String recordedStudentId) {
	RecordedStudent recordedStudent = getRecordedStudentById(recordedStudentId);
		
		Responce responce=new Responce();
if(recordedStudent==null) {

	responce.setMassege("recordedStudent for the given id not found");
	responce.setStatus(false);
	return responce;
}

recordedStudent.setTeacherId("NO");

RecordedStudent updatedRecordedStudent = recordedStudentRepository.save(recordedStudent);

if(updatedRecordedStudent==null) {
	responce.setMassege("failed to delete the menter");
	responce.setStatus(false);
	return responce;
}
responce.setMassege("successfully deleted menter");
responce.setStatus(true);
		return responce;
		
	}
	
	
}

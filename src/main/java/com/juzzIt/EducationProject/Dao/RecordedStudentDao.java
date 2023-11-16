package com.juzzIt.EducationProject.Dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.juzzIt.EducationProject.DaoInterface.RecordedStudentDaoInterface;
import com.juzzIt.EducationProject.DaoInterface.TeacherDaoInterface;
import com.juzzIt.EducationProject.Entity.RecordedStudentBatch;
import com.juzzIt.EducationProject.Entity.Student;
import com.juzzIt.EducationProject.Models.Responce;
import com.juzzIt.EducationProject.Repositary.RecordedStudentBatchRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
@Component
public class RecordedStudentDao implements RecordedStudentDaoInterface {

	@Autowired
	private RecordedStudentBatchRepository recordedStudentRepository;
	 
	@Autowired
	private EntityManager entityManager;
	
	@Autowired
	private TeacherDaoInterface teacherDaoInterface;

	@Override
	public RecordedStudentBatch addRecordedStudent(RecordedStudentBatch  recordedStudentBatch) {
		return recordedStudentRepository.save(recordedStudentBatch);
	}

	@Override
	public RecordedStudentBatch getRecordedStudentById(String recordedStudentId) {
		
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<RecordedStudentBatch> createQuery = criteriaBuilder.createQuery(RecordedStudentBatch.class);
		Root<RecordedStudentBatch> root = createQuery.from(RecordedStudentBatch.class);
		Predicate predicate = criteriaBuilder.equal(root.get("recordedStudentId"), recordedStudentId);
		createQuery.select(root).where(predicate);
		List<RecordedStudentBatch> resultList = entityManager.createQuery(createQuery).getResultList();
		if(resultList.isEmpty()) {
			return null;
			}
		return resultList.get(0);
		
		
	}

	@Override
	public Responce assignMenterToStudent(String recordedStudentId, String teacherId) {
	
		RecordedStudentBatch recordedStudent = getRecordedStudentById(recordedStudentId);
		
		Responce responce=new Responce();
if(recordedStudent==null) {

	responce.setMassege("recordedStudent for the given id not found");
	responce.setStatus(false);
	return responce;
}

if(!recordedStudent.getTeacherId().equalsIgnoreCase("NO")) {
	responce.setMassege("menter is alrady assigned for this student");
	responce.setStatus(false);
	return responce;
}
recordedStudent.setTeacherId(teacherId);
RecordedStudentBatch updatedRecordedStudent = recordedStudentRepository.save(recordedStudent);
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

		RecordedStudentBatch recordedStudent = getRecordedStudentById(recordedStudentId);
		
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
		

		RecordedStudentBatch recordedStudent = getRecordedStudentById(recoredStudentId);
		
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
	System.out.println();
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

RecordedStudentBatch updatedRecordedStudent = recordedStudentRepository.save(recordedStudent);
if(updatedRecordedStudent==null) {
	responce.setMassege("failed to update the student data");
	responce.setStatus(false);
 return responce;
}
responce.setMassege("details updated successfully");
responce.setStatus(true);
		return responce;
	}

	@Override
	public List<Map<String, Object>> getAllRecordedStudent() {
		
		
		
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<RecordedStudentBatch> createQuery = criteriaBuilder.createQuery(RecordedStudentBatch.class);
		Root<RecordedStudentBatch> root = createQuery.from(RecordedStudentBatch.class);
		createQuery.select(root);
		   List<RecordedStudentBatch> resultList = entityManager.createQuery(createQuery).getResultList();

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
		RecordedStudentBatch recordedStudent = getRecordedStudentById(recordedStudentId);
		
		Responce responce=new Responce();
if(recordedStudent==null) {

	responce.setMassege("recordedStudent for the given id not found");
	responce.setStatus(false);
	return responce;
}

recordedStudent.setTeacherId("NO");

RecordedStudentBatch updatedRecordedStudent = recordedStudentRepository.save(recordedStudent);

if(updatedRecordedStudent==null) {
	responce.setMassege("failed to delete the menter");
	responce.setStatus(false);
	return responce;
}
responce.setMassege("successfully deleted menter");
responce.setStatus(true);
		return responce;
		
	}

	@Override
	public List<Map<String, Object>> getRecordedStudentWithOutMenter() {
		try {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<RecordedStudentBatch> createQuery = criteriaBuilder.createQuery(RecordedStudentBatch.class);
		Root<RecordedStudentBatch> root = createQuery.from(RecordedStudentBatch.class);
	Predicate predicate = criteriaBuilder.equal(root.get("teacherId"), "NO");
		createQuery.select(root).where(predicate);
		   List<RecordedStudentBatch> resultList = entityManager.createQuery(createQuery).getResultList();

		   List<Map<String, Object>> collect = resultList.stream().map(result->{
			Map<String, Object> map=new LinkedHashMap<String,Object>();
		
			map.put("RECORDED_STUDENT_ID", result.getRecordedStudentId());
			map.put("COURSE_NAME", result.getCourseName());
			map.put("COURSE_TYPE", result.getCourseTypeName());
			map.put("STUDENT_PERMITION_STATUS", result.getStudentPermitionStatus());
			map.put("STUDENT_PLACEMNENT_STATUS", result.getStudentPlacementStatus());
			map.put("STUDENT_NAME", result.getStudentName());
			map.put("ENROLL_TYPE", result.getEnrollTyp());
			map.put("MENTER_PERMITION_STATUS", result.getMenterPermitionStatus());
			if( result.getTeacherId().equalsIgnoreCase("NO")) {
				map.put("TEACHER","N");
			}else {
				map.put("TEACHER", teacherDaoInterface.getTeacherById(result.getTeacherId()));
			}	
			return map;
		}).collect(Collectors.toList());
	return collect;
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Map<String, Object>> getRecordedStudentWithMenter() {
		
		try {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<RecordedStudentBatch> createQuery = criteriaBuilder.createQuery(RecordedStudentBatch.class);
		Root<RecordedStudentBatch> root = createQuery.from(RecordedStudentBatch.class);
		Predicate predicate = criteriaBuilder.notEqual(root.get("teacherId"), "NO");
		createQuery.select(root).where(predicate);
		   List<RecordedStudentBatch> resultList = entityManager.createQuery(createQuery).getResultList();
		   List<Map<String, Object>> collect = resultList.stream().map(result->{
			Map<String, Object> map=new LinkedHashMap<String,Object>();
		
			map.put("RECORDED_STUDENT_ID", result.getRecordedStudentId());
			map.put("COURSE_NAME", result.getCourseName());
			map.put("COURSE_TYPE", result.getCourseTypeName());
			map.put("STUDENT_PERMITION_STATUS", result.getStudentPermitionStatus());
			map.put("STUDENT_PLACEMNENT_STATUS", result.getStudentPlacementStatus());
			map.put("STUDENT_NAME", result.getStudentName());
			map.put("ENROLL_TYPE", result.getEnrollTyp());
			map.put("MENTER_PERMITION_STATUS", result.getMenterPermitionStatus());
			if( result.getTeacherId().equalsIgnoreCase("NO")) {
				map.put("TEACHER","N");
			}else {
				map.put("TEACHER", teacherDaoInterface.getTeacherById(result.getTeacherId()));
			}	
			return map;
		}).collect(Collectors.toList());
		return collect;
		
		}catch(Exception e) {
			e.printStackTrace();
return null;
		}
	}

	@Override
	public HashMap<String, Object> getRecodedStudentDetailsByRecodedStudentBatch(String recodedStudentBatchId) {
		
		
String []data= {"enroll_details_id","student_name","student_email","mobile_number"};
		
		
		String query= " select student_enroll_details.enroll_details_id,student_enroll_details.student_name , student_enroll_details.student_email ,student_enroll_details.mobile_number "
				+ " from recorded_student_batch , student_enroll_details "
				+ " where "
				+ "  recorded_student_batch.recorded_student_id = '"+recodedStudentBatchId+"'" 
				+ "  and  recorded_student_batch.student_enroll_details_id = student_enroll_details.enroll_details_id ";
		
		
		
		Query createNativeQuery = entityManager.createNativeQuery(query);
		List<Object[]> resultList = createNativeQuery.getResultList();
		
List<HashMap<String, Object>> finalOutput = new ArrayList<HashMap<String,Object>>();
		
		
		for(Object res[]:resultList) {
			  LinkedHashMap<String, Object> lh = new LinkedHashMap<String, Object>();
			for(int i=0; i<=res.length-1; i++) {
				if (res[i] == null || res[i].toString().trim().isEmpty()) {
					lh.put(data[i], "");
				} else {
					lh.put(data[i], res[i].toString());
				}
			}
			finalOutput.add(lh);
		}

		return finalOutput.get(0);
	}

	@Override
	public HashMap<String, Object> getCourseTypeDataByRecodedStudentBatchId(String recodedStudentBatchId) {
        String []data= {"course_type_id","course_type_name","course_level"};
		String query= " select course_type.course_type_id,course_type.course_type_name , course_type.course_level  "
				+ " from recorded_student_batch , course_type "
				+ " where "
				+ "  recorded_student_batch.recorded_student_id = '"+recodedStudentBatchId+"'" 
				+ "  and  recorded_student_batch.course_type_id = course_type.course_type_id ";
		
		
		Query createNativeQuery = entityManager.createNativeQuery(query);
		List<Object[]> resultList = createNativeQuery.getResultList();
		
List<HashMap<String, Object>> finalOutput = new ArrayList<HashMap<String,Object>>();
		
		
		for(Object res[]:resultList) {
			  LinkedHashMap<String, Object> lh = new LinkedHashMap<String, Object>();
			for(int i=0; i<=res.length-1; i++) {
				if (res[i] == null || res[i].toString().trim().isEmpty()) {
					lh.put(data[i], "");
				} else {
					lh.put(data[i], res[i].toString());
				}
			}
			finalOutput.add(lh);
		}
		System.out.println("finalOutput--> "+finalOutput);
		if(finalOutput.isEmpty()) {
			return null;
		}
		return finalOutput.get(0);
	}

	@Override
	public List<Map<String, Object>> getAllRecodedBatchsByStudentId(Student student) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<RecordedStudentBatch> createQuery = criteriaBuilder.createQuery(RecordedStudentBatch.class);
		Root<RecordedStudentBatch> root = createQuery.from(RecordedStudentBatch.class);
		Predicate predicate1 = criteriaBuilder.equal(root.get("student"), student);
		Predicate predicate2 = criteriaBuilder.equal(root.get("studentPermitionStatus"), "A");
		Predicate predicate = criteriaBuilder.and(predicate1, predicate2);
		createQuery.select(root).where(predicate);
		   List<RecordedStudentBatch> resultList = entityManager.createQuery(createQuery).getResultList();
		   List<Map<String, Object>> collect = resultList.stream().map(result->{
			Map<String, Object> map=new LinkedHashMap<String,Object>();
			map.put("RECORDED_STUDENT_BATCH_ID", result.getRecordedStudentId());
			map.put("COURSE_NAME", result.getCourseName());
			map.put("COURSE_TYPE", result.getCourseTypeName());
			map.put("STUDENT_NAME", result.getStudentName());
			map.put("ENROLL_TYPE", result.getEnrollTyp());
            // map.put("TEACHER", teacherDaoInterface.getTeacherById(result.getTeacherId()));
			return map;
		}).collect(Collectors.toList());
		return collect;
	}

	@Override
	public List<Map<String, Object>> getAllRecodedBatchsByTrainerId(String trainerId) {
		System.out.println(trainerId);
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<RecordedStudentBatch> createQuery = criteriaBuilder.createQuery(RecordedStudentBatch.class);
		Root<RecordedStudentBatch> root = createQuery.from(RecordedStudentBatch.class);
		Predicate predicate1 = criteriaBuilder.equal(root.get("teacherId"), trainerId);
		Predicate predicate2 = criteriaBuilder.equal(root.get("menterPermitionStatus"), "A");
		Predicate predicate = criteriaBuilder.and(predicate1, predicate2);
		createQuery.select(root).where(predicate);
		   List<RecordedStudentBatch> resultList = entityManager.createQuery(createQuery).getResultList();
		   List<Map<String, Object>> collect = resultList.stream().map(result->{
			Map<String, Object> map=new LinkedHashMap<String,Object>();
			map.put("RECORDED_STUDENT_ID", result.getRecordedStudentId());
			map.put("COURSE_NAME", result.getCourseName());
			map.put("COURSE_TYPE", result.getCourseTypeName());
			map.put("STUDENT_NAME", result.getStudentName());
			map.put("ENROLL_TYPE", result.getEnrollTyp());
			map.put("student_Data", getRecodedStudentDetailsByRecodedStudentBatch(result.getRecordedStudentId()));
			map.put("courseType_Data", getCourseTypeDataByRecodedStudentBatchId(result.getRecordedStudentId()));
			return map;
		}).collect(Collectors.toList());
		return collect;
	}
	
	
}

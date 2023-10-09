package com.juzzIt.EducationProject.Dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.xml.crypto.Data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.juzzIt.EducationProject.DaoInterface.BatchCourseDaoInterface;
import com.juzzIt.EducationProject.DaoInterface.BatchCourseStudentDaoInterface;
import com.juzzIt.EducationProject.Entity.BatchCourse;
import com.juzzIt.EducationProject.Entity.BatchCourseStudent;
import com.juzzIt.EducationProject.Entity.StudentEnrollDetails;
import com.juzzIt.EducationProject.Models.Responce;
import com.juzzIt.EducationProject.Repositary.BatchCourseStudentRepository;
import com.juzzIt.EducationProject.Repositary.StudentEnrollDetailsRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

@Component
public class BatchCourseStudentDao implements BatchCourseStudentDaoInterface {

	@Autowired
	private BatchCourseStudentRepository batchCourseStudentRepo;

	@Autowired
	private StudentEnrollDetailsRepository studentEnrollDetailsRepo;

	@Autowired
	private BatchCourseDaoInterface batchCourseDaoInterface;

	@Autowired
	private EntityManager entityManager;

	@Override
	public BatchCourseStudent addStodentTobatch(BatchCourseStudent student) {
		// TODO Auto-generated method stub
		return batchCourseStudentRepo.save(student);
	}

	@Override
	public Responce deleteStudent(String StudentBatchId) throws Exception {
		
		
		Responce responce = new Responce();
		try {
		
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		
		CriteriaQuery<BatchCourseStudent> query = criteriaBuilder.createQuery(BatchCourseStudent.class);
		
		
		Root<BatchCourseStudent> root = query.from(BatchCourseStudent.class);
		
		Predicate predicate = criteriaBuilder.equal(root.get("batchCourseStudentId"), StudentBatchId);
		
		
		query.select(root).where(predicate);
		
		List<BatchCourseStudent> resultList = entityManager.createQuery(query).getResultList();	
		
		
		
		if(resultList.isEmpty()) {
			responce.setMassege("student with the given id not found");
			responce.setStatus(false);
		}else {
			
		BatchCourseStudent batchCourseStudent = resultList.get(0);
		
		batchCourseStudentRepo.delete(batchCourseStudent);
	

//			String sql="select batch_course_student.student_enroll_details_id from batch_course_student where batch_course_student_id='"+StudentBatchId+"'";
//			List resultList2 = entityManager.createNamedQuery(sql).getResultList();
//			
//			if(!resultList2.isEmpty()) {
//				for(Object res: resultList2) {
//					studentEnrollDetailsRepo.deleteById(res.toString());
//				}
//			}
			responce.setMassege("student deleted from the batch successfully");
			responce.setStatus(true);
			
		}
		
//
//		Optional<BatchCourseStudent> student = batchCourseStudentRepo.findById(StudentBatchId);
//
//		
//		if (student.isPresent()) {
//
//			BatchCourseStudent batchCourseStudent = student.get();
//			String sql="select batch_course_student.student_enroll_details_id from batch_course_student where batch_course_student_id='"+StudentBatchId+"'";
//			List resultList2 = entityManager.createNamedQuery(sql).getResultList();
//			
//		
//			
//			
//		
//			StudentEnrollDetails studentEnrollDetails = batchCourseStudent.getStudentEnrollDetails();
//		
//			batchCourseStudentRepo.delete(batchCourseStudent);
//			responce.setMassege("student deleted from the batch successfully");
//			responce.setStatus(true);
//			
//			if (studentEnrollDetails != null) {
//				studentEnrollDetailsRepo.delete(studentEnrollDetails);
//			}
//		} else {
//			responce.setMassege("student with the given id not found");
//		}
		}catch (Exception e) {
			throw new Exception("Getting problem while deleting Student");
		}
		return responce;
	}

	@Override
	public List<Map<String, Object>> getAllStudent(String batchCourseId) {

		BatchCourse batchCourse = batchCourseDaoInterface.getBatchCourseById(batchCourseId);

		if (batchCourse == null) {
			return null;
		}

		CriteriaBuilder builder = entityManager.getCriteriaBuilder();

		CriteriaQuery<BatchCourseStudent> createQuery = builder.createQuery(BatchCourseStudent.class);

		Root<BatchCourseStudent> root = createQuery.from(BatchCourseStudent.class);

		Predicate predicate = builder.equal(root.get("batchCourse"), batchCourse);

		createQuery.select(root).where(predicate);

		List<BatchCourseStudent> resultList = entityManager.createQuery(createQuery).getResultList();

		List<Map<String, Object>> collect = resultList.stream().map(result -> {

			
			Map<String, Object> map = new LinkedHashMap<String, Object>();
			map.put("student_batchId", result.getBatchCourseStudentId());
			map.put("student_name", result.getStudentName());
			map.put("enroll_Type", result.getEnrollType());
			map.put("student_PermitionStatus", result.getStudentPermitionStatus());
			map.put("student_Enroll_details",getStudentEnrolldetails( result.getBatchCourseStudentId()));
			return map;
		}).collect(Collectors.toList());
		
	

		return collect;
	}
	
	public List<HashMap<String, Object>> getStudentEnrolldetails(String batchCourseStudentId) {
		
		
		String []data= {"ENROLL_DETAILS_ID","STUDENT_NAME","STUDENT_EMAIL","MOBILE_NUMBER"};
		
		
		String query= " select student_enroll_details.ENROLL_DETAILS_ID,student_enroll_details.STUDENT_NAME,student_enroll_details.STUDENT_EMAIL,student_enroll_details.MOBILE_NUMBER  "
				+ " from student_enroll_details , batch_course_student "
				+ " where "
				+ "  batch_course_student.BATCH_COURSE_STUDENT_ID='"+batchCourseStudentId+"'" 
				+ "  and  student_enroll_details.ENROLL_DETAILS_ID = batch_course_student.STUDENT_ENROLL_DETAILS_ID ";
		
		
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
		
		
		return finalOutput;
	}
	
	

	@Override
	public List<Map<String, Object>> getStudentDetailsByStudentBatchId(String StudentBatchId) throws Exception {
		// TODO Auto-generated method stub
		return batchCourseDaoInterface.getAllBatchCourseBySubBatchId(StudentBatchId);
	}

	@Override
	public Responce updateBatchCourseStudentDetailes(String batchCourseStudentId,
			HashMap<String, Object> batchCourseStudentDetails) throws Exception {
		Responce responce = new Responce();
		try {
//		
//		Optional<BatchCourseStudent> student = batchCourseStudentRepo.findById(batchCourseStudentId);

		
		
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		
		CriteriaQuery<BatchCourseStudent> query = criteriaBuilder.createQuery(BatchCourseStudent.class);
		
		
		Root<BatchCourseStudent> root = query.from(BatchCourseStudent.class);
		
		Predicate predicate = criteriaBuilder.equal(root.get("batchCourseStudentId"), batchCourseStudentId);
		
		
		query.select(root).where(predicate);
		
		List<BatchCourseStudent> resultList = entityManager.createQuery(query).getResultList();	
		
		
		
		
		
		if (!resultList.isEmpty()) {
			
			BatchCourseStudent batchCourseStudent = resultList.get(0);
			
			
			
			if(batchCourseStudentDetails.get("student_name")!=null) {
				batchCourseStudent.setStudentName(batchCourseStudentDetails.get("student_name").toString());
			}
			
			

			if(batchCourseStudentDetails.get("enroll_Type")!=null) {
				batchCourseStudent.setEnrollType(batchCourseStudentDetails.get("enroll_Type").toString());
			}
			

			if(batchCourseStudentDetails.get("student_PermitionStatus")!=null) {
				
				if(batchCourseStudent.getStudentPermitionStatus().equalsIgnoreCase("D")) {
					batchCourseStudent.setStudentPermitionStatus("A");
				}else {
					batchCourseStudent.setStudentPermitionStatus("D");
				}
			}
			
			
			BatchCourseStudent updatedBatchCourseStudent = batchCourseStudentRepo.save(batchCourseStudent);
			
			if(updatedBatchCourseStudent==null) {
				responce.setMassege("failed to update the details");
				responce.setStatus(false);
			}
			
			responce.setMassege("details updated successfully");
			responce.setStatus(true);
			
		}else {
			responce.setMassege("student with the batch course student id not found");
			responce.setStatus(false);
		}
		}catch (Exception e) {
			throw new Exception("Getting problem while updating Batch Course Student");
		}
		return responce;
	}

}

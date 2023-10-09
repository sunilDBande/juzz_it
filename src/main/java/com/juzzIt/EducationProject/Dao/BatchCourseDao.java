package com.juzzIt.EducationProject.Dao;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.juzzIt.EducationProject.DaoInterface.BatchCourseDaoInterface;
import com.juzzIt.EducationProject.DaoInterface.CourseTypeDaoInterface;
import com.juzzIt.EducationProject.DaoInterface.SubBatchDaoInterface;
import com.juzzIt.EducationProject.Entity.BatchCourse;
import com.juzzIt.EducationProject.Entity.SubBatch;
import com.juzzIt.EducationProject.Models.Responce;
import com.juzzIt.EducationProject.Repositary.BatchCourseRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;

@Repository
public class BatchCourseDao implements BatchCourseDaoInterface {

@Autowired
private BatchCourseRepository batchCourseRepo;

@Autowired
private SubBatchDaoInterface subBatchDaoInterface;
@Autowired
private CourseTypeDaoInterface courseTypeDaoInterface;

@Autowired
private EntityManager entityManager;


@Override
public BatchCourse addBatchCourse(BatchCourse batchCourse) {
	return batchCourseRepo.save(batchCourse);
}

@Override
public BatchCourse getBatchCourseById(String batchCourseId) {
	
	CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
	CriteriaQuery<BatchCourse> createQuery = criteriaBuilder.createQuery(BatchCourse.class);
	
	Root<BatchCourse> root = createQuery.from(BatchCourse.class);
	
	Predicate predicate = criteriaBuilder.equal(root.get("batchCourseId"), batchCourseId);
	
	
	createQuery.select(root).where(predicate);
	List<BatchCourse> resultList = entityManager.createQuery(createQuery).getResultList();
	
	if(resultList.isEmpty()) {
		return null;
	}else {
		return resultList.get(0);
	}
	
//	
//	System.out.println("batchCourseId--> "+batchCourseId);
//	Optional<BatchCourse> batchCourse = batchCourseRepo.findById(batchCourseId);
//
//	if(batchCourse.isPresent()) {
//		return batchCourse.get();
//	}
//	
//	return null;
}

@Override
public Responce deleteBatchCourse(String batchCourseId) throws Exception {
	Responce responce=new Responce();
	try {
	CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
	System.out.println(batchCourseId);
	CriteriaQuery<BatchCourse> createQuery = criteriaBuilder.createQuery(BatchCourse.class);
	Root<BatchCourse> root = createQuery.from(BatchCourse.class);
	Predicate predicate = criteriaBuilder.equal(root.get("batchCourseId"), batchCourseId);
		
	createQuery.select(root).where(predicate);
	List<BatchCourse> resultList = entityManager.createQuery(createQuery).getResultList();
	

	
	
	if(resultList.isEmpty()) {
		responce.setMassege("batch course with the given id not found ");
		responce.setStatus(false);
	}else {
		batchCourseRepo.delete( resultList.get(0));
		responce.setMassege("batch course deleted successfully");
	responce.setStatus(true);
	}
	}catch (Exception e) {
		throw new Exception("Getting problem while deleting Batch-Course");
	}
	return responce;
	
	
//	Optional<BatchCourse> batchCourse = batchCourseRepo.findById(batchCourseId);
//	Responce responce=new Responce();
//	if(batchCourse.isPresent()) {
//		batchCourseRepo.delete(batchCourse.get());
//		responce.setMassege("batch course deleted successfully");
//		responce.setStatus(true);
//	}else {
//		responce.setMassege("batchCourse with the givwn id not found");
//		responce.setStatus(false);
//	}
//	
//	return responce;
}

@Override
@Transactional
public List<Map<String, Object>> getAllBatchCourseBySubBatchId(String subBatchId) throws Exception {
	List<Map<String, Object>> collect = null;
	try {
	SubBatch subBatch = subBatchDaoInterface.getSubBatchById(subBatchId);
	if(subBatch==null) {
		return null;
	}
	
	CriteriaBuilder builder = entityManager.getCriteriaBuilder();
	
	CriteriaQuery<BatchCourse> createQuery = builder.createQuery(BatchCourse.class);
	Root<BatchCourse> root = createQuery.from(BatchCourse.class);
	
	
	Predicate predicate = builder.equal(root.get("subBatch"), subBatch);
	
	
	createQuery.select(root).where(predicate);
	
	
	List<BatchCourse> resultList = entityManager.createQuery(createQuery).getResultList();
	
	
	 collect = resultList.stream().map( result->{
		
		Map<String, Object> map=new LinkedHashMap<String, Object>();
		
		map.put("batch_courseId", result.getBatchCourseId());
		map.put("batch_courseName", result.getBatchCourseName());
		map.put("course_Name", result.getCourseName());
		map.put("active_batchCourse", result.getActive_batchCourse());
		map.put("admition_status", result.getAdmitionStatus());
		map.put("batch_completionStatus", result.getBatchCompletionStatus());
		map.put("temp_delete", result.getTemp_delete());
		map.put("course_TypeName",result.getCourseType().getCourseTypeName());
	
		return map;
	}).collect(Collectors.toList());
	}catch (Exception e) {
		throw new Exception("Getting problem while getting All Batch Course By Sub-BatchId");
	}
	return collect;
}

@Override
public boolean existsByBatchCourseNameAndSubBatch(String batchCourseName,SubBatch subBatch) {
	// TODO Auto-generated method stub
	return batchCourseRepo.existsByBatchCourseNameOrSubBatch(batchCourseName,subBatch);
}

@Override
public Responce updateBatchCourseDetaile(String batchCourseId, HashMap<String, Object> batchCourseData) throws Exception {
BatchCourse batchCourse = getBatchCourseById(batchCourseId);
	Responce responce=new Responce();
	try {
	if(batchCourse!=null) {
		
	
		if(batchCourseData.get("batchCourse_Name")!=null) {
			batchCourse.setBatchCourseName(batchCourseData.get("batchCourse_Name").toString());
		}
		

		if(batchCourseData.get("course_Name")!=null) {
			batchCourse.setCourseName(batchCourseData.get("course_Name").toString());
		}
		


		if(batchCourseData.get("active_batchCourse")!=null) {
			
			
			if(batchCourse.getActive_batchCourse().equalsIgnoreCase("D")) {
				batchCourse.setActive_batchCourse("A");
			}
			else {
				batchCourse.setActive_batchCourse("D");
			}
		}

		if(batchCourseData.get("admition_status")!=null) {
			if(batchCourse.getAdmitionStatus().equalsIgnoreCase("D")) {
				batchCourse.setAdmitionStatus("A");
			}
			else {
				batchCourse.setAdmitionStatus("D");
			}
		}

		if(batchCourseData.get("batch_conpletion_Status")!=null) {
			if(batchCourse.getBatchCompletionStatus().equalsIgnoreCase("D")) {
				batchCourse.setBatchCompletionStatus("A");
			}
			else {
				batchCourse.setBatchCompletionStatus("D");
			}
		}
		
		

		BatchCourse updated_BatchCourse = batchCourseRepo.save(batchCourse);
		
		
		if(updated_BatchCourse==null) {
			responce.setMassege("failed to update the details");
			responce.setStatus(false);
		}
		
		responce.setMassege("details updated successfully");
		responce.setStatus(true);
		
	}else {
		responce.setMassege("batch course withe the given id not found");
		responce.setStatus(false);
	}
}catch (Exception e) {
	throw new Exception("Getting problem while updating Batch-Course-Detaile");
}
	return responce;
}
}

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
import com.juzzIt.EducationProject.DaoInterface.BatchCourseSubjectDaoInterface;
import com.juzzIt.EducationProject.Entity.BatchCourse;
import com.juzzIt.EducationProject.Entity.BatchCourseSubject;
import com.juzzIt.EducationProject.Entity.Teacher;
import com.juzzIt.EducationProject.Models.Responce;
import com.juzzIt.EducationProject.Repositary.BatchCourseSubjectRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;


@Repository
public class BatchCourseSubjectDao implements BatchCourseSubjectDaoInterface{

	@Autowired
	private BatchCourseSubjectRepository batchCourseSubjectRepo;
	@Autowired
	private BatchCourseDaoInterface batchCourseDaoInterface;
	
	@Autowired
	private EntityManager entityManager;
	
	public BatchCourseSubject addSubjectToBatch(BatchCourseSubject batchCourseSubject) {
		// TODO Auto-generated method stub
		return batchCourseSubjectRepo.save(batchCourseSubject);
	}

	@Override
	public Responce deleteSubjectFromBatch(String subjectId) throws Exception {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		Responce responce=new Responce();
       try {
		CriteriaQuery<BatchCourseSubject> createQuery = criteriaBuilder.createQuery(BatchCourseSubject.class);
		
		Root<BatchCourseSubject> root = createQuery.from(BatchCourseSubject.class);
		
		Predicate predicate = criteriaBuilder.equal(root.get("batchCourseSubjectId"), subjectId);
		
		createQuery.select(root).where(predicate);
		List<BatchCourseSubject> resultList = entityManager.createQuery(createQuery).getResultList();
		
		if(resultList.isEmpty()) {
			responce.setMassege("subject with the given id not found");
			responce.setStatus(false);
		}else {
			batchCourseSubjectRepo.delete(resultList.get(0));
			responce.setMassege("subject deleted from the batch");
			responce.setStatus(true);
		}
		
//		Optional<BatchCourseSubject> subject = batchCourseSubjectRepo.findById(subjectId);
//		if(subject.isPresent()) {
//			batchCourseSubjectRepo.delete(subject.get());
//			responce.setMassege("subject deleted from the batch");
//			responce.setStatus(true);
//		}else {
//			responce.setMassege("subject with the given id not found");
//			responce.setStatus(false);
//		}
       }catch (Exception e) {
		throw new Exception("Getting problem while deleting Subject From Batch");
	}
		return responce;
	}

	@Override
	public List<Map<String, Object>> getAllSubjectByBatchCourseId(String batchCourseId) throws Exception {
		BatchCourse batchCourse = batchCourseDaoInterface.getBatchCourseById(batchCourseId);
		List<Map<String, Object>> collect = null;
		try {
		if(batchCourse==null) {
			return null;
		}


		CriteriaBuilder builder = entityManager.getCriteriaBuilder();

		CriteriaQuery<BatchCourseSubject> createQuery = builder.createQuery(BatchCourseSubject.class);

		Root<BatchCourseSubject> root = createQuery.from(BatchCourseSubject.class);
				
		Predicate predicate = builder.equal(root.get("batchCourse"), batchCourse);

		createQuery.select(root).where(predicate);

		List<BatchCourseSubject> resultList = entityManager.createQuery(createQuery).getResultList();

		 collect = resultList.stream().map(result->{
			
			Map<String , Object> map=new LinkedHashMap<String, Object>();
			map.put("subject_Id", result.getBatchCourseSubjectId());
			map.put("subject_Name", result.getBatchCourseSubjectName());
			Teacher teacher = result.getTeacher();
			map.put("teacher_Name", teacher.getTeacherName());
			map.put("teacher_Id", teacher.getTeacherId());
			map.put("teacher_PermitionStatus", result.getTeacherPermitionStutus());
			return map;
		}).collect(Collectors.toList());
		}catch (Exception e) {
			throw new Exception("Getting problem while getting All Subject By Batch CourseId");
		}
				return collect;
	}

	@Override
	public Responce updateBatchCourseSubject(String batchCourseSubjectId,HashMap<String, Object> batchCourseSubjectDetails) throws Exception {
		
  BatchCourseSubject subject = getbatchCourseSubjectById(batchCourseSubjectId);
		
		Responce responce=new Responce();
		try {
		if(subject!=null) {
			
		
			
			
			if(batchCourseSubjectDetails.get("subject_Name")!=null) {
				
				subject.setBatchCourseSubjectName(batchCourseSubjectDetails.get("subject_Name").toString());
			}
			

			if(batchCourseSubjectDetails.get("teacher_Name")!=null) {
				
				subject.setBatchCourseSubjectName(batchCourseSubjectDetails.get("subject_Name").toString());
			}
			
					
			
			if(batchCourseSubjectDetails.get("teacher_PermitionStatus")!=null) {
				
				if(subject.getTeacherPermitionStutus().equalsIgnoreCase("D")) {
					subject.setTeacherPermitionStutus("A");
				}else {
					subject.setTeacherPermitionStutus("D");
				}	
			}
			
			
			BatchCourseSubject updatedBatchCourseSubject = batchCourseSubjectRepo.save(subject);
			
			if(updatedBatchCourseSubject==null) {
				responce.setMassege("failed to update the details");
				responce.setStatus(false);
			}
			
			responce.setMassege("details updated successfully");
			responce.setStatus(true);
			
			
		}else {
            responce.setMassege("batch course subject with the given id not found");
            responce.setStatus(false);
		}
		}catch (Exception e) {
			throw new Exception("Getting problem while updating BatchCourseSubject");
		}
		return responce;
	}

	@Override
	public BatchCourseSubject getbatchCourseSubjectById(String batchCourseSubjectId) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		
		
		CriteriaQuery<BatchCourseSubject> createQuery = criteriaBuilder.createQuery(BatchCourseSubject.class);
		
		Root<BatchCourseSubject> root = createQuery.from(BatchCourseSubject.class);
		
		Predicate predicate = criteriaBuilder.equal(root.get("batchCourseSubjectId"), batchCourseSubjectId);
		
		createQuery.select(root).where(predicate);
		
		List<BatchCourseSubject> resultList = entityManager.createQuery(createQuery).getResultList();
		
		if(resultList.isEmpty()) {
			return null;
		}else {
			return resultList.get(0);
		}
		
		
//		
//		Optional<BatchCourseSubject> batchCourseSubject = batchCourseSubjectRepo.findById(batchCourseSubjectId);
//		
//		if(batchCourseSubject.isPresent()) {
//			return batchCourseSubject.get();
//		}
//		return null;
	
	}

}

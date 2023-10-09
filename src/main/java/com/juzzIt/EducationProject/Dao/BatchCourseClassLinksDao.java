package com.juzzIt.EducationProject.Dao;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.juzzIt.EducationProject.DaoInterface.BatchCourseClassLinksDaoInterface;
import com.juzzIt.EducationProject.Entity.BatchCourse;
import com.juzzIt.EducationProject.Entity.BatchCourseClassLinks;
import com.juzzIt.EducationProject.Entity.BatchCourseSubject;
import com.juzzIt.EducationProject.Models.Responce;
import com.juzzIt.EducationProject.Repositary.BatchCourseClassLinksRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

@Component
public class BatchCourseClassLinksDao  implements BatchCourseClassLinksDaoInterface{
@Autowired 
private BatchCourseClassLinksRepository batchCourseClassLinksRepository;
	


  @Autowired
   private EntityManager entityManager;

	@Override
	public BatchCourseClassLinks addNewClassLink(BatchCourseClassLinks batchCourseClassLinks) {
		// TODO Auto-generated method stub
		return batchCourseClassLinksRepository.save(batchCourseClassLinks);
	}


	@Override
	public Responce deleteClassLink(String classId) throws Exception {
		Optional<BatchCourseClassLinks> classLink = batchCourseClassLinksRepository.findById(classId);
		Responce responce =new Responce();
		try {
		if(classLink.isPresent()) {
			batchCourseClassLinksRepository.delete(classLink.get());
            responce.setMassege("class link deleted successfully");
            responce.setStatus(true);
		}else {
			responce.setMassege("class link for the given id not found");
			responce.setStatus(false);
		}
		}catch (Exception e) {
			throw new Exception("Getting problem while deleting ClassLink");
		}
		
		return responce;
	}


	@Override
	public List<Map<String, Object>> getAllClassLinksByBatchCourseId(BatchCourse batchCourse) throws Exception {
		// TODO Auto-generated method stub
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		List<Map<String, Object>> collect = null;
		try {
		CriteriaQuery<BatchCourseClassLinks> createQuery = criteriaBuilder.createQuery(BatchCourseClassLinks.class);
		Root<BatchCourseClassLinks> root = createQuery.from(BatchCourseClassLinks.class);
		Predicate predicate = criteriaBuilder.equal(root.get("batchCourse"), batchCourse);
		createQuery.select(root).where(predicate);
		List<BatchCourseClassLinks> resultList = entityManager.createQuery(createQuery).getResultList();
	
		 collect = resultList.stream().map(res -> {
			
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("class_Id", res.getClassId());
			map.put("class_Title", res.getClassTitle());
			map.put("subject_Name", res.getSubjectName());
			map.put("teacher_Name", res.getTeacherName());
			map.put("class_Date", res.getClassDate());
			map.put("class_Time", res.getClassTime());
			map.put("class_Link", res.getClassLink());
			return map;
		}).collect(Collectors.toList());
		}catch (Exception e) {
			throw new Exception("Getting problem while getting All ClassLinks By BatchCourseId");
		}
		return collect;
	}


	@Override
	public List<Map<String, Object>> getAllClassLinksByBatchCourseIdAndBatchCoourseSubjectId(BatchCourse batchCourse,
			BatchCourseSubject batchCourseSubject) throws Exception {
		System.out.println("*******");
		List<Map<String, Object>> collect=null;
		try {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<BatchCourseClassLinks> createQuery = criteriaBuilder.createQuery(BatchCourseClassLinks.class);
		Root<BatchCourseClassLinks> root = createQuery.from(BatchCourseClassLinks.class);
		Predicate predicate = criteriaBuilder.equal(root.get("batchCourse"), batchCourse);
		Predicate predicate2 = criteriaBuilder.equal(root.get("batchCourseSubject"), batchCourseSubject);
		Predicate predicate3 = criteriaBuilder.and(predicate, predicate2);
		createQuery.select(root).where(predicate3);
		List<BatchCourseClassLinks> resultList = entityManager.createQuery(createQuery).getResultList();
	
		 collect = resultList.stream().map(res -> {
			
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("class_Id", res.getClassId());
			map.put("class_Title", res.getClassTitle());
			map.put("subject_Name", res.getSubjectName());
			map.put("teacher_Name", res.getTeacherName());
			map.put("class_Date", res.getClassDate());
			map.put("class_Time", res.getClassTime());
			map.put("class_Link", res.getClassLink());
			return map;
		}).collect(Collectors.toList());
		System.out.println(collect);
		}catch (Exception e) {
			throw new Exception("Getting problem while getting All ClassLinks By BatchCourseId And BatchCoourseSubjectId");
		}
		return collect;
	}

}

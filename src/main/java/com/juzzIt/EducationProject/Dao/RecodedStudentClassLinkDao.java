package com.juzzIt.EducationProject.Dao;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.juzzIt.EducationProject.DaoInterface.RecodedStudentClassLinkDaoDaoInterface;
import com.juzzIt.EducationProject.Entity.RecodedStudentClassLinks;
import com.juzzIt.EducationProject.Entity.RecordedStudent;
import com.juzzIt.EducationProject.Models.Responce;
import com.juzzIt.EducationProject.Repositary.RecodedStudentClassLinksRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;


@Component
public class RecodedStudentClassLinkDao implements RecodedStudentClassLinkDaoDaoInterface {

	
	@Autowired
	private RecodedStudentClassLinksRepository recodedStudentClassLinksRepository;
	
	@Autowired
	private EntityManager entityManager;
	
	
	public RecodedStudentClassLinks addRecodedStudentClassLinks(RecodedStudentClassLinks recodedStudentClassLinks) {
		return recodedStudentClassLinksRepository.save(recodedStudentClassLinks);
	}
	
	public RecodedStudentClassLinks getRecodedStudentClassLinksById(String classLinkId) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<RecodedStudentClassLinks> createQuery = criteriaBuilder.createQuery(RecodedStudentClassLinks.class);
		Root<RecodedStudentClassLinks> root = createQuery.from(RecodedStudentClassLinks.class);
	
		Predicate predicate = criteriaBuilder.equal(root.get(""), classLinkId);
		createQuery.select(root).where(predicate);
		List<RecodedStudentClassLinks> resultList = entityManager.createQuery(createQuery).getResultList();
		if(resultList.isEmpty()) {
			return null;
		}
		return resultList.get(0);
	}

	@Override
	public Responce deleteClassLink(String classLinkId) {
		RecodedStudentClassLinks recodedStudentClassLinks = getRecodedStudentClassLinksById(classLinkId);
		
		Responce responce=new Responce();
		
		if(recodedStudentClassLinks==null) {
			responce.setMassege("class link with the given id not found");
			responce.setStatus(false);
			return responce;
		}
		
		
		recodedStudentClassLinksRepository.delete(recodedStudentClassLinks);
		responce.setMassege("class link deleted successfully");
		responce.setStatus(true);
		return responce;
		
		
		
	}

	@Override
	public List<Map<String, Object>> getAllRecodedClassLinks(RecordedStudent recordedStudent) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<RecodedStudentClassLinks> createQuery = criteriaBuilder.createQuery(RecodedStudentClassLinks.class);
		Root<RecodedStudentClassLinks> root = createQuery.from(RecodedStudentClassLinks.class);
	
		Predicate predicate = criteriaBuilder.equal(root.get("recordedStudent"), recordedStudent);
		createQuery.select(root).where(predicate);
		List<RecodedStudentClassLinks> resultList = entityManager.createQuery(createQuery).getResultList();
		
		List<Map<String, Object>> collect = resultList.stream().map(result->{
			Map<String, Object> map=new LinkedHashMap<String, Object>();
			map.put("class_Id", result.getClassId());
			map.put("class_subjectName", result.getSubjectName());
			map.put("class_Title", result.getClassTitle());
			map.put("class_Link", result.getClassLink());
			map.put("class_Time", result.getClassTime());
			map.put("class_Date", result.getClassDate());
			return map;	
		}).collect(Collectors.toList());
		return collect;
	}

}

package com.juzzIt.EducationProject.Dao;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.juzzIt.EducationProject.DaoInterface.BatchCourseRecordedVideoSubjectDaoInterface;
import com.juzzIt.EducationProject.Entity.BatchCourse;
import com.juzzIt.EducationProject.Entity.BatchCourseRecordedVideoSubject;
import com.juzzIt.EducationProject.Models.Responce;
import com.juzzIt.EducationProject.Repositary.BatchCourseRecordedVideoSubjectRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

@Component
public class BatchCourseRecordedVideoSubjectDao  implements BatchCourseRecordedVideoSubjectDaoInterface{

	@Autowired
	private BatchCourseRecordedVideoSubjectRepository batchCourseRecordedVideoSubjectRepository;
	@Autowired
	private EntityManager entityManager;
	
	
	@Override
	public BatchCourseRecordedVideoSubject addNewbatchCourseRecordedVideoSubject(
			BatchCourseRecordedVideoSubject batchCourseRecordedVideoSubject) {
		
		return batchCourseRecordedVideoSubjectRepository.save(batchCourseRecordedVideoSubject);
	}

	@Override
	public BatchCourseRecordedVideoSubject getBatchCourseRecordedVideoSubjectById(
			String batchCourseRecordedVideoSubjectId) {
		
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		
		CriteriaQuery<BatchCourseRecordedVideoSubject> createQuery = criteriaBuilder.createQuery(BatchCourseRecordedVideoSubject.class);
	
		Root<BatchCourseRecordedVideoSubject> root = createQuery.from(BatchCourseRecordedVideoSubject.class);
		
		Predicate predicate = criteriaBuilder.equal(root.get("videoSubjectId"), batchCourseRecordedVideoSubjectId);
		
		createQuery.select(root).where(predicate);
		
		List<BatchCourseRecordedVideoSubject> resultList = entityManager.createQuery(createQuery).getResultList();
		
if(resultList.isEmpty()) {
	return null;
}
		


		
		return resultList.get(0);
	}

	@Override
	public Responce deletebatchCourseRecordedVideoSubject(String recordedVideoSubjectsId) {

		BatchCourseRecordedVideoSubject batchCourseRecordedVideoSubject = getBatchCourseRecordedVideoSubjectById(recordedVideoSubjectsId);
		Responce responce=new Responce();
		if(batchCourseRecordedVideoSubject==null) {
			
			responce.setMassege("Recorded Video Subject for the given given id not found");
			responce.setStatus(false);
			return responce;
			
		}

         batchCourseRecordedVideoSubjectRepository.delete(batchCourseRecordedVideoSubject);
         responce.setMassege("recorded video deleted successfully");
         responce.setStatus(true);
		return responce;
	}

	@Override
	public Responce updatebatchCourseRecordedVideoSubject(String recordedVideoSubjectsId,
			Map<String, Object> subjectData) {

		BatchCourseRecordedVideoSubject batchCourseRecordedVideoSubject = getBatchCourseRecordedVideoSubjectById(recordedVideoSubjectsId);
		Responce responce=new Responce();
		if(batchCourseRecordedVideoSubject==null) {
			responce.setMassege("Recorded Video Subject for the given given id not found");
			responce.setStatus(false);
			return responce;	
		}
		
		
		if(subjectData.get("video_Subject_Name")!=null) {
			batchCourseRecordedVideoSubject.setVideoSubjectName(subjectData.get("Video_Subject_Name").toString());
		}
		
		if(subjectData.get("active_Status")!=null) {
			if(batchCourseRecordedVideoSubject.getActiveStatus().equalsIgnoreCase("D")) {
				batchCourseRecordedVideoSubject.setActiveStatus("A");
			}else {
				batchCourseRecordedVideoSubject.setActiveStatus("D");
			}
		}
		
		BatchCourseRecordedVideoSubject updatedbatchCourseRecordedVideoSubject=	batchCourseRecordedVideoSubjectRepository.save(batchCourseRecordedVideoSubject);
		
		
		if(updatedbatchCourseRecordedVideoSubject==null) {
			responce.setMassege("failed to update the data");
			responce.setStatus(false);
			return responce;
		}
		responce.setMassege("details updated successfully");
		responce.setStatus(true);
		return responce;
	}

	@Override
	public List<Map<String, Object>> getAllbatchCourseRecordedVideoSubjectByBatchCourseId(BatchCourse batchCourse) {
CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		
		CriteriaQuery<BatchCourseRecordedVideoSubject> createQuery = criteriaBuilder.createQuery(BatchCourseRecordedVideoSubject.class);
		Root<BatchCourseRecordedVideoSubject> root = createQuery.from(BatchCourseRecordedVideoSubject.class);
		Predicate predicate = criteriaBuilder.equal(root.get("batchCourse"), batchCourse);
		createQuery.select(root).where(predicate);
		List<BatchCourseRecordedVideoSubject> resultList = entityManager.createQuery(createQuery).getResultList();
		
		List<Map<String, Object>> collect = resultList.stream().map(result->{
			Map< String, Object> map=new LinkedHashMap<String, Object>();
			map.put("video_Subject_Id", result.getVideoSubjectId());
			map.put("video_Subject_Name", result.getVideoSubjectName());
			map.put("active_Status", result.getActiveStatus());
			return map;
		}).collect(Collectors.toList());
		return collect;
	}

	
	
}

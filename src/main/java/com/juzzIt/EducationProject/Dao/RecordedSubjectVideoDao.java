package com.juzzIt.EducationProject.Dao;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.juzzIt.EducationProject.DaoInterface.RecordedSubjectVideoDaoInterface;
import com.juzzIt.EducationProject.Entity.BatchCourseRecordedVideoSubject;
import com.juzzIt.EducationProject.Entity.RecordedSubjectVideo;
import com.juzzIt.EducationProject.Models.Responce;
import com.juzzIt.EducationProject.Repositary.RecordedSubjectVideoRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
@Component
public class RecordedSubjectVideoDao implements RecordedSubjectVideoDaoInterface {

	@Autowired
	private RecordedSubjectVideoRepository recordedSubjectVideoRepository;
	@Autowired
	private EntityManager entityManager;
	@Override
	public RecordedSubjectVideo addNewRecordedSubjectVideo(RecordedSubjectVideo recordedSubjectVideo) {
		return recordedSubjectVideoRepository.save(recordedSubjectVideo);
	}
	
	@Override
	public RecordedSubjectVideo getRecordedSubjectVideoById(String recordedSubjectVideoId) {

		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<RecordedSubjectVideo> createQuery = criteriaBuilder.createQuery(RecordedSubjectVideo.class);
		Root<RecordedSubjectVideo> root = createQuery.from(RecordedSubjectVideo.class);
		Predicate predicate = criteriaBuilder.equal(root.get("recordedVideoId"), recordedSubjectVideoId);
		createQuery.select(root).where(predicate);
		List<RecordedSubjectVideo> resultList = entityManager.createQuery(createQuery).getResultList();
		if(resultList.isEmpty()) {
			return null;
		}
		return resultList.get(0);
	}

	@Override
	public Responce deleteRecordedSubjectVideo(String recordedVideoId) {
		RecordedSubjectVideo recordedSubjectVideo = getRecordedSubjectVideoById(recordedVideoId);
		
		Responce responce=new Responce();
		if(recordedSubjectVideo==null) {
			responce.setMassege("recorded Video with the given id not found");
			responce.setStatus(false);
			return  responce;
		}
		
		recordedSubjectVideoRepository.delete(recordedSubjectVideo);
		responce.setMassege("video deleted successfully");
		responce.setStatus(true);
		return responce;
	}

	@Override
	public Responce updateRecodedSubjectVideo(String recordedVideoId, HashMap<String, Object> RecordedVideoData) {
RecordedSubjectVideo recordedSubjectVideo = getRecordedSubjectVideoById(recordedVideoId);
		
		Responce responce=new Responce();
		if(recordedSubjectVideo==null) {
			responce.setMassege("recorded Video with the given id not found");
			responce.setStatus(false);
			return  responce;
		}
		
		if(RecordedVideoData.get("vedio_Topic")!=null) {
			
			recordedSubjectVideo.setVedioTopic(RecordedVideoData.get("vedio_Topic").toString());
			
		}
	if(RecordedVideoData.get("video_URL")!=null) {
		recordedSubjectVideo.setVideoURL(RecordedVideoData.get("video_URL").toString());
		}
	if(RecordedVideoData.get("active_Status")!=null) {
		
		if(recordedSubjectVideo.getActiveStatus().equalsIgnoreCase("D")) {
			recordedSubjectVideo.setActiveStatus("A");
		}else {
			recordedSubjectVideo.setActiveStatus("D");
		}
	}
	if(RecordedVideoData.get("video_Desc")!=null) {
		recordedSubjectVideo.setVideoDesc(RecordedVideoData.get("video_Desc").toString());
	}
		
	
	RecordedSubjectVideo updatedrecordedSubjectVideo = recordedSubjectVideoRepository.save(recordedSubjectVideo);
		
	
if(updatedrecordedSubjectVideo==null) {
	responce.setMassege("failed to update the data");
	responce.setStatus(false);
	return  responce;
}
		
responce.setMassege("detailes updated successfully");
responce.setStatus(true);
		return responce;
	}

	@Override
	public List<Map<String, Object>> getAllRecordedSubjectVideo(
		BatchCourseRecordedVideoSubject batchCourseRecordedVideoSubject) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<RecordedSubjectVideo> createQuery = criteriaBuilder.createQuery(RecordedSubjectVideo.class);
		Root<RecordedSubjectVideo> root = createQuery.from(RecordedSubjectVideo.class);
		Predicate predicate = criteriaBuilder.equal(root.get("batchCourseRecordedVideoSubject"), batchCourseRecordedVideoSubject);
		createQuery.select(root).where(predicate);
		List<RecordedSubjectVideo> resultList = entityManager.createQuery(createQuery).getResultList();
		List<Map<String, Object>> collect = resultList.stream().map(result->{
			Map<String , Object> map=new LinkedHashMap<String, Object>();
			map.put("recordedVideoId", result.getRecordedVideoId());
			map.put("vedioTopic", result.getVedioTopic());
			map.put("videoDesc", result.getVideoDesc());
			map.put("videoURL", result.getVideoURL());
			map.put("activeStatus", result.getActiveStatus());
			map.put("createdDate", result.getCreatedDate());
			return map;
		}).collect(Collectors.toList());
		return collect;
	}
	
	
	
	
	
}

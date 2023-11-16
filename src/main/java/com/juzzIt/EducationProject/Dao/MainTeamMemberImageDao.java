package com.juzzIt.EducationProject.Dao;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.juzzIt.EducationProject.DaoInterface.MainTeamMemberImageDaoInterface;
import com.juzzIt.EducationProject.Entity.MainTeamMember;
import com.juzzIt.EducationProject.Entity.MainTeamMemberImage;
import com.juzzIt.EducationProject.Models.Responce;
import com.juzzIt.EducationProject.Repositary.MainTeamMemberImageRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;


@Service
public class MainTeamMemberImageDao implements MainTeamMemberImageDaoInterface {
	
	@Autowired
	private MainTeamMemberImageRepository mainTeamMemberImageRepository;
	
	@Autowired
	private EntityManager entityManager;
	
	@Override
	public MainTeamMemberImage addMainTeamMemberImage(MainTeamMemberImage mainTeamMemberImage) {
		return mainTeamMemberImageRepository.save(mainTeamMemberImage);
	}
	@Override
	public MainTeamMemberImage getMainTeamMemberImageById(String mainTeamMemberImageId) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<MainTeamMemberImage> createQuery = criteriaBuilder.createQuery(MainTeamMemberImage.class);
		Root<MainTeamMemberImage> root = createQuery.from(MainTeamMemberImage.class);
		Predicate predicate = criteriaBuilder.equal(root.get("imageId"), mainTeamMemberImageId);
		createQuery.select(root).where(predicate);
		List<MainTeamMemberImage> resultList = entityManager.createQuery(createQuery).getResultList();
		if(resultList.isEmpty()) {
			return null;
		}
		return resultList.get(0);
	}

	@Override
	public Responce deleteMainTeamMemberImage(String mainTeamMemberImageId) {
		MainTeamMemberImage mainTeamMemberImage = getMainTeamMemberImageById( mainTeamMemberImageId);
		
		Responce responce=new Responce();
		if(mainTeamMemberImage==null) {
			responce.setMassege("image with the givenj id not found");
			responce.setStatus(false);
			return responce;
		}
		mainTeamMemberImageRepository.delete(mainTeamMemberImage);
		responce.setMassege("image deleted successfully");
		responce.setStatus(true);
		return responce;
	}

	@Override
	public Responce updateMainTeamMemberImage(String MainTeamMemberImageId,
	    HashMap<String, Object> mainTeamMemberImageData) {
        MainTeamMemberImage mainTeamMemberImage = getMainTeamMemberImageById( MainTeamMemberImageId);
		Responce responce=new Responce();
		if(mainTeamMemberImage==null) {
			responce.setMassege("image with the givenj id not found");
			responce.setStatus(false);
			return responce;
		}
		
		if(mainTeamMemberImageData.get("active_Status")!=null) {
			
			if(mainTeamMemberImage.getActiveStatus().equalsIgnoreCase("D")) {
				mainTeamMemberImage.setActiveStatus("A");
			}else {
				mainTeamMemberImage.setActiveStatus("D");
			}
		}
		
		
		
		
		MainTeamMemberImage teamMemberImage = mainTeamMemberImageRepository.save(mainTeamMemberImage);
		if(teamMemberImage==null) {
			responce.setMassege("failed to update the image data");
			responce.setStatus(false);
			return responce;
		}
		responce.setMassege("details updated successfully");
		responce.setStatus(true);
		return responce;
	}
	@Override
	public List<Map<String, Object>> getAllMainTeamMemberImage(MainTeamMember mainTeamMember) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<MainTeamMemberImage> createQuery = criteriaBuilder.createQuery(MainTeamMemberImage.class);
		Root<MainTeamMemberImage> root = createQuery.from(MainTeamMemberImage.class);
		Predicate predicate = criteriaBuilder.equal(root.get("mainTeamMember"), mainTeamMember);
		createQuery.select(root).where(predicate);
		List<MainTeamMemberImage> resultList = entityManager.createQuery(createQuery).getResultList();
		List<Map<String, Object>> collect = resultList.stream().map(result->{
			Map<String, Object> map=new LinkedHashMap<String ,Object>();
			map.put("image_Id", result.getImageId());
			map.put("image_URL", result.getImageURL());
			map.put("active_Status", result.getActiveStatus());	
			return map;
		}).collect(Collectors.toList());
		return collect;
	}

	

}

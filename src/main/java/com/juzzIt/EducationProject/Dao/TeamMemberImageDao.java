package com.juzzIt.EducationProject.Dao;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.juzzIt.EducationProject.DaoInterface.TeamMemberImageDaoInterface;
import com.juzzIt.EducationProject.Entity.TeamMember;
import com.juzzIt.EducationProject.Entity.TeamMemberImage;
import com.juzzIt.EducationProject.Models.Responce;
import com.juzzIt.EducationProject.Repositary.TeamMemberImageRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;


@Service
public class TeamMemberImageDao implements TeamMemberImageDaoInterface {
	
	
	@Autowired
	private TeamMemberImageRepository teamMemberImageRepository;
	
	@Autowired
	private EntityManager entityManager;

	@Override
	public TeamMemberImage addTeamMemberImage(TeamMemberImage teamMemberImage) {
		// TODO Auto-generated method stub
		return teamMemberImageRepository.save(teamMemberImage);
	}

	@Override
	public TeamMemberImage getTeamMemberImageById(String teamMemberImageId) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<TeamMemberImage> createQuery = criteriaBuilder.createQuery(TeamMemberImage.class);
		Root<TeamMemberImage> root = createQuery.from(TeamMemberImage.class);
		Predicate predicate = criteriaBuilder.equal(root.get("imageId"), teamMemberImageId);
        createQuery.select(root).where(predicate);
        List<TeamMemberImage> resultList = entityManager.createQuery(createQuery).getResultList();
        if(resultList.isEmpty()) {
	       return null;
          }
           return resultList.get(0);
	}

	@Override
	public Responce deleteTeamMemberImage(String teamMemberImageId) {
		TeamMemberImage teamMemberImage = getTeamMemberImageById( teamMemberImageId);
		Responce responce=new Responce();
		if(teamMemberImage==null) {
			responce.setMassege("image with the given id not found");
			responce.setStatus(false);
			return responce;
		}
		
		teamMemberImageRepository.delete(teamMemberImage);
		responce.setMassege("image deleeted successfully");
		responce.setStatus(true);
		return responce;
	}

	@Override
	public Responce updateTeamMemberImage(String teamMemberImageId, HashMap<String, Object> teamMemberImageData) {
		TeamMemberImage teamMemberImage = getTeamMemberImageById( teamMemberImageId);
		Responce responce=new Responce();
		if(teamMemberImage==null) {
			responce.setMassege("image with the given id not found");
			responce.setStatus(false);
			return responce;
		}
		if(teamMemberImageData.get("active_Status")!=null) {
if(teamMemberImage.getActiveStatus().equalsIgnoreCase("D")) {
	teamMemberImage.setActiveStatus("A");
}
else {
	teamMemberImage.setActiveStatus("D");
}
		}
		
		
		TeamMemberImage memberImage = teamMemberImageRepository.save(teamMemberImage);
		if(memberImage==null) {
			responce.setMassege("");
			responce.setStatus(false);
            return responce;
		}
		responce.setMassege("details updated successfully");
		responce.setStatus(true);
		return responce;
	}

	@Override
	public List<Map<String, Object>> getAllTeamMemberImage(TeamMember teamMember) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<TeamMemberImage> createQuery = criteriaBuilder.createQuery(TeamMemberImage.class);
		Root<TeamMemberImage> root = createQuery.from(TeamMemberImage.class);
		Predicate predicate = criteriaBuilder.equal(root.get("teamMember"), teamMember);
        createQuery.select(root).where(predicate);
        List<TeamMemberImage> resultList = entityManager.createQuery(createQuery).getResultList();
        
        List<Map<String, Object>> collect = resultList.stream().map(result->{
        	Map<String, Object> map=new LinkedHashMap<String ,Object>();
        	map.put("IMAGE_ID", result.getImageId());
        	map.put("IMAGE_URL", result.getImageURL());
        	map.put("ACTIVE_STATUS", result.getActiveStatus());	
        	return map;
        }).collect(Collectors.toList());
		return collect;
	}

}

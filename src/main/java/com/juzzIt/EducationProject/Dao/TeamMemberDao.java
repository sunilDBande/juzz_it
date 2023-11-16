package com.juzzIt.EducationProject.Dao;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.juzzIt.EducationProject.DaoInterface.TeamMemberDaoInterface;
import com.juzzIt.EducationProject.Entity.TeamMember;
import com.juzzIt.EducationProject.Models.Responce;
import com.juzzIt.EducationProject.Repositary.TeamMemberRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;


@Service
public class TeamMemberDao implements TeamMemberDaoInterface {
@Autowired
private TeamMemberRepository teamMemberRepository;

@Autowired
private EntityManager entityManager;
	
	
	@Override
	public TeamMember addTeamMember(TeamMember teamMember) {
		// TODO Auto-generated method stub
		return teamMemberRepository.save(teamMember);
	}

	@Override
	public TeamMember getTeamMemberById(String teamMemberId) {
	CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
	CriteriaQuery<TeamMember> createQuery = criteriaBuilder.createQuery(TeamMember.class);
	Root<TeamMember> root = createQuery.from(TeamMember.class);
	Predicate predicate = criteriaBuilder.equal(root.get("teamMemberId"), teamMemberId);
	createQuery.select(root).where(predicate);
	List<TeamMember> resultList = entityManager.createQuery(createQuery).getResultList();
	if(resultList.isEmpty()) {
		return null;
	}
	return resultList.get(0);

	}

	@Override
	public Responce deleteTeamMember(String teamMemberId) {
		TeamMember teamMember = getTeamMemberById( teamMemberId);
		
		Responce responce=new Responce();
		
		
		if(teamMember==null) {
			responce.setMassege("memner with the given id not found");
			responce.setStatus(false);
			return responce;
		}
		
		teamMemberRepository.delete(teamMember);
		responce.setMassege("member deleted successfully");
		responce.setStatus(true);
		
		return responce;
	}

	@Override
	public Responce updateTeamMember(String teamMemberId, HashMap<String, Object> teamMemberData) {
TeamMember teamMember = getTeamMemberById( teamMemberId);
		
		Responce responce=new Responce();
		
		
		if(teamMember==null) {
			responce.setMassege("memner with the given id not found");
			responce.setStatus(false);
			return responce;
		}
		
		
		if(teamMemberData.get("")!=null) {
			
		}
		
        if(teamMemberData.get("")!=null) {
			
		}
		
        if(teamMemberData.get("")!=null) {
	
         }

        if(teamMemberData.get("")!=null) {
	
         }

		
		
		TeamMember member = teamMemberRepository.save(teamMember);
		if(member==null) {
			responce.setMassege("failed to update the details");
			responce.setStatus(false);
			return responce;
		}
		
		responce.setMassege("details updated successfully");
		responce.setStatus(true);
		
		return responce;
	}

	@Override
	public List<Map<String, Object>> getTeamMember() {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<TeamMember> createQuery = criteriaBuilder.createQuery(TeamMember.class);
		Root<TeamMember> root = createQuery.from(TeamMember.class);
		
		
		createQuery.select(root);
		
		List<TeamMember> resultList = entityManager.createQuery(createQuery).getResultList();
		
		
		List<Map<String, Object>> collect = resultList.stream().map(result->{
			
			Map<String, Object> map=new LinkedHashMap<String,Object>();
			map.put("MEMBER_ID", result.getTeamMemberId());
			map.put("MEMBER_NAME", result.getTeamMemberName());
			map.put("MEMBER_POSITION", result.getTeamMemberPosition());
			map.put("MEMBER_INTRUDUCTION", result.getTeamMemberIntruduction());
			
			
			return map;
		}).collect(Collectors.toList());
		
			return collect;
	}

}

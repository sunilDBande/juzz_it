package com.juzzIt.EducationProject.Dao;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.juzzIt.EducationProject.DaoInterface.MainTeamMemberDaoInterface;
import com.juzzIt.EducationProject.Entity.MainTeamMember;
import com.juzzIt.EducationProject.Models.Responce;
import com.juzzIt.EducationProject.Repositary.MainTeamMemberRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;


@Service
public class MainTeamMemberDao implements MainTeamMemberDaoInterface {
	
	@Autowired
	private MainTeamMemberRepository mainTeamMemberRepository;
	
	@Autowired
	private EntityManager entityManager;

	@Override
	public MainTeamMember addNewMainTeamMember(MainTeamMember mainTeamMember) {
		// TODO Auto-generated method stub
		return mainTeamMemberRepository.save(mainTeamMember);
	}

	@Override
	public MainTeamMember getMainTeamMemberById(String mainTeamMemberId) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<MainTeamMember> createQuery = criteriaBuilder.createQuery(MainTeamMember.class);
		Root<MainTeamMember> root = createQuery.from(MainTeamMember.class);
		Predicate predicate = criteriaBuilder.equal(root.get("mainTeamMemberId"), mainTeamMemberId);
		createQuery.select(root).where(predicate);
		List<MainTeamMember> resultList = entityManager.createQuery(createQuery).getResultList();
		
		if(resultList.isEmpty()) {
			return null;
		}
		return resultList.get(0);
	}

	@Override
	public Responce deleteMainTeamMember(String mainTeamMemberId) {
        MainTeamMember mainTeamMember = getMainTeamMemberById( mainTeamMemberId);
		Responce responce=new Responce();
		
		if(mainTeamMember==null) {
			responce.setMassege("member with the given id not found");
			responce.setStatus(false);
			return responce;
		}
		
		
		mainTeamMemberRepository.delete(mainTeamMember);
		responce.setMassege("member deleted successfully");
		responce.setStatus(true);
		return responce;
	}

	@Override
	public Responce updateMainTeamMember(String MainTeamMemberId, HashMap<String, Object> memberData) {
		MainTeamMember mainTeamMember = getMainTeamMemberById( MainTeamMemberId);
		
		Responce responce=new Responce();
		
		if(mainTeamMember==null) {
			responce.setMassege("member with the given id not found");
			responce.setStatus(false);
			return responce;
		}
		
		if(memberData.get("")!=null) {
			
		}
        if(memberData.get("")!=null) {
			
		}
        if(memberData.get("")!=null) {
	
        }
		
		
		
		MainTeamMember updatedMainTeamMember = mainTeamMemberRepository.save(mainTeamMember);
		
		
		if(updatedMainTeamMember==null) {
			responce.setMassege("");
			responce.setStatus(false);
            return responce;
		}
		
		responce.setMassege("details updated succeccfully");
		responce.setStatus(true);
		return responce;
	}

	@Override
	public List<Map<String, Object>> getAllMainTeamMember() {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<MainTeamMember> createQuery = criteriaBuilder.createQuery(MainTeamMember.class);
		Root<MainTeamMember> root = createQuery.from(MainTeamMember.class);
		createQuery.select(root);
		List<MainTeamMember> resultList = entityManager.createQuery(createQuery).getResultList();
		List<Map<String, Object>> collect = resultList.stream().map(result->{
			Map<String, Object> map=new LinkedHashMap<String, Object>();
			
			map.put("MEMBER_ID", result.getMainTeamMemberId());
			map.put("MEMBER_INTRUDUCTION", result.getMainTeamMemberIntruduction());
			map.put("MEMBER_NAME", result.getMainTeamMemberName());
			map.put("MEMBER_POSITION", result.getMainTeamMemberPosition());
			
			return map;
		}).collect(Collectors.toList());
		
		
		return collect;
	}

}

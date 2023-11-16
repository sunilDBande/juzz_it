package com.juzzIt.EducationProject.Services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.juzzIt.EducationProject.DaoInterface.TeamMemberDaoInterface;
import com.juzzIt.EducationProject.Entity.TeamMember;
import com.juzzIt.EducationProject.Models.Responce;
import com.juzzIt.EducationProject.ServiceInterface.TeamMemberImageServiceInterface;
import com.juzzIt.EducationProject.ServiceInterface.TeamMemberServiceInterface;

@Service
public class TeamMemberService implements TeamMemberServiceInterface {
	
	@Autowired
	private TeamMemberDaoInterface teamMemberDaoInterface;
	
	@Autowired
	private TeamMemberImageServiceInterface teamMemberImageServiceInterface;

	@Override
	public Responce addTeamMember(HashMap<String, Object> memberData) {
		Responce responce=new Responce();
		if(memberData.get("Member_Name")==null||memberData.get("Member_Position")==null||memberData.get("Member_Intruduction")==null) {
			responce.setMassege("name ,intruduction and position are required");
			responce.setStatus(false);
			return responce; 
		}
		TeamMember teamMember=new TeamMember();
		UUID id=UUID.randomUUID();
		teamMember.setTeamMemberId(id.toString());
		teamMember.setTeamMemberIntruduction(memberData.get("Member_Intruduction").toString());
		teamMember.setTeamMemberName(memberData.get("Member_Name").toString());
		teamMember.setTeamMemberPosition(memberData.get("Member_Position").toString());
		TeamMember addedTeamMember = teamMemberDaoInterface.addTeamMember(teamMember);
		if(addedTeamMember==null) {
			responce.setMassege("");
			responce.setStatus(false);
             return responce;
		}
		responce.setMassege("mamber added successfully");
		responce.setStatus(true);
		return responce;
	}

	@Override
	public Responce deleteTeamMember(String memberId) {
		return teamMemberDaoInterface.deleteTeamMember(memberId);
	}

	@Override
	public Responce updateTeamMember(String memberId, HashMap<String, Object> memberData) {
		return teamMemberDaoInterface.updateTeamMember(memberId, memberData);
	}

	@Override
	public List<Map<String, Object>> getAllTeamMember() {
		return teamMemberDaoInterface.getTeamMember();
	}

	@Override
	public List<Map<String, Object>> getAllTeamMemberWithImages() {
		 List<Map<String, Object>> teamMember = teamMemberDaoInterface.getTeamMember();
		 return teamMember.stream().map(result->{
			 result.put("MEMBER_IMAGE", teamMemberImageServiceInterface.getAllTeamMemberImages(result.get("MEMBER_ID").toString()));
			 return result;
		 }).collect(Collectors.toList());
		
	}

}

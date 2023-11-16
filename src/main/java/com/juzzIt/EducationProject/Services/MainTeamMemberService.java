package com.juzzIt.EducationProject.Services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.juzzIt.EducationProject.DaoInterface.MainTeamMemberDaoInterface;
import com.juzzIt.EducationProject.Entity.MainTeamMember;
import com.juzzIt.EducationProject.Models.Responce;
import com.juzzIt.EducationProject.ServiceInterface.MainTeamMemberImageServiceInterface;
import com.juzzIt.EducationProject.ServiceInterface.MainTeamMemberServiceInterface;

@Service
public class MainTeamMemberService  implements MainTeamMemberServiceInterface{
	
	
	@Autowired
	private MainTeamMemberDaoInterface mainTeamMemberDaoInterface;
	
	@Autowired
	private MainTeamMemberImageServiceInterface mainTeamMemberImageServiceInterface;

	@Override
	public Responce addMainTeamMember(HashMap<String, Object> memberData) {
	Responce responce =new Responce();
	if(memberData.get("Member_Name")==null||memberData.get("Member_Position")==null||memberData.get("Member_Intruduction")==null) {
		responce.setMassege("");
		responce.setStatus(false);
		return responce;
	}
	
	MainTeamMember mainTeamMember=new MainTeamMember();
	UUID id=UUID.randomUUID();
	mainTeamMember.setMainTeamMemberId(id.toString());
	mainTeamMember.setMainTeamMemberIntruduction(memberData.get("Member_Intruduction").toString());
	mainTeamMember.setMainTeamMemberName(memberData.get("Member_Name").toString());
	mainTeamMember.setMainTeamMemberPosition(memberData.get("Member_Position").toString());
	MainTeamMember addedMainTeamMember = mainTeamMemberDaoInterface.addNewMainTeamMember(mainTeamMember);
	if(addedMainTeamMember==null) {
		responce.setMassege("failed to add the data");
		responce.setStatus(false);
		return responce;
	}
	responce.setMassege("member added successfully");
	responce.setStatus(true);
	
		return responce;
	}

	@Override
	public Responce deleteMainTeamMember(String memberId) {
		// TODO Auto-generated method stub
		return mainTeamMemberDaoInterface.deleteMainTeamMember(memberId);
	}

	@Override
	public Responce updateMainTeamMember(String memberId, HashMap<String, Object> memberData) {
		// TODO Auto-generated method stub
		return mainTeamMemberDaoInterface.updateMainTeamMember(memberId, memberData);
	}

	@Override
	public List<Map<String, Object>> getAllMainTeamMember() {
		// TODO Auto-generated method stub
		return mainTeamMemberDaoInterface.getAllMainTeamMember();
	}

	@Override
	public List<Map<String, Object>> getAllMainTeamMemberWithImages() {
		List<Map<String, Object>> alledMainTeamMember = mainTeamMemberDaoInterface.getAllMainTeamMember();
		return	alledMainTeamMember.stream().map(result->{
			result.put("MEMBER_IMAGES", mainTeamMemberImageServiceInterface.getAllMainTeamMemberImages(result.get("MEMBER_ID").toString()));	
			return result;
		}).collect(Collectors.toList());
	}

}

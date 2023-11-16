package com.juzzIt.EducationProject.Services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.juzzIt.EducationProject.DaoInterface.TeamMemberDaoInterface;
import com.juzzIt.EducationProject.DaoInterface.TeamMemberImageDaoInterface;
import com.juzzIt.EducationProject.Entity.TeamMember;
import com.juzzIt.EducationProject.Entity.TeamMemberImage;
import com.juzzIt.EducationProject.Models.Responce;
import com.juzzIt.EducationProject.ServiceInterface.TeamMemberImageServiceInterface;


@Service
public class TeamMemberImageService implements TeamMemberImageServiceInterface {
	
	
	@Autowired
	private TeamMemberImageDaoInterface teamMemberImageDaoInterface;
	
	@Autowired
	private TeamMemberDaoInterface teamMemberDaoInterface;
	
	@Override
	public Responce addTeamMemberImages(String teamMemberId, HashMap<String, Object> memberData) {
		Responce responce=new Responce();
		if(memberData.get("image_URL")==null) {
			responce.setMassege("");
			responce.setStatus(false);
            return responce;
		}
		TeamMember teamMember = teamMemberDaoInterface.getTeamMemberById(teamMemberId);
		if(teamMember==null) {
			responce.setMassege("member with given id not found");
			responce.setStatus(false);
            return responce;
		}
		TeamMemberImage teamMemberImage=new TeamMemberImage();
		UUID id=UUID.randomUUID();
		teamMemberImage.setActiveStatus("D");
		teamMemberImage.setImageId(id.toString());
		teamMemberImage.setImageURL(memberData.get("image_URL").toString());
		teamMemberImage.setTeamMember(teamMember);
		TeamMemberImage addedTeamMemberImage = teamMemberImageDaoInterface.addTeamMemberImage(teamMemberImage);
		if(addedTeamMemberImage==null) {
			responce.setMassege("failed to add the team member");
			responce.setStatus(false);
			return responce;
		}
		responce.setMassege("image aded successfully");
		responce.setStatus(true);
		
		return responce;
	}

	@Override
	public Responce deleteTeamMemberImages(String memberId) {
		return teamMemberImageDaoInterface.deleteTeamMemberImage(memberId);
	}

	@Override
	public Responce updateTeamMemberImages(String memberId, HashMap<String, Object> memberData) {
		return teamMemberImageDaoInterface.updateTeamMemberImage(memberId, memberData);
	}

	@Override
	public List<Map<String, Object>> getAllTeamMemberImages(String teamMemberId) {
		TeamMember teamMember = teamMemberDaoInterface.getTeamMemberById(teamMemberId);
		if(teamMember==null) {
            return new ArrayList<Map< String ,Object>>();
		}
		return teamMemberImageDaoInterface.getAllTeamMemberImage(teamMember);
	}

}

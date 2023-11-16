package com.juzzIt.EducationProject.ServiceInterface;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.juzzIt.EducationProject.Models.Responce;

public interface TeamMemberImageServiceInterface {

	
	public Responce addTeamMemberImages(String teamMemberId, HashMap<String, Object> memberData) ;
	public Responce deleteTeamMemberImages(String memberId) ;
	public Responce updateTeamMemberImages(String memberId,HashMap<String, Object> memberData) ;
	public List<Map<String, Object>> getAllTeamMemberImages(String teamMemberId);
}

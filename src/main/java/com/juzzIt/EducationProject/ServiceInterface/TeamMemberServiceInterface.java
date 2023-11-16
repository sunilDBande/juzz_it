package com.juzzIt.EducationProject.ServiceInterface;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.juzzIt.EducationProject.Models.Responce;

public interface TeamMemberServiceInterface {
	

	public Responce addTeamMember(HashMap<String, Object> memberData) ;
	
	public Responce deleteTeamMember(String memberId) ;
	
	public Responce updateTeamMember(String memberId,HashMap<String, Object> memberData) ;
	
	public List<Map<String, Object>> getAllTeamMember();
	
	public List<Map<String, Object>> getAllTeamMemberWithImages();


}

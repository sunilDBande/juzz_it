package com.juzzIt.EducationProject.ServiceInterface;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.juzzIt.EducationProject.Models.Responce;

public interface MainTeamMemberServiceInterface {

	public Responce addMainTeamMember(HashMap<String, Object> memberData) ;
	public Responce deleteMainTeamMember(String memberId) ;
	public Responce updateMainTeamMember(String memberId,HashMap<String, Object> memberData) ;
	public List<Map<String, Object>> getAllMainTeamMember();
	public List<Map<String, Object>> getAllMainTeamMemberWithImages();
	
}
	
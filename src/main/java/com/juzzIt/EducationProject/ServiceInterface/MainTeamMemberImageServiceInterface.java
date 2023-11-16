package com.juzzIt.EducationProject.ServiceInterface;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.juzzIt.EducationProject.Models.Responce;

public interface MainTeamMemberImageServiceInterface {
	public Responce addMainTeamMemberImages(String mainTeamMemberId, HashMap<String, Object> memberData) ;
	public Responce deleteMainTeamMemberImages(String memberId) ;
	public Responce updateMainTeamMemberImages(String memberId,HashMap<String, Object> memberData) ;
	public List<Map<String, Object>> getAllMainTeamMemberImages(String mainTeamMemberId);
}

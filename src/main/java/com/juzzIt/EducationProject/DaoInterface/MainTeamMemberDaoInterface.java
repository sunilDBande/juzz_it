package com.juzzIt.EducationProject.DaoInterface;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.juzzIt.EducationProject.Entity.MainTeamMember;
import com.juzzIt.EducationProject.Models.Responce;

public interface MainTeamMemberDaoInterface {
	
	public MainTeamMember addNewMainTeamMember(MainTeamMember mainTeamMember);
	public MainTeamMember getMainTeamMemberById(String mainTeamMemberId);
	public Responce deleteMainTeamMember(String mainTeamMemberId);
	public Responce updateMainTeamMember(String MainTeamMemberId,HashMap<String, Object> memberData);
	public List<Map<String, Object>> getAllMainTeamMember();

}

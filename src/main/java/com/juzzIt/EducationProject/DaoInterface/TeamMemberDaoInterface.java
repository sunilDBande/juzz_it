package com.juzzIt.EducationProject.DaoInterface;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.juzzIt.EducationProject.Entity.TeamMember;
import com.juzzIt.EducationProject.Models.Responce;

public interface TeamMemberDaoInterface {
	
	public TeamMember addTeamMember(TeamMember teamMember);
	public TeamMember getTeamMemberById(String teamMemberId);
	public Responce deleteTeamMember(String teamMemberId);
	public Responce updateTeamMember(String teamMemberId,HashMap<String, Object> teamMemberData);
	public List<Map<String ,Object>> getTeamMember();
	

}

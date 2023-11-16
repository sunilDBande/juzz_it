package com.juzzIt.EducationProject.DaoInterface;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.juzzIt.EducationProject.Entity.TeamMember;
import com.juzzIt.EducationProject.Entity.TeamMemberImage;
import com.juzzIt.EducationProject.Models.Responce;

public interface TeamMemberImageDaoInterface {
	
	
	public TeamMemberImage addTeamMemberImage(TeamMemberImage teamMemberImage);
	public TeamMemberImage getTeamMemberImageById(String teamMemberImageId);
	public Responce deleteTeamMemberImage(String teamMemberImageId);
	public Responce updateTeamMemberImage(String teamMemberImageId,HashMap<String, Object> teamMemberImageData);
	public List<Map<String, Object>> getAllTeamMemberImage(TeamMember teamMember);

}

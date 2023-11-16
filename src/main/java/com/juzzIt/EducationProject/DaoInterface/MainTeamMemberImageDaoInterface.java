package com.juzzIt.EducationProject.DaoInterface;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.juzzIt.EducationProject.Entity.MainTeamMember;
import com.juzzIt.EducationProject.Entity.MainTeamMemberImage;
import com.juzzIt.EducationProject.Models.Responce;

public interface MainTeamMemberImageDaoInterface {
	
	public MainTeamMemberImage addMainTeamMemberImage(MainTeamMemberImage mainTeamMemberImage);
	public MainTeamMemberImage getMainTeamMemberImageById(String mainTeamMemberImageId);
	public Responce deleteMainTeamMemberImage(String mainTeamMemberImageId);
	public Responce updateMainTeamMemberImage(String MainTeamMemberImageId ,HashMap<String, Object> mainTeamMemberImageData);
	public List<Map<String, Object>> getAllMainTeamMemberImage(MainTeamMember mainTeamMember);

}

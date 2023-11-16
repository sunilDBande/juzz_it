package com.juzzIt.EducationProject.Services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.juzzIt.EducationProject.DaoInterface.MainTeamMemberDaoInterface;
import com.juzzIt.EducationProject.DaoInterface.MainTeamMemberImageDaoInterface;
import com.juzzIt.EducationProject.Entity.MainTeamMember;
import com.juzzIt.EducationProject.Entity.MainTeamMemberImage;
import com.juzzIt.EducationProject.Models.Responce;
import com.juzzIt.EducationProject.ServiceInterface.MainTeamMemberImageServiceInterface;


@Service
public class MainTeamMemberImageService  implements MainTeamMemberImageServiceInterface{
	
	
	@Autowired
	private  MainTeamMemberImageDaoInterface mainTeamMemberImageDaoInterface;
	@Autowired
	private MainTeamMemberDaoInterface mainTeamMemberDaoInterface;

	@Override
	public Responce addMainTeamMemberImages(String mainTeamMemberId,HashMap<String, Object> memberData) {
		Responce responce=new Responce();
		if(memberData.get("IMAGE_URL")==null) {	
			responce.setMassege("image is required");
			responce.setStatus(false);
            return responce;
		}
		MainTeamMember mainTeamMember = mainTeamMemberDaoInterface.getMainTeamMemberById(mainTeamMemberId);
		if(mainTeamMember==null) {
			responce.setMassege("member with the given id not found");
			responce.setStatus(false);
			return responce; 
		}
		MainTeamMemberImage mainTeamMemberImage=new MainTeamMemberImage();
		UUID id=UUID.randomUUID();
		mainTeamMemberImage.setActiveStatus("D");
		mainTeamMemberImage.setImageId(id.toString());
		mainTeamMemberImage.setImageURL(memberData.get("IMAGE_URL").toString());
		mainTeamMemberImage.setMainTeamMember(mainTeamMember);
		MainTeamMemberImage addedMainTeamMemberImage = mainTeamMemberImageDaoInterface.addMainTeamMemberImage(mainTeamMemberImage);
		if(addedMainTeamMemberImage==null) {
			responce.setMassege("failed to add the image");
			responce.setStatus(false);
			return responce;
		}
		responce.setMassege("image added successfully");
		responce.setStatus(true);
		
		return responce;
	}

	@Override
	public Responce deleteMainTeamMemberImages(String memberId) {
		// TODO Auto-generated method stub
		return mainTeamMemberImageDaoInterface.deleteMainTeamMemberImage(memberId);
	}

	@Override
	public Responce updateMainTeamMemberImages(String memberId, HashMap<String, Object> memberData) {
		// TODO Auto-generated method stub
		return mainTeamMemberImageDaoInterface.updateMainTeamMemberImage(memberId, memberData);
	}

	@Override
	public List<Map<String, Object>> getAllMainTeamMemberImages(String mainTeamMemberId) {
		MainTeamMember mainTeamMember = mainTeamMemberDaoInterface.getMainTeamMemberById(mainTeamMemberId);
		if(mainTeamMember==null) {
			return new ArrayList<Map<String,Object>>(); 
		}
		return mainTeamMemberImageDaoInterface.getAllMainTeamMemberImage(mainTeamMember);
	}

	
}

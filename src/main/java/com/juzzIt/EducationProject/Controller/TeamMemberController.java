package com.juzzIt.EducationProject.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.juzzIt.EducationProject.Models.Responce;
import com.juzzIt.EducationProject.ServiceInterface.MainTeamMemberImageServiceInterface;
import com.juzzIt.EducationProject.ServiceInterface.MainTeamMemberServiceInterface;
import com.juzzIt.EducationProject.ServiceInterface.TeamMemberImageServiceInterface;
import com.juzzIt.EducationProject.ServiceInterface.TeamMemberServiceInterface;

@RestController
@RequestMapping("/tmr")
public class TeamMemberController {
	
	@Autowired
	private MainTeamMemberServiceInterface mainTeamMemberServiceInterface;
	
	@Autowired
	private MainTeamMemberImageServiceInterface mainTeamMemberImageServiceInterface;
	@Autowired
	private TeamMemberServiceInterface teamMemberServiceInterface;
	
	@Autowired
	private TeamMemberImageServiceInterface teamMemberImageServiceInterface;
	
	
	@PostMapping("/mainTeamMembers")
	public Responce addMainTeamMember(@RequestBody HashMap<String, Object> memberData) {
		return mainTeamMemberServiceInterface.addMainTeamMember(memberData);
	}
	
	
	@DeleteMapping("/mainTeamMember/{memberId}")
	public Responce deleteMainTeamMember(@PathVariable("memberId") String memberId) {
		return mainTeamMemberServiceInterface.deleteMainTeamMember(memberId);
	}
	
	
	@PutMapping("/mainTeamMember/{memberId}")
	public Responce updateMainTeamMember(@PathVariable("memberId") String memberId,@RequestBody HashMap<String, Object> memberData) {
		return mainTeamMemberServiceInterface.updateMainTeamMember(memberId, memberData);
	}
	
	@GetMapping("/mainTeamMembers")
	public List<Map<String, Object>> getAllMainTeamMember(){
		return mainTeamMemberServiceInterface.getAllMainTeamMember();
	}
	
	
	@GetMapping("/student/mainTeamMembers")
	public List<Map<String, Object>> getAllMainTeamMemberWithImages(){
		return mainTeamMemberServiceInterface.getAllMainTeamMemberWithImages();
	}
	
	
	///  main team member image
	
	@PostMapping("/mainTeamMembers/{mainTeamMemberId}/images")
	public Responce addMainTeamMemberImages(
	@PathVariable("mainTeamMemberId")	String mainTeamMemberId,@RequestBody	HashMap<String, Object> memberData) {
		return mainTeamMemberImageServiceInterface.addMainTeamMemberImages(mainTeamMemberId, memberData);
	}
	
	@DeleteMapping("/mainTeamMembers/images/{imageId}")
	public Responce deleteMainTeamMemberImages(@PathVariable("imageId") String imageId) {
		return mainTeamMemberImageServiceInterface.deleteMainTeamMemberImages(imageId);
	}
	
	@PutMapping("/mainTeamMembers/images/{imageId}")
	public Responce updateMainTeamMemberImages(@PathVariable("imageId") String imageId,@RequestBody HashMap<String, Object> memberData) {
		return mainTeamMemberImageServiceInterface.updateMainTeamMemberImages(imageId, memberData);
	}
	
	@GetMapping("/mainTeamMembers/{mainTeamMemberId}/images")
	public List<Map<String, Object>> getAllMainTeamMemberImages(@PathVariable("mainTeamMemberId") String mainTeamMemberId){
		return mainTeamMemberImageServiceInterface.getAllMainTeamMemberImages(mainTeamMemberId);
	}
	
	
	
	
	
	/// team member
	
	
	
	@PostMapping("/teamMembers")
	public Responce addTeamMember(@RequestBody HashMap<String, Object> memberData) {
		return teamMemberServiceInterface.addTeamMember(memberData);
	}
	
	@DeleteMapping("/teamMembers/{memberId}")
	public Responce deleteTeamMember(@PathVariable("memberId") String memberId) {
		return teamMemberServiceInterface.deleteTeamMember(memberId);
	}
	
	@PutMapping("/teamMembers/{memberId}")
	public Responce updateTeamMember(@PathVariable("memberId") String memberId,@RequestBody HashMap<String, Object> memberData) {
		return teamMemberServiceInterface.updateTeamMember(memberId, memberData);
	}
	
	@GetMapping("/teamMembers")
	public List<Map<String, Object>> getAllTeamMember(){
		return teamMemberServiceInterface.getAllTeamMember();
	}

	@GetMapping("/student/teamMembers")
	public List<Map<String, Object>> getAllTeamMemberWithImages(){
		return teamMemberServiceInterface.getAllTeamMemberWithImages();
	}
	
	
	/// team member image
	
	
	
	@PostMapping("/teamMembers/{teamMemberId}/images")
	public Responce addTeamMemberImages(@PathVariable("teamMemberId") String teamMemberId,@RequestBody HashMap<String, Object> memberData) {
		return teamMemberImageServiceInterface.addTeamMemberImages(teamMemberId, memberData);
	}
	
	
	@DeleteMapping("/teamMembers/images/{imageId}")
	public Responce deleteTeamMemberImages(@PathVariable("imageId") String imageId) {
		return teamMemberImageServiceInterface.deleteTeamMemberImages(imageId);
	}
	
	@PutMapping("/teamMembers/images/{imageId}")
	public Responce updateTeamMemberImages (@PathVariable("imageId") String imageId,@RequestBody HashMap<String, Object> memberData) {
		return teamMemberImageServiceInterface.updateTeamMemberImages(imageId, memberData);
	}
	
	@GetMapping("/teamMembers/{teamMemberId}/images")
	public List<Map<String, Object>> getAllTeamMemberImages(@PathVariable("teamMemberId") String teamMemberId){
		return teamMemberImageServiceInterface.getAllTeamMemberImages(teamMemberId);
	}
}

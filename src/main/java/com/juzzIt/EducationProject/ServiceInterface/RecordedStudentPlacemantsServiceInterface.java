package com.juzzIt.EducationProject.ServiceInterface;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.juzzIt.EducationProject.Models.Responce;

public interface RecordedStudentPlacemantsServiceInterface {

	   public Responce addRecodedStudentPlacements(  String recodedStudentId,HashMap<String, Object> placementData) ;
	   public Responce deleteRecodedStudentPlacements(String placmentId) ;
	   public Responce updateRecodedStudentPlacements(String placmentId,HashMap<String, Object> placmentData) ;
	   public List<Map<String, Object>>  getRecodedStudentPlacements(String recodedStudentId);
	   
}

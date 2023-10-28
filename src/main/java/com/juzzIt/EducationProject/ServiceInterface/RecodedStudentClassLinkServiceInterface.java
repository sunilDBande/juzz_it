package com.juzzIt.EducationProject.ServiceInterface;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.juzzIt.EducationProject.Models.Responce;

public interface RecodedStudentClassLinkServiceInterface {

	 public Responce addRecodedStudentCLassLinks(String recodedStudentId,HashMap<String, Object> classLinkData) ;
	   public Responce deleteRecodedStudentCLassLinks(String classLinkId) ;
	   public List<Map<String, Object>> getRecodedStudentCLassLinks(String recodedStudentId);
}

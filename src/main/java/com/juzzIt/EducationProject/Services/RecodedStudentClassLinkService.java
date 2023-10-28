package com.juzzIt.EducationProject.Services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.juzzIt.EducationProject.DaoInterface.RecodedStudentClassLinkDaoDaoInterface;
import com.juzzIt.EducationProject.DaoInterface.RecordedStudentDaoInterface;
import com.juzzIt.EducationProject.Entity.RecodedStudentClassLinks;
import com.juzzIt.EducationProject.Entity.RecordedStudent;
import com.juzzIt.EducationProject.Models.Responce;
import com.juzzIt.EducationProject.ServiceInterface.RecodedStudentClassLinkServiceInterface;


@Service
public class RecodedStudentClassLinkService implements RecodedStudentClassLinkServiceInterface {

	
	@Autowired
	private RecodedStudentClassLinkDaoDaoInterface recodedStudentClassLinkDaoInterface;
	

	@Autowired
	private RecordedStudentDaoInterface recordedStudentDaoInterface;
	
	@Override
	public Responce addRecodedStudentCLassLinks(String recodedStudentId, HashMap<String, Object> classLinkData) {
		Responce responce = new Responce();
	
		if (classLinkData.get("class_Title") == null || classLinkData.get("class_Date") == null
				|| classLinkData.get("class_time") == null || classLinkData.get("class_link") == null||classLinkData.get("subject_Name") == null) {
			responce.setMassege("title , date ,time and links are needed");
			responce.setStatus(false);
			return responce;
		}
		
		
		RecordedStudent recordedStudent = recordedStudentDaoInterface.getRecordedStudentById(recodedStudentId);
		
		
		if(recordedStudent==null) {
			responce.setMassege("student with the given id not found");
			responce.setStatus(false);
			return null;
		}
		RecodedStudentClassLinks RecodedStudentClasslinks=new RecodedStudentClassLinks();
		
		
		UUID id=UUID.randomUUID();
		RecodedStudentClasslinks.setClassDate((LocalDateTime)classLinkData.get("class_Date") );
		RecodedStudentClasslinks.setClassId(id.toString());
		RecodedStudentClasslinks.setClassLink( classLinkData.get("class_link").toString());
		RecodedStudentClasslinks.setClassTime(classLinkData.get("class_time").toString());
		RecodedStudentClasslinks.setClassTitle(classLinkData.get("class_Title").toString());
		RecodedStudentClasslinks.setRecordedStudent(recordedStudent);
		RecodedStudentClasslinks.setSubjectName(classLinkData.get("subject_Name").toString());
		
		RecodedStudentClassLinks addedRecodedStudentClassLinks = recodedStudentClassLinkDaoInterface.addRecodedStudentClassLinks(RecodedStudentClasslinks);
		
		if(addedRecodedStudentClassLinks==null) {
			responce.setMassege("failed to add the class link");
			responce.setStatus(false);
			return responce;
		}
		responce.setMassege("class link added successfully");
		responce.setStatus(true);
		return responce;
	}

	@Override
	public Responce deleteRecodedStudentCLassLinks(String classLinkId) {
		// TODO Auto-generated method stub
		return recodedStudentClassLinkDaoInterface.deleteClassLink(classLinkId);
	}

	@Override
	public List<Map<String, Object>> getRecodedStudentCLassLinks(String recodedStudentId) {
		
		RecordedStudent recordedStudent = recordedStudentDaoInterface.getRecordedStudentById(recodedStudentId);
		
		
		if(recordedStudent==null) {
			return new ArrayList<Map<String,Object>>();
		}
		return recodedStudentClassLinkDaoInterface.getAllRecodedClassLinks(recordedStudent);
	}

}

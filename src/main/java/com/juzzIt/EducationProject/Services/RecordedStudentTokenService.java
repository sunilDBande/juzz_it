package com.juzzIt.EducationProject.Services;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.juzzIt.EducationProject.DaoInterface.RecordedStudentDaoInterface;
import com.juzzIt.EducationProject.DaoInterface.RecordedStudentTokenDaoInterface;
import com.juzzIt.EducationProject.Entity.RecordedStudent;
import com.juzzIt.EducationProject.Entity.RecordedStudentToken;
import com.juzzIt.EducationProject.Models.Responce;
import com.juzzIt.EducationProject.ServiceInterface.RecordedStudentTokenServiceInterface;
@Service
public class RecordedStudentTokenService implements RecordedStudentTokenServiceInterface {

	@Autowired
	private RecordedStudentDaoInterface  recordedStudentDaoInterface;
	
	@Autowired
	private RecordedStudentTokenDaoInterface recordedStudentTokenDaoInterface;
	
	
	@Override
	public Responce addNewToken(String recordedStudentId, HashMap<String, Object> tokenData) {
	
		
		Responce responce=new Responce();
		
		if(tokenData.get("tokenName")==null||tokenData.get("tokenDesc")==null) {
			responce.setMassege("");
			responce.setStatus(false);
			return responce;
		}
		
		
		RecordedStudent recordedStudent = recordedStudentDaoInterface.getRecordedStudentById(recordedStudentId);
		
		if(recordedStudent==null) {
			responce.setMassege("");
			responce.setStatus(false);
			return responce;
		}
		
		RecordedStudentToken recordedStudentToken=new RecordedStudentToken();
		recordedStudentToken.setRecordedStudent(recordedStudent);
		recordedStudentToken.setActiveStatus("A");
		recordedStudentToken.setCreatedDateTime(LocalDateTime.now());
		recordedStudentToken.setTokenDesc(tokenData.get("tokenDesc").toString());
		recordedStudentToken.setTokenName(tokenData.get("tokenName").toString());
		
		RecordedStudentToken addedToken = recordedStudentTokenDaoInterface.addNewToken(recordedStudentToken);
		
		if(addedToken==null) {
			responce.setMassege("failed to create the token");
			responce.setStatus(false);
		}
		responce.setMassege("token added successfully");
		responce.setStatus(true);
		
		return responce;
	}

	@Override
	public Responce deleteToken(String tokanData) {
		// TODO Auto-generated method stub
		return recordedStudentTokenDaoInterface.deleteToken(tokanData);
	}

	@Override
	public Responce updateToken(String tokanId, HashMap<String, Object> tokenData) {
		// TODO Auto-generated method stub
		return recordedStudentTokenDaoInterface.updateToken(tokanId, tokenData);
	}

	@Override
	public List<Map<String, Object>> getAllTokensActive() {
		// TODO Auto-generated method stub
		return recordedStudentTokenDaoInterface.getAllTokensActive();
	}

	@Override
	public List<Map<String, Object>> getTokenByRecordedStudentId(String recordedStudentId) {
		// TODO Auto-generated method stub
		return recordedStudentTokenDaoInterface.getTokenByRecordedStudentId(null);
	}

	@Override
	public List<Map<String, Object>> getAllTokens() {
		// TODO Auto-generated method stub
		return recordedStudentTokenDaoInterface.getAllTokens();
	}

}

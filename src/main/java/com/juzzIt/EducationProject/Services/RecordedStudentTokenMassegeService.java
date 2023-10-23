package com.juzzIt.EducationProject.Services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.juzzIt.EducationProject.DaoInterface.AdminDaoInterface;
import com.juzzIt.EducationProject.DaoInterface.RecordedStudentTokenDaoInterface;
import com.juzzIt.EducationProject.DaoInterface.RecordedStudentTokenMassegeDaoInterface;
import com.juzzIt.EducationProject.DaoInterface.StudentDaoInterface;
import com.juzzIt.EducationProject.DaoInterface.TeacherDaoInterface;
import com.juzzIt.EducationProject.Entity.Admin;
import com.juzzIt.EducationProject.Entity.RecordedStudentToken;
import com.juzzIt.EducationProject.Entity.RecordedStudentTokenMassege;
import com.juzzIt.EducationProject.Entity.Student;
import com.juzzIt.EducationProject.Entity.Teacher;
import com.juzzIt.EducationProject.Models.Responce;
import com.juzzIt.EducationProject.Models.UseTypeEnum;
import com.juzzIt.EducationProject.ServiceInterface.RecordedStudentTokenMassegeInterface;


@Service
public class RecordedStudentTokenMassegeService  implements RecordedStudentTokenMassegeInterface{

	@Autowired
	private RecordedStudentTokenMassegeDaoInterface recordedStudentTokenMassegeDaoInterface;
	
	@Autowired
	private RecordedStudentTokenDaoInterface recordedStudentTokenDaoInterface;

	@Autowired
	private AdminDaoInterface adminDaoInterface;
	
	@Autowired
	private StudentDaoInterface studentDaoInterface;
	
	@Autowired
	private TeacherDaoInterface teacherDaoInterface;
	
	
	@Override
	public Responce addTokenMassege(String tokanId,String senderId, HashMap<String, Object> tokenMassegeData) {
		
		
		Responce responce=new Responce();
		RecordedStudentTokenMassege recordedStudentTokenMassege=new RecordedStudentTokenMassege();
		
		if(tokenMassegeData.get("sender_Type")==null||tokenMassegeData.get("massege")==null) {
			responce.setMassege("");
			responce.setStatus(false);
			return responce;
		}
		
		RecordedStudentToken recordedStudentToken = recordedStudentTokenDaoInterface.getRecordedStudentTokenById(tokanId);
		if(recordedStudentToken==null) {
			responce.setMassege("Token with the id not found");
			responce.setStatus(false);
			return responce;
		}
		
		
		
	
		 if(tokenMassegeData.get("sender_Type").toString().equalsIgnoreCase(UseTypeEnum.ADMIN.toString())) {
		
			Admin admin = adminDaoInterface.getAdminByAdminId(senderId);
			if(admin==null) {
				responce.setMassege("user with the given id not found");
				responce.setStatus(false);
				return responce;
			}
		
			recordedStudentTokenMassege.setSenderId(admin.getAdminId());
			recordedStudentTokenMassege.setSendername(admin.getAdminName());
			recordedStudentTokenMassege.setSenderType(admin.getRole());
		}else if(tokenMassegeData.get("sender_Type").toString().equalsIgnoreCase(UseTypeEnum.STUDENT.toString())) {
			Student student = studentDaoInterface.getStudentById(senderId);
			if(student==null) {
				responce.setMassege("user with the given id not found");
				responce.setStatus(false);
				return responce;
			}
			
			
			recordedStudentTokenMassege.setSenderId(student.getStudentId());
			recordedStudentTokenMassege.setSendername(student.getStudentName());
			recordedStudentTokenMassege.setSenderType(student.getRole());
		}else if(tokenMassegeData.get("sender_Type").toString().equalsIgnoreCase(UseTypeEnum.TRAINER.toString())) {
	
			Teacher teacher = teacherDaoInterface.getTeacherById(senderId);
     
			if(teacher==null) {
				responce.setMassege("user with the given id not found");
				responce.setStatus(false);
				return responce;
			}
			recordedStudentTokenMassege.setSenderId(teacher.getTeacherId());
			recordedStudentTokenMassege.setSendername(teacher.getTeacherName());
			recordedStudentTokenMassege.setSenderType(teacher.getRole());
		
		}else {
        	responce.setMassege("user with the type not found");
	        responce.setStatus(false);
	        return responce;
     }
		
		
		recordedStudentTokenMassege.setMassege(senderId);
		recordedStudentTokenMassege.setCreatedDateTime(LocalDateTime.now());
		recordedStudentTokenMassege.setRecordedStudentToken(recordedStudentToken);

		RecordedStudentTokenMassege addedTokenMassege = recordedStudentTokenMassegeDaoInterface.addTokenMassege(recordedStudentTokenMassege);
		if(addedTokenMassege==null) {
			responce.setMassege("failed add the massege");
			responce.setStatus(false);
			return responce;
		}
		responce.setMassege("massege added successfully");
		responce.setStatus(true);
		return responce;
	}

	@Override
	public Responce deleteTokenMassege(String tokanMassegeId) {
		// TODO Auto-generated method stub
		return recordedStudentTokenMassegeDaoInterface.deleteTokenMassege(tokanMassegeId);
	}

	@Override
	public Responce updateTokenMassege(String tokanMassegeId, HashMap<String, Object> tokenMassegeData) {
		// TODO Auto-generated method stub
		return recordedStudentTokenMassegeDaoInterface.updateTokenMassege(tokanMassegeId, tokenMassegeData);
	}

	@Override
	public List<Map<String, Object>> getAllTokenMassege(String tokenId) {
		RecordedStudentToken recordedStudentToken = recordedStudentTokenDaoInterface.getRecordedStudentTokenById(tokenId);
		if(recordedStudentToken==null) {
		return new ArrayList<Map<String,Object>>();
		}
		return recordedStudentTokenMassegeDaoInterface.getAllTokenMassege(recordedStudentToken);
	}
	
	
	
	
}

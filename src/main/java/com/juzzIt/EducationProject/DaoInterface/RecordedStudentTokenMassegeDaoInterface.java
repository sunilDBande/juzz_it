package com.juzzIt.EducationProject.DaoInterface;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.juzzIt.EducationProject.Entity.RecordedStudentToken;
import com.juzzIt.EducationProject.Entity.RecordedStudentTokenMassege;
import com.juzzIt.EducationProject.Models.Responce;

public interface RecordedStudentTokenMassegeDaoInterface {
	
	    public RecordedStudentTokenMassege addTokenMassege( RecordedStudentTokenMassege recordedStudentTokenMassege );
	    public RecordedStudentTokenMassege getRecordedStudentTokenMassegeById(String massegeId);
	    public Responce deleteTokenMassege( String tokanMassegeId);
	    public Responce updateTokenMassege( String tokanMassegeId ,HashMap<String, Object> tokenMassegeData ) ;
	    public List<Map<String , Object>> getAllTokenMassege(RecordedStudentToken recordedStudentToken);
}

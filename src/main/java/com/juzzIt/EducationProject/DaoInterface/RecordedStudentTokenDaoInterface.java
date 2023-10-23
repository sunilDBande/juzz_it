package com.juzzIt.EducationProject.DaoInterface;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.juzzIt.EducationProject.Entity.RecordedStudent;
import com.juzzIt.EducationProject.Entity.RecordedStudentToken;
import com.juzzIt.EducationProject.Models.Responce;

public interface RecordedStudentTokenDaoInterface {

    public RecordedStudentToken addNewToken( RecordedStudentToken recordedStudentToken);
    public RecordedStudentToken getRecordedStudentTokenById(String recoredStudentstudentId);
    public Responce deleteToken( String tokanId) ;
    public Responce updateToken( String tokanId,  HashMap<String, Object> tokenData);
    public List<Map<String , Object>> getAllTokensActive();
    public List<Map<String , Object>> getAllTokens();
    public List<Map<String, Object>> getTokenByRecordedStudentId( RecordedStudent recordedStudent);
}

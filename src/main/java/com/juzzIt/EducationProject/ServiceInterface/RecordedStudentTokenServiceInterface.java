package com.juzzIt.EducationProject.ServiceInterface;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.juzzIt.EducationProject.Models.Responce;


public interface RecordedStudentTokenServiceInterface {

    public Responce addNewToken( String recordedStudentId ,  HashMap<String, Object> tokenData);
    public Responce deleteToken( String tokanData) ;
    public Responce updateToken( String tokanId,  HashMap<String, Object> tokenData);
    public List<Map<String , Object>> getAllTokensActive();
    public List<Map<String , Object>> getAllTokens();
    public List<Map<String, Object>> getTokenByRecordedStudentId( String recordedStudentId);
    
}

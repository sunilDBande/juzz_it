package com.juzzIt.EducationProject.ServiceInterface;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.PathVariable;

import com.juzzIt.EducationProject.Models.Responce;

public interface RecordedStudentTokenMassegeInterface {

	
	  public Responce addTokenMassege( String tokanId,String senderId,HashMap<String, Object> tokenMassegeData );
	    public Responce deleteTokenMassege( String tokanMassegeId);
	    public Responce updateTokenMassege( String tokanMassegeId ,HashMap<String, Object> tokenMassegeData ) ;
	    public List<Map<String , Object>> getAllTokenMassege(String tokenId);
}

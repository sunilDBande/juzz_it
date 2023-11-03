package com.juzzIt.EducationProject.Services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.juzzIt.EducationProject.DaoInterface.RecordedStudentDaoInterface;
import com.juzzIt.EducationProject.DaoInterface.RecordedStudentPlacemantsDaoInterface;
import com.juzzIt.EducationProject.Entity.RecordedStudentBatch;
import com.juzzIt.EducationProject.Entity.RecordedStudentPlacemants;
import com.juzzIt.EducationProject.Models.Responce;
import com.juzzIt.EducationProject.ServiceInterface.RecordedStudentPlacemantsServiceInterface;


@Service
public class RecordedStudentPlacemantsService implements RecordedStudentPlacemantsServiceInterface {
@Autowired
private RecordedStudentPlacemantsDaoInterface recordedStudentPlacemantsDaoInterface;
@Autowired
private RecordedStudentDaoInterface recordedStudentDaoInterface;

	
	@Override
	public Responce addRecodedStudentPlacements(String recodedStudentId, HashMap<String, Object> placementData) {
		
		Responce responce=new Responce();
				if(placementData.get("company_name")==null||placementData.get("company_Intruduction")==null||placementData.get("apply_link")==null) {
			responce.setMassege("company name  , company overview and apply link is needed");
			responce.setStatus(false);
			return responce;
		}
				
		RecordedStudentBatch recordedStudent = recordedStudentDaoInterface.getRecordedStudentById(recodedStudentId);
				if(recordedStudent==null) {
					responce.setMassege("");
					responce.setStatus(false);
					return responce;
				}
				
				
				UUID id=UUID.randomUUID();	
				RecordedStudentPlacemants recordedStudentPlacemants=new RecordedStudentPlacemants();
				recordedStudentPlacemants.setActive_Placement("D");
				recordedStudentPlacemants.setApplyLink(placementData.get("apply_link").toString());
				recordedStudentPlacemants.setCompanyIntroduction(placementData.get("company_Intruduction").toString());
				recordedStudentPlacemants.setPlacementId(id.toString());
				recordedStudentPlacemants.setCompanyName(placementData.get("company_name").toString());
				recordedStudentPlacemants.setRecordedStudentBatch(recordedStudent);				
				RecordedStudentPlacemants addedNewRecordedStudentPlacemants = recordedStudentPlacemantsDaoInterface.addNewRecordedStudentPlacemants(recordedStudentPlacemants);
				if(addedNewRecordedStudentPlacemants==null) {
					responce.setMassege("failed to add the placement");
					responce.setStatus(false);
					return responce;
				}
				responce.setMassege("placment added successfully");
				responce.setStatus(true);
	         	return responce;
	}

	@Override
	public Responce deleteRecodedStudentPlacements(String placmentId) {
		return recordedStudentPlacemantsDaoInterface.deleteRecordedStudentPlacemants(placmentId);
	}

	@Override
	public Responce updateRecodedStudentPlacements(String placmentId, HashMap<String, Object> placmentData) {
		return recordedStudentPlacemantsDaoInterface.updateRecordedStudentPlacemants(placmentId, placmentData);
	}

	@Override
	public List<Map<String, Object>> getRecodedStudentPlacements(String recodedStudentId) {
		RecordedStudentBatch recordedStudent = recordedStudentDaoInterface.getRecordedStudentById(recodedStudentId);
		if(recordedStudent==null) {
		return new ArrayList<Map<String,Object>>();
		}
		
		return recordedStudentPlacemantsDaoInterface.getRecordedStudentPlacemants(recordedStudent);
	}

}

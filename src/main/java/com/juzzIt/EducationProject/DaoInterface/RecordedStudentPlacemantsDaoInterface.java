package com.juzzIt.EducationProject.DaoInterface;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.juzzIt.EducationProject.Entity.RecordedStudentBatch;
import com.juzzIt.EducationProject.Entity.RecordedStudentPlacemants;
import com.juzzIt.EducationProject.Models.Responce;

public interface RecordedStudentPlacemantsDaoInterface {
	public RecordedStudentPlacemants addNewRecordedStudentPlacemants(RecordedStudentPlacemants recordedStudentPlacemants);
	public RecordedStudentPlacemants getRecordedStudentPlacemantsById(String recordedStudentPlacemantsId);
	public Responce updateRecordedStudentPlacemants(String placementId,HashMap<String, Object> placementData);
	public Responce deleteRecordedStudentPlacemants(String placementId);
	public List<Map<String , Object>> getRecordedStudentPlacemants(RecordedStudentBatch  recordedStudentBatch);
}

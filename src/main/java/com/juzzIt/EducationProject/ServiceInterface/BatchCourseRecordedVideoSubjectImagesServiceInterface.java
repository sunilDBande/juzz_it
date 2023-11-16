package com.juzzIt.EducationProject.ServiceInterface;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


import com.juzzIt.EducationProject.Models.Responce;

public interface BatchCourseRecordedVideoSubjectImagesServiceInterface {

	
	public Responce addRecodedSubjectImage( String SubjectId, HashMap<String, Object> imageData);
	public Responce deleteRecodedSubjectImage(String imageId);
	public Responce updateRecodedSubjectImage( String imageId,HashMap<String, Object> imageData);
	public List<Map<String, Object>> getAllRecodedVideoImages( String subjectId);
}

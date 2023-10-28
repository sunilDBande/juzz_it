package com.juzzIt.EducationProject.ServiceInterface;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.juzzIt.EducationProject.Models.Responce;

public interface RecodedStudentPlacementImageServiceInterface {

	 
	   public Responce addPlacementImage(String placementId,HashMap<String, Object>  imageData) ;
	   public Responce deletePlacementImage(String imageId) ; 
	   public Responce updatePlacementImages(String imageId,HashMap<String, Object> imageData) ;
	   public List<Map<String, Object>> getAllPlacementImages(String placementId);
}

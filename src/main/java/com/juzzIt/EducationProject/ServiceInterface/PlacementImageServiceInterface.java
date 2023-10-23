package com.juzzIt.EducationProject.ServiceInterface;

import java.util.HashMap;
import java.util.List;
import java.util.Map;



import com.juzzIt.EducationProject.Models.Responce;

public interface PlacementImageServiceInterface {
	
	public Responce addNewPlacementImage(String placementId, HashMap<String, Object> placementImgaeData);
	public Responce deletePlacementImage( String placementimageId) ;
	public Responce updatePlacementImage( String placementImageId, HashMap<String, Object> placementImageData);
	public List<Map<String , Object>> getPlacementImate(String placementId);
}

package com.juzzIt.EducationProject.DaoInterface;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.juzzIt.EducationProject.Entity.BatchCoursePlacements;
import com.juzzIt.EducationProject.Entity.PlacementImage;
import com.juzzIt.EducationProject.Models.Responce;

public interface PlacementImageDaoInterface {
	public PlacementImage addNewPlacementImage(PlacementImage placementImage);
	public PlacementImage getPlacementImageById(String placementImageId);
	public Responce deletePlacementImage( String placementimageId) ;
	public Responce updatePlacementImage( String placementImageId, HashMap<String, Object> placementImageData);
	public List<Map<String , Object>> getPlacementImate(BatchCoursePlacements placement);
}

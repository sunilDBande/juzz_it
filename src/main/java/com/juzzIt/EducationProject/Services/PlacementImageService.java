package com.juzzIt.EducationProject.Services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.juzzIt.EducationProject.DaoInterface.BatchCoursePlacementsDaoInterface;
import com.juzzIt.EducationProject.DaoInterface.PlacementImageDaoInterface;
import com.juzzIt.EducationProject.Entity.BatchCoursePlacements;
import com.juzzIt.EducationProject.Entity.PlacementImage;
import com.juzzIt.EducationProject.Models.Responce;
import com.juzzIt.EducationProject.ServiceInterface.PlacementImageServiceInterface;
@Service
public class PlacementImageService  implements PlacementImageServiceInterface {

	@Autowired
	private PlacementImageDaoInterface placementImageDaoInterface;
	@Autowired
	private BatchCoursePlacementsDaoInterface batchCoursePlacementsDaoInterface;
	
	
	@Override
	public Responce addNewPlacementImage(String placementId, HashMap<String, Object> placementImgaeData) {
		
		Responce responce=new Responce();
		
		if(placementImgaeData.get("image_URL")==null) {
			responce.setMassege("image is rquired");
			responce.setStatus(false);
			return responce;
		}
		
		
		BatchCoursePlacements batchCoursePlacement = batchCoursePlacementsDaoInterface.getBatchCoursePlacementsById(placementId);
		
	if(batchCoursePlacement==null) {
		responce.setMassege("batchCoursePlacement with the given id not found");
		responce.setStatus(false);
		return responce;
	}
	
	PlacementImage placementImage=new PlacementImage();
		
	placementImage.setBatchCoursePlacements(batchCoursePlacement);
	placementImage.setActiveStatus("D");
	placementImage.setCreatedDate(LocalDateTime.now());
	placementImage.setImageURL(placementImgaeData.get("image_URL").toString());
	placementImage.setPalcementImageId(placementImgaeData.get("id").toString());	
	PlacementImage addedPlacementImage = placementImageDaoInterface.addNewPlacementImage(placementImage);
	
	if(addedPlacementImage==null) {
		responce.setMassege("batchCoursePlacement with the given id not found");
		responce.setStatus(false);
		return responce;
	}
	
	responce.setMassege("image added successfully");
	responce.setStatus(true);
		return responce;
	}

	@Override
	public Responce deletePlacementImage(String placementimageId) {
		// TODO Auto-generated method stub
		return placementImageDaoInterface.deletePlacementImage(placementimageId);
	}

	@Override
	public Responce updatePlacementImage(String placementImageId, HashMap<String, Object> placementImageData) {
		// TODO Auto-generated method stub
		return placementImageDaoInterface.updatePlacementImage(placementImageId, placementImageData);
	}

	@Override
	public List<Map<String, Object>> getPlacementImate(String placementId) {
		BatchCoursePlacements batchCoursePlacement = batchCoursePlacementsDaoInterface.getBatchCoursePlacementsById(placementId);
		if(batchCoursePlacement==null) {	
			return new ArrayList<Map<String,Object>>();
		}
		return placementImageDaoInterface.getPlacementImate(batchCoursePlacement);
	}

}

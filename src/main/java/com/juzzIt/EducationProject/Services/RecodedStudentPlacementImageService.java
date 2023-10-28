package com.juzzIt.EducationProject.Services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.juzzIt.EducationProject.DaoInterface.RecodedStudentPlacementImageDaoInterface;
import com.juzzIt.EducationProject.DaoInterface.RecordedStudentPlacemantsDaoInterface;
import com.juzzIt.EducationProject.Entity.RecodedStudentPlacementImage;
import com.juzzIt.EducationProject.Entity.RecordedStudentPlacemants;
import com.juzzIt.EducationProject.Models.Responce;
import com.juzzIt.EducationProject.ServiceInterface.RecodedStudentPlacementImageServiceInterface;


@Service
public class RecodedStudentPlacementImageService implements RecodedStudentPlacementImageServiceInterface {

	@Autowired
	private RecodedStudentPlacementImageDaoInterface recodedStudentPlacementImageDaoInterface;
	
	@Autowired
	private RecordedStudentPlacemantsDaoInterface recordedStudentPlacemantsDaoInterface;
	
	
	@Override
	public Responce addPlacementImage(String placementId, HashMap<String, Object> imageData) {
		
		
		Responce responce =new Responce();
		if(imageData.get("image_URL")==null) {
			responce.setMassege("image id requied");
			responce.setStatus(false);
			return responce;
		}
		
		RecordedStudentPlacemants recordedStudentPlacemants = recordedStudentPlacemantsDaoInterface.getRecordedStudentPlacemantsById(placementId);
		
		
if(recordedStudentPlacemants==null) {
	responce.setMassege("student with the given id not found");
	responce.setStatus(false);
     return responce;
}
RecodedStudentPlacementImage recodedStudentPlacementImage=new RecodedStudentPlacementImage();
UUID id=UUID.randomUUID();		

recodedStudentPlacementImage.setImageId(id.toString());
recodedStudentPlacementImage.setImageURL(imageData.get("image_URL").toString());
recodedStudentPlacementImage.setActiveStatus("D");
recodedStudentPlacementImage.setRecordedStudentPlacemants(recordedStudentPlacemants);

RecodedStudentPlacementImage addedRecodedStudentPlacementImage = recodedStudentPlacementImageDaoInterface.addRecodedStudentPlacementImage(recodedStudentPlacementImage);

if(addedRecodedStudentPlacementImage==null) {
	responce.setMassege("failed to add the image");
	responce.setStatus(false);
	return responce;
}

responce.setMassege("image added successfully");
responce.setStatus(true);
		return responce;
	}

	@Override
	public Responce deletePlacementImage(String imageId) {
		// TODO Auto-generated method stub
		return recodedStudentPlacementImageDaoInterface.deleteRecodedStudentPlacementImage(imageId);
	}

	@Override
	public Responce updatePlacementImages(String imageId, HashMap<String, Object> imageData) {
		// TODO Auto-generated method stub
		return recodedStudentPlacementImageDaoInterface.updateRecodedStudentPlacementImage(imageId, imageData);
	}

	@Override
	public List<Map<String, Object>> getAllPlacementImages(String placementId) {
		
		RecordedStudentPlacemants recordedStudentPlacemants = recordedStudentPlacemantsDaoInterface.getRecordedStudentPlacemantsById(placementId);
		
		
if(recordedStudentPlacemants==null) {
return new ArrayList<Map<String,Object>>();
}
		return recodedStudentPlacementImageDaoInterface.getRecodedStudentPlacementImage(recordedStudentPlacemants);
	}

	
}

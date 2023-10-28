package com.juzzIt.EducationProject.DaoInterface;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.juzzIt.EducationProject.Entity.RecodedStudentPlacementImage;
import com.juzzIt.EducationProject.Entity.RecordedStudentPlacemants;
import com.juzzIt.EducationProject.Models.Responce;

public interface RecodedStudentPlacementImageDaoInterface {
  public RecodedStudentPlacementImage addRecodedStudentPlacementImage(RecodedStudentPlacementImage recodedStudentPlacementImage);
  public RecodedStudentPlacementImage getRecodedStudentPlacementImageById(String imageId);
  public Responce deleteRecodedStudentPlacementImage(String imageId);
  public Responce updateRecodedStudentPlacementImage(String imageId,HashMap<String, Object> imageData);
  public List<Map<String, Object>> getRecodedStudentPlacementImage(RecordedStudentPlacemants RecordedStudentPlacemants);
  
  
}

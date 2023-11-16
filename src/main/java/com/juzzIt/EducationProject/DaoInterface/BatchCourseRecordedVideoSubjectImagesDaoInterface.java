package com.juzzIt.EducationProject.DaoInterface;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.juzzIt.EducationProject.Entity.BatchCourseRecordedVideoSubject;
import com.juzzIt.EducationProject.Entity.BatchCourseRecordedVideoSubjectImages;
import com.juzzIt.EducationProject.Models.Responce;

public interface BatchCourseRecordedVideoSubjectImagesDaoInterface {

	public BatchCourseRecordedVideoSubjectImages addBatchCourseRecordedVideoSubjectImages(BatchCourseRecordedVideoSubjectImages batchCourseRecordedVideoSubjectImages);
public BatchCourseRecordedVideoSubjectImages getBatchCourseRecordedVideoSubjectImagesById(String imageId);
public Responce deleteBatchCourseRecordedVideoSubjectImages(String imageId);
public Responce updateBatchCourseRecordedVideoSubjectImages(String imageId,HashMap<String , Object> imageData);
public List<Map<String, Object>> getAllSubjectImagesBySubject(BatchCourseRecordedVideoSubject batchCourseRecordedVideoSubject);

}

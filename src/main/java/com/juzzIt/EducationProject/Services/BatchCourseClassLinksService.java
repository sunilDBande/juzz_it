package com.juzzIt.EducationProject.Services;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.juzzIt.EducationProject.Dao.EntityDao;
import com.juzzIt.EducationProject.DaoInterface.BatchCourseClassLinksDaoInterface;
import com.juzzIt.EducationProject.DaoInterface.BatchCourseDaoInterface;
import com.juzzIt.EducationProject.DaoInterface.BatchCourseSubjectDaoInterface;
import com.juzzIt.EducationProject.Entity.BatchCourse;
import com.juzzIt.EducationProject.Entity.BatchCourseClassLinks;
import com.juzzIt.EducationProject.Entity.BatchCourseSubject;
import com.juzzIt.EducationProject.Models.Responce;
import com.juzzIt.EducationProject.Models.UniqueIdGenerations;
import com.juzzIt.EducationProject.ServiceInterface.BatchCourseClassLinksServiceServicesInterface;

@Service
public class BatchCourseClassLinksService implements BatchCourseClassLinksServiceServicesInterface {

	@Autowired
	private BatchCourseDaoInterface batchCourseDaoInterface;
	@Autowired
	private BatchCourseSubjectDaoInterface batchCourseSubjectDaoInterface;
	@Autowired
	private BatchCourseClassLinksDaoInterface batchCourseClassLinksDaoInterface;
	
	@Autowired
	private UniqueIdGenerations uniqueIdGenerations;

	@Autowired
	private EntityDao entityDao;

	@Override
	public Responce addnewClassLink(String batchCourseId, String batchCourseSubjectId,
			HashMap<String, Object> classLinkData) throws Exception {
		System.out.println();
		Responce responce = new Responce();
		try {
		if (classLinkData.get("class_Title") == null || classLinkData.get("class_Date") == null
				|| classLinkData.get("class_time") == null || classLinkData.get("class_link") == null) {
			responce.setMassege("title , date ,time and links are needed");
			responce.setStatus(false);
			return responce;

		}

		BatchCourse batchCourse = batchCourseDaoInterface.getBatchCourseById(batchCourseId);

		if (batchCourse == null) {
			responce.setMassege("batch vourse with the given id not found");
			responce.setStatus(false);
			return responce;
		}

		BatchCourseSubject batchCourseSubject = batchCourseSubjectDaoInterface
				.getbatchCourseSubjectById(batchCourseSubjectId);

		if (batchCourseSubject == null) {
			responce.setMassege("batch course subject with the geven id not found");
			responce.setStatus(false);
			return responce;
		}

		BatchCourseClassLinks newClassLink = new BatchCourseClassLinks();

		newClassLink.setBatchCourse(batchCourse);
		newClassLink.setBatchCourseSubject(batchCourseSubject);

		HashMap<String, Object> data = new HashMap<String, Object>();
		data.put("Entity_Name", "BatchCourseClassLinks");
		data.put("Entity_SubName", "CLSLINK");
		data.put("Entity_Count", 0);
		HashMap<String, Object> uniqueIdGeneration = uniqueIdGenerations.uniqueIdGeneration(data);
		// *******
		System.out.println("IMP---> " + uniqueIdGeneration);
		System.out.println("IMP3---> " + uniqueIdGeneration.get("count"));
		String subName = (String) uniqueIdGeneration.get("Entity_SubName");
		long count = (long) uniqueIdGeneration.get("count");
		if (count < 10) {
			newClassLink.setClassId(subName + "00" + count);
		} else if (count < 100 && count > 10) {
			newClassLink.setClassId(subName + "0" + count);
		} else {
			newClassLink.setClassId(subName + "" + count);
		}
		// *******

		newClassLink.setClassTitle(classLinkData.get("class_Title").toString());
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
		LocalDateTime classData = LocalDateTime.parse(classLinkData.get("class_Date").toString(), formatter);

		newClassLink.setClassDate(classData);
		newClassLink.setClassTime(classLinkData.get("class_time").toString());
		newClassLink.setClassLink(classLinkData.get("class_link").toString());
		newClassLink.setSubjectName(batchCourseSubject.getBatchCourseSubjectName());
		newClassLink.setTeacherName(batchCourseSubject.getTeacher().getTeacherName());

		 BatchCourseClassLinks addedNewClassLink = batchCourseClassLinksDaoInterface.addNewClassLink(newClassLink);

		if (addedNewClassLink == null) {
			responce.setMassege("failed to add the class link");
			responce.setStatus(false);
			return responce;
		}else {
			entityDao.updateCountForCourseTable(data);
		}

		responce.setMassege("class link added successfully");
		responce.setStatus(true);
		}catch (Exception e) {
			throw new Exception("Getting problem while adding new ClassLink");
		}
		return responce;
	}

	@Override
	public Responce deleteClassLink(String classId) throws Exception {
		// TODO Auto-generated method stub
		return batchCourseClassLinksDaoInterface.deleteClassLink(classId);
	}

	@Override
	public List<Map<String, Object>> getAllClassLinksByBatchCourseId(String batchCourseId) throws Exception {
		
		
		BatchCourse batchCourse = batchCourseDaoInterface.getBatchCourseById(batchCourseId);
		
		if(batchCourse==null) {
			return null;
		}
	
		return batchCourseClassLinksDaoInterface.getAllClassLinksByBatchCourseId(batchCourse);
	}

	@Override
	public List<Map<String, Object>> getAllClassLinksByBatchCourseIdAndBatchCoourseSubjectId(String batchCourseId,
			String batchCourseSubjectId) throws Exception {
//		 batchCourseDaoInterface.getBatchCourseById(batchCourseId);
		System.out.println("batchCourseId--> "+batchCourseId);
		System.out.println("batchCourseSubjectId--> "+batchCourseSubjectId);
		BatchCourse batchCourse = batchCourseDaoInterface.getBatchCourseById(batchCourseId);
		

		if(batchCourse==null) {
			System.out.println("batchCourse--> "+batchCourse.getBatchCourseId());
			return null;
		}
		
		BatchCourseSubject batchCourseSubject = batchCourseSubjectDaoInterface.getbatchCourseSubjectById(batchCourseSubjectId);
		
		
		if(batchCourseSubject==null) {
			System.out.println("batchCourseSubject--> "+batchCourseSubject.getBatchCourseSubjectId());
			return null;
		}
		
		return batchCourseClassLinksDaoInterface.getAllClassLinksByBatchCourseIdAndBatchCoourseSubjectId(batchCourse, batchCourseSubject);
	}

}

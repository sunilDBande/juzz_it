package com.juzzIt.EducationProject.Services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.juzzIt.EducationProject.Dao.EntityDao;
import com.juzzIt.EducationProject.DaoInterface.CourseTypeDaoInterface;
import com.juzzIt.EducationProject.DaoInterface.CourseTypeKeyHighlightsDaoInterface;
import com.juzzIt.EducationProject.Entity.CourseType;
import com.juzzIt.EducationProject.Entity.CourseTypeKeyHighlights;
import com.juzzIt.EducationProject.Models.Responce;
import com.juzzIt.EducationProject.Models.UniqueIdGenerations;
import com.juzzIt.EducationProject.ServiceInterface.CourseTypeKeyHighlightsServiceInterface;

@Service
public class CourseTypeKeyHighlightsServiceImplementation implements CourseTypeKeyHighlightsServiceInterface {

	@Autowired
	private CourseTypeDaoInterface courseTypeDaoInterface;

	@Autowired
	private CourseTypeKeyHighlightsDaoInterface courseTypeKeyHighlightsDaoInterface;

	@Autowired
	private UniqueIdGenerations uniqueIdGenerations;

	@Autowired
	private EntityDao entityDao;

	@Override

	public Responce addKeyHighlight(String courseTypeId, HashMap<String, Object> KeyHighlight) throws Exception {

		Responce responce = new Responce();
     try {
		if (KeyHighlight.get("highlight") == null) {
			responce.setMassege("key highlight need");
			responce.setStatus(false);
			return responce;
		}

		CourseType courseType = courseTypeDaoInterface.getCourseTypeById(courseTypeId);

		if (courseType == null) {
			responce.setMassege("courseType with the given id not found");
			responce.setStatus(false);
			return responce;
		}

		CourseTypeKeyHighlights highlight = new CourseTypeKeyHighlights();

		highlight.setCourseType(courseType);
		highlight.setHighlight(KeyHighlight.get("highlight").toString());

		HashMap<String, Object> data = new HashMap<String, Object>();
		data.put("Entity_Name", "CourseTypeKeyHighlights");
		data.put("Entity_SubName", "CTKEYHIGH");
		data.put("Entity_Count", 0);
		HashMap<String, Object> uniqueIdGeneration = uniqueIdGenerations.uniqueIdGeneration(data);
		// *******
		String subName = (String) uniqueIdGeneration.get("Entity_SubName");
		long count = (long) uniqueIdGeneration.get("count");
		if (count < 10) {
			highlight.setHighlightId(subName + "00" + count);
		} else if (count < 100 && count > 10) {
			highlight.setHighlightId(subName + "0" + count);
		} else {
			highlight.setHighlightId(subName + "" + count);
		}
		// *******

		CourseTypeKeyHighlights addedKeyHighlight = courseTypeKeyHighlightsDaoInterface.addKeyHighlight(highlight);

		if (addedKeyHighlight == null) {
			responce.setMassege("failed to add the details");
			responce.setStatus(false);
			return responce;
		} else {
			entityDao.updateCountForCourseTable(data);
		}
		responce.setMassege("key highlight added successfully");
		responce.setStatus(true);
     }catch (Exception e) {
		throw new Exception("Getting problem while adding KeyHighlight");
	}
		return responce;

	}

	@Override
	public Responce deleteKeyHighlight(String keyHighlightId) throws Exception {

		return courseTypeKeyHighlightsDaoInterface.deleteKeyHighlight(keyHighlightId);
	}

	@Override
	public List<Map<String, Object>> getAllKeyHighlight(String courseTypeId) throws Exception {
		// TODO Auto-generated method stub
		return courseTypeKeyHighlightsDaoInterface.getAllKeyHighlightByCourseTypeId(courseTypeId);
	}

}

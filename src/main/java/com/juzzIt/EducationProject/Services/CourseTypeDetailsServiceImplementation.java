package com.juzzIt.EducationProject.Services;

import java.util.HashMap;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.juzzIt.EducationProject.Dao.EntityDao;
import com.juzzIt.EducationProject.DaoInterface.CourseTypeDaoInterface;
import com.juzzIt.EducationProject.DaoInterface.CourseTypeDetailsDaoInterface;
import com.juzzIt.EducationProject.Entity.CourseType;
import com.juzzIt.EducationProject.Entity.CourseTypeDetails;
import com.juzzIt.EducationProject.Models.Responce;
import com.juzzIt.EducationProject.Models.UniqueIdGenerations;
import com.juzzIt.EducationProject.ServiceInterface.CourseTypeDetailsServiceInterface;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

@Service
public class CourseTypeDetailsServiceImplementation implements CourseTypeDetailsServiceInterface {

	@Autowired
	private CourseTypeDetailsDaoInterface courseTypeDetailsDaoInterface;

	@Autowired
	private CourseTypeDaoInterface courseTypeDaoInterface;

	@Autowired
	private EntityManager entityManager;

	@Autowired
	private Responce responce;

	@Autowired
	private UniqueIdGenerations uniqueIdGenerations;

	@Autowired
	private EntityDao entityDao;

	@Override
	public Responce addCourseTypeDetails(String courseTypeId, HashMap<String, Object> courseTypeDetails) throws Exception {

		Responce responce = new Responce();
		CourseType courseType = courseTypeDaoInterface.getCourseTypeById(courseTypeId);
try {
		if (courseType == null) {
			responce.setMassege("course type for the given id not found");
			responce.setStatus(false);
			return responce;
		}

		CourseTypeDetails courseDetails = new CourseTypeDetails();
		HashMap<String, Object> data = new HashMap<String, Object>();
		data.put("Entity_Name", "CourseTypeDetails");
		data.put("Entity_SubName", "CURTYPDET");
		data.put("Entity_Count", 0);
		HashMap<String, Object> uniqueIdGeneration = uniqueIdGenerations.uniqueIdGeneration(data);
		// *******
		System.out.println("IMP---> " + uniqueIdGeneration);
		System.out.println("IMP3---> " + uniqueIdGeneration.get("count"));
		String subName = (String) uniqueIdGeneration.get("Entity_SubName");
		long count = (long) uniqueIdGeneration.get("count");
		if (count < 10) {
			courseDetails.setDetailId(subName + "00" + count);
		} else if (count < 100 && count > 10) {
			courseDetails.setDetailId(subName + "0" + count);
		} else {
			courseDetails.setDetailId(subName + "" + count);
		}
		// *******
		courseDetails.setCourseType(courseType);

		if (courseTypeDetails.get("actual_Price") != null) {
			courseDetails.setActualPrice((int) courseTypeDetails.get("actual_Price"));
		}

		if (courseTypeDetails.get("discount_Price") != null) {
			courseDetails.setDiscountPrice((int) courseTypeDetails.get("discount_Price"));
		}
		if (courseTypeDetails.get("course_Desc") != null) {
			courseDetails.setCourseDesc(courseTypeDetails.get("course_Desc").toString());
		} else {
			courseDetails.setCourseDesc("");
		}
		if (courseTypeDetails.get("course_Overview") != null) {
			courseDetails.setCourseOverview(courseTypeDetails.get("course_Overview").toString());
		} else {
			courseDetails.setCourseOverview("");
		}
		if (courseTypeDetails.get("course_Duriation_Desc") != null) {
			courseDetails.setCourseDuriationDesc(courseTypeDetails.get("course_Duriation_Desc").toString());
		} else {
			courseDetails.setCourseDuriationDesc("");
		}

		CourseTypeDetails addedCourseTypeDetails = courseTypeDetailsDaoInterface.addCourseTypeDetails(courseDetails);

		if (addedCourseTypeDetails == null) {
			responce.setMassege("failed to add  the data to database");
			responce.setStatus(false);
			return responce;
		}else {
			 entityDao.updateCountForCourseTable(data);		
		}

		responce.setMassege("course type details added successfully");
		responce.setStatus(true);
}catch (Exception e) {
	throw new Exception("Getting problem while adding CourseTypeDetails");
}
		return responce;
	}

	@Override
	public List<HashMap<String, Object>> getCourseTypeDetails(String courseTypeId) throws Exception {
		// TODO Auto-generated method stub
		return courseTypeDetailsDaoInterface.getCourseTypeDetails(courseTypeId);
	}

	public Responce updateCourseDetails(String courseDetailId, HashMap<String, Object> coursetypeDetails) throws Exception {
		try {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		System.out.println("coursetypeDetails--> " + coursetypeDetails);
		CriteriaQuery<CourseTypeDetails> createQuery = criteriaBuilder.createQuery(CourseTypeDetails.class);
		Root<CourseTypeDetails> root = createQuery.from(CourseTypeDetails.class);
		Predicate predicate = null;
		if (courseDetailId != null) {
			predicate = criteriaBuilder.equal(root.get("detailId"), courseDetailId);
		}
		createQuery.select(root).where(predicate);
		List<CourseTypeDetails> resultList = entityManager.createQuery(createQuery).getResultList();

		if (!resultList.isEmpty()) {
			CourseTypeDetails courseTypeDetailsObj = resultList.get(0);
			if (courseTypeDetailsObj == null) {
				responce.setStatus(false);
				responce.setMassege("Update Not Possible for provided course detail Id!!!......");
			} else {
				if (coursetypeDetails.get("actual_Price") != null) {
					courseTypeDetailsObj.setActualPrice((int) coursetypeDetails.get("actual_Price"));
				}
				if (coursetypeDetails.get("course_Desc") != null) {
					courseTypeDetailsObj.setCourseDesc((String) coursetypeDetails.get("course_Desc"));
				}
				if (coursetypeDetails.get("course_Duriation_Desc") != null) {
					courseTypeDetailsObj
							.setCourseDuriationDesc((String) coursetypeDetails.get("course_Duriation_Desc"));
				}
				if (coursetypeDetails.get("course_Overview") != null) {
					courseTypeDetailsObj.setCourseOverview((String) coursetypeDetails.get("course_Overview"));
				}
				if (coursetypeDetails.get("discount_Price") != null) {
					courseTypeDetailsObj.setDiscountPrice((int) coursetypeDetails.get("discount_Price"));
				}
//				return entityManager.merge(courseTypeDetailsObj);
				courseTypeDetailsDaoInterface.addCourseTypeDetails(courseTypeDetailsObj);
				responce.setStatus(true);
				responce.setMassege("Course details type is Update Successfully!!!......");
			}
		} else {
			responce.setStatus(false);
			responce.setMassege("Update Not Possible for provided course detail Id!!!......");
		}
		}catch (Exception e) {
			throw new Exception("Getting problem while updating CourseDetails");
		}
		return responce;

	}

}

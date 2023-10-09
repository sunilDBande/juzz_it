package com.juzzIt.EducationProject.Services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.juzzIt.EducationProject.Dao.EntityDao;
import com.juzzIt.EducationProject.DaoInterface.BatchCourseDaoInterface;
import com.juzzIt.EducationProject.DaoInterface.CourseTypeDaoInterface;
import com.juzzIt.EducationProject.DaoInterface.SubBatchDaoInterface;
import com.juzzIt.EducationProject.Entity.BatchCourse;
import com.juzzIt.EducationProject.Entity.CourseType;
import com.juzzIt.EducationProject.Entity.SubBatch;
import com.juzzIt.EducationProject.Models.Responce;
import com.juzzIt.EducationProject.Models.UniqueIdGenerations;
import com.juzzIt.EducationProject.ServiceInterface.BatchCourseServiceInterface;

import jakarta.transaction.Transactional;

@Service
public class BatchCourseServiceImplementation implements BatchCourseServiceInterface {

	@Autowired
	private SubBatchDaoInterface subBatchDaoInterface;

	private CourseTypeDaoInterface courseTypeDaoInterface;

    @Autowired
    public BatchCourseServiceImplementation(CourseTypeDaoInterface courseTypeDaoInterface) {
        this.courseTypeDaoInterface = courseTypeDaoInterface;
    }
	@Autowired
	private BatchCourseDaoInterface batchCourseDaoInterface;

	@Autowired
	private UniqueIdGenerations uniqueIdGenerations;

	@Autowired
	private EntityDao entityDao;

	@Override
	public Responce addBatchCourse(String subBatchId, String courseTypeId, HashMap<String, Object> batchCourse) throws Exception {
		Responce responce = new Responce();
		try {
System.out.println("batchCourse--> "+batchCourse);
		if (batchCourse.get("batch_CourseName") == null || batchCourse.get("courseName") == null) {

			responce.setMassege("batch course name and course name is required");
			responce.setStatus(false);
			return responce;
		}

		SubBatch subBatch = subBatchDaoInterface.getSubBatchById(subBatchId);

		if (subBatch == null) {
			responce.setMassege("sub batch with the given id not found");
			responce.setStatus(false);
			return responce;
		}

//		if (batchCourseDaoInterface.existsByBatchCourseNameAndSubBatch(batchCourse.get("batch_CourseName").toString(),
//				subBatch)) {
//			responce.setMassege("batch Course for this sub Batch is alrady created");
//			responce.setStatus(false);
//			return responce;
//		}

		CourseType courseType = courseTypeDaoInterface.getCourseTypeById(courseTypeId);

		if (courseType == null) {
			responce.setMassege("course type with the given id not found");
			responce.setStatus(false);
			return responce;
		}

		BatchCourse newBatchCourse = new BatchCourse();
		newBatchCourse.setSubBatch(subBatch);
		newBatchCourse.setCourseType(courseType);

		HashMap<String, Object> data = new HashMap<String, Object>();
		data.put("Entity_Name", "BatchCourse");
		data.put("Entity_SubName", "BTHCOUR");
		HashMap<String, Object> uniqueIdGeneration = uniqueIdGenerations.uniqueIdGeneration(data);
// *******
		String subName = (String) uniqueIdGeneration.get("Entity_SubName");
		long count = (long) uniqueIdGeneration.get("count");
		if (count < 10) {
			newBatchCourse.setBatchCourseId(subName + "00" + count);
		} else if (count < 100 && count > 10) {
			newBatchCourse.setBatchCourseId(subName + "0" + count);
		} else {
			newBatchCourse.setBatchCourseId(subName + "" + count);
		}
// *******

		newBatchCourse.setBatchCourseName((String)batchCourse.get("batch_CourseName") );
		newBatchCourse.setCourseName((String)batchCourse.get("courseName"));
		newBatchCourse.setActive_batchCourse("D");
		newBatchCourse.setAdmitionStatus("D");
		newBatchCourse.setTemp_delete("A");
		newBatchCourse.setBatchCompletionStatus("D");

		BatchCourse addedBatchCourse = batchCourseDaoInterface.addBatchCourse(newBatchCourse);

		if (addedBatchCourse == null) {
			responce.setMassege("batch course added successfully");
			responce.setStatus(false);
			return responce;
		} else {
			entityDao.updateCountForCourseTable(data);
		}

		responce.setMassege("batch course added successfully");
		responce.setStatus(true);
		}catch (Exception e) {
			throw new Exception("Getting problem while adding Batch-Course");
		}
		return responce;

	}


	public Responce deleteBatchCourse(String batchCourseId) throws Exception {
		return batchCourseDaoInterface.deleteBatchCourse(batchCourseId);
	}


	@Transactional
	public List<Map<String, Object>> getAllBatchCourses(String subBatchId) throws Exception {
		return batchCourseDaoInterface.getAllBatchCourseBySubBatchId(subBatchId);
	}
	
	public Responce updateBatchCourseDetaile(String batchCourseId, HashMap<String, Object> batchCourseData) throws Exception {
		return batchCourseDaoInterface.updateBatchCourseDetaile(batchCourseId, batchCourseData);
	}

}

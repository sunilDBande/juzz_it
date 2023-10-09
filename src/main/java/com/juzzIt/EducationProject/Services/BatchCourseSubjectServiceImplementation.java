package com.juzzIt.EducationProject.Services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.juzzIt.EducationProject.Dao.EntityDao;
import com.juzzIt.EducationProject.DaoInterface.BatchCourseDaoInterface;
import com.juzzIt.EducationProject.DaoInterface.BatchCourseSubjectDaoInterface;
import com.juzzIt.EducationProject.Entity.BatchCourse;
import com.juzzIt.EducationProject.Entity.BatchCourseSubject;
import com.juzzIt.EducationProject.Entity.Teacher;
import com.juzzIt.EducationProject.Models.Responce;
import com.juzzIt.EducationProject.Models.UniqueIdGenerations;
import com.juzzIt.EducationProject.Repositary.TeacherRepository;
import com.juzzIt.EducationProject.ServiceInterface.BatchCourseSubjectServiceInterface;

@Service
public class BatchCourseSubjectServiceImplementation implements BatchCourseSubjectServiceInterface {

	@Autowired
	private TeacherRepository teacherRepo;

	@Autowired
	private BatchCourseDaoInterface batchCourseDaoInterface;

	@Autowired
	private BatchCourseSubjectDaoInterface batchCourseSubjectDaoInterface;

	@Autowired
	private UniqueIdGenerations uniqueIdGenerations;

	@Autowired
	private EntityDao entityDao;

	@Override
	public Responce addSubjectToBatch(String batchCourseId, String teacherId, HashMap<String, Object> subjctDetails) throws Exception {

		Responce responce = new Responce();
      try {
		if (subjctDetails.get("subject_name") == null) {

			responce.setMassege("subject name is required");
			responce.setStatus(false);
			return responce;
		}

		Optional<Teacher> teacher = teacherRepo.findById(teacherId);

		if (!teacher.isPresent()) {
			responce.setMassege("techer with the given id not found");
			responce.setStatus(false);
			return responce;
		}

		BatchCourse batchCourse = batchCourseDaoInterface.getBatchCourseById(batchCourseId);

		BatchCourseSubject newsubject = new BatchCourseSubject();
		newsubject.setTeacher(teacher.get());
		newsubject.setBatchCourse(batchCourse);
		newsubject.setBatchCourseSubjectName(subjctDetails.get("subject_name").toString());
		HashMap<String, Object> data = new HashMap<String, Object>();
		data.put("Entity_Name", "BatchCourseSubject");
		data.put("Entity_SubName", "BTCSUB");
		HashMap<String, Object> uniqueIdGeneration = uniqueIdGenerations.uniqueIdGeneration(data);
// *******
		String subName = (String) uniqueIdGeneration.get("Entity_SubName");
		long count = (long) uniqueIdGeneration.get("count");
		if (count < 10) {
			newsubject.setBatchCourseSubjectId(subName + "00" + count);
		} else if (count < 100 && count > 10) {
			newsubject.setBatchCourseSubjectId(subName + "0" + count);
		} else {
			newsubject.setBatchCourseSubjectId(subName + "" + count);
		}
// *******
		newsubject.setTeacherPermitionStutus("D");
		BatchCourseSubject addedSubject = batchCourseSubjectDaoInterface.addSubjectToBatch(newsubject);

		if (addedSubject == null) {
			responce.setMassege("failed to added the subjeect to batch");
			responce.setStatus(false);
			return responce;
		} else {
			entityDao.updateCountForCourseTable(data);
		}

		responce.setMassege("subject added to  teh batch successfully");

		responce.setStatus(true);
      }catch (Exception e) {
		throw new Exception("Getting problem while adding Subject To Batch");
	}
		return responce;
	}

	@Override
	public Responce deleteBatchCourseSubject(String batchCourseSubjectId) throws Exception {
		// TODO Auto-generated method stub
		return batchCourseSubjectDaoInterface.deleteSubjectFromBatch(batchCourseSubjectId);
	}

	@Override
	public List<Map<String, Object>> getAllSubjectByBatchCourseId(String batchCourseId) throws Exception {
		// TODO Auto-generated method stub
		return batchCourseSubjectDaoInterface.getAllSubjectByBatchCourseId(batchCourseId);
	}

	@Override
	public Responce updateBatchCourseSubject(String batchCourseSubjectId,
			HashMap<String, Object> batchCourseSubjectDetails) throws Exception {
		// TODO Auto-generated method stub
		return batchCourseSubjectDaoInterface.updateBatchCourseSubject(batchCourseSubjectId, batchCourseSubjectDetails);
	}

}

package com.juzzIt.EducationProject.Services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.juzzIt.EducationProject.Dao.EntityDao;
import com.juzzIt.EducationProject.DaoInterface.BatchCourseDaoInterface;
import com.juzzIt.EducationProject.DaoInterface.BatchCourseStudentDaoInterface;
import com.juzzIt.EducationProject.DaoInterface.StudentCourseEnrollRequestDaoInterface;
import com.juzzIt.EducationProject.Entity.BatchCourse;
import com.juzzIt.EducationProject.Entity.BatchCourseStudent;
import com.juzzIt.EducationProject.Entity.Student;
import com.juzzIt.EducationProject.Entity.StudentEnrollDetails;
import com.juzzIt.EducationProject.Models.Responce;
import com.juzzIt.EducationProject.Models.UniqueIdGenerations;
import com.juzzIt.EducationProject.Repositary.StudentRepository;
import com.juzzIt.EducationProject.ServiceInterface.BatchCourseStudentServiceInterface;
import com.juzzIt.EducationProject.ServiceInterface.CourseTypeImageServiceInterface;

import jakarta.persistence.EntityManager;

@Service
public class BatchCourseStudentServiceImplementation  implements BatchCourseStudentServiceInterface{

	
	@Autowired
	private BatchCourseDaoInterface batchCourseDaoInterface;
	
	@Autowired
	private StudentRepository studentRepo;
	
	@Autowired
	private StudentCourseEnrollRequestDaoInterface  studentCourseEnrollRequestDaoInterface;
	
	
	@Autowired
	private BatchCourseStudentDaoInterface batchCourseStudentDaoInterface;
	
	@Autowired 
	private CourseTypeImageServiceInterface courseTypeImageServiceInterface;
	
	@Autowired
	private EntityManager entityManager;
	
	@Autowired
	private Responce responce;
	
	@Autowired
	private UniqueIdGenerations uniqueIdGenerations;

	@Autowired
	private EntityDao entityDao;
	
	@Override
	public Responce addStudentToBatch(String batchCourseId, String studentId, String requestId,
			HashMap<String, Object> details) throws Exception {
		
		
		Responce responce=new Responce();
		try {
		if(details.get("student_name")==null||details.get("enroll_type")==null) {
			responce.setMassege("student name and enroll type is required");
			responce.setStatus(false);
			return responce;
		}
		
		BatchCourse batchCourse = batchCourseDaoInterface.getBatchCourseById(batchCourseId);
		
		if(batchCourse==null) {
			responce.setMassege("batchCourse with the given id not");
			responce.setStatus(false);
			return responce;
		}
		
		
		Optional<Student> student = studentRepo.findById(studentId);
		
		if(!student.isPresent()) {
			responce.setMassege("student with the given id not found");
			responce.setStatus(false);
			return responce;
		}
		
		StudentEnrollDetails enrollDetails = studentCourseEnrollRequestDaoInterface.getEnrollDetailsByRequestId(requestId);
		
		
		if(enrollDetails==null) {
			responce.setMassege("no details found for this student");
			responce.setStatus(false);
			return responce;
			}
		
		
		BatchCourseStudent newBatchCourseStudent=new BatchCourseStudent();
		HashMap<String, Object> data = new HashMap<String, Object>();
		data.put("Entity_Name", "BatchCourseStudent");
		data.put("Entity_SubName", "JITSTD");
		data.put("Entity_Count", 0);
		HashMap<String, Object> uniqueIdGeneration = uniqueIdGenerations.uniqueIdGeneration(data);
		// *******
		System.out.println("IMP---> " + uniqueIdGeneration);
		System.out.println("IMP3---> " + uniqueIdGeneration.get("count"));
		String subName = (String) uniqueIdGeneration.get("Entity_SubName");
		long count = (long) uniqueIdGeneration.get("count");
		if (count < 10) {
			newBatchCourseStudent.setBatchCourseStudentId(subName + "00" + count);
		} else if (count < 100 && count > 10) {
			newBatchCourseStudent.setBatchCourseStudentId(subName + "0" + count);
		} else {
			newBatchCourseStudent.setBatchCourseStudentId(subName + "" + count);
		}
		// *******
		newBatchCourseStudent.setBatchCourse(batchCourse);
		newBatchCourseStudent.setStudent(student.get());
		newBatchCourseStudent.setStudentEnrollDetails(enrollDetails);
		newBatchCourseStudent.setStudentName(details.get("student_name").toString());
		newBatchCourseStudent.setEnrollType(details.get("enroll_type").toString());
		newBatchCourseStudent.setStudentPermitionStatus("D");
		
		
		boolean requestDeleted = studentCourseEnrollRequestDaoInterface.deleteEnrollRequestAfterAcmition(requestId);
		
		if(requestDeleted==false) {
			responce.setMassege("failed delete the rquest data after admition");
		}
		
BatchCourseStudent addStodentTobatch = batchCourseStudentDaoInterface.addStodentTobatch(newBatchCourseStudent);
		
		
		if(addStodentTobatch==null) {
			responce.setMassege("failed to add the student");
			responce.setStatus(false);
			return responce;
		}else {
			entityDao.updateCountForCourseTable(data);
		}
		
		responce.setMassege("student added to the batch successfully");
		responce.setStatus(true);
		}catch (Exception e) {
			throw new Exception("Getting problem while adding Student To Batch");
		}
				return responce;
	}

	@Override
	public Responce deleteBatchCourseStudentById(String batchCourseStudentId) throws Exception {
		
		return batchCourseStudentDaoInterface.deleteStudent(batchCourseStudentId);
	}

	@Override
	public List<Map<String, Object>> getAllstudentsByBatchCourse(String batchCourseId) {
		// TODO Auto-generated method stub
		return batchCourseStudentDaoInterface.getAllStudent(batchCourseId);
	}

	@Override
	public Responce updateBatchCourseStudentDetailes(String batchCourseStudentId,
			HashMap<String, Object> batchCourseStudentDetails) throws Exception {
		return batchCourseStudentDaoInterface.updateBatchCourseStudentDetailes(batchCourseStudentId, batchCourseStudentDetails);
	}

	@Override
	public List<HashMap<String, Object>> getAllStudentEnrollBatchsByStudentId(String studentId) {
		
		
		List<HashMap<String, Object>> enrollBatchs = batchCourseStudentDaoInterface.getAllStudentEnrollBatchsByStudentId(studentId);
		
		
		return	enrollBatchs.stream().map(result->{
			
			
	
			
		result.put("courseType_Images", 	courseTypeImageServiceInterface.getCourseTypeImage(result.get("course_type_id").toString()));
			
			return result;
			
			
		}).collect(Collectors.toList());
		
		
//		return batchCourseStudentDaoInterface.getAllStudentEnrollBatchsByStudentId(studentId);
	}

	
	
	
	
}

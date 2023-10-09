package com.juzzIt.EducationProject.Dao;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.juzzIt.EducationProject.DaoInterface.TeacherDaoInterface;
import com.juzzIt.EducationProject.Entity.Teacher;
import com.juzzIt.EducationProject.Models.Responce;
import com.juzzIt.EducationProject.Models.UniqueIdGenerations;
import com.juzzIt.EducationProject.Repositary.TeacherRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

@Component
public class TeacherDao implements TeacherDaoInterface {

	@Autowired
	private TeacherRepository teacherRepo;

	@Autowired
	private UniqueIdGenerations uniqueIdGenerations;

	@Autowired
	private EntityDao entityDao;

	@Autowired
	private TeacherRepository teacherRepository;

	@Autowired
	private Responce responce;
	
	@Autowired
	private EntityManager entityManager;

	

	@Override
	public Responce addNewTeacher(HashMap<String, Object> teacher) {
		Responce responce = new Responce();
	
		Teacher teacherobj = new Teacher();
		teacherobj.setCreatedDate(LocalDateTime.now());
		teacherobj.setUpdateDate(LocalDateTime.now());
		 if(teacher.get("teacher_Name")==null || teacher.get("teacher_Address")==null ||
				 teacher.get("teacher_Email")==null || teacher.get("teacher_MobileNum")==null || teacher.get("teacher_Password")==null) {
	    	 responce.setStatus(false);
			 responce.setMassege("Please pass fields teacher_Name, teacher_Address, teacher_Email, teacher_MobileNum, and teacher_Password...");
	    	 return responce;
	     }
		try {
			boolean isExists = teacherRepo.existsByTeacherEmail(teacherobj.getTeacherEmail());
			if (isExists) {
				responce.setStatus(false);
				responce.setMassege("teacher with email id is alrady exist");
			} else {

				HashMap<String, Object> data = new HashMap<String, Object>();
				data.put("Entity_Name", "Teacher");
				data.put("Entity_SubName", "TCHR");
				HashMap<String, Object> uniqueIdGeneration = uniqueIdGenerations.uniqueIdGeneration(data);
				// *******
				String subName = (String) uniqueIdGeneration.get("Entity_SubName");
				long count = (long) uniqueIdGeneration.get("count");
				if (count < 10) {
					teacherobj.setTeacherId(subName + "00" + count);
				} else if (count < 100 && count > 10) {
					teacherobj.setTeacherId(subName + "0" + count);
				} else {
					teacherobj.setTeacherId(subName + "" + count);
				}
				teacherobj.setTeacherName((String) teacher.get("teacher_Name"));
				teacherobj.setTeacherAddress((String) teacher.get("teacher_Address"));
				teacherobj.setTeacherEmail((String) teacher.get("teacher_Email"));
				teacherobj.setTeacherMobileNumber((long) teacher.get("teacher_MobileNum"));
				teacherobj.setTeacherPassword((String) teacher.get("teacher_Password"));
				Teacher save = teacherRepository.save(teacherobj);
				if (save != null) {
					entityDao.updateCountForCourseTable(data);
					responce.setStatus(true);
					responce.setMassege("teacher Added succeccfully.....");
				} else {
					responce.setStatus(false);
					responce.setMassege("Teacher is not created.....");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return responce;
	}

	@Override
	public Responce updateTeacher(Teacher teacher) {
		try {
			
			teacherRepo.save(teacher);
			responce.setStatus(true);
            responce.setMassege("teacher details updated successfully");
		}catch (Exception e) {
			
		}
		return null;
	}
	@Override
	public List<HashMap<String, Object>> getAllTeacherDetails(String teacherId) throws Exception {
		String arrayOfReqFields[] = {"batch_course_subject_id", "batch_course_subject_name", "batch_course_id", "batch_cource_name",
				"subbatch_name","course_type_Id","course_type_name","course_level","course_name", "course_active", "batch_name"};
//		String teacher_Id = "TCHR002";
		List<HashMap<String, Object>> finalOutput = new ArrayList<HashMap<String,Object>>();
		try {
		String teacher_permition_status = "A";
		String query = "SELECT batch_course_subject.batch_course_subject_id, batch_course_subject.batch_course_subject_name, batch_course_subject.batch_course_id,"
				+ "batch_course.batch_cource_name, subbatch.subbatch_name, course_type.course_type_id, course_type.course_type_name, course_type.course_level, course.course_name,"
				+ "course.course_active, batch.batch_name"
				+ " FROM batch_course_subject, batch_course, subbatch, course_type, course, batch"
				+ " WHERE " //Give space****
				+ " batch_course_subject.teacher_id='"+teacherId+"'"
				+ " AND batch_course_subject.teacher_permition_status='"+teacher_permition_status+"'"
				+ " AND batch_course.batch_course_id = batch_course_subject.batch_course_id"
				+ " AND batch_course.subbatch_id = subbatch.subbatch_id"
				+ " AND batch_course.course_type_id = course_type.course_type_id"
				+ " AND course_type.cource_id = course.course_id"
				+ " AND subbatch.batch_id = batch.batch_id";
		System.out.println(query);
		Query createNativeQuery = entityManager.createNativeQuery(query);
		List<Object[]> resultList = createNativeQuery.getResultList();
		
		System.out.println(resultList);
		
		
		
		for(Object res[]:resultList) {
		  LinkedHashMap<String, Object> lh = new LinkedHashMap<String, Object>();
			for(int i=0; i<=res.length-1; i++) {
				if (res[i] == null || res[i].toString().trim().isEmpty()) {
					lh.put(arrayOfReqFields[i], "");
				} else {
					lh.put(arrayOfReqFields[i], res[i].toString());
				}
			}
			finalOutput.add(lh);
		}
		System.out.println("finalOutput--> "+finalOutput);
		}catch (Exception e) {
			throw new Exception("Getting problem while getting All Teacher Details");
		}
		return finalOutput;
	}

}

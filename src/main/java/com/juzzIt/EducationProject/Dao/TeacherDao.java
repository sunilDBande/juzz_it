package com.juzzIt.EducationProject.Dao;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.juzzIt.EducationProject.DaoInterface.TeacherDaoInterface;
import com.juzzIt.EducationProject.Entity.Teacher;
import com.juzzIt.EducationProject.JWTimplementation.JwtHelper;
import com.juzzIt.EducationProject.Models.LogInOrSignUpResponce;
import com.juzzIt.EducationProject.Models.Responce;
import com.juzzIt.EducationProject.Models.UniqueIdGenerations;
import com.juzzIt.EducationProject.Models.UseTypeEnum;
import com.juzzIt.EducationProject.Repositary.TeacherRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

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
	
	@Autowired
   private PasswordEncoder passwordEncoder;
	

    @Autowired
    private JwtHelper helper;

	

	@Override
	public LogInOrSignUpResponce addNewTeacher(HashMap<String, Object> teacher) {
		
		LogInOrSignUpResponce logInOrSignUpResponce=new LogInOrSignUpResponce();
		
		
		Responce responce = new Responce();
	
		Teacher teacherobj = new Teacher();
		teacherobj.setCreatedDate(LocalDateTime.now());
		teacherobj.setUpdateDate(LocalDateTime.now());
		 if(teacher.get("teacher_Name")==null || teacher.get("teacher_Address")==null ||
				 teacher.get("teacher_Email")==null || teacher.get("teacher_MobileNum")==null || teacher.get("teacher_Password")==null) {
			 logInOrSignUpResponce.setSuccessStatus(false);
			 logInOrSignUpResponce.setMassage(null);
	    	 return logInOrSignUpResponce;
	     }
		try {
			boolean isExists = teacherRepo.existsByTeacherEmail(teacherobj.getTeacherEmail());
			if (isExists) {
				logInOrSignUpResponce.setSuccessStatus(isExists);
				logInOrSignUpResponce.setMassage("teacher with email id is alrady exist");
				return logInOrSignUpResponce;
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
				
			String password=passwordEncoder.encode((String) teacher.get("teacher_Password"));
				
				teacherobj.setTeacherName((String) teacher.get("teacher_Name"));
				teacherobj.setTeacherAddress((String) teacher.get("teacher_Address"));
				teacherobj.setTeacherEmail((String) teacher.get("teacher_Email"));
				teacherobj.setTeacherMobileNumber((long) teacher.get("teacher_MobileNum"));
				teacherobj.setTeacherPassword(password);
				teacherobj.setRole(UseTypeEnum.TRAINER.toString());
				Teacher save = teacherRepository.save(teacherobj);
				if (save != null) {
					entityDao.updateCountForCourseTable(data);
					
					  String token = this.helper.generateToken(save.getTeacherEmail()+"*"+UseTypeEnum.TRAINER.toString());
					
					  logInOrSignUpResponce.setJwtTokan(token);
					  logInOrSignUpResponce.setUserType(save.getRole());
					  logInOrSignUpResponce.setUserId(save.getTeacherId());
					  logInOrSignUpResponce.setSuccessStatus(true);
					  logInOrSignUpResponce.setMassage("teacher Added succeccfully.....");
				} else {
				
					logInOrSignUpResponce.setSuccessStatus(false);
					logInOrSignUpResponce.setMassage("Teacher is not created.....");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return logInOrSignUpResponce;
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

	@Override
	public Teacher getTeacherByEmail(String email) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Teacher> createQuery = criteriaBuilder.createQuery(Teacher.class);
		Root<Teacher> root = createQuery.from(Teacher.class);
		Predicate predicate = criteriaBuilder.equal(root.get("teacherEmail"), email);
		
		createQuery.select(root).where(predicate);
		
		List<Teacher> resultList = entityManager.createQuery(createQuery).getResultList();
		
		if(resultList.isEmpty()) {
			return null;
		}
	
		return resultList.get(0);
	}


	@Override
	public List<Map<String, Object>> getAllTeacher() {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Teacher> createQuery = criteriaBuilder.createQuery(Teacher.class);
		Root<Teacher> root = createQuery.from(Teacher.class);		
		createQuery.select(root);
		
		List<Teacher> resultList = entityManager.createQuery(createQuery).getResultList();
		
		List<Map<String, Object>> collect = resultList.stream().map(result->{
			
			
			Map<String, Object> map=new LinkedHashMap<String, Object>();
			map.put("teacherId", result.getTeacherId());
			map.put("teacherName", result.getTeacherName());
			map.put("teacherEmail", result.getTeacherEmail());
			map.put("teacherPassword", result.getTeacherPassword());
			map.put("teacherMobileNumber", result.getTeacherMobileNumber());
			map.put("TEACHER_ADDRESS", result.getTeacherAddress());
			map.put("CREATED_DATE", result.getCreatedDate());
			
			
			return map;
		}).collect(Collectors.toList());
		
		return collect;
	}

	@Override
	public Teacher getTeacherById(String teacherId) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Teacher> createQuery = criteriaBuilder.createQuery(Teacher.class);
		Root<Teacher> root = createQuery.from(Teacher.class);
		Predicate predicate = criteriaBuilder.equal(root.get("teacherId"), teacherId);
		
		createQuery.select(root).where(predicate);
		
		List<Teacher> resultList = entityManager.createQuery(createQuery).getResultList();
		
		if(resultList.isEmpty()) {
			return null;
		}
	
		return resultList.get(0);
	}


}

package com.juzzIt.EducationProject.Services;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.juzzIt.EducationProject.DaoInterface.TeacherDaoInterface;
import com.juzzIt.EducationProject.Entity.Teacher;
import com.juzzIt.EducationProject.Models.LogInData;
import com.juzzIt.EducationProject.Models.LogInOrSignUpResponce;
import com.juzzIt.EducationProject.Models.Responce;
import com.juzzIt.EducationProject.Repositary.TeacherRepository;
import com.juzzIt.EducationProject.ServiceInterface.TeacherServiceInterface;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

@Service
public class TeacherServiceImplementation implements TeacherServiceInterface{

	@Autowired
	private TeacherRepository teacherRepo;
	
	@Autowired
	private TeacherDaoInterface teacherDaoInterface;
	
	@Autowired
	private EntityManager entityManager;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public LogInOrSignUpResponce addNewTeacher(HashMap<String, Object> teacher) {
		
		return teacherDaoInterface.addNewTeacher(teacher);
	   
	}

	@Override
	public Responce logInValidate(LogInData logInData) {
		Responce responce=new Responce();
		boolean isExists=teacherRepo.existsByTeacherEmail(logInData.getEmail());
		if(isExists) {
				Teacher teacher=teacherRepo.findByTeacherEmail(logInData.getEmail()).get(0);
				
				if(teacher.getTeacherPassword().equals(logInData.getPassword())) {
					responce.setStatus(true);
					responce.setMassege(teacher.getTeacherId());
				}else {
					responce.setStatus(false);
					responce.setMassege("password is incurrect");
					
				}
		
		}else {
			responce.setStatus(false);
           responce.setMassege("account with this email does not exist");
		}
		
		
		return responce;
	}

	@Override
	public Teacher getTeacherById(String teacherId) throws Exception {
		
		return   teacherDaoInterface.getTeacherById(teacherId);
	}

	@Override
	public Responce updateTeacher(String teacherId, HashMap<String, Object> teacher) throws Exception {
		
		Responce responce=new Responce();
		try {
		Teacher teacherobj = new Teacher();
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Teacher> createQuery = criteriaBuilder.createQuery(Teacher.class);
		Root<Teacher> root = createQuery.from(Teacher.class);
		Predicate predicate = criteriaBuilder.equal(root.get("teacherId"), teacherId);
		createQuery.select(root).where(predicate);
	    List<Teacher> resultList = entityManager.createQuery(createQuery).getResultList();
	    if(resultList.size() == 0) {
	    	responce.setStatus(false);
	    	responce.setMassege("Teacher not found for this Id");
	    }else {
	    	Teacher teacher2 = resultList.get(0);
	    	if(teacher.get("teacher_Name") != null) {
	    		teacher2.setTeacherName((String) teacher.get("teacher_Name"));
	    	}else {
	    		teacher2.setTeacherName(teacher2.getTeacherName());
	    	}
	    	if(teacher.get("teacher_Address") != null) {
	    	teacher2.setTeacherAddress((String) teacher.get("teacher_Address"));
	    	}else {
	    		teacher2.setTeacherAddress(teacher2.getTeacherAddress());
	    	}
	    	if(teacher.get("teacher_Mail") != null) {
	    	teacher2.setTeacherEmail((String) teacher.get("teacher_Mail"));
	    	}else {
	    		teacher2.setTeacherEmail(teacher2.getTeacherEmail());
	    	}
	    	if(teacher.get("teacher_MobileNum") != null) {
	    	teacher2.setTeacherMobileNumber((long) teacher.get("teacher_MobileNum"));
	    	}else {
	    		teacher2.setTeacherMobileNumber(teacher2.getTeacherMobileNumber());
	    	}
	    	if( teacher.get("teacher_Password") != null) {
	    	teacher2.setTeacherPassword((String) teacher.get("teacher_Password"));
	    	}else {
	    		teacher2.setTeacherPassword(teacher2.getTeacherPassword());
	    	}
	    	teacher2.setCreatedDate(teacher2.getCreatedDate());
	    	teacher2.setUpdateDate(LocalDateTime.now());
		   responce = teacherDaoInterface.updateTeacher(teacher2);
		
	    }
		}catch (Exception e) {
			throw new Exception("Getting problem while updating Teacher");
		}
		return responce;
	}

	@Override
	public List<Map<String, Object>> getTeachers() {
		return teacherDaoInterface.getAllTeacher();
		
		
	}

	@Override
	public Responce deleteTeacher(String teacherId) throws Exception {
		Responce responce=new Responce();
		try {
		Optional<Teacher> teacher=teacherRepo.findById(teacherId);
		
	  if(teacher.isPresent()) {
			teacherRepo.delete(teacher.get());
			responce.setStatus(true);
			responce.setMassege("teacher detail deleted successfully");
		}else {
		responce.setStatus(false);
		responce.setMassege("teacher detail is not deleted");
		}
		}catch (Exception e) {
			throw new Exception("Getting problem while deleting Teacher");
		}
		return responce;
	}

	@Override
	public List<HashMap<String, Object>> getAllTeacherDetails(String teacherId) throws Exception {
		// TODO Auto-generated method stub
		 return teacherDaoInterface.getAllTeacherDetails(teacherId);
	}

	@Override
	public Teacher getTeacherByEmail(String email) {
		// TODO Auto-generated method stub
		return null;
	}

}

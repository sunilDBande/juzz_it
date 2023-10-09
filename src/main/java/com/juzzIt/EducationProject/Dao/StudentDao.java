package com.juzzIt.EducationProject.Dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.juzzIt.EducationProject.DaoInterface.StudentDaoInterface;
import com.juzzIt.EducationProject.Entity.Student;
import com.juzzIt.EducationProject.Models.Responce;
import com.juzzIt.EducationProject.Repositary.StudentRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
@Component
public class StudentDao implements StudentDaoInterface {

	@Autowired
	StudentRepository studentRepo;
	
	@Autowired
	private Responce responce;
	
	@Autowired
	private EntityManager entityManager;
	
	@Override
	public Student addNewStudent(Student student) {
		Student save = studentRepo.save(student);
		
		return save;
	}

	@Override
	public Responce updateStudent(Student student) {
		studentRepo.save(student);
		responce.setStatus(true);
		responce.setMassege("student data updated successfully");
		return responce;
	}

	@Override
	public Responce deleteStudentById(String sudentId) {
		studentRepo.deleteById(sudentId);
		responce.setStatus(true);
		responce.setMassege("student data deleted successfully");
		return responce;
	}

	@Override
	public List<Map<String, Object>> getAllStudent() throws Exception {
		List<Map<String, Object>>  studentList = null;
		try {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Student> createQuery = criteriaBuilder.createQuery(Student.class);
		Root<Student> root = createQuery.from(Student.class);
		createQuery.select(root);
		List<Student> resultList = entityManager.createQuery(createQuery).getResultList();
		
		if(resultList.size() != 0) {
			studentList  = resultList.stream().map(result->{
				
				Map<String , Object> map=new LinkedHashMap<String, Object>();
				
				map.put("student_Id", result.getStudentId());
				map.put("student_Address", result.getStudentAddress());
				map.put("student_Email", result.getStudentEmail());
				map.put("student_MobileNum", result.getStudentMobileNumber());
				map.put("student_Name", result.getStudentName());
				map.put("student_EnrolledStatus", result.getStudent_Enroll());
				
				return map;
			}).collect(Collectors.toList());
 		}
		}catch (Exception e) {
			throw new Exception("Getting problem while getting All Student");
		}
		return studentList;
	}

}

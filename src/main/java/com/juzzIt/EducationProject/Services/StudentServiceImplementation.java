package com.juzzIt.EducationProject.Services;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.juzzIt.EducationProject.Dao.EntityDao;
import com.juzzIt.EducationProject.DaoInterface.StudentDaoInterface;
import com.juzzIt.EducationProject.Entity.Student;
import com.juzzIt.EducationProject.Models.LogInData;
import com.juzzIt.EducationProject.Models.Responce;
import com.juzzIt.EducationProject.Models.UniqueIdGenerations;
import com.juzzIt.EducationProject.Repositary.StudentRepository;
import com.juzzIt.EducationProject.ServiceInterface.StudentServiceInterface;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

@Service
public class StudentServiceImplementation implements StudentServiceInterface{

	@Autowired
	StudentRepository studentRepo;

	@Autowired
	private UniqueIdGenerations uniqueIdGenerations;

	@Autowired
	private EntityDao entityDao;
	
	@Autowired
	private StudentDaoInterface studentDaoInterface;
	
	@Autowired
	private EntityManager entityManager;
	
	@Autowired
	private Responce responce;
	
	@Override
	public Responce addNewStudent(HashMap<String, Object> student) throws Exception {
		Responce responce=new Responce();
		try {
		Student studentobj = new Student();
		studentobj.setCreatedDate(LocalDateTime.now());
		studentobj.setUpdatedDate(LocalDateTime.now());
	     if(student.get("student_Address")==null || student.get("student_Email")==null ||
	    		 student.get("student_MobileNum")==null || student.get("student_Name")==null || student.get("student_Password")==null) {
	    	 responce.setStatus(false);
			 responce.setMassege("Please pass fields student_Address, student_Email, student_MobileNum, student_Name, and student_Password..");
	    	 return responce;
	     }
			boolean isExist=studentRepo.existsByStudentEmail((String)student.get("student_Email"));
			if(isExist) {
				responce.setStatus(false);
				responce.setMassege("account with this email aleady exist");
			}else {
				HashMap<String, Object> data = new HashMap<String, Object>();
				data.put("Entity_Name", "Student");
				data.put("Entity_SubName", "STD");
				data.put("Entity_Count", 0);
				HashMap<String, Object> uniqueIdGeneration = uniqueIdGenerations.uniqueIdGeneration(data);
				// *******
				System.out.println("IMP---> " + uniqueIdGeneration);
				System.out.println("IMP3---> " + uniqueIdGeneration.get("count"));
				String subName = (String) uniqueIdGeneration.get("Entity_SubName");
				long count = (long) uniqueIdGeneration.get("count");
				if (count < 10) {
					studentobj.setStudentId(subName + "00" + count);
				} else if (count < 100 && count > 10) {
					studentobj.setStudentId(subName + "0" + count);
				} else {
					studentobj.setStudentId(subName + "" + count);
				}
				// *******
				studentobj.setStudentAddress((String)student.get("student_Address"));
				studentobj.setStudentEmail((String)student.get("student_Email"));
				studentobj.setStudentMobileNumber((long)student.get("student_MobileNum"));
				studentobj.setStudentName((String)student.get("student_Name"));
				studentobj.setStudentPassword((String)student.get("student_Password"));
				studentobj.setStudent_Enroll("D");
				Student addedNewStudent = studentDaoInterface.addNewStudent(studentobj);
				if(addedNewStudent!=null) {
					responce.setStatus(true);
					responce.setMassege("student data added successfully");
					entityDao.updateCountForCourseTable(data);
				}else {		
					responce.setStatus(false);
					responce.setMassege("Problem while adding student...");
				}
			}
					
		}catch (Exception e) {
			throw new Exception("Getting problem while adding New Student");
		}
		
		return responce;
	}

	
	@Override
	public Student getStudentById(String studentId) {
		System.out.println(studentRepo.findById(studentId));
		
		return studentRepo.findById(studentId).get();
	}

	@Override
	public Responce updateStudent(String studentId, HashMap<String, Object> student) throws Exception {
		try {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Student> createQuery = criteriaBuilder.createQuery(Student.class);
		Root<Student> root = createQuery.from(Student.class);
		Predicate predicate = criteriaBuilder.equal(root.get("studentId"), studentId);
		createQuery.select(root).where(predicate);
		List<Student> resultList = entityManager.createQuery(createQuery).getResultList();
		if(resultList.size() != 0) {
			Student studentobj = resultList.get(0);
			studentobj.setStudentAddress(student.get("student_Address")!=null?(String)student.get("student_Address"):studentobj.getStudentAddress());
			studentobj.setStudentEmail(student.get("student_Email")!=null?(String)student.get("student_Email"):studentobj.getStudentEmail());
			studentobj.setStudentMobileNumber(student.get("student_MobileNum")!=null?(long)student.get("student_MobileNum"):studentobj.getStudentMobileNumber());
			studentobj.setStudentName(student.get("student_Name")!=null?(String)student.get("student_Name"):studentobj.getStudentName());
			studentobj.setStudentPassword(student.get("student_Password")!=null?(String)student.get("student_Password"):studentobj.getStudentPassword());
			studentobj.setStudent_Enroll(studentobj.getStudent_Enroll());
			studentobj.setCreatedDate(studentobj.getCreatedDate());
			studentobj.setUpdatedDate(LocalDateTime.now());
			responce = studentDaoInterface.updateStudent(studentobj);
		}else {
			responce.setStatus(false);
			responce.setMassege("Student for given Id is not present");
		}
		}catch (Exception e) {
			throw new Exception("Getting problem while updating Student");
		}
		return responce;
	}

	@Override
	public List<Map<String, Object>> getStudents() throws Exception {
		return studentDaoInterface.getAllStudent();
	}


	@Override
	public Responce logInValidate(LogInData logInData) {
	Responce responce=new Responce();
   
	boolean isExists=studentRepo.existsByStudentEmail(logInData.getEmail());
	if(isExists) {
		Student student =studentRepo.findByStudentEmail(logInData.getEmail()).get();
		if(student.getStudentPassword().equals(logInData.getPassword())) {
			responce.setStatus(true);
            responce.setMassege(student.getStudentId());
		}else {
			responce.setStatus(false);
			responce.setMassege("password is Incurrect");
		}
	}else {
		responce.setStatus(false);
		responce.setMassege("account with this email does not exists");
	}
		return responce;
	}
	
	@Override
	public Responce updateStudentAfterEnroll(String studentId) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Student> createQuery = criteriaBuilder.createQuery(Student.class);
		Root<Student> root = createQuery.from(Student.class);
		Predicate predicate = criteriaBuilder.equal(root.get("studentId"), studentId);
		createQuery.select(root).where(predicate);
		List<Student> resultList = entityManager.createQuery(createQuery).getResultList();
		if(resultList.size() != 0) {
			Student studentobj = resultList.get(0);
			studentobj.setStudentAddress(studentobj.getStudentAddress());
			studentobj.setStudentEmail(studentobj.getStudentEmail());
			studentobj.setStudentMobileNumber(studentobj.getStudentMobileNumber());
			studentobj.setStudentName(studentobj.getStudentName());
			studentobj.setStudentPassword(studentobj.getStudentPassword());
			studentobj.setStudent_Enroll("A");
			studentobj.setCreatedDate(studentobj.getCreatedDate());
			studentobj.setUpdatedDate(LocalDateTime.now());
			responce = studentDaoInterface.updateStudent(studentobj);
		}else {
			responce.setStatus(false);
			responce.setMassege("Student for given Id is not present");
		}
		return responce;
	}


	@Override
	public Responce deleteStudentById(String studentId) {
		// TODO Auto-generated method stub
		return studentDaoInterface.deleteStudentById(studentId);
	}
}

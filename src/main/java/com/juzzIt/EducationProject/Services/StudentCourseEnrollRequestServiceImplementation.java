package com.juzzIt.EducationProject.Services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.juzzIt.EducationProject.Dao.EntityDao;
import com.juzzIt.EducationProject.DaoInterface.CourseDaoInterface;
import com.juzzIt.EducationProject.DaoInterface.CourseTypeDaoInterface;
import com.juzzIt.EducationProject.DaoInterface.StudentCourseEnrollRequestDaoInterface;
import com.juzzIt.EducationProject.DaoInterface.StudentEnrollDetailsDaoInterface;
import com.juzzIt.EducationProject.Entity.Course;
import com.juzzIt.EducationProject.Entity.CourseType;
import com.juzzIt.EducationProject.Entity.Student;
import com.juzzIt.EducationProject.Entity.StudentCourseEnrollRequest;
import com.juzzIt.EducationProject.Entity.StudentEnrollDetails;
import com.juzzIt.EducationProject.Models.Responce;
import com.juzzIt.EducationProject.Models.UniqueIdGenerations;
import com.juzzIt.EducationProject.Repositary.StudentRepository;
import com.juzzIt.EducationProject.ServiceInterface.StudentCourseEnrollRequestServiceInterface;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;

@Service
public class StudentCourseEnrollRequestServiceImplementation implements StudentCourseEnrollRequestServiceInterface {

	@Autowired
	private StudentRepository studentRepo;

	@Autowired
	private CourseTypeDaoInterface courseTypeDaoInterface;

	@Autowired
	private CourseDaoInterface courseDaoInterface;

	@Autowired
	private StudentEnrollDetailsDaoInterface studentEnrollDetailsDaoInterface;

	@Autowired
	private StudentCourseEnrollRequestDaoInterface studentCourseEnrollRequestDaoInterface;

	@Autowired
	private UniqueIdGenerations uniqueIdGenerations;

	@Autowired
	private EntityDao entityDao;

	@Autowired
	private EntityManager entityManager;
	@Override
	public Responce addEnrollRequest(String studentId, String courseTypeId, HashMap<String, Object> requestData) {

		Responce responce = new Responce();
	
System.out.println("requestData--> "+requestData);
System.out.println("studentId--> "+studentId);
		if (requestData.get("student_name") == null || requestData.get("enroll_Type") == null
				|| requestData.get("student_email") == null || requestData.get("student_mobileNumber") == null
				|| requestData.get("country") == null || requestData.get("state") == null
				|| requestData.get("district") == null || requestData.get("pinCode") == null) {

			responce.setMassege("name ,email  , mobine number and enroll type, country, state, district"
					+ ", pinCode. are required");
			responce.setStatus(false);
			return responce;
		}

		Optional<Student> student = studentRepo.findById(studentId);

		if (!student.isPresent()) {
			responce.setMassege("student with the given id not found ");
			responce.setStatus(false);
			return responce;
		}

		CourseType courseType = courseTypeDaoInterface.getCourseTypeById(courseTypeId);
		
		if(courseType==null) {
			responce.setMassege("courseType with the given id not found ");
			responce.setStatus(false);
			return responce;
		}
		

		Course course = courseType.getCourse();
		if (course == null) {
			responce.setMassege(" course for course type not found");
			responce.setStatus(false);
			return responce;
		}

		if (courseType == null) {
			responce.setMassege("coursentype with the given id not found ");
			responce.setStatus(false);
			return responce;
		}
		StudentEnrollDetails enrollDetails = new StudentEnrollDetails();
		try {
		

		enrollDetails.setStudentName(requestData.get("student_name").toString());
		enrollDetails.setStudentEmail(requestData.get("student_email").toString());
		enrollDetails.setMobileNubber((long) requestData.get("student_mobileNumber"));

		
			enrollDetails.setCountry(requestData.get("country").toString());
		
	
			enrollDetails.setState(requestData.get("state").toString());


	
			enrollDetails.setDistrict(requestData.get("district").toString());
		
			enrollDetails.setPinCode((int) requestData.get("pinCode"));
	
		HashMap<String, Object> data = new HashMap<String, Object>();
		data.put("Entity_Name", "StudentEnrollDetails");
		data.put("Entity_SubName", "STENREQ");
		HashMap<String, Object> uniqueIdGeneration = uniqueIdGenerations.uniqueIdGeneration(data);
		// *******
		String subName = (String) uniqueIdGeneration.get("Entity_SubName");
		long count = (long) uniqueIdGeneration.get("count");
		if (count < 10) {
			enrollDetails.setEnrollDetailsId(subName + "00" + count);
		} else if (count < 100 && count > 10) {
			enrollDetails.setEnrollDetailsId(subName + "0" + count);
		} else {
			enrollDetails.setEnrollDetailsId(subName + "" + count);
		}
		// *******

		StudentEnrollDetails addedStudentDetailes = studentEnrollDetailsDaoInterface.addStudentDetailes(enrollDetails);

		if (addedStudentDetailes == null) {
			responce.setMassege("not able to add the details");
			responce.setStatus(false);
			return responce;
		}

		StudentCourseEnrollRequest newRequest = new StudentCourseEnrollRequest();
		HashMap<String, Object> data1 = new HashMap<String, Object>();
		data1.put("Entity_Name", "StudentCourseEnrollRequest");
		data1.put("Entity_SubName", "ENREQ");
		HashMap<String, Object> uniqueIdGeneration1 = uniqueIdGenerations.uniqueIdGeneration(data1);
		// *******
		String subName1 = (String) uniqueIdGeneration1.get("Entity_SubName");
		long count1 = (long) uniqueIdGeneration1.get("count");
		if (count1 < 10) {
			newRequest.setEnrollRequestId(subName + "00" + count1);
		} else if (count1 < 100 && count1 > 10) {
			newRequest.setEnrollRequestId(subName + "0" + count1);
		} else {
			newRequest.setEnrollRequestId(subName + "" + count1);
		}
		// *******
		newRequest.setStudent(student.get());
		newRequest.setCourseType(courseType);
		newRequest.setStudentEnrollDetails(addedStudentDetailes);
		newRequest.setCourseName(course.getCourseName());
		newRequest.setStudentName(requestData.get("student_name").toString());
		newRequest.setCourseTypeName(courseType.getCourseTypeName());
		newRequest.setCourseEnrollType(requestData.get("enroll_Type").toString());

		StudentCourseEnrollRequest addedRequest = studentCourseEnrollRequestDaoInterface.addRequest(newRequest);

		if (addedRequest == null) {
			responce.setMassege("Request failed...");
			responce.setStatus(false);
			studentEnrollDetailsDaoInterface.deletedEnrollDetails(enrollDetails);
			return responce;
		} else {
			entityDao.updateCountForCourseTable(data1);
			entityDao.updateCountForCourseTable(data);
		}

		responce.setMassege("request seand successfully");
		responce.setStatus(true);
		}catch (Exception e) {
			studentEnrollDetailsDaoInterface.deletedEnrollDetails(enrollDetails);
			e.printStackTrace();
		}
		
		return responce;
	}

	
	public Responce deleteRequest(String requestId) throws Exception {

		return  studentCourseEnrollRequestDaoInterface.deleteRequest(requestId) ;
	}

	@Override
	@Transactional
	public List<Map<String, Object>> getAllEnrollReq() throws Exception {
		List<Map<String, Object>> collect = null;
		try {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<StudentCourseEnrollRequest> createQuery = criteriaBuilder.createQuery(StudentCourseEnrollRequest.class);
		Root<StudentCourseEnrollRequest> root = createQuery.from(StudentCourseEnrollRequest.class);
		createQuery.select(root);
		List<StudentCourseEnrollRequest> resultList = entityManager.createQuery(createQuery).getResultList();
		 collect=resultList.stream().map(result->{
			Map<String , Object> map=new LinkedHashMap<String, Object>();
			map.put("request_Id", result.getEnrollRequestId());
			map.put("enroll_Type", result.getCourseEnrollType());
			map.put("student_Detail", result.getStudentEnrollDetails());
			map.put("courseTypeData", studentCourseEnrollRequestDaoInterface.getCourseTypeDataByRequestId( result.getEnrollRequestId()));
			map.put("course_Name", result.getCourseName());
			map.put("course_TypeName", result.getCourseTypeName());
			map.put("student_Name", result.getStudentName());
			Student student = result.getStudent();
			map.put("student_Id", student.getStudentId());
			System.out.println("map--> "+map);
			return map;
		}).collect(Collectors.toList());
		}catch (Exception e) {
			
			e.printStackTrace();
			throw new Exception("Getting problem while getting All EnrollReq");
		}
		return collect;
	}
	
	

//	public HashMap<String, Object> getCourseTypeDataByRequestId(String  requestId){
//String []data= {"COURSE_TYPE_ID"};
//		
//		
//		String query= " select course_type.course_type_id   "
//				+ " from student_course_enroll_request , course_type "
//				+ " where "
//				+ "  student_course_enroll_request.enroll_request_id='"+requestId+"'" 
//				+ "  and  student_course_enroll_request.course_type_id = course_type.course_type_id  ";
//		
//		
//		Query createNativeQuery = entityManager.createNativeQuery(query);
//		List<Object[]> resultList = createNativeQuery.getResultList();
//		
//         List<HashMap<String, Object>> finalOutput = new ArrayList<HashMap<String,Object>>();
//		
//		
//		for(Object res[]:resultList) {
//			  LinkedHashMap<String, Object> lh = new LinkedHashMap<String, Object>();
//			for(int i=0; i<=res.length-1; i++) {
//				if (res[i] == null || res[i].toString().trim().isEmpty()) {
//					lh.put(data[i], "");
//				} else {
//					lh.put(data[i], res[i].toString());
//				}
//			}
//			finalOutput.add(lh);
//		}
//		System.out.println("finalOutput--> "+finalOutput);
//		if(finalOutput.isEmpty()) {
//			return null;
//		}
//		return finalOutput.get(0);
//	}

}

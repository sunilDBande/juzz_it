package com.juzzIt.EducationProject.Services;

import java.util.ArrayList;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.juzzIt.EducationProject.Dao.EntityDao;
import com.juzzIt.EducationProject.DaoInterface.CourseDaoInterface;
import com.juzzIt.EducationProject.DaoInterface.CourseTypeDaoInterface;
import com.juzzIt.EducationProject.DaoInterface.CourseTypeImageDaoInterface;
import com.juzzIt.EducationProject.Entity.Course;
import com.juzzIt.EducationProject.Models.Responce;
import com.juzzIt.EducationProject.Models.UniqueIdGanaretor;
import com.juzzIt.EducationProject.Models.UniqueIdGenerations;
import com.juzzIt.EducationProject.ServiceInterface.CourseImageServiceInterface;
import com.juzzIt.EducationProject.ServiceInterface.CourseServiceInterface;
import com.juzzIt.EducationProject.ServiceInterface.CourseTypeBagroundImageServiceInterface;

@Service
public class CourseServiceImplementation implements CourseServiceInterface {

	@Autowired
	private CourseDaoInterface courseDaoInterface;

	@Autowired
	private CourseTypeDaoInterface courseTypeDaoInterface;

	@Autowired
	private UniqueIdGenerations uniqueIdGenerations;
	
	@Autowired
	private CourseImageServiceInterface courseImageServiceInterface;
	
	@Autowired
	private CourseTypeImageService courseTypeImageService;

	@Autowired
	private EntityDao entityDao;
	
	
	@Autowired
	private CourseTypeBagroundImageServiceInterface courseTypeBagroundImageServiceInterface;
	
	@Autowired
	private CourseTypeImageDaoInterface courseTypeImageDaoInterface;
	

	@Override
	public Responce addCourse(HashMap<String, Object> course) throws Exception {
		Responce responce = new Responce();
		try {

			String course_Name = (String) course.get("course_name");

			if (course_Name == null) {
				responce.setMassege("course name required");
				responce.setStatus(false);
				return responce;
			}

			if (courseDaoInterface.courseExist(course_Name)) {
				responce.setMassege("course with the name is alrady created");
				responce.setStatus(false);
				return responce;
			}

			Course newCourse = new Course();
			HashMap<String, Object> data = new HashMap<String, Object>();
			data.put("Entity_Name", "Course");
			data.put("Entity_SubName", "COUR");
			data.put("Entity_Count", 0);
			HashMap<String, Object> uniqueIdGeneration = uniqueIdGenerations.uniqueIdGeneration(data);
			// *******
			System.out.println("IMP---> " + uniqueIdGeneration);
			System.out.println("IMP3---> " + uniqueIdGeneration.get("count"));
			String subName = (String) uniqueIdGeneration.get("Entity_SubName");
			long count = (long) uniqueIdGeneration.get("count");
			if (count < 10) {
				newCourse.setCourseId(subName + "00" + count);
			} else if (count < 100 && count > 10) {
				newCourse.setCourseId(subName + "0" + count);
			} else {
				newCourse.setCourseId(subName + "" + count);
			}
			// *******
			newCourse.setCourseName((String) course.get("course_name"));
			newCourse.setCourseActive("D");
			newCourse.setTempDelete("A");
			newCourse.setFutureCourseStatus("D");

			Course addedCours = courseDaoInterface.addCourse(newCourse);

			if (addedCours == null) {
				responce.setMassege("problom occured while storing the data");
				responce.setStatus(false);
			}else {
				 entityDao.updateCountForCourseTable(data);		
			}

			responce.setMassege("course created successfully");
			responce.setStatus(true);

		} catch (Exception e) {
			throw new Exception("Getting problem while adding Course");
		}

		return responce;

	}

	@Override
	public Responce updateCourse(String courseId, HashMap<String, Object> course) throws Exception {

		return courseDaoInterface.updateCourse(courseId, course);
	}

	@Override
	public List<HashMap<String, Object>> getAllCourse() throws Exception {
		return courseDaoInterface.getCourses();
	}

	@Override
	public List<HashMap<String, Object>> getAllCoursesAndItsTypes() throws Exception {
		List<HashMap<String, Object>> courses = courseDaoInterface.getCourses();
//        List<HashMap<String, Object>> output = new ArrayList<HashMap<String,Object>>();
//		for(Iterator<HashMap<String, Object>> iterator = courses.iterator();iterator.hasNext();) {
//			HashMap<String, Object> hasNext = (HashMap<String, Object>)iterator.next();
//			System.out.println("--> "+hasNext.entrySet());
//			String courseId = (String)hasNext.get("course_Id");
//			System.out.println("--> "+courseId);
//			List<Map<String, Object>> courseTypeByCourseId = courseTypeDaoInterface.getCourseTypeByCourseId(courseId);
//		    hasNext.put("Course_type", courseTypeByCourseId);
//		    output.add(hasNext);
//		}
//		return output;
		return courses.stream().map(course -> {
			System.out.println("--> " + course.entrySet());
			String courseId = (String) course.get("course_Id");
			System.out.println("--> " + courseId);
			course.put("course_BagtoundImage", courseImageServiceInterface.getCoueseImage(courseId));
			List<Map<String, Object>> courseTypes = courseTypeDaoInterface.getCourseTypeByCourseId(courseId);
		
			List<Map<String, Object>> collect = courseTypes.stream().map(result->{
				result.put("courseType_bagroundImage", courseTypeBagroundImageServiceInterface.getCourseTypeBagroundImage(result.get("course_typeId").toString()));
				result.put("courseTypeImage", courseTypeImageService.getCourseTypeImage(result.get("course_typeId").toString()));	
						return result;
			}).collect(Collectors.toList());
			course.put("courseType", collect);
			return course;
		}).collect(Collectors.toList());
	}

	@Override
	public List<HashMap<String, Object>> getAllTempDeletedCourse() throws Exception {
		return courseDaoInterface.getTempDeletedCourses();
	}

	@Override
	public Responce deleteCourse(String courseId) {
		return courseDaoInterface.deleteCourse(courseId);
	}

}

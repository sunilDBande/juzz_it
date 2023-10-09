package com.juzzIt.EducationProject.Repositary;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.juzzIt.EducationProject.Entity.CourseType;

@Repository
public interface CourseTypeRepositary extends JpaRepository<CourseType, String> {

	boolean existsByCourseTypeName(String courseTypeId);
}

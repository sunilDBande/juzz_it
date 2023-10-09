package com.juzzIt.EducationProject.Repositary;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.juzzIt.EducationProject.Entity.CourseTypeDetails;

@Repository
public interface CourseTypeDetailsRepositary extends JpaRepository<CourseTypeDetails, String>{

	
}

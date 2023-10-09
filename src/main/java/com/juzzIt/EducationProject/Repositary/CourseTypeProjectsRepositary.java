package com.juzzIt.EducationProject.Repositary;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.juzzIt.EducationProject.Entity.CourseTypeProjects;

@Repository
public interface CourseTypeProjectsRepositary extends JpaRepository<CourseTypeProjects, String>{

	
	
}

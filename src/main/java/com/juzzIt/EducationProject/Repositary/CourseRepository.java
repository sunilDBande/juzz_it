package com.juzzIt.EducationProject.Repositary;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.juzzIt.EducationProject.Entity.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, String> {
    
		boolean existsByCourseName(String courseName);
}


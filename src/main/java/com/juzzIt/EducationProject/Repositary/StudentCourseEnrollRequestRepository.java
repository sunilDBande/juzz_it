package com.juzzIt.EducationProject.Repositary;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.juzzIt.EducationProject.Entity.StudentCourseEnrollRequest;

@Repository
public interface StudentCourseEnrollRequestRepository extends JpaRepository<StudentCourseEnrollRequest, String> {

	
}

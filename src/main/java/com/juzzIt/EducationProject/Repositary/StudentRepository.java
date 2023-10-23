package com.juzzIt.EducationProject.Repositary;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.juzzIt.EducationProject.Entity.Student;

@Repository
public interface StudentRepository  extends JpaRepository<Student, String>{

	boolean existsByStudentEmail(String studentEmail);
	List<Student> findByStudentEmail(String studentEmail);;
}
		
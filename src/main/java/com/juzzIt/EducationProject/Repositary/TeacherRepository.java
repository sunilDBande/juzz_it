package com.juzzIt.EducationProject.Repositary;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.juzzIt.EducationProject.Entity.Teacher;
@Repository
public interface TeacherRepository extends JpaRepository<Teacher, String> {

	
	boolean existsByTeacherEmail(String teacherEmail);
	
	Optional<Teacher> findByTeacherEmail(String teacherEmail);
}

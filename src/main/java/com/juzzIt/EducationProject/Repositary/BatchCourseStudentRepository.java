package com.juzzIt.EducationProject.Repositary;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.juzzIt.EducationProject.Entity.BatchCourseStudent;

@Repository
public interface BatchCourseStudentRepository extends JpaRepository<BatchCourseStudent, String>{

}

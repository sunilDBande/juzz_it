package com.juzzIt.EducationProject.Repositary;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.juzzIt.EducationProject.Entity.BatchCourseSubject;

@Repository
public interface BatchCourseSubjectRepository extends JpaRepository<BatchCourseSubject, String> {

}

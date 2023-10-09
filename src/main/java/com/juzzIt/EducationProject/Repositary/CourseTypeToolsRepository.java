package com.juzzIt.EducationProject.Repositary;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.juzzIt.EducationProject.Entity.CourseTypeTools;

@Repository
public interface CourseTypeToolsRepository extends JpaRepository<CourseTypeTools, String>{

}

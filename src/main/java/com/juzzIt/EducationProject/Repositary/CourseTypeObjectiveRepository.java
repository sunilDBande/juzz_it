package com.juzzIt.EducationProject.Repositary;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.juzzIt.EducationProject.Entity.CourseTypeObjective;

@Repository
public interface CourseTypeObjectiveRepository  extends JpaRepository<CourseTypeObjective,String>{

}

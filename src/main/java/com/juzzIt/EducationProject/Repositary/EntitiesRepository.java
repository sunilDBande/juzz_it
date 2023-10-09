package com.juzzIt.EducationProject.Repositary;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.juzzIt.EducationProject.Entity.Course;
import com.juzzIt.EducationProject.Entity.Entities;

@Repository
public interface EntitiesRepository extends JpaRepository<Entities, Long> {

	
}

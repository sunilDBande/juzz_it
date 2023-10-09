package com.juzzIt.EducationProject.Repositary;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.juzzIt.EducationProject.Entity.EnrollType;

@Repository
public interface EnrollTypeRepository extends JpaRepository<EnrollType, String> {	
	boolean existsByEnrollType(String enrollType);
	
}

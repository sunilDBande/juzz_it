package com.juzzIt.EducationProject.Repositary;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.juzzIt.EducationProject.Entity.BatchCoursePlacements;

@Repository
public interface BatchCoursePlacementsRepository extends JpaRepository<BatchCoursePlacements, String> {

	
	
}

package com.juzzIt.EducationProject.Repositary;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.juzzIt.EducationProject.Entity.BatchCourse;
import com.juzzIt.EducationProject.Entity.SubBatch;

@Repository
public interface BatchCourseRepository extends JpaRepository<BatchCourse, String>{

	
	boolean   existsByBatchCourseNameOrSubBatch(String batchCourseName,SubBatch subBatch);
}

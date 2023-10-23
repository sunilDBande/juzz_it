package com.juzzIt.EducationProject.Repositary;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.juzzIt.EducationProject.Entity.BatchCourseRecordedVideoSubject;
@Repository
public interface BatchCourseRecordedVideoSubjectRepository  extends JpaRepository<BatchCourseRecordedVideoSubject, String>{

	
	
}

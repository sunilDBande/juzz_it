package com.juzzIt.EducationProject.Repositary;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.juzzIt.EducationProject.Entity.Lesson;

@Repository
public interface LessonRepositary extends JpaRepository<Lesson, String> {

	
}

package com.juzzIt.EducationProject.Repositary;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.juzzIt.EducationProject.Entity.CourseTypeKeyHighlights;

@Repository
public interface CourseTypeKeyHighlightsRepository extends JpaRepository<CourseTypeKeyHighlights, String> {

}

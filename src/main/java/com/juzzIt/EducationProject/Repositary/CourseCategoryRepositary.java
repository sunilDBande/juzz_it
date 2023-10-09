package com.juzzIt.EducationProject.Repositary;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.juzzIt.EducationProject.Entity.CourseCategory;

@Repository
public interface CourseCategoryRepositary extends JpaRepository<CourseCategory, String> {

	boolean existsByCategoryName(String categoryId);
}

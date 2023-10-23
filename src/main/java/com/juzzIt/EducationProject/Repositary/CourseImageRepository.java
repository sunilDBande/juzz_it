package com.juzzIt.EducationProject.Repositary;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.juzzIt.EducationProject.Entity.CourseImage;

@Repository
public interface CourseImageRepository extends JpaRepository<CourseImage, String> {

}

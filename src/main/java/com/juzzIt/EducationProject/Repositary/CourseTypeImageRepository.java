package com.juzzIt.EducationProject.Repositary;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.juzzIt.EducationProject.Entity.CourseTypeImage;

@Repository
public interface CourseTypeImageRepository extends JpaRepository<CourseTypeImage, String>  {

}

package com.juzzIt.EducationProject.Repositary;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.juzzIt.EducationProject.Entity.CourseTypeBagroundImage;
@Repository
public interface CourseTypeBagroundImageRepository  extends JpaRepository<CourseTypeBagroundImage,String>{

}

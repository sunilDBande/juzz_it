package com.juzzIt.EducationProject.Repositary;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.juzzIt.EducationProject.Entity.CourseTypeVideo;
@Repository
public interface CourseTypeVideoRepository  extends JpaRepository<CourseTypeVideo, String>{

}

package com.juzzIt.EducationProject.Repositary;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.juzzIt.EducationProject.Entity.CourseTypeEssentials;

@Repository
public interface CourseTypeEssentialsRepositary extends JpaRepository<CourseTypeEssentials, String> {

}

package com.juzzIt.EducationProject.Repositary;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.juzzIt.EducationProject.Entity.StudentEnrollDetails;

@Repository
public interface StudentEnrollDetailsRepository extends JpaRepository<StudentEnrollDetails, String>{

}

package com.juzzIt.EducationProject.Repositary;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.juzzIt.EducationProject.Entity.RecordedStudentToken;
@Repository
public interface RecordedStudentTokenRepository extends JpaRepository<RecordedStudentToken, String> {

}

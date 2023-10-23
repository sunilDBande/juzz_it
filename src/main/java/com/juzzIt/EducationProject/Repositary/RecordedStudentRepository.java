package com.juzzIt.EducationProject.Repositary;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.juzzIt.EducationProject.Entity.RecordedStudent;
@Repository
public interface RecordedStudentRepository  extends JpaRepository<RecordedStudent, String>{

}

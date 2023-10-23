package com.juzzIt.EducationProject.Repositary;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.juzzIt.EducationProject.Entity.RecordedStudentTokenMassege;
@Repository
public interface RecordedStudentTokenMassegeRepository extends JpaRepository<RecordedStudentTokenMassege, String> {

}

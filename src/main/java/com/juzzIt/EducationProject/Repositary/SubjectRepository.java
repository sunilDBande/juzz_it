package com.juzzIt.EducationProject.Repositary;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.juzzIt.EducationProject.Entity.Subject;


@Repository
public interface SubjectRepository extends JpaRepository<Subject, String> {

	boolean existsBySubjectName(String subjectName);
}

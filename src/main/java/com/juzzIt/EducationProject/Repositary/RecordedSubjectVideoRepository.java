package com.juzzIt.EducationProject.Repositary;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.juzzIt.EducationProject.Entity.RecordedSubjectVideo;

@Repository
public interface RecordedSubjectVideoRepository extends JpaRepository<RecordedSubjectVideo, String>  {

}

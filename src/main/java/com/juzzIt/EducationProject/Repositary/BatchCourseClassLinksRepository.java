package com.juzzIt.EducationProject.Repositary;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.juzzIt.EducationProject.Entity.BatchCourseClassLinks;

@Repository
public interface BatchCourseClassLinksRepository extends JpaRepository<BatchCourseClassLinks, String> {

}

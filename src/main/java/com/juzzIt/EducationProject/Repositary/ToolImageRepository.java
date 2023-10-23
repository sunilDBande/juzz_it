package com.juzzIt.EducationProject.Repositary;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.juzzIt.EducationProject.Entity.ToolImage;

@Repository
public interface ToolImageRepository  extends JpaRepository<ToolImage, String>{

}

package com.juzzIt.EducationProject.Repositary;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.juzzIt.EducationProject.Entity.Topic;

@Repository 
public interface TopicRepositary  extends JpaRepository<Topic, String>{

}

package com.juzzIt.EducationProject.Repositary;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.juzzIt.EducationProject.Entity.TopicVideo;

@Repository
public interface TopicVideoRepository  extends JpaRepository<TopicVideo, String>{

	
	
}

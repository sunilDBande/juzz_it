package com.juzzIt.EducationProject.Repositary;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.juzzIt.EducationProject.Entity.Batch;

@Repository
public interface BatchRepository extends JpaRepository<Batch, String>{

	
	boolean existsByBatchName(String batchName);
}

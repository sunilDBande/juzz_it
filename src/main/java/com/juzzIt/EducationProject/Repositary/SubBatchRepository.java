package com.juzzIt.EducationProject.Repositary;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.juzzIt.EducationProject.Entity.Batch;
import com.juzzIt.EducationProject.Entity.SubBatch;

@Repository
public interface SubBatchRepository extends JpaRepository<SubBatch, String> {

	boolean existsBySubBatchNameAndBatch(String subBatchName,Batch batch);
}

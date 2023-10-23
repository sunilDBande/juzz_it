package com.juzzIt.EducationProject.Repositary;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.juzzIt.EducationProject.Entity.PlacementImage;
@Repository
public interface PlacementImageRepository extends JpaRepository<PlacementImage, String> {

}

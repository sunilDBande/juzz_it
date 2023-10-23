package com.juzzIt.EducationProject.Repositary;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.juzzIt.EducationProject.Entity.SalesExecutive;
@Repository
public interface SalesExecutiveRepository extends JpaRepository<SalesExecutive, String> {
boolean existsBySalesExecutiveEmail(String email);

List<SalesExecutive>  findBySalesExecutiveEmail(String email);

}

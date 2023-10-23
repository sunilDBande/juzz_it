package com.juzzIt.EducationProject.Repositary;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.juzzIt.EducationProject.Entity.Admin;

@Repository
public interface AdminRepository  extends JpaRepository<Admin, String> {
	boolean existsByAdminEmail(String AdminEmail);
	List<Admin> findByAdminEmail(String email);
}
	
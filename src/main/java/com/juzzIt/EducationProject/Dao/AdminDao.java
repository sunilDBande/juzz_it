package com.juzzIt.EducationProject.Dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.juzzIt.EducationProject.DaoInterface.AdminDaoInterface;
import com.juzzIt.EducationProject.Entity.Admin;
import com.juzzIt.EducationProject.Models.Responce;
import com.juzzIt.EducationProject.Repositary.AdminRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

@Repository
public class AdminDao  implements AdminDaoInterface{
    @Autowired
    private AdminRepository adminRepository;
    
    @Autowired
    private EntityManager entityManager;
	
	

	public Admin createAdminAcount(Admin admin) {	
		return adminRepository.save(admin);
	}

	@Override
	public Admin getAdminByAdminId(String AdminId) {
		
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Admin> createQuery = criteriaBuilder.createQuery(Admin.class);
		Root<Admin> root = createQuery.from(Admin.class);
	Predicate predicate = criteriaBuilder.equal(root.get("adminId"),AdminId );
	
	
	createQuery.select(root).where(predicate);
	
	List<Admin> resultList = entityManager.createQuery(createQuery).getResultList();
	if(resultList.isEmpty()) {
		return null;
	}
		return resultList.get(0);
	}


	@Override
	public Responce deleteAdminAccount(String adminId) {
		
		Responce responce=new Responce();
		Admin admin = getAdminByAdminId(adminId);
		
		if(admin!=null) {
			adminRepository.delete(admin);
			responce.setMassege("admin Accont deleted successfully");
			responce.setStatus(true);
		}else {
             responce.setMassege("admin with the given it not found");
 			responce.setStatus(false);
		}
		return responce;
	}

	@Override
	public Responce updateAdminAccount(String adminId,HashMap<String, Object> adminData) {
		Responce responce=new Responce();
		Admin admin = getAdminByAdminId(adminId);
		if(admin!=null) {
			
			if(adminData.get("admin_Email")!=null) {
				admin.setAdminEmail(adminData.get("admin_Email").toString());
			}
			if(adminData.get("admin_Password")!=null) {
				admin.setAdminPassword(adminData.get("admin_Password").toString());
			}
			
			Admin updatedadmin = adminRepository.save(admin);
			
			if(updatedadmin==null) {
				responce.setMassege("failed to update the data");
				responce.setStatus(false);
			}
			
			responce.setMassege("admin details updated successfully");
			responce.setStatus(true);
			
			
		}else {
            responce.setMassege("admin with the given it not found");
			responce.setStatus(false);
		}
		
		return null;
	}
	
	@Override
	public boolean iSexists(String adminEmail) {
		return adminRepository.existsByAdminEmail(adminEmail);
	}

	@Override
	public Admin getAdminByEmail(String email) {
		
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Admin> createQuery = criteriaBuilder.createQuery(Admin.class);
		Root<Admin> root = createQuery.from(Admin.class);
	Predicate predicate = criteriaBuilder.equal(root.get("adminEmail"),email );
	
	
	createQuery.select(root).where(predicate);
	
	List<Admin> resultList = entityManager.createQuery(createQuery).getResultList();
	if(resultList.isEmpty()) {
		return null;
	}
		return resultList.get(0);
	}


}

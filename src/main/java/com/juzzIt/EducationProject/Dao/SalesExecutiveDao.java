package com.juzzIt.EducationProject.Dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.juzzIt.EducationProject.DaoInterface.SalesExecutiveDaoInterface;
import com.juzzIt.EducationProject.Entity.SalesExecutive;
import com.juzzIt.EducationProject.Models.Responce;
import com.juzzIt.EducationProject.Repositary.SalesExecutiveRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;


@Component
public class SalesExecutiveDao implements SalesExecutiveDaoInterface {
	
	@Autowired
	private SalesExecutiveRepository salesExecutiveRepo;
	
	@Autowired 
	private EntityManager entityManager;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public SalesExecutive addNewSalesExecutive(SalesExecutive salesExecutive) {
		return salesExecutiveRepo.save(salesExecutive);
	}

	
	@Override
	public SalesExecutive getSalesExecutiveById(String salesExecutiveId) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<SalesExecutive> createQuery = criteriaBuilder.createQuery(SalesExecutive.class);
		Root<SalesExecutive> root = createQuery.from(SalesExecutive.class);
		Predicate predicate = criteriaBuilder.equal(root.get("salesExecutiveId"), salesExecutiveId );
		createQuery.select(root).where(predicate);
		List<SalesExecutive> resultList = entityManager.createQuery(createQuery).getResultList();
		if(resultList.isEmpty()) {
			return null;
		}
		return resultList.get(0);
	}
	
	@Override
	public Responce updateSalesExecutiveData(String salesExecutiveId, HashMap<String, Object> salesExecutiveData) {
		Responce responce=new Responce();
		SalesExecutive salesExecutive = getSalesExecutiveById(salesExecutiveId);
		
		
		if(salesExecutive==null) {
			responce.setMassege("salesExecutive with the given id not found");
			responce.setStatus(false);
		}
		
		if(salesExecutiveData.get("address")!=null) {
			salesExecutive.setSalesExecutiveAddress(salesExecutiveData.get("address").toString());	
		}
if(salesExecutiveData.get("email")!=null) {
	salesExecutive.setSalesExecutiveEmail(salesExecutiveData.get("email").toString());
		}
if(salesExecutiveData.get("mobile_Number")!=null) {
	
	salesExecutive.setSalesExecutiveMobileNumber((long)salesExecutiveData.get("mobile_Number"));
	
}
if(salesExecutiveData.get("name")!=null) {
	
	salesExecutive.setSalesExecutiveName(salesExecutiveId);
}
if(salesExecutiveData.get("password")!=null) {

	
	salesExecutive.setSalesExecutivePassWord(passwordEncoder.encode(salesExecutiveData.get("password").toString()));
}
		
		
		

SalesExecutive updatedsalesExecutive = salesExecutiveRepo.save(salesExecutive);

if(updatedsalesExecutive==null) {
	responce.setMassege("failed to update the details");
	responce.setStatus(false);
}
		
responce.setMassege("successfullu updated the details");
responce.setStatus(true);

		return responce;
	}

	@Override
	public Responce deleteSalesExecutive(String salesExecutiveId) {
		SalesExecutive salesExecutive = getSalesExecutiveById(salesExecutiveId);
		Responce responce=new Responce();
		if(salesExecutive!=null) {
			salesExecutiveRepo.delete(salesExecutive);
			responce.setMassege("salesExecutive deleted successfully");
			responce.setStatus(true);
		}else {
			responce.setMassege("salesExecutive with the given id not found");
			responce.setStatus(false);
		}
		return responce;
	}

	public boolean isExists(String Email) {
		return salesExecutiveRepo.existsBySalesExecutiveEmail(Email);
	}


	@Override
	public SalesExecutive getSalesExecutiveByEmail(String email) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<SalesExecutive> createQuery = criteriaBuilder.createQuery(SalesExecutive.class);
		Root<SalesExecutive> root = createQuery.from(SalesExecutive.class);
		Predicate predicate = criteriaBuilder.equal(root.get("salesExecutiveEmail"), email );
		createQuery.select(root).where(predicate);
		List<SalesExecutive> resultList = entityManager.createQuery(createQuery).getResultList();
		if(resultList.isEmpty()) {
			return null;
		}
		return resultList.get(0);
	}

	
	
	
	

}

package com.juzzIt.EducationProject.Services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.juzzIt.EducationProject.Entity.EnrollType;
import com.juzzIt.EducationProject.Models.Responce;
import com.juzzIt.EducationProject.Repositary.EnrollTypeRepository;
import com.juzzIt.EducationProject.ServiceInterface.EnrollTypeServicesInterface;

@Service
public class EnrollTypeServicesImplementation implements EnrollTypeServicesInterface {

	@Autowired
	EnrollTypeRepository enrollTypeRepo;
	
	@Override
	public Responce addNewEnrollType(EnrollType enrollType) throws Exception {
		Responce responce =new Responce();
		try {
		if(enrollType.getEnrollType()!=null) {
			
			
			System.out.println("%%%%%%%%%%%%%%%%%%%%%%%");
			
			boolean isExists=enrollTypeRepo.existsByEnrollType(enrollType.getEnrollType());
			
			System.out.println(isExists);
			if(!isExists) {
				System.out.println("%%%%%%%%%%%%%%%%%%%%%%%");
		EnrollType save = enrollTypeRepo.save(enrollType);
		
	System.out.println(save);
		responce.setStatus(true);
        responce.setMassege("EnrollType added successfully");
			}else {
				responce.setMassege("enroll type is alrady exist");
				responce.setStatus(false);
			}
		}else {
			responce.setMassege("enroll typr is required");
			responce.setStatus(false);
		}
		}catch (Exception e) {
			throw new Exception("Getting problem while adding New EnrollType");
		}
		return responce;
	}

	@Override
	public Responce deleteEnrollType(String enrollTypeId) throws Exception {
		
		

		Responce responce =new Responce();
		try {
			Optional<EnrollType> enrollType=enrollTypeRepo.findById(enrollTypeId);
		if(enrollType.isPresent()) {
			
			enrollTypeRepo.deleteById(enrollTypeId);
			responce.setStatus(true);
	        responce.setMassege("EnrollType deleted successfully");
		}else {
			responce.setStatus(false);
	        responce.setMassege("EnrollType with given id not found");
		}
		}catch (Exception e) {
			throw new Exception("Getting problem while deleting EnrollType");
		}
		return responce;
	}

	@Override
	public List<EnrollType> getAllEnrollType() {
				return enrollTypeRepo.findAll();
	}

}

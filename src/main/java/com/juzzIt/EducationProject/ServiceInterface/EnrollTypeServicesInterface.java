package com.juzzIt.EducationProject.ServiceInterface;

import java.util.List;

import com.juzzIt.EducationProject.Entity.EnrollType;
import com.juzzIt.EducationProject.Models.Responce;

public interface EnrollTypeServicesInterface {

	
	public Responce addNewEnrollType(EnrollType enrollType) throws Exception;
	public Responce deleteEnrollType(String enrollType) throws Exception;
	public List<EnrollType> getAllEnrollType();
}

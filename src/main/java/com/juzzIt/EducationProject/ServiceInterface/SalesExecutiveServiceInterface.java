package com.juzzIt.EducationProject.ServiceInterface;

import java.util.HashMap;

import com.juzzIt.EducationProject.Entity.SalesExecutive;
import com.juzzIt.EducationProject.Models.LogInOrSignUpResponce;
import com.juzzIt.EducationProject.Models.Responce;
public interface SalesExecutiveServiceInterface {
	
	public LogInOrSignUpResponce addNewSalesExecutive( HashMap<String, Object> salesExecutiveData);
	  public Responce  updateSalesExecutive(String salesExecutiveId,HashMap<String, Object> salesExecutiveData );
	  public Responce deleteSalesExecutiveAccount(String salesExecutiveId);  
	  public SalesExecutive getSalesExecutiveByEmail(String email);

}

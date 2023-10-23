package com.juzzIt.EducationProject.DaoInterface;

import java.util.HashMap;

import com.juzzIt.EducationProject.Entity.SalesExecutive;
import com.juzzIt.EducationProject.Models.Responce;

public interface SalesExecutiveDaoInterface {
	
	
	public SalesExecutive addNewSalesExecutive(SalesExecutive salesExecutive);
	public Responce updateSalesExecutiveData(String salesExecutiveId,HashMap<String, Object> salesExecutiveData);
	public Responce deleteSalesExecutive(String salesExecutiveId);
	public boolean isExists(String Email);
	
	public SalesExecutive getSalesExecutiveById(String salesExecutiveId);
	  public SalesExecutive getSalesExecutiveByEmail(String email);

	
}

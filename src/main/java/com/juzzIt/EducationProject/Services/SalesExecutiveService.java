package com.juzzIt.EducationProject.Services;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.juzzIt.EducationProject.DaoInterface.SalesExecutiveDaoInterface;
import com.juzzIt.EducationProject.Entity.SalesExecutive;
import com.juzzIt.EducationProject.JWTimplementation.JwtHelper;
import com.juzzIt.EducationProject.Models.LogInOrSignUpResponce;
import com.juzzIt.EducationProject.Models.Responce;
import com.juzzIt.EducationProject.Models.UseTypeEnum;
import com.juzzIt.EducationProject.ServiceInterface.SalesExecutiveServiceInterface;


@Service
public class SalesExecutiveService implements SalesExecutiveServiceInterface {
	
	
	@Autowired
	private SalesExecutiveDaoInterface salesExecutiveDaoInterface;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	

    @Autowired
    private JwtHelper helper;

	@Override
	public LogInOrSignUpResponce addNewSalesExecutive(HashMap<String, Object> salesExecutiveData) {
		
		LogInOrSignUpResponce logInOrSignUpResponce=new LogInOrSignUpResponce();
		
		if(salesExecutiveData.get("password")==null||
				salesExecutiveData.get("address")==null||
				salesExecutiveData.get("email")==null||
				salesExecutiveData.get("mobile_Number")==null||
				salesExecutiveData.get("name")==null) {
			logInOrSignUpResponce.setSuccessStatus(false);
			logInOrSignUpResponce.setMassage("password, address, email ,  mobile_Number, name are requied ");
			return logInOrSignUpResponce;
		}
		
		boolean exists = salesExecutiveDaoInterface.isExists(salesExecutiveData.get("email").toString());
		
		
		if(!exists){
			logInOrSignUpResponce.setSuccessStatus(false);
			logInOrSignUpResponce.setMassage("SalesExecutive with this email is alrady exist");
			return logInOrSignUpResponce;
		}
		
		SalesExecutive newSalesExecutive=new SalesExecutive();
		
		newSalesExecutive.setSalesExecutiveId(salesExecutiveData.get("id").toString());
		String password = passwordEncoder.encode(salesExecutiveData.get("password").toString());
		newSalesExecutive.setRole(UseTypeEnum.SALES_EXECUTIVE.toString());
		newSalesExecutive.setSalesExecutiveAddress(salesExecutiveData.get("address").toString());
		newSalesExecutive.setSalesExecutiveEmail(salesExecutiveData.get("email").toString());
		newSalesExecutive.setSalesExecutiveMobileNumber((long) salesExecutiveData.get("mobile_Number"));
		newSalesExecutive.setSalesExecutivePassWord(password);
		newSalesExecutive.setSalesExecutiveName(salesExecutiveData.get("name").toString());
		
		
		SalesExecutive addedSalesExecutive = salesExecutiveDaoInterface.addNewSalesExecutive(newSalesExecutive);
		
		if(addedSalesExecutive==null) {
			logInOrSignUpResponce.setSuccessStatus(false);
			logInOrSignUpResponce.setMassage("failed to add the new user");
			return logInOrSignUpResponce;
		}
		
		  String token = this.helper.generateToken(addedSalesExecutive.getSalesExecutiveEmail()+"*"+UseTypeEnum.SALES_EXECUTIVE);
	
		logInOrSignUpResponce.setSuccessStatus(true);
		logInOrSignUpResponce.setJwtTokan(token);
		logInOrSignUpResponce.setMassage("SalesExecutive is added successfully");
		logInOrSignUpResponce.setUserId(addedSalesExecutive.getSalesExecutiveId());
		addedSalesExecutive.setRole(addedSalesExecutive.getRole());
		return logInOrSignUpResponce;
	}

	@Override
	public Responce updateSalesExecutive(String salesExecutiveId, HashMap<String, Object> salesExecutiveData) {
		return salesExecutiveDaoInterface.updateSalesExecutiveData(salesExecutiveId, salesExecutiveData);
	}

	@Override
	public Responce deleteSalesExecutiveAccount(String salesExecutiveId) {
		return salesExecutiveDaoInterface.deleteSalesExecutive(salesExecutiveId);
	}

	@Override
	public SalesExecutive getSalesExecutiveByEmail(String email) {
		return salesExecutiveDaoInterface.getSalesExecutiveByEmail(email);
	}

		
	
}

package com.juzzIt.EducationProject.ServiceInterface;

import java.util.HashMap;

import org.springframework.web.bind.annotation.RequestBody;

import com.juzzIt.EducationProject.Models.LogInOrSignUpResponce;

public interface AdminServiceInterface {

	
	public LogInOrSignUpResponce createAdminAcount(HashMap<String, Object> adminData) ;
}

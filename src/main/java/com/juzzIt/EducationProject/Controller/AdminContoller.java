package com.juzzIt.EducationProject.Controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.juzzIt.EducationProject.Models.LogInOrSignUpResponce;
import com.juzzIt.EducationProject.ServiceInterface.AdminServiceInterface;

@RestController
@RequestMapping("/admin")
public class AdminContoller {
	
	@Autowired
	private AdminServiceInterface adminServiceInterface;

	@PostMapping("/ceate")
	public LogInOrSignUpResponce createAdminAcount(@RequestBody HashMap<String, Object> adminData) {
		return adminServiceInterface.createAdminAcount(adminData);
	}
	}

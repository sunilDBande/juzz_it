package com.juzzIt.EducationProject.Controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.juzzIt.EducationProject.Models.LogInOrSignUpResponce;
import com.juzzIt.EducationProject.ServiceInterface.AuthServiceInterface;



@RestController
@RequestMapping("/Auth")
@CrossOrigin(origins = "*")
public class AuthController {
	
	@Autowired
	private AuthServiceInterface authServiceInterface;
	
	
	
	@PostMapping("/student/login")
	public LogInOrSignUpResponce studentLogIn(@RequestBody Map<String, Object> loginData) {
		LogInOrSignUpResponce logInOrSignUpResponce=new LogInOrSignUpResponce();
			if(loginData.get("email")==null||loginData.get("password")==null) {
				logInOrSignUpResponce.setMassage("email and password are required");
				logInOrSignUpResponce.setSuccessStatus(false);
				return logInOrSignUpResponce;	
			}
		return authServiceInterface.StudentLogIn(loginData.get("email").toString(), loginData.get("password").toString());
	}

	@PostMapping("/trainer/login")
	public LogInOrSignUpResponce trainerLogIn(@RequestBody Map<String, Object> loginData) {
		LogInOrSignUpResponce logInOrSignUpResponce=new LogInOrSignUpResponce();
		if(loginData.get("email")==null||loginData.get("password")==null) {
			logInOrSignUpResponce.setMassage("email and password are required");
			logInOrSignUpResponce.setSuccessStatus(false);
			return logInOrSignUpResponce;	
		}
		return authServiceInterface.TeacherLogIn(loginData.get("email").toString(), loginData.get("password").toString());
	}
	
	
	
	@PostMapping("/salesExecutive/login")
	public LogInOrSignUpResponce salesExecutiveLogIn(@RequestBody Map<String, Object> loginData) {
		LogInOrSignUpResponce logInOrSignUpResponce=new LogInOrSignUpResponce();
		if(loginData.get("email")==null||loginData.get("password")==null) {
			logInOrSignUpResponce.setMassage("email and password are required");
			logInOrSignUpResponce.setSuccessStatus(false);
			return logInOrSignUpResponce;	
		}
		return authServiceInterface.SalesExecutiveLogIn(loginData.get("email").toString(), loginData.get("password").toString());
	}
	
	
	
	@PostMapping("/admin/login")
	public LogInOrSignUpResponce AdminLogIn(@RequestBody Map<String, Object> loginData) {
		LogInOrSignUpResponce logInOrSignUpResponce=new LogInOrSignUpResponce();
		if(loginData.get("email")==null||loginData.get("password")==null) {
			logInOrSignUpResponce.setMassage("email and password are required");
			logInOrSignUpResponce.setSuccessStatus(false);
			return logInOrSignUpResponce;	
		}
		
		System.out.println(loginData.get("email").toString());
		System.out.println(loginData.get("password").toString());
		return authServiceInterface.AdminLogin(loginData.get("email").toString(), loginData.get("password").toString());
	}
	
	
	
}

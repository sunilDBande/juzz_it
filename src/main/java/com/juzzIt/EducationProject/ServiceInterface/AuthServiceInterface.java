package com.juzzIt.EducationProject.ServiceInterface;

import com.juzzIt.EducationProject.Models.LogInOrSignUpResponce;

public interface AuthServiceInterface {

	public LogInOrSignUpResponce StudentLogIn(String email ,String password);
	public LogInOrSignUpResponce TeacherLogIn(String email,String password);
	public LogInOrSignUpResponce SalesExecutiveLogIn(String email,String password);
	public LogInOrSignUpResponce  AdminLogin(String email,String password);
}

package com.juzzIt.EducationProject.Services;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.juzzIt.EducationProject.DaoInterface.AdminDaoInterface;
import com.juzzIt.EducationProject.Entity.Admin;
import com.juzzIt.EducationProject.JWTimplementation.JwtHelper;
import com.juzzIt.EducationProject.Models.LogInOrSignUpResponce;
import com.juzzIt.EducationProject.Models.UseTypeEnum;
import com.juzzIt.EducationProject.ServiceInterface.AdminServiceInterface;


@Service
public class AdminServiceImplementation implements AdminServiceInterface{

	@Autowired
	private AdminDaoInterface adminDaoInterface;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	

    @Autowired
    private JwtHelper helper;
	@Override
	public LogInOrSignUpResponce createAdminAcount(HashMap<String, Object> adminData) {
	
		LogInOrSignUpResponce logInOrSignUpResponce=new LogInOrSignUpResponce();
		
		if(adminData.get("name")==null||adminData.get("email")==null||adminData.get("password")==null) {
			logInOrSignUpResponce.setMassage("name, email , password are required");
			logInOrSignUpResponce.setSuccessStatus(false);
			return logInOrSignUpResponce;
		}
		
		
		boolean iSexists = adminDaoInterface.iSexists(adminData.get("email").toString());
		
		
		if(iSexists) {
			logInOrSignUpResponce.setMassage("acount with this emain is alrady created");
			logInOrSignUpResponce.setSuccessStatus(false);
			return logInOrSignUpResponce;
		}
		String encodedPassword = passwordEncoder.encode(adminData.get("password").toString());
		
		
		Admin newAdmin=new Admin();
		newAdmin.setAdminId(adminData.get("id").toString());
		newAdmin.setAdminEmail(adminData.get("email").toString());
		newAdmin.setAdminName(adminData.get("name").toString());
		newAdmin.setRole(UseTypeEnum.ADMIN.toString());
		newAdmin.setAdminPassword(encodedPassword);
		
		Admin createdAdminAcount = adminDaoInterface.createAdminAcount(newAdmin);
		
		if(createdAdminAcount==null) {
			logInOrSignUpResponce.setMassage("failed to create the admin acount");
			logInOrSignUpResponce.setSuccessStatus(false);
			return logInOrSignUpResponce;
		}
		
		  String token = this.helper.generateToken(createdAdminAcount.getAdminEmail()+"*"+UseTypeEnum.ADMIN.toString());
		
		logInOrSignUpResponce.setJwtTokan(token);
		logInOrSignUpResponce.setMassage("succefully created acount");
		logInOrSignUpResponce.setSuccessStatus(true);
		logInOrSignUpResponce.setUserType(createdAdminAcount.getRole());
		logInOrSignUpResponce.setUserId(createdAdminAcount.getAdminId());
		
		return logInOrSignUpResponce;
	}

}

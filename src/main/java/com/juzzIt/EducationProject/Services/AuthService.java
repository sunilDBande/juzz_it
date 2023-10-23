package com.juzzIt.EducationProject.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.juzzIt.EducationProject.DaoInterface.AdminDaoInterface;
import com.juzzIt.EducationProject.DaoInterface.SalesExecutiveDaoInterface;
import com.juzzIt.EducationProject.DaoInterface.StudentDaoInterface;
import com.juzzIt.EducationProject.DaoInterface.TeacherDaoInterface;
import com.juzzIt.EducationProject.Entity.Admin;
import com.juzzIt.EducationProject.Entity.SalesExecutive;
import com.juzzIt.EducationProject.Entity.Student;
import com.juzzIt.EducationProject.Entity.Teacher;
import com.juzzIt.EducationProject.JWTimplementation.JwtHelper;
import com.juzzIt.EducationProject.Models.LogInOrSignUpResponce;
import com.juzzIt.EducationProject.Models.UseTypeEnum;
import com.juzzIt.EducationProject.ServiceInterface.AuthServiceInterface;

@Service
public class AuthService implements AuthServiceInterface {
	
	
	@Autowired
	private AdminDaoInterface adminDaoInterface;
	
	@Autowired
	private SalesExecutiveDaoInterface salesExecutiveDaoInterface;
	
	@Autowired
	private StudentDaoInterface studentDaoInterface;
	
	@Autowired
	private TeacherDaoInterface teacherDaoInterface;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	

	  @Autowired
	  private JwtHelper helper;
	

	@Override
	public LogInOrSignUpResponce StudentLogIn(String email, String password) {

		Student student = studentDaoInterface.getStudentByEmail(email);
		LogInOrSignUpResponce response=new LogInOrSignUpResponce();
		
		
		if(student==null) {
			response.setSuccessStatus(false);
			response.setMassage("accont with this email not found");
			return response;	
		}
		
		
		
		String currentPassword=student.getStudentPassword();
		
		final var isCorrectPassword = passwordEncoder.matches(password, currentPassword);
		if (Boolean.FALSE.equals(isCorrectPassword)) {
			 throw new BadCredentialsException(" Invalid Username or Password  !!");
		}
		
	     String token = this.helper.generateToken(email+"*"+UseTypeEnum.STUDENT.toString());
		
		
	     
         response.setMassage("login successfull");
         response.setJwtTokan(token);
         response.setSuccessStatus(true);
         response.setUserId(student.getStudentId());               
         response.setUserType(student.getRole());
		
		

		return response;
	}

	@Override
	public LogInOrSignUpResponce TeacherLogIn(String email, String password) {
	 Teacher teacher = teacherDaoInterface.getTeacherByEmail(email);
		LogInOrSignUpResponce response=new LogInOrSignUpResponce();
		
		
		if(teacher==null) {
			response.setSuccessStatus(false);
			response.setMassage("accont with this email not found");
			return response;	
		}
		
		
		
		String currentPassword=teacher.getTeacherPassword();
		
		final var isCorrectPassword = passwordEncoder.matches(password, currentPassword);
		if (Boolean.FALSE.equals(isCorrectPassword)) {
			 throw new BadCredentialsException(" Invalid Username or Password  !!");
		}
		
	     String token = this.helper.generateToken(email+"*"+UseTypeEnum.TRAINER.toString());
		
		
	     
         response.setMassage("login successfull");
         response.setJwtTokan(token);
         response.setSuccessStatus(true);
         response.setUserId(teacher.getTeacherId());               
         response.setUserType(teacher.getRole());
		
		

		return response;
	}

	@Override
	public LogInOrSignUpResponce SalesExecutiveLogIn(String email, String password) {
		SalesExecutive salesExecutive = salesExecutiveDaoInterface.getSalesExecutiveByEmail(email);
		LogInOrSignUpResponce response=new LogInOrSignUpResponce();
		
		
		if(salesExecutive==null) {
			response.setSuccessStatus(false);
			response.setMassage("accont with this email not found");
			return response;	
		}
		
		
		
		String currentPassword=salesExecutive.getSalesExecutivePassWord();
		
		final var isCorrectPassword = passwordEncoder.matches(password, currentPassword);
		if (Boolean.FALSE.equals(isCorrectPassword)) {
			 throw new BadCredentialsException(" Invalid Username or Password  !!");
		}
		
	     String token = this.helper.generateToken(email+"*"+UseTypeEnum.SALES_EXECUTIVE.toString());
	     
         response.setMassage("login successfull");
         response.setJwtTokan(token);
         response.setSuccessStatus(true);
         response.setUserId(salesExecutive.getSalesExecutiveId());               
         response.setUserType(salesExecutive.getRole());
		return response;
	}

	@Override
	public LogInOrSignUpResponce AdminLogin(String email, String password) {
		Admin admin = adminDaoInterface.getAdminByEmail(email);
		LogInOrSignUpResponce response=new LogInOrSignUpResponce();
		
		
		if(admin==null) {
			response.setSuccessStatus(false);
			response.setMassage("accont with this email not found");
			return response;	
		}
		
		
		
		String currentPassword=admin.getAdminPassword();
		
		final var isCorrectPassword = passwordEncoder.matches(password, currentPassword);
		if (Boolean.FALSE.equals(isCorrectPassword)) {
			 throw new BadCredentialsException(" Invalid Username or Password  !!");
		}
		
	     String token = this.helper.generateToken(email+"*"+UseTypeEnum.ADMIN.toString());
 
         response.setMassage("login successfull");
         response.setJwtTokan(token);
         response.setSuccessStatus(true);
         response.setUserId(admin.getAdminId());               
         response.setUserType(admin.getRole());
		
		return response;
	}
	
	   @ExceptionHandler(BadCredentialsException.class)
	    public String exceptionHandler() {
	        return "Credentials Invalid !!";
	    }

}

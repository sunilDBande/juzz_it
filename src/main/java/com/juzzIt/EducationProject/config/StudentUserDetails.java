package com.juzzIt.EducationProject.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.juzzIt.EducationProject.Entity.Student;
import com.juzzIt.EducationProject.Repositary.StudentRepository;

@Component
public class StudentUserDetails implements UserDetailsService {
	
	@Autowired
	private StudentRepository studentRepository;
	
	   @Override
	    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	        String userName, password;
	        List<GrantedAuthority> authorities;
	        List<Student> customer = studentRepository.findByStudentEmail(username);
	        if (customer.size() == 0) {
	            throw new UsernameNotFoundException("User details not found for the user : " + username);
	        } else{
	            userName = customer.get(0).getStudentEmail();
	            password = customer.get(0).getStudentPassword();
	            authorities = new ArrayList<>();
	            authorities.add(new SimpleGrantedAuthority(customer.get(0).getRole()));
	        }
	        return new User(userName,password,authorities);
	    }

}

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

import com.juzzIt.EducationProject.Entity.Admin;
import com.juzzIt.EducationProject.Repositary.AdminRepository;

@Component
public class AdminUserDetails implements UserDetailsService  {

	
	@Autowired
	private AdminRepository adminRepository;
	
	
	   @Override
	    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	        String userName, password;
	        List<GrantedAuthority> authorities;
	        List<Admin> customer = adminRepository.findByAdminEmail(username);
	        if (customer.size() == 0) {
	            throw new UsernameNotFoundException("User details not found for the user : " + username);
	        } else{
	            userName = customer.get(0).getAdminEmail();
	            password = customer.get(0).getAdminPassword();
	            authorities = new ArrayList<>();
	            authorities.add(new SimpleGrantedAuthority(customer.get(0).getRole()));
	        }
	        return new User(userName,password,authorities);
	    }
}

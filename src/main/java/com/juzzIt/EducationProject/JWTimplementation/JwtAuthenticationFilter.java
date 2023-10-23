package com.juzzIt.EducationProject.JWTimplementation;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.juzzIt.EducationProject.Models.UseTypeEnum;
import com.juzzIt.EducationProject.config.AdminUserDetails;
import com.juzzIt.EducationProject.config.SalesExecutiveUserDetails;
import com.juzzIt.EducationProject.config.StudentUserDetails;
import com.juzzIt.EducationProject.config.TrainerUserDetails;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
	

	
	  private Logger logger = LoggerFactory.getLogger(OncePerRequestFilter.class);
	    @Autowired
	    private JwtHelper jwtHelper;
	    	    
	    
	    @Autowired
	    private AdminUserDetails adminUserDetails;
	    
	    @Autowired
	    private SalesExecutiveUserDetails salesExecutiveUserDetails;
	    
	    @Autowired
	    private StudentUserDetails studentUserDetails;
	    
	    @Autowired
	    private TrainerUserDetails trainerUserDetails;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
	
		
		  String requestHeader = request.getHeader("Authorization");
	        //Bearer 2352345235sdfrsfgsdfsdf
	        logger.info(" Header :  {}", requestHeader);
	        String userdata = null;
	        String token = null;
	     
	        
	        
	        if (requestHeader != null && requestHeader.startsWith("Bearer")) {
	            //looking good
	            token = requestHeader.substring(7);
	            try {
	            	
	            	 userdata = this.jwtHelper.getUsernameFromToken(token);
	            	 System.out.println(userdata);

	            } catch (IllegalArgumentException e) {
	                logger.info("Illegal Argument while fetching the username !!");
	                e.printStackTrace();
	            } catch (ExpiredJwtException e) {
	                logger.info("Given jwt token is expired !!");
	                e.printStackTrace();
	            } catch (MalformedJwtException e) {
	                logger.info("Some changed has done in token !! Invalid Token");
	                e.printStackTrace();
	            } catch (Exception e) {
	                e.printStackTrace();

	            }
	            

	        } else {
	            logger.info("Invalid Header Value !! ");
	        }


	        //
	        if (userdata != null && SecurityContextHolder.getContext().getAuthentication() == null) {

	        	  Boolean validateToken=false;
	        	
	        	String[] data = userdata.split("\\*");
	        	 UserDetails userDetails=null;
	        	
	        		        	
	        	if(data[1].equalsIgnoreCase(UseTypeEnum.ADMIN.toString())) {
	        		  userDetails = this.adminUserDetails.loadUserByUsername(data[0]);
	        		   validateToken = this.jwtHelper.validateToken(token, userDetails,UseTypeEnum.ADMIN.toString());
	        	}
                 if(data[1].equalsIgnoreCase(UseTypeEnum.SALES_EXECUTIVE.toString())) {
                	  userDetails = this.salesExecutiveUserDetails.loadUserByUsername(data[0]);
                	   validateToken = this.jwtHelper.validateToken(token, userDetails,UseTypeEnum.SALES_EXECUTIVE.toString());
	        	}
                  if(data[1].equalsIgnoreCase(UseTypeEnum.STUDENT.toString())) {
                	   userDetails = this.studentUserDetails.loadUserByUsername(data[0]);
                	    validateToken = this.jwtHelper.validateToken(token, userDetails,UseTypeEnum.STUDENT.toString());
                 }
                  if(data[1].equalsIgnoreCase(UseTypeEnum.TRAINER.toString())) {
                	   userDetails = this.trainerUserDetails.loadUserByUsername(data[0]);
                	    validateToken = this.jwtHelper.validateToken(token, userDetails,UseTypeEnum.TRAINER.toString());
                  }
	        	
	   
	        	 //fetch user detail from username
	          
	          
	            if (validateToken) {

	                //set the authentication
	                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
	                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
	                SecurityContextHolder.getContext().setAuthentication(authentication);


	            } else {
	                logger.info("Validation fails !!");
	            }
	            
	        }

	        filterChain.doFilter(request, response);
	            
	            
	            }

		
		
		
		
	}

   
